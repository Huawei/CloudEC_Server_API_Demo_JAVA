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
 * 企业号码管理视图
 * Corp Number Management View
 * @author wWX534262
 *
 */
@SuppressWarnings("all")
public class CorpNumPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8871409754410514595L;
	
	private static final Logger LOGGER = LogManager.getLogger(CorpNumPanel.class);

	private JLabel errInfoLabel = new JLabel();
	private JLabel tipInfoLabel = new JLabel(Properties_language_Utils.getValue("enter.CorpNumPanel.tipInfoLabel"));

	private JLabel reqParams = new JLabel(Properties_language_Utils.getValue("reqParams"));

	private JLabel corpIdLabel = new JLabel("corpId:");
	private JTextField corpIdField = new JTextField(10);

	private JLabel pageIndexLabel = new JLabel("pageIndex:");
	private JTextField pageIndexField = new JTextField(10);

	private JLabel pageSizeLabel = new JLabel("pageSize:");
	private JTextField pageSizeField = new JTextField(10);

	private JLabel searchKeyLabel = new JLabel("searchKey:");
	private JTextField searchKeyField = new JTextField(10);
	
	private JLabel numberListLabel = new JLabel("numberList:");
	private JTextField numberListField = new JTextField(10);

	private JLabel assignStatusLabel = new JLabel("assignStatus:");
	private JTextField assignStatusField = new JTextField(10);
	
	private JLabel numLabel = new JLabel("number:");
	private JTextField numField = new JTextField(10);
	
	private JButton queryPageBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.queryPageBtn"),200,35);

	private JButton insertBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.insertBtn"),200,35);

	private JButton modifyBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.modifyBtn"),200,35);

	private JButton deleteBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.deleteBtn"),200,35);

	private JButton queryBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.queryBtn"),200,35);
	
	private JButton modifyNumCallRightsBtn = new MyButton(Properties_language_Utils.
			getValue("enter.CorpNumPanel.modifyNumCallRightsBtn"),200,35);
	

	public CorpNumPanel() 
	{
		add(getPanels());
		init();
	}

	private JPanel getPanels() {
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel(gridbag);

		buildPanel(panel, gridbag, c, new JComponent[] { reqParams }, 0, 0, 10, 40, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { corpIdLabel }, 0, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { corpIdField }, 1, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { pageIndexLabel }, 2, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { pageIndexField }, 3, 2, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { pageSizeLabel }, 0, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { pageSizeField }, 1, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { searchKeyLabel }, 2, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { searchKeyField }, 3, 3, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { assignStatusLabel }, 0, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { assignStatusField}, 1, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { numLabel }, 2, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { numField}, 3, 4, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { numberListLabel }, 0, 5, 10, 20, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { numberListField}, 1, 5, 10, 20, 1, 1);

		buildPanel(panel, gridbag, c, new JComponent[] { insertBtn }, 0, 6, 10, 20, 2, 1);
		insertBtn.addActionListener(this);
			
		buildPanel(panel, gridbag, c, new JComponent[] { modifyBtn }, 2, 6, 10, 20, 2, 1);
		modifyBtn.addActionListener(this);
		buildPanel(panel, gridbag, c, new JComponent[] { queryPageBtn }, 0, 7, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { deleteBtn }, 2, 7, 10, 20, 2, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { queryBtn }, 0, 8, 10, 20, 2, 1);
		
		buildPanel(panel, gridbag, c, new JComponent[] { modifyNumCallRightsBtn }, 2, 8, 10, 20, 2, 1);
		modifyNumCallRightsBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// 构造一个新的JFrame，作为新窗口。
				// Construct a new JFrame as a new window
				JFrame frame = new JFrame(Properties_language_Utils.getValue("enter.CorpNumPanel.frameTitle_callRights"));
				setSize(1050, 680);
			    frame.setSize(1050, 680);
			     
			    JPanel center = new ModifyCallRightsPanel();
			    JScrollPane centerJPane = new JScrollPane(center);
			     
			    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
			     
				frame.setResizable(true);
				frame.setVisible(true);
				
			}
		});

		buildPanel(panel, gridbag, c, new JComponent[] { errInfoLabel }, 0, 9, 100, 5, 4, 1);
		buildPanel(panel, gridbag, c, new JComponent[] { tipInfoLabel }, 0, 10, 10, 20, 8, 1);
		
	    //报文位置
		//message location
		buildPanel(panel, gridbag, c, new JComponent[] {new LogPanel()}, 4, 0, 10, 20, 4, 10);

		return panel;
	}

	private void init() 
	{
		tipInfoLabel.setForeground(Color.blue);
		//分页查询企业号码按钮事件
		//Pagination Query Company Number Button Event
		this.queryPageBtn.addMouseListener(new MouseAdapter()
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
	            		String pageIndex = pageIndexField.getText();
	                	String pageSize = pageSizeField.getText();
	                	String searchKey = searchKeyField.getText();
	                	String assignStatus = assignStatusField.getText();
	                	
	                	queryNumInPagination(pageIndex, pageSize, searchKey, assignStatus);
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//查询企业号码按钮事件
		//Query Company Number Button Event
		this.queryBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, numField},
	            		new String[] {"corpId", "number"});
	                
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
	            		queryCorpNum();
	            	}
	            };
	            Future future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
	        }
		});
		
		//删除企业号码按钮事件
		//Delete company number button event
		this.deleteBtn.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
	        {
				showErrInfoWithColor("");
	            
	            boolean flag = validateParams(
	            		new JTextField[] {corpIdField, numberListField},
	            		new String[] {"corpId", "numberList"});
	                
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
	            		deleteCorpNum();
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

	//查询企业号码
	//Query company number
	private void queryCorpNum() 
	{
		try 
		{
			Token token = LoginUtils.getToken();

			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			EcService.get("/corp/" + corpIdField.getText() + "/number/" + numField.getText().replace("+", "%2b"),
					request, errInfoLabel, token);

			EcService.finish();
		} 
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
			LOGGER.error("get Token error:" + e);
		}
	}

	//删除企业号码
	//Delete company number
	private void deleteCorpNum() 
	{
		try 
		{
			Token token = LoginUtils.getToken();

			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_DELETE);
			
			StringBuffer stringBuffer = new StringBuffer();
			String[] split = numberListField.getText().split(",");
			stringBuffer.append("[");
			for (String number : split) 
			{
				stringBuffer.append("\"").append(number).append("\"").append(",");
			}
			String numbers = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1);
			String numbersParam = numbers + "]";
			Map<String, String> parameters = request.getParameters();
			parameters.put("numberList", numbersParam);

			request.setParameters(parameters);
			EcService.delete("/corp/" + corpIdField.getText() + "/number", 
					request, errInfoLabel, token);
			
			EcService.finish();
		} 
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
			LOGGER.error("get Token error:" + e);
		}
	}

	//分页查询企业号码
	//Paging query company number
	private void queryNumInPagination(String pageIndex, String pageSize, String searchKey, String assignStatus) 
	{
		try 
		{
			Token token = LoginUtils.getToken();

			// 利用token发送restful请求
			// Send a restful request using token
			RestRequest request = new RestRequest(HTTPConstant.HTTP_METHOD_GET);
			Map<String, String> parameters = request.getParameters();
			parameters.put("pageIndex", pageIndex);
			parameters.put("pageSize", pageSize);

			if (StringUtils.isNotEmpty(searchKey)) 
			{
				parameters.put("searchKey", searchKey);
			}

			if (StringUtils.isNotEmpty(assignStatus)) 
			{
				parameters.put("assignStatus", assignStatus);
			} 
			else 
			{
				parameters.put("assignStatus", "0");
			}
			request.setParameters(parameters);
			EcService.get("/corp/" + corpIdField.getText() + "/number", 
					request, errInfoLabel, token);

			EcService.finish();
		} 
		catch (Exception e) 
		{
			showErrInfoWithColor(Properties_language_Utils.getValue("resultTip"));
			LOGGER.error("get Token error:" + e);
		}
	}

	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
			int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight) {
		JPanel subPanel = new JPanel();

		for (JComponent component : components) {
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
	private boolean validateParams(JTextField[] textFields, String[] errTexts) {
		for (int i = 0; i < textFields.length; i++) {
			if (null == textFields[i] || null == textFields[i].getText() || "".equals(textFields[i].getText().trim())) {
				showErrInfoWithColor(errTexts[i] + Properties_language_Utils.getValue("isNotEmpty"));
				return false;
			}
		}

		return true;
	}

	private void showErrInfoWithColor(String errInfo) {
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
		JFrame frame = new JFrame(Properties_language_Utils.getValue("enter.CorpNumPanel.frameTitle"));
		setSize(1050, 680);
	    frame.setSize(1050, 680);
	     
	    JPanel center = new AddCorpNumPanel();
	    JScrollPane centerJPane = new JScrollPane(center);
	     
	    frame.getContentPane().add(centerJPane, BorderLayout.CENTER);
	     
		frame.setResizable(true);
		frame.setVisible(true);
	}

}
