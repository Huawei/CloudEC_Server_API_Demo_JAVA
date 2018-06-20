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


public abstract class Base64Utils
{
    
    /**
     * 对数据进行Base64编码
     * Base64 encoding data
     * 
     * @param btData 数据/data
     * @return 以Base64编码的数据/Base64 encoded data
     */
    public static String encode(byte[] btData)
    {
        int iLen = 0;
        boolean l_bFlag;
        int l_iGroup;
        char[] l_szData;
        byte[] l_btTmp;
        
        int ii;
        int jj;
        int kk;
        
        String l_stEncoding = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        
        if (btData == null)
        {
            return null;
        }

        iLen = btData.length;
        
        l_bFlag = ((iLen % 3) == 0);
        
        l_iGroup = iLen / 3;
        
        ii = l_iGroup;
        
        if (!l_bFlag)
        {
            ii++;
        }
        
        l_szData = new char[4 * ii];
        l_btTmp = new byte[3];
        
        for (ii = 0, jj = 0, kk = 0; ii < l_iGroup; ii++)
        {
            l_btTmp[0] = btData[kk++];
            l_btTmp[1] = btData[kk++];
            l_btTmp[2] = btData[kk++];
            
            l_szData[jj++] = l_stEncoding.charAt((l_btTmp[0] >> 2) & 0x3F);
            l_szData[jj++] = l_stEncoding.charAt(((l_btTmp[0] & 0x03) << 4) | ((l_btTmp[1] >> 4) & 0x0F));
            l_szData[jj++] = l_stEncoding.charAt(((l_btTmp[1] & 0x0F) << 2) | ((l_btTmp[2] >> 6) & 0x03));
            l_szData[jj++] = l_stEncoding.charAt(l_btTmp[2] & 0x3F);
        }
        
        if (!l_bFlag)
        {
            l_btTmp[0] = btData[kk++];
            
            l_szData[jj++] = l_stEncoding.charAt((l_btTmp[0] >> 2) & 0x3F);
            l_szData[jj + 1] = '=';
            l_szData[jj + 2] = '=';
            
            if ((iLen % 3) == 1)
            {
                l_szData[jj] = l_stEncoding.charAt((l_btTmp[0] & 0x03) << 4);
            }
            else
            {
                l_btTmp[1] = btData[kk];
                
                l_szData[jj++] = l_stEncoding.charAt(((l_btTmp[0] & 0x03) << 4) | ((l_btTmp[1] >> 4) & 0x0F));
                l_szData[jj] = l_stEncoding.charAt((l_btTmp[1] & 0x0F) << 2);
            }
        }
        
        return new String(l_szData);
    }
    
    /**
     * 对Base64数据进行解码， 转换失败返回 null
     * Decoding base64 data, conversion failed returns null
     * 
     * @param stData 数据/data
     * @return 解码后的数据/Decoded data
     */
    public static byte[] getFromBASE64(String stData)
    {
        if (StringUtils.isEmpty(stData))
        {
            return new byte[0];
        }
        int l_iLen;
        int l_iGroup;
        int ii;
        int jj;
        int kk;
        boolean l_bFlag;
        char[] l_szTmp;
        byte[] l_btData = new byte[0];
        
        l_iLen = stData.length();
        
        if ((l_iLen % 4) != 0)
        {
            return l_btData;
        }
        
        l_iGroup = l_iLen / 4;
        ii = l_iGroup * 3;
        l_bFlag = true;
        l_szTmp = new char[4];
        
        if (stData.charAt(l_iLen - 1) == '=')
        {
            l_iLen--;
            ii--;
            l_iGroup--;
            
            l_bFlag = false;
            
            if (stData.charAt(l_iLen - 1) == '=')
            {
                l_iLen--;
                ii--;
            }
        }
        
        for (jj = 0; jj < l_iLen; jj++)
        {
            l_szTmp[0] = stData.charAt(jj);
            
            if (!((l_szTmp[0] == '+') || (('/' <= l_szTmp[0]) && (l_szTmp[0] <= '9'))
                || (('A' <= l_szTmp[0]) && (l_szTmp[0] <= 'Z')) || (('a' <= l_szTmp[0]) && (l_szTmp[0] <= 'z'))))
            {
                return l_btData;
            }
        }
        
        l_btData = new byte[ii];
        
        for (ii = 0, jj = 0, kk = 0; ii < l_iGroup; ii++)
        {
            l_szTmp[0] = returnToData(stData.charAt(kk++));
            l_szTmp[1] = returnToData(stData.charAt(kk++));
            l_szTmp[2] = returnToData(stData.charAt(kk++));
            l_szTmp[3] = returnToData(stData.charAt(kk++));
            
            l_btData[jj++] = (byte)((l_szTmp[0] << 2) | ((l_szTmp[1] >> 4) & 0x03));
            l_btData[jj++] = (byte)((l_szTmp[1] << 4) | ((l_szTmp[2] >> 2) & 0x0F));
            l_btData[jj++] = (byte)((l_szTmp[2] << 6) | (l_szTmp[3] & 0x3F));
        }
        
        if (!l_bFlag)
        {
            l_szTmp[0] = returnToData(stData.charAt(kk++));
            l_szTmp[1] = returnToData(stData.charAt(kk++));
            
            l_btData[jj++] = (byte)((l_szTmp[0] << 2) | ((l_szTmp[1] >> 4) & 0x03));
            
            if ((l_iLen % 4) == 3)
            {
                l_szTmp[2] = returnToData(stData.charAt(kk));
                
                l_btData[jj] = (byte)((l_szTmp[1] << 4) | ((l_szTmp[2] >> 2) & 0x0F));
            }
        }
        
        return l_btData;
    }
    
    // cChar 的合法性由 getFromBASE64中的代码保证了
    //The validity of cChar is guaranteed by the code in getFromBASE64
    private static char returnToData(char cChar) 
    {
        if (('A' <= cChar) && (cChar <= 'Z'))
        {
            cChar -= 'A';
        }
        else if (('a' <= cChar) && (cChar <= 'z'))
        {
            cChar -= 'a';
            cChar += 26;
        }
        else if (('0' <= cChar) && (cChar <= '9'))
        {
            cChar -= '0';
            cChar += 52;
        }
        else if (cChar == '+')
        {
            cChar = 62;
        }
        else
        {
            cChar = 63;
        }
        
        return cChar;
    }
}
