package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.bean.common.RestRequest;
import com.huawei.esdk.ec.bean.common.Token;
import com.huawei.esdk.ec.bean.enterprise.LayoutModeBean;
import com.huawei.esdk.ec.bean.enterprise.SubscriberInPic;
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.MeetingTokenUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 切换视频显示策略的视图
 * Switch video display strategies view
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class VideoConferencePanel extends JPanel
{
	private static final long serialVersionUID = -4166487757925505344L;
	
	private static final Logger LOGGER = LogManager.getLogger(VideoConferencePanel.class);
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel errInfoLabel = new JLabel();
	private JLabel infoLabel = new JLabel(Properties_language_Utils.
			getValue("enter.VideoConferencePanel.infoLabel"));
	
	private JLabel conferenceidLabel = new JLabel("conferenceid:");
	private JTextField conferenceidField = new JTextField(10);
	
	private JLabel switchModeLabel = new JLabel("switchMode:");
	private JTextField switchModeField = new JTextField(10);
	
	private JLabel imageTypeLabel = new JLabel("imageType:");
	private JTextField imageTypeField = new JTextField(10);
	
	private JLabel indexLabel = new JLabel("index:");
	private JTextField indexField = new JTextField(10);
	
	private JLabel subscriberLabel = new JLabel("subscriber:");
	private JTextField subscriberField = new JTextField(10);
	
	private JButton spriteListBtn = new MyButton(Properties_language_Utils.
			getValue("enter.VideoConferencePanel.spriteListBtn"));
	
	private JButton layoutModeBtn = new MyButton(Properties_language_Utils.
			getValue("enter.VideoConferencePanel.layoutModeBtn"));
	
	List<SubscriberInPic> subscriberInPics = new ArrayList<>();
	
	public VideoConferencePanel()
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
		buildPanel(panel, gridbag, c, new JComponent[] {switchModeLabel}, 2, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {switchModeField}, 3, 1, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {imageTypeLabel}, 0, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {imageTypeField}, 1, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {indexLabel}, 2, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {indexField}, 3, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {subscriberLabel}, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {subscriberField}, 1, 3, 10, 20, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {spriteListBtn}, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {layoutModeBtn}, 1, 4, 10, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 2, 3, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {infoLabel}, 0, 5, 10, 10, 4, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
		
	}
	
	private void init() 
	{
		infoLabel.setForeground(Color.BLUE);
		
		//点击子画面列表按钮事件
		//Click on the sprite list button event
		this.spriteListBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
			    
	    		subscriberInPics.add(addSprite());
	    		showErrInfoWithColor(Properties_language_Utils.getValue("enter.VideoConferencePanel.resultTip"));
			}
	           
		});
		
		//点击切换会议显示策略按钮事件
		//Click to switch conference display policy button events
		this.layoutModeBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				showErrInfoWithColor("");
			            
				boolean flag = validateParams(
						new JTextField[] {conferenceidField, switchModeField, imageTypeField},
			            new String[] {"conferenceid", "switchMode", "imageType"});
			                
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
	            		layoutMode();
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
	
	//切换会议显示策略
	//Switch conference display strategy
	private void layoutMode() 
	{
		try
		{
			Token token = LoginUtils.getToken();
			
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_PUT);
			Map<String, String> httpHeaders = request.getHttpHeaders();
			httpHeaders.put("Conference-Authorization", MeetingTokenUtils.getMeetingToken());
			
			LayoutModeBean layoutModeBean = new LayoutModeBean();
			layoutModeBean.setSwitchMode(switchModeField.getText());
			layoutModeBean.setImageType(imageTypeField.getText());
			if (subscriberInPics.size() != 0) 
			{
				layoutModeBean.setSubscriberInPics(subscriberInPics);
			}
			
			request.setPayload(layoutModeBean);
			
			EcService.put("/conferences/" + conferenceidField.getText() + "/layoutMode" ,
						request, errInfoLabel, token);
			
			subscriberInPics.clear();
			
			EcService.finish();
		} 
		catch (Exception e)
		{
			LOGGER.error("get Token error",e);
		}
	}
	
	//子画面设置
	//Sub screen settings
	private SubscriberInPic addSprite() 
	{
		SubscriberInPic subscriberInPic = new SubscriberInPic();
		
		if (StringUtils.isNotEmpty(indexField.getText())) 
		{
			subscriberInPic.setIndex(Integer.parseInt(indexField.getText()));
		}
		if (StringUtils.isNotEmpty(subscriberField.getText())) 
		{
			subscriberInPic.setSubscriber(subscriberField.getText());
		}
		
		return subscriberInPic;
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

}


