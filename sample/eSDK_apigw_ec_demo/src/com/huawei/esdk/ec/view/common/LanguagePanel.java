package com.huawei.esdk.ec.view.common;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.huawei.esdk.ec.demo.Runme;
import com.huawei.esdk.ec.utils.LanguageUtils;

public class LanguagePanel extends JPanel
{
	private static final Logger LOGGER = LogManager.getLogger(LanguagePanel.class);

	private static final long serialVersionUID = -9160993073637348555L;
	
	private JRadioButton chinese = new JRadioButton("简体中文");
	
	private JRadioButton english = new JRadioButton("English");
	
	private JButton okButton = new MyButton("OK");
	
	public LanguagePanel() 
	{
		add(getPanels());
		init();
	}

	private void init() 
	{
		chinese.setSelected(true);
		this.okButton.addMouseListener(new MouseAdapter()
        {
            
			public void mouseClicked(MouseEvent e)
            {
				if (chinese.isSelected()) 
				{
					//数字0代表中文
					LanguageUtils.setIndex(0);
				}else 
				{
					//数字1代表英文
					LanguageUtils.setIndex(1);
				}
               
                Runnable runnable = new Runnable()
	            {
	            	@Override
	            	public void run()
	            	{
	            		new Runme();
	            		
	            		Runme.getFrames()[0].setVisible(false);
	            	}
	            };
	            Future<?> future = Executors.newSingleThreadExecutor().submit(runnable);
	            if(future.isDone()) 
	            {
	            	LOGGER.info("future.isDone() is true");
	            }
            }
        });
	}
		

	private JPanel getPanels() 
	{
		GridBagLayout gridbag = new GridBagLayout();
	    GridBagConstraints c = new GridBagConstraints();
	    JPanel panel = new JPanel(gridbag);
	    
	    buildPanel(panel, gridbag, c, new JComponent[] {chinese}, 0, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {english}, 1, 0, 10, 5, 1, 1);
	    buildPanel(panel, gridbag, c, new JComponent[] {okButton}, 2, 0, 10, 5, 1, 1);
	    ButtonGroup buttons = new ButtonGroup();
	    buttons.add(chinese);
	    buttons.add(english);
	    
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
}
