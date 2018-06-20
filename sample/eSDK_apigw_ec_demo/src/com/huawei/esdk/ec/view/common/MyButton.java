package com.huawei.esdk.ec.view.common;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.LineMetrics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class MyButton extends JButton
{
    
    private static final long serialVersionUID = -5091102180245292855L;
    
    private static final int NORMAL = 0;
    
    private static final int FOCUSED = 1;
    
    private static final int PRESSED = 2;
    
    private static final int RELEASED = 3;
    
    private String text;
    
    private int state = NORMAL;
    
    Shape shape;
    
    public MyButton(String text)
    {
        
        super(text);
        this.text = text;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
    }
    
    //x表示横向像素，y表示纵向像素，用于设置按钮大小
    //x indicates a horizontal pixel and y indicates a vertical pixel for setting the button size
    public MyButton(String text,int x,int y)
    {
        
        super(text);
        this.text = text;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setPreferredSize(new Dimension(x,y));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        
        if (state == NORMAL)
        {
            Color[] colors = {new Color(255, 255, 255, 0), new Color(150, 150, 150, 255)};
            paintButton(g, colors);
        }
        else if (state == FOCUSED)
        {
            Color[] colors = {new Color(255, 255, 255, 0), new Color(12, 150, 150, 255)};
            paintButton(g, colors);
        }
        else if (state == PRESSED)
        {
            Color[] colors = {new Color(255, 255, 255, 0), new Color(150, 12, 150, 255)};
            paintButton(g, colors);
        }
        else if (state == RELEASED)
        {
            Color[] colors = {new Color(255, 255, 255, 0), new Color(150, 150, 12, 255)};
            paintButton(g, colors);
        }
        
        addMouseListener(new MouseAdapter()
        {
            
            @Override
            public void mouseEntered(MouseEvent e)
            {
                state = FOCUSED;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e)
            {
                state = NORMAL;
                repaint();
            }
            
            @Override
            public void mousePressed(MouseEvent e)
            {
                state = PRESSED;
                repaint();
            }
            
            @Override
            public void mouseReleased(MouseEvent e)
            {
                state = RELEASED;
                repaint();
            }
            
        });
        
    }
    
    private void paintButton(Graphics g, Color[] colors)
    {
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = this.getWidth();
        int height = this.getHeight();
        
        Point2D center = new Point2D.Float((float)width / 2, (float)height / 2);
        float radius = (float)width / 2;
        float[] dist = {0.0f, 0.8f};
        RadialGradientPaint paint = new RadialGradientPaint(center, radius, dist, colors);
        g2.setPaint(paint);
        shape = new RoundRectangle2D.Double(0, 0, width, height, height, height);
        g2.fill(shape);
        
        Font defaultFont = getFont();
        g2.setFont(defaultFont);
        g2.setColor(Color.BLACK);
        if(null != defaultFont) 
        {
        	Rectangle2D rect = defaultFont.getStringBounds(text, g2.getFontRenderContext());
        	LineMetrics lineMetrics = defaultFont.getLineMetrics(text, g2.getFontRenderContext());
        	g2.drawString(text,
        			(float)(width / (float)2 - rect.getWidth() / (float)2),
        			((height / (float)2)
        					+ ((lineMetrics.getAscent() + lineMetrics.getDescent()) / 2 - lineMetrics.getDescent())));
        }
                
    }
        
}
