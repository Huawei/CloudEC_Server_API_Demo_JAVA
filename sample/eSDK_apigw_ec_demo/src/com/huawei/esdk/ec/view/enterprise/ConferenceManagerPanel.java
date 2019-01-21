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
import java.util.HashMap;
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
 * 会议管理接口视图
 * Conference management interface view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class ConferenceManagerPanel extends JPanel implements ActionListener
{

	private static final long serialVersionUID = 4514894286520353532L;
	
	private static final Logger LOGGER = LogManager.getLogger(ConferenceManagerPanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel conferenceidLabel = new JLabel("conferenceid:");
	private JTextField conferenceidField = new JTextField(10);
	
	private JLabel accountIDLabel = new JLabel("accountID:");
	private JTextField accountIDField = new JTextField(10);
	
	private JLabel subjectLabel = new JLabel("subject:");
	private JTextField subjectField = new JTextField(10);
	
	private JLabel conferenceTypeLabel = new JLabel("conferenceType:");
	private JTextField conferenceTypeField = new JTextField(10);
	
	private JLabel conferenceRightLabel = new JLabel("conferenceRight:");
	private JTextField conferenceRightField = new JTextField(10);
	
	private JLabel conferenceStateLabel = new JLabel("conferenceState:");
	private JTextField conferenceStateField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JButton addConferenceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceManagerPanel.addConferenceBtn"),200,35);
	
	private JButton modifyConferenceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceManagerPanel.modifyConferenceBtn"),200,35);
	
	private JButton deleConferenceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceManagerPanel.deleConferenceBtn"),200,35);
	
	private JButton queryConferenceListBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceManagerPanel.queryConferenceListBtn"),200,35);
	
	private JButton queryConferenceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceManagerPanel.queryConferenceBtn"),200,35);
	
	//查询历史会议列表button
	private JButton queryHistoryConferenceBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceManagerPanel.queryHistoryConferenceBtn"),200,35);
	
	//删除录制文件button
	private JButton delRecordingBtn = new MyButton(Properties_language_Utils.
			getValue("enter.ConferenceManagerPanel.delRecordingBtn"),200,35);
	
	
	public ConferenceManagerPanel() 
	{
		add(getPanels());
	    init();
	}
	
	private JPanel getPanels() 
	{
		 GridBagLayout gridbag = new GridBagLayout();
		 GridBagConstraints c = new GridBagConstraints();
		 JPanel panel = new JPanel(gridbag);
		
		 buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 10, 40, 1, 1);  
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceidLabel}, 0, 1, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceidField}, 1, 1, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {accountIDLabel}, 2, 1, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {accountIDField}, 3, 1, 6, 20, 1, 1);
		 
		 buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 2, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 2, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 2, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 2, 6, 20, 1, 1);
		 
		 buildPanel(panel, gridbag, c, new JComponent[] {subjectLabel}, 0, 3, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {subjectField}, 1, 3, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceTypeLabel}, 2, 3, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceTypeField}, 3, 3, 6, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceRightLabel}, 0, 4, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceRightField}, 1, 4, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceStateLabel}, 2, 4, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {conferenceStateField}, 3, 4, 6, 20, 1, 1);
		 
		 //按钮
		 //button
		 buildPanel(panel, gridbag, c, new JComponent[] {addConferenceBtn}, 0, 5, 10, 20, 1, 1);
		 addConferenceBtn.addActionListener(this);
		 buildPanel(panel, gridbag, c, new JComponent[] {modifyConferenceBtn}, 2, 5, 10, 20, 1, 1);
		 modifyConferenceBtn.addActionListener(this);
		 buildPanel(panel, gridbag, c, new JComponent[] {queryConferenceListBtn}, 0, 6, 10, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {queryConferenceBtn}, 2, 6, 6, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {deleConferenceBtn}, 0, 7, 6, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {queryHistoryConferenceBtn}, 2, 7, 6, 20, 1, 1);
		 buildPanel(panel, gridbag, c, new JComponent[] {delRecordingBtn}, 0, 8, 6, 20, 1, 1);
		 
		 buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 9, 100, 5, 4, 1);
		 
		 //报文位置
		 //message location
		 buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		 
		 return panel;
	}
	
	private void init() 
	{
		//取消会议接口按钮
		//Cancel meeting button
		this.deleConferenceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {conferenceidField},
			            new String[] {"conferenceid"});
			                
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
	            		deleConference();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//查询会议列表按钮事件
		//Query conference list button events
		this.queryConferenceListBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
			    EcService.begin();
			    EcService.loading(errInfoLabel);
			            
			    Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		queryConferenceList();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//查询会议信息按钮事件
		//Query conference information button events
		this.queryConferenceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {conferenceidField},
			            new String[] {"conferenceid"});
			                
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
	            		queryConference();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//查询历史会议列表按钮事件
		//Query historical conference list button events
		this.queryHistoryConferenceBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {pageIndexField, pageSizeField},
			            new String[] {"pageIndex", "pageSize"});
			                
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
	            		queryHistoryConference();
	            	}

	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
		
		//删除录制文件按钮事件
		//delete recording button events
		this.delRecordingBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {conferenceidField},
			            new String[] {"conferenceid"});
			                
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
	            		delRecording();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
			}
		});
	}
	
	//删除录播文件
	//delete recording files
	private void delRecording() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			
			EcService.delete("/conferences/" + conferenceidField.getText() + "/recording", request, errInfoLabel, token);
			
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
	
	//查询历史会议列表
	//Query historical conference list
	private void queryHistoryConference() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			
			Map<String,String> params = request.getParameters();
			
			params.put("pageIndex", pageIndexField.getText());
			
			params.put("pageSize", pageSizeField.getText());
			
			EcService.get("/conferences/history", request, errInfoLabel, token);
			
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
	
	//查询会议信息
	//Query conference information 
	private void queryConference() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			
			Map<String,String> params = new HashMap<String,String>();
			if (StringUtils.isNotEmpty(pageIndexField.getText())) 
			{
				params.put("pageIndex", pageIndexField.getText());
			}
			if (StringUtils.isNotEmpty(pageSizeField.getText())) 
			{
				params.put("pageSize", pageSizeField.getText());
			}
			
			request.setParameters(params);
			
			EcService.get("/conferences/" + conferenceidField.getText(), request, errInfoLabel, token);
			
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
	
	//查询会议信息列表
	//Query conference list
	private void queryConferenceList() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			
			//拼接参数
			//Splicing parameters
			Map<String,String> param = new HashMap<String,String>();
			if (StringUtils.isNotEmpty(accountIDField.getText())) 
			{
				param.put("accountID", accountIDField.getText());
			}
			if (StringUtils.isNotEmpty(conferenceidField.getText())) 
			{
				param.put("conferenceID", conferenceidField.getText());
			}
			if (StringUtils.isNotEmpty(subjectField.getText())) 
			{
				param.put("subject", subjectField.getText());
			}
			if (StringUtils.isNotEmpty(conferenceTypeField.getText())) 
			{
				param.put("conferenceType", conferenceTypeField.getText());
			}
			if (StringUtils.isNotEmpty(conferenceRightField.getText())) 
			{
				param.put("conferenceRight", conferenceRightField.getText());
			}
			if (StringUtils.isNotEmpty(conferenceStateField.getText())) 
			{
				param.put("conferenceState", conferenceStateField.getText());
			}
			if (StringUtils.isNotEmpty(pageIndexField.getText())) 
			{
				param.put("pageIndex", pageIndexField.getText());
			}
			if (StringUtils.isNotEmpty(pageSizeField.getText())) 
			{
				param.put("pageSize", pageSizeField.getText());
			}
			
			request.setParameters(param);
			
			EcService.get("/conferences", request, errInfoLabel, token);
			
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
	
	//取消会议
	//Cancel the meeting
	private void deleConference() 
	{
		try 
		{
			Token token = LoginUtils.getToken();
			
			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			
			EcService.delete("/conferences/" + conferenceidField.getText(), request, errInfoLabel, token);
			
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
	
	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
		        int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight)
	{
		JPanel subPanel = new JPanel();
     
		for (JComponent component : components)
		{
			component.setFont(new Font("宋体", Font.CENTER_BASELINE, 18));
			subPanel.add(component);
		}
     
		c.fill = GridBagConstraints.VERTICAL;// 加高组件直到他足以在垂直方向上填满其显示区域，但不更改其高度
		c.anchor = GridBagConstraints.WEST;// 当组件小于其显示区域时，用于确定将组件至于西面
		c.gridx = gridx;
		c.gridy = gridy;
		c.ipadx = ipadx;
		c.ipady = ipady;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		gridbag.setConstraints(subPanel, c);
		panel.add(subPanel);
	}
	
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
		// Construct a new JFrame as a new window.
		JFrame frame = new JFrame(Properties_language_Utils.getValue("enter.ConferenceManagerPanel.frameTitle"));
		
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	    
	    JPanel center = new AddConferencePanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	    
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
        frame.setResizable(true);
        frame.setVisible(true);
	}
}
