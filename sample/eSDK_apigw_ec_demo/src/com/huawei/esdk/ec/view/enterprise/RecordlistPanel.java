package com.huawei.esdk.ec.view.enterprise;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import com.huawei.esdk.ec.constant.HTTPConstant;
import com.huawei.esdk.ec.service.EcService;
import com.huawei.esdk.ec.utils.LoginUtils;
import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.StringUtils;
import com.huawei.esdk.ec.view.common.LogPanel;
import com.huawei.esdk.ec.view.common.MyButton;
/**
 * 请求录音列表视图
 */
@SuppressWarnings("all")
public class RecordlistPanel extends JPanel
{

	private static final long serialVersionUID = -9024457694743109238L;
	
	private static final Logger LOGGER = LogManager.getLogger(UcRolePanel.class);
	
	private JLabel errInfoLabel = new JLabel();
	
	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));
	
	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);
	
	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel startTimeLabel = new JLabel("startTime:");
	private JTextField startTimeField = new JTextField(10);
	
	private JLabel endTimeLabel = new JLabel("endTime:");
	private JTextField endTimeField = new JTextField(10);
	
	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);
	
	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);
	
	private JLabel onlyLocalRecordLabel = new JLabel("onlyLocalRecord:");
	private JTextField onlyLocalRecordField = new JTextField(10);
	
	private JLabel isConfRecordLabel = new JLabel("isConfRecord:");
	private JTextField isConfRecordField = new JTextField(10);
	
	private JLabel isMultiKeyLabel = new JLabel("isMultiKey:");
	private JTextField isMultiKeyField = new JTextField(10);
	
	private JLabel isExactSearchLabel = new JLabel("isExactSearch:");
	private JTextField isExactSearchField = new JTextField(10);
	
	private JButton queryBtn = new MyButton(Properties_language_Utils.
			getValue("enter.RecordlistPanel.queryBtn"));
	
	public RecordlistPanel() 
	{
		add(getPanels());
		init();
	}

	private JPanel getPanels()
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
		
		buildPanel(panel, gridbag, c, new JComponent[] {reqParams}, 0, 0, 20, 40, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdLabel}, 0, 1, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {corpIdField}, 1, 1, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {searchKeyLabel}, 2, 1, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {searchKeyField}, 3, 1, 20, 10, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {startTimeLabel}, 0, 2, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {startTimeField}, 1, 2, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {endTimeLabel}, 2, 2, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {endTimeField}, 3, 2, 20, 10, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {pageIndexLabel}, 0, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pageIndexField}, 1, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pageSizeLabel}, 2, 3, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {pageSizeField}, 3, 3, 20, 10, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {onlyLocalRecordLabel}, 0, 4, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {onlyLocalRecordField}, 1, 4, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isConfRecordLabel}, 2, 4, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isConfRecordField}, 3, 4, 20, 10, 1, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {isMultiKeyLabel}, 0, 5, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isMultiKeyField}, 1, 5, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isExactSearchLabel}, 2, 5, 20, 10, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {isExactSearchField}, 3, 5, 20, 10, 1, 1);
		
		//按钮
		//button
		buildPanel(panel, gridbag, c, new JComponent[] {queryBtn}, 0, 6, 5, 10, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] {errInfoLabel}, 0, 7, 30, 5, 4, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);
		
		return panel;
	}
	
	private void init() 
	{
		this.queryBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				showErrInfoWithColor("");
				
				boolean flag = validateParams(
						new JTextField[] {corpIdField, pageIndexField, pageSizeField},
						new String[] {"corpId", "pageIndex", "pageSize"});
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
						requestRecordlist();
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
	
	//请求录音列表
	//request recordlist
	private void requestRecordlist() 
	{
		RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
		Map<String, String> parameters = request.getParameters();
		parameters.put("pageIndex", pageIndexField.getText());
		parameters.put("pageSize", pageSizeField.getText());
		if (StringUtils.isNotEmpty(searchKeyField.getText()))
		{
			parameters.put("searchKey", searchKeyField.getText());
		}
		if (StringUtils.isNotEmpty(startTimeField.getText())) 
		{
			parameters.put("startTime", startTimeField.getText());
		}
		if (StringUtils.isNotEmpty(endTimeField.getText())) 
		{
			parameters.put("endTime", endTimeField.getText());
		}
		if (StringUtils.isNotEmpty(onlyLocalRecordField.getText())) 
		{
			parameters.put("onlyLocalRecord", onlyLocalRecordField.getText());
		}
		if (StringUtils.isNotEmpty(isConfRecordField.getText())) 
		{
			parameters.put("isConfRecord", isConfRecordField.getText());
		}
		if (StringUtils.isNotEmpty(isMultiKeyField.getText())) 
		{
			parameters.put("isMultiKey", isMultiKeyField.getText());
		}
		if (StringUtils.isNotEmpty(isExactSearchField.getText())) 
		{
			parameters.put("isExactSearch", isExactSearchField.getText());
		}
		
		Token token = LoginUtils.getToken();
		 
		// 利用token发送restful请求
		// Send a restful request using token
		EcService.get("/corp/" + corpIdField.getText() + "/recordlist", 
				request, errInfoLabel, token);
		 
		EcService.finish();
			 
		
	}
	
	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
			int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight)
	{
		JPanel subPanel = new JPanel();
		
		for(JComponent component : components)
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
		for(int i = 0; i < textFields.length; i++)
		{
			if(null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText().trim()))
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
