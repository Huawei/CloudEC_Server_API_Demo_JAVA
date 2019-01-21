package com.huawei.esdk.ec.utils;
/**
 * Copyright 2015 Huawei Technologies Co., Ltd. All rights reserved.
 * eSDK is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *   
 * http://www.apache.org/licenses/LICENSE-2.0
 *   
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.UnsupportedCharsetException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.RestResponse;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.constant.HTTPConstant;

@SuppressWarnings("deprecation")
public class RestUtils
{
    private static final Logger LOGGER = LogManager.getLogger(RestUtils.class);
    
    /*
	 * Max connections of connection pool
	 */
	private static final int MAXCONNECTION = 2000;
	/*
	 * Connections of every route
	 */
	private static final int MAXPERROUTE = 2000;
	/*
	 * Max request time of getting a connection from connection pool
	 */
	private static final int REQUESTTIMEOUT = 5000;
	/*
	 * Max time of a request
	 */
	private static final int CONNECTIMEOUT = 5000;
	/*
	 * Max time of waiting for response message
	 */
	private static final int SOCKETIMEOUT = 0;
    
    private static final RestUtils REST_UTIL = new RestUtils();
    
    private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();
    
    
    private BasicHttpContext localContext = null;
    
    private HttpHost target;
    
    
    private static PoolingHttpClientConnectionManager connManager = null;
	
	private static CloseableHttpClient client = null;
    
	public static void setClient(CloseableHttpClient client) {
		RestUtils.client = client;
	}
	
	public static void setConnManager(PoolingHttpClientConnectionManager connManager) {
		RestUtils.connManager = connManager;
	}
    private RestUtils()
    {
    
    }
    
    public synchronized static RestUtils getInstance()
    {
        return REST_UTIL;
    }
    
    /**
     * send information to the token that has been acquired
     * @author t81023743
     * @param token token
     * @param resourceUri resourceURI
     * @param message restquest
     * @return response
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     */
    public RestResponse sendMessage(Token token, String resourceUri, RestRequest message)
    		throws ClientProtocolException, URISyntaxException, IOException
    {
    	//write the token to the request
    	Map<String, String> mapheaders = message.getHttpHeaders();
        String authorization = token.getToken();
        mapheaders.put("Authorization", authorization);
        
        //getting the HTTP host information
        HttpHost target = token.getHttpHost(); 
        
		return sendMessage(target, resourceUri, message, null, null);
    }
    
    public RestResponse sendMessage(HttpHost target, String resourceUri, RestRequest message)
        throws ClientProtocolException, URISyntaxException, IOException
    {
        return sendMessage(target, resourceUri, message, null, null);
    }
    
    
    public RestResponse sendMessage(HttpHost target, String resourceUri, RestRequest message, String userName,
        String password)
            throws ClientProtocolException, URISyntaxException, IOException
    {
        
        if (null == target)
        {
            throw new NullPointerException("HttpHost target can not be null.");
        }
        else
        {
            this.target = target;
        }
        
        RestResponse restResponse = new RestResponse();
        
        adapterScheme();
        
        HttpRequestBase request = buildRequestMessage(message, resourceUri);
        
        HttpResponse response = null;
        
        try
        {
            response = client.execute(target, request, localContext);
            
            if (response != null)
            {
                restResponse.setHttpCode(response.getStatusLine().getStatusCode());
                
                HttpEntity entity = response.getEntity();
                if (null != entity)
                {
                    restResponse.setEntity(EntityUtils.toString(entity));
                }
                
                Header[] headers = response.getAllHeaders();
                if (null != headers)
                {
                    Map<String, List<String>> headerMap = restResponse.getHeaders();
                    
                    for (Header header : headers)
                    {
                        if (headerMap.containsKey(header.getName()))
                        {
                            headerMap.get(header.getName()).add(header.getValue());
                        }
                        else
                        {
                            List<String> headerVals = new ArrayList<String>();
                            headerVals.add(header.getValue());
                            headerMap.put(header.getName(), headerVals);
                        }
                    }
                }
                
                return restResponse;
            }
            
            return null;
            
        }
		catch (RuntimeException e)
        {
            LOGGER.error("httpclient error", e);
            return null;
        }
		catch (Exception e)
        {
            LOGGER.error("httpclient error", e);
            return null;
        }
        finally
        {
            request.releaseConnection();  
        }
        
    }
    
    public static class TrustStrategyE implements TrustStrategy
    {
    	@Override
		public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException 
    	{
			return true;
		}
    }
    
    public static class X509HostnameVerifierE implements X509HostnameVerifier
    {
    	public boolean verify(String arg0, SSLSession arg1) {
	        return true;
	    }
	    public void verify(String host, SSLSocket ssl)
	            throws IOException {
	    }
	    public void verify(String host, X509Certificate cert)
	            throws SSLException {
	    }
	    public void verify(String host, String[] cns,
	            String[] subjectAlts) throws SSLException {
	    }
    }
    
    //获取https请求客户端
    //Get https request client
    private void adapterScheme()
    {
    	SSLContext sslContext;
    	
        if (HTTPConstant.HTTPS_SCHEME.equalsIgnoreCase(target.getSchemeName()))
        { 
			try {
				sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategyE() {
					
					
				}).build();
			
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext
						, new X509HostnameVerifierE(){
					
				});
				
				Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
						.register("http", PlainConnectionSocketFactory.getSocketFactory())
						.register("https", sslsf)
						.build();
				
				RestUtils.setConnManager(new PoolingHttpClientConnectionManager(socketFactoryRegistry));
				connManager.setMaxTotal(MAXCONNECTION);
				connManager.setDefaultMaxPerRoute(MAXPERROUTE);
				
			} 
			catch (RuntimeException e) 
			{
	            throw e;
			}
			catch (Exception e)
	        {
				LOGGER.error("https error :" + e);
	            return;
	        }
			
			RestUtils.setClient(getConnection());
	    } 
        else
        {
        	LOGGER.error("do not support http");
        	return;
        }
	        
    }
    
    public static class HttpRequestRetryHandlerE implements HttpRequestRetryHandler{

		@Override
		public boolean retryRequest(IOException arg0, int arg1, HttpContext arg2) {
			// TODO Auto-generated method stub
			return false;
		}
    	
    }
    
    private static CloseableHttpClient getConnection()
    {
    	RequestConfig restConfig = RequestConfig.custom().setConnectionRequestTimeout(REQUESTTIMEOUT)
    			.setConnectTimeout(CONNECTIMEOUT)
    			.setSocketTimeout(SOCKETIMEOUT).build();
    	HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandlerE()
        {
            public boolean retryRequest(IOException exception, int executionCount,
                    HttpContext context)
            {
                if (executionCount >= 3)
                {
                   return false; 
                }
                if (exception instanceof NoHttpResponseException) 
                {
                    return true;  
                } 
                if (exception instanceof InterruptedIOException) 
                {
                    return false;
                }
                if (exception instanceof SSLHandshakeException) 
                {
                    return false;  
                }  
                if (exception instanceof UnknownHostException) 
                {
                    return false;  
                }  
                if (exception instanceof ConnectTimeoutException) 
                {
                    return false;  
                }  
                if (exception instanceof SSLException) 
                {
                    return false;  
                }
                
                HttpClientContext clientContext = HttpClientContext.adapt(context);  
                HttpRequest request = clientContext.getRequest();  
                if (!(request instanceof HttpEntityEnclosingRequest)) 
                {  
                    return true;  
                }  
                return false;  
            }
        };
    	CloseableHttpClient httpClient = HttpClients.custom()
    			.setConnectionManager(connManager).setDefaultRequestConfig(restConfig).setRetryHandler(retryHandler).build();
    	return httpClient;
    }
    
    private HttpRequestBase buildRequestMessage(RestRequest message, String resourceUri)
        throws URISyntaxException, UnsupportedEncodingException
    {
        HttpRequestBase request;
        
        if (HTTPConstant.HTTP_METHOD_GET.equalsIgnoreCase(message.getHttpMethod()))
        {
            HttpGet httpGet = new HttpGet(resourceUri);
            setParameters(httpGet, message.getParameters());
            request = httpGet;
        }
        else if (HTTPConstant.HTTP_METHOD_POST.equalsIgnoreCase(message.getHttpMethod()))
        {
            HttpPost httpPost = new HttpPost(resourceUri);
            try 
            {
				httpPost.setEntity(new StringEntity(OBJECTMAPPER.writeValueAsString(message.getPayload()), ContentType.APPLICATION_JSON));
			} 
            catch (UnsupportedCharsetException | JsonProcessingException e) 
            {
				LOGGER.error("UnsupportedCharsetException | JsonProcessingException");
			}
            request = httpPost;
        }
        else if (HTTPConstant.HTTP_METHOD_PUT.equalsIgnoreCase(message.getHttpMethod()))
        {
            HttpPut httpPut = new HttpPut(resourceUri);
            try 
            {
				httpPut.setEntity(new StringEntity(OBJECTMAPPER.writeValueAsString(message.getPayload()), ContentType.APPLICATION_JSON));
			} catch (UnsupportedCharsetException | JsonProcessingException e) 
            {
				LOGGER.error("UnsupportedCharsetException | JsonProcessingException");
			}
            request = httpPut;
        }
        else if (HTTPConstant.HTTP_METHOD_DELETE.equalsIgnoreCase(message.getHttpMethod()))
        {
            HttpDelete httpDelete = new HttpDelete(resourceUri);
            setParameters(httpDelete, message.getParameters());
            request = httpDelete;
        }
        else
        {
            String msg = message.getHttpMethod() + " is not a valid HTTP method";
            LOGGER.error(msg);
            throw new IllegalArgumentException(msg);
        }
        
        setHttpHeaders(request, message.getHttpHeaders());
        
        return request;
    }
    
    private void setParameters(HttpRequestBase httpRequest, Map<String, String> parameters)
        throws URISyntaxException
    {
        if (null != parameters && !parameters.isEmpty())
        {
            URIBuilder uriBuilder = new URIBuilder(httpRequest.getURI());
            
            for (Map.Entry<String, String> entry : parameters.entrySet())
            {
                uriBuilder.addParameter(entry.getKey(), entry.getValue());
            }
            
            httpRequest.setURI(uriBuilder.build());
        }
    }
    
    private void setHttpHeaders(HttpRequestBase request, Map<String, String> httpHeaders)
    {
        if (null != httpHeaders)
        {
            for (Map.Entry<String, String> entry : httpHeaders.entrySet())
            {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }
    
}
