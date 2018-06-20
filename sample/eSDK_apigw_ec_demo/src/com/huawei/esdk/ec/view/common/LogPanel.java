package com.huawei.esdk.ec.view.common;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.huawei.esdk.ec.utils.Properties_language_Utils;
import com.huawei.esdk.ec.utils.ResultUtils;
import com.huawei.esdk.ec.utils.StringUtils;
/**
 * 请求响应消息视图
 * Request response message view
 * @author wwx534262
 *
 */
public class LogPanel extends JPanel
{
	private static final long serialVersionUID = 7796868931786285983L;
	
	private JLabel msgLabel = new JLabel(Properties_language_Utils.getValue("commom.LogPanel.msgLabel"));
	
	private JButton showBtn = new MyButton(Properties_language_Utils.getValue("commom.LogPanel.showBtn"));
	
	private JTextArea retDescArea = new JTextArea(15, 30);
	
	public LogPanel() 
	{
		add(getPanels());
		init();
	}
	
	private JPanel getPanels() 
	{
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		JPanel panel = new JPanel(gridbag);
		JScrollPane jsp = new JScrollPane(retDescArea);
		
		buildPanel(panel, gridbag, c, new JComponent[] {msgLabel}, 0, 0, 10, 5, 1, 1);
		buildPanel(panel, gridbag, c, new JComponent[] {showBtn}, 1, 0, 10, 5, 1, 1);
		
		retDescArea.setBorder(BorderFactory.createTitledBorder(""));
		retDescArea.setFont(new Font("宋体", Font.CENTER_BASELINE, 18));
		buildPanel(panel, gridbag, c, new JComponent[] {jsp}, 0, 1, 5, 20, 2, 1);
		
		return panel;
	}
	
	private void init() 
	{
		
		this.showBtn.addMouseListener(new MouseAdapter() 
		{
			public void mouseClicked(MouseEvent e) 
			{
				retDescArea.setText("");
				if (StringUtils.isNotEmpty(ResultUtils.getReq())) 
				{
					retDescArea.setText(ResultUtils.getReq() +"\r\n" +"---------------------------" 
							+ "\r\n" + ResultUtils.getResponse());
				}
			}
		});
	}
	
	private void buildPanel(JPanel panel, GridBagLayout gridbag, GridBagConstraints c, JComponent[] components,
			int gridx, int gridy, int ipadx, int ipady, int gridwidth, int gridheight) 
	{
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
}
