package com.huawei.esdk.ec.view.enterprise;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;


/**
 * 企业成员管理视图
 * Enterprise member management view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class CorpMemberPanel extends JPanel implements ActionListener
{

	 private static final long serialVersionUID = 5804108538094060983L;
	
	 private static final Logger LOGGER = LogManager.getLogger(CorpMemberPanel.class);
	 
	 private JLabel errInfoLabel = new JLabel();
	 private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.
			 getValue("enter.CorpMemberPanel.tipInfoLabel"));
	 
	 private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	 
	 private JLabel corpIdLabel = new JLabel("corpId:");
	 private JTextField corpIdField = new JTextField(10);
	 
	 private JLabel userAccountLabel = new JLabel("userAccount:");
	 private JTextField userAccountField = new JTextField(10);
	 
	 private JLabel pageIndexLabel = new JLabel("pageIndex:");
	 private JTextField pageIndexField = new JTextField(10);
	 
	 private JLabel pageSizeLabel = new JLabel("pageSize:");
	 private JTextField pageSizeField = new JTextField(10);
	
	 private JLabel searchKeyLabel = new JLabel("searchKey:");
	 private JTextField searchKeyField = new JTextField(10);
	
	 private JLabel deptCodeLabel = new JLabel("deptCode:");
	 private JTextField deptCodeField = new JTextField(10);
	 
	 private JLabel searchTypeLabel = new JLabel("searchType:");
	 private JTextField searchTypeField = new JTextField(10);
	 
	 private JLabel membersLabel = new JLabel("members:");
	 private JTextField membersField = new JTextField(10);
	
	 private JButton accountAddBtn = new MyButton(Properties_language_Utils.
			 getValue("enter.CorpMemberPanel.AccountAddBtn"),240,35);
	    
	 private JButton modifyAcctBtn = new MyButton(Properties_language_Utils.
			 getValue("enter.CorpMemberPanel.modifyAcctBtn"),240,35);
	    
	 private JButton deltAcctBtn = new MyButton(Properties_language_Utils.
			 getValue("enter.CorpMemberPanel.deltAcctBtn"),240,35);
	 
	 private JButton batchDeltAcctBtn = new MyButton(Properties_language_Utils.
			 getValue("enter.CorpMemberPanel.batchDeltAcctBtn"),240,35);
	    
	 private JButton searchAcctBtn = new MyButton(Properties_language_Utils.
			 getValue("enter.CorpMemberPanel.searchAcctBtn"),240,35);
	 
	 private JButton queryPageAcctBtn = new MyButton(Properties_language_Utils.
			 getValue("enter.CorpMemberPanel.queryPageAcctBtn"),240,35);
	 
	 
	 
	 public CorpMemberPanel() 
	 {
		 add(getPanels());
	     init();
	 }
	 
	 private void init() 
	 {
		 tipInfoLabel.setForeground(Color.blue);
		 
		 //点击批量删除企业成员事件
		 //Click to delete enterprise member events in batch
		 this.batchDeltAcctBtn.addMouseListener(new MouseAdapter() 
		 {
			 public void mouseClicked(MouseEvent e) 
			 {
				 showErrInfoWithColor("");
	             
	             boolean flag = validateParams(
	            		 new JTextField[] {corpIdField, membersField},
	            		 new String[] {"corpId", "members"});
	             
	             if(!flag) 
	             {
	            	 return;
	             }
	             EcService.begin();
	             EcService.loading(errInfoLabel);
	             
	             Runnable runnable = new Runnable()
	             {
	            	 @Override
	            	 public void run()
	            	 {
	            		 batchDeleAccount();
	            	 }
	             };
	             Future future = Executors.newSingleThreadExecutor().submit(runnable);
		         if(!future.isDone()) {
		             LOGGER.error("addMouseListener fail");
		         }
			 }
		 });
		 
		 //点击删除企业成员按钮事件
		 //Click to delete enterprise member button event
		 this.deltAcctBtn.addMouseListener(new MouseAdapter() 
		 {
			 public void mouseClicked(MouseEvent e) 
			 {
				 showErrInfoWithColor("");
	             
	             boolean flag = validateParams(
	            		 new JTextField[] {corpIdField, userAccountField},
	            		 new String[] {"corpId", "userAccount"});
	             
	             if(!flag) 
	             {
	            	 return;
	             }
	             EcService.begin();
	             EcService.loading(errInfoLabel);

	             Runnable runnable = new Runnable()
	             {
	            	 @Override
	            	 public void run()
	            	 {
	            		 deleAccount();
	            	 }
	             };
	             Future future = Executors.newSingleThreadExecutor().submit(runnable);
		         if(!future.isDone()) {
		             LOGGER.error("addMouseListener fail");
		         }
			 }
		 });
		 
		 // 点击查询企业成员按钮事件
		 // Click on the query enterprise member button event
		 this.searchAcctBtn.addMouseListener(new MouseAdapter()
		 {
			 public void mouseClicked(MouseEvent e)
	         {
				 showErrInfoWithColor("");
	            
	             boolean flag = validateParams(
	            		 new JTextField[] {corpIdField, userAccountField},
	            		 new String[] {"corpId", "userAccount"});
	                
	             if (!flag)
	             {
	            	 return;
	             }
	             EcService.begin();
	             EcService.loading(errInfoLabel);
	            
	             Runnable runnable = new Runnable()
	             {
	            	 @Override
	            	 public void run()
	            	 {
	            		 searchAccount();
	            	 }
	             };
	             Future future = Executors.newSingleThreadExecutor().submit(runnable);
		         if(!future.isDone()) {
		             LOGGER.error("addMouseListener fail");
		         }
	         }
	    });
        
        // 分页查询部门下企业按钮事件
		// Paging query departments at enterprises button events
        this.queryPageAcctBtn.addMouseListener(new MouseAdapter()
        {
            
	        public void mouseClicked(MouseEvent e)
	        {
	            showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	                new JTextField[] {corpIdField, pageIndexField, pageSizeField, deptCodeField},
	                new String[] {"corpId", "pageIndex", "pageSize", "deptCode"});
	                
	            if (!flag)
	            {
	                return;
	            }
	            EcService.begin();
	            EcService.loading(errInfoLabel);
	            
                Runnable runnable = new Runnable()
	             {
	            	 @Override
	            	 public void run()
	            	 {
	            		 String searchKey = searchKeyField.getText();
	                 	 String pageIndex = pageIndexField.getText();
	                 	 String pageSize = pageSizeField.getText();
	                 	 String deptCode = deptCodeField.getText();
	                 	
	                     queryPageAccount(searchKey, pageIndex, pageSize, deptCode);
	            	 }
	             };
	             Future future = Executors.newSingleThreadExecutor().submit(runnable);
		         if(!future.isDone()) {
		             LOGGER.error("addMouseListener fail");
		         }
	        }
	    });
	 }
	 
	 //批量删除企业成员
	 //Delete enterprise members in batches
	 private void batchDeleAccount() 
	 {
		 try 
		 {
			 Token token = LoginUtils.getToken();
			 
			 // 利用token发送restful请求
			 // Send a restful request using token
			 RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			 
			 StringBuffer stringBuffer = new StringBuffer();
			 String[] split = membersField.getText().split(",");
			 stringBuffer.append("[");
			 for (String member : split) 
			 {
				 stringBuffer.append("\"").append(member).append("\"").append(",");
			 }
			 String members = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
			 String membersParam = members + "]";
			 
			 Map<String, String> parameters = request.getParameters();
			 parameters.put("members", membersParam);
			 
			 request.setParameters(parameters);
			 EcService.delete("/corp/" + corpIdField.getText() + "/member", request, errInfoLabel, token);
			 EcService.finish();
			 
		 } 
		 catch (RuntimeException e) 
		 {
			LOGGER.error("get Token error");
		 }
		 catch (Exception e) 
		 {
			LOGGER.error("get Token error");
		 }
	 }
	 
	 //删除企业成员
	 //Delete company members
	 private void deleAccount() 
	 {
		 try 
		 {
			 Token token = LoginUtils.getToken();
			 
			 // 利用token发送restful请求
			 // Send a restful request using token
			 RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			 
			 
			 String userAccount = userAccountField.getText().replace("@", "%40");
			 EcService.delete("/corp/" + corpIdField.getText() + "/member/" + userAccount,
					 request, errInfoLabel, token);
			 EcService.finish();
			 
		 } 
		 catch (RuntimeException e) 
		 {
			 LOGGER.error("get Token error");
		 }
		 catch (Exception e) 
		 {
			 LOGGER.error("get Token error");
		 }
	 }
	 
	 //分页查询部门下企业成员
	 //Pagination query company members under department
	 private void queryPageAccount(String searchKey, String pageIndex, String pageSize, String deptCode) 
	 {
		 try 
		 {
			 Token token = LoginUtils.getToken();
			
			 // 利用token发送restful请求
			 // Send a restful request using token
			 RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			 Map<String, String> parameters = request.getParameters();
			 if (StringUtils.isNotEmpty(searchKey))
			 {
				 parameters.put("searchKey", searchKey);
			 }
			 parameters.put("pageIndex", pageIndex);
			 parameters.put("pageSize", pageSize);
			 parameters.put("departmentCode", deptCode);
			 request.setParameters(parameters);
			 
			 EcService.get("/corp/" + corpIdField.getText() + "/member", request, errInfoLabel, token);
			 EcService.finish();
		} 
		catch (RuntimeException e) 
		{
			LOGGER.error("get Token error");
		}
		catch (Exception e) 
		{
			LOGGER.error("get Token error");
		}
	 }
	 
	 //查询企业成员
	 //Query company members
	 public void searchAccount() 
	 {
		 try 
		 {
			 Token token = LoginUtils.getToken();
			
			 // 利用token发送restful请求
			 // Send a restful request using token
			 RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			 
			 String userAccount = userAccountField.getText().replace("@", "%40");
			 
			 EcService.get("/corp/" + corpIdField.getText() + "/member/" + userAccount,
					 request, errInfoLabel, token);
			 EcService.finish();
		} 
		catch (RuntimeException e) 
		{
			 LOGGER.error("get Token error");
		}
		catch (Exception e) 
		{
			 LOGGER.error("get Token error");
		}
	 }

	 
	 private JPanel getPanels()
	 {
	    GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1);  
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 1, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountLabel}, 2, 1, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {userAccountField}, 3, 1, 6, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 3, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 0, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 1, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deptCodeLabel}, 2, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {deptCodeField}, 3, 4, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchTypeLabel}, 0, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchTypeField}, 1, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {membersLabel}, 2, 5, 10, 20, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {membersField}, 3, 5, 10, 20, 1, 1);
	  
	    buildPanel(panel, gridbag, c, new JComponent[] {deltAcctBtn}, 0, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {searchAcctBtn}, 2, 6, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {queryPageAcctBtn}, 0, 7, 10, 20, 2, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {batchDeltAcctBtn}, 2, 7, 10, 20, 2, 1);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {accountAddBtn}, 0, 8, 10, 20, 2, 1);
	    accountAddBtn.addActionListener(this);
	    buildPanel(panel, gridbag, c, new JComponent[] {modifyAcctBtn}, 2, 8, 10, 20, 2, 1);
	    modifyAcctBtn.addActionListener(this);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 9, 100, 5, 4, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {tipInfoLabel}, 0, 10, 100, 5, 4, 1);
	    
	    //报文位置
	    //message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
	    return panel;
	 }
	 
	 private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
		        int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight)
     {
		 JPanel subPanel = new JPanel();
        
         for (JComponent component : components)
         {
        	 component.setFont(new Font("宋体", Font.CENTER_BASELINE, 18));
             subPanel.add(component);
         }
        
         c.fill = GridBagConstraints.VERTICAL;
         c.anchor = GridBagConstraints.WEST;
         c.gridx = gridx;
         c.gridy = gridy;
         c.ipadx = ipadx;
         c.ipady = ipady;
         c.gridwidth = gridwidth;
         c.gridheight = gridheight;
         gridbag.setConstraints(subPanel, c);
         panel.add(subPanel);
		        
     }
	  
	 //判断参数为空性
	 //Judgment parameter is empty
	 private boolean validateParams(JTextField[] textFields, String[] errTexts)
	 {
		 for (int i = 0; i < textFields.length; i++)
		 {
			 if (null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText().trim()))
			 {
				 showErrInfoWithColor(errTexts[i] + Properties_language_Utils.getValue("isNotEmpty"));
				 return false;
			 }
		 }
    
		 return true;
	 }
	 
	 private void showErrInfoWithColor(String errInfo)
	 {
		 if (null == errInfo) 
		 {
			 return;
		 }
	        
		 errInfoLabel.setForeground(Color.red);
		 errInfoLabel.setText(errInfo);
	 }

	 @Override
	 public void actionPerformed(ActionEvent e) 
	 {
		 // 构造一个新的JFrame，作为新窗口。
		 // Construct a new JFrame as a new window
		 JFrame frame = new JFrame(Properties_language_Utils.
				 getValue("enter.CorpMemberPanel.frameTitle"));
		 setSize(1050, 680);
	     frame.setSize(1050, 680);
	     
	     JPanel center = new AddCorpMemberPanel();
	     JScrollPane centerJPane = new JScrollPane(center);
	     
	     frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
	     
		 frame.setResizable(true);
		 frame.setVisible(true);
	 }
}
