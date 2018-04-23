package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class ColorLegend extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ColorLegend() {
		setLayout(new MigLayout());
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		JLabel title = new JLabel("<html> <b> Legend of edges weights");
		JLabel color_1 = new JLabel("<html> <b>  1");
		JLabel color_2 = new JLabel("<html> <b>  2");
		JLabel color_3 = new JLabel("<html> <b>  3");
		JLabel color_4 = new JLabel("<html> <b>  4");
		JLabel color_5 = new JLabel("<html> <b>  5");
		JLabel color_6 = new JLabel("<html> <b>  6");
		JLabel color_7 = new JLabel("<html> <b>  7");
		JLabel color_8 = new JLabel("<html> <b>  8");
		JLabel color_9 = new JLabel("<html> <b>  9");
		JLabel color_10 = new JLabel("<html> <b> (10;19)");
		JLabel color_11 = new JLabel("<html> <b>  20;29)");
		JLabel color_12 = new JLabel("<html> <b> (30;39)");
		JLabel color_13 = new JLabel("<html> <b> (40;49)");
		JLabel color_14 = new JLabel("<html> <b> (50;59)");
		JLabel color_15 = new JLabel("<html> <b> (60;69)");
		JLabel color_16 = new JLabel("<html> <b> > 70");
		add(title, "wrap");
		//add(color_1, "height 6.25%, wrap");
		//add(color_2, "height 6.25%, wrap");
		//add(color_3, "height 6.25%, wrap");
		//add(color_4, "height 6.25%, wrap");
		//add(color_5, "height 6.25%, wrap");
		//add(color_6, "height 6.25%, wrap");
		//add(color_7, "height 6.25%, wrap");
		//add(color_8, "height 6.25%, wrap");
		//add(color_9, "height 6.25%, wrap");
		//add(color_10, "height 6.25%, wrap");
		//add(color_11, "height 6.25%, wrap");
		//add(color_12, "height 6.25%, wrap");
		//add(color_13, "height 6.25%, wrap");
		//add(color_14, "height 6.25%, wrap");
		//add(color_15, "height 6.25%, wrap");
		//add(color_16, "height 6.25%, wrap");
		
		
	}
	protected void paintComponent( Graphics g ) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = (int) getWidth();
        int w1 = 0;//(int)2*(getWidth()/3);
        int h = getHeight();
        int h_absolute = (int)(h/18);
        Color color_1 = Color.RED;
        Color color_2 = Color.GREEN;
        Color color_3 = Color.BLUE;
        Color color_4 = Color.WHITE;
        Color color_5 = Color.CYAN;
        Color color_6 = Color.GRAY;
        Color color_7 = Color.YELLOW;
        Color color_8 = Color.ORANGE;
        Color color_9 = Color.PINK;
        g2d.setColor(color_1);
        //g2d.drawRect(c, d, a, b);
        g2d.fillRect(w1, 2*h_absolute, w, 3*h_absolute);
        g2d.setColor(color_2);
        g2d.fillRect(w1, 3*h_absolute, w, 4*h_absolute);
        g2d.setColor(color_3);
        g2d.fillRect(w1, 4*h_absolute, w, 5*h_absolute);
        g2d.setColor(color_4);
        g2d.fillRect(w1, 5*h_absolute, w, 6*h_absolute);
        g2d.setColor(color_5);
        g2d.fillRect(w1, 6*h_absolute, w, 7*h_absolute);
        g2d.setColor(color_6);
        g2d.fillRect(w1, 7*h_absolute, w, 8*h_absolute);
        g2d.setColor(color_7);
        g2d.fillRect(w1, 8*h_absolute, w, 9*h_absolute);
        g2d.setColor(color_8);
        g2d.fillRect(w1, 9*h_absolute, w, 10*h_absolute);
        g2d.setColor(color_9);
        g2d.fillRect(w1, 10*h_absolute, w, 11*h_absolute);
        Color color_10 = new Color(255 - (int)(255 / 20) * 10, 125, (int)(255 / 20) * 10);
        Color color_19 = new Color(255 - (int)(255 / 20) * 19, 125, (int)(255 / 20) * 19);
        GradientPaint gp = new GradientPaint(0, 0, color_10, w, 0, color_19);
        g2d.setPaint(gp);
        g2d.fillRect(w1, 11*h_absolute, w, 12*h_absolute);
        
        Color color_20 = new Color(125, ((int)(255 / 30) * 20), 255 - (int)(255 / 30) * 20);
        Color color_29 = new Color(125, ((int)(255 / 30) * 29), 255 - (int)(255 / 30) * 29);
        gp = new GradientPaint(0, 0, color_20, w, 0, color_29);
        g2d.setPaint(gp);
        g2d.fillRect(w1, 12*h_absolute, w, 13*h_absolute);
        
        Color color_30 = new Color(255 - (int)(255 / 40) * 30 ,0 , (int)(255 / 40) * 30);
        Color color_39 = new Color(255 - (int)(255 / 40) * 39 ,0 , (int)(255 / 40) * 39);
        gp = new GradientPaint(0, 0, color_30, w, 0, color_39);
        g2d.setPaint(gp);
        g2d.fillRect(w1, 13*h_absolute, w, 14*h_absolute);
        
        Color color_40 = new Color(255 - (int)(255 / 50) * 40 ,100 , 255);
        Color color_49 = new Color(255 - (int)(255 / 50) * 49 ,100 , 255);
        gp = new GradientPaint(0, 0, color_40, w, 0, color_49);
        g2d.setPaint(gp);
        g2d.fillRect(w1, 14*h_absolute, w, 15*h_absolute);
        
        Color color_50 = new Color(255 - (int)(255 / 60) * 50 , (int)(255 / 60) * 50, (int)(255 / 60) * 50);
        Color color_59 = new Color(255 - (int)(255 / 60) * 59 , (int)(255 / 60) * 59, (int)(255 / 60) * 59);
        gp = new GradientPaint(0, 0, color_50, w, 0, color_59);
        g2d.setPaint(gp);
        g2d.fillRect(w1, 15*h_absolute, w, 16*h_absolute);
        
        Color color_60 = new Color(255, 255 - (int)(255 / 70) * 60 , (int)(255 / 70) * 60);
        Color color_69 = new Color(255, 255 - (int)(255 / 70) * 69 , (int)(255 / 70) * 69);
        gp = new GradientPaint(0, 0, color_60, w, 0, color_69);
        g2d.setPaint(gp);
        g2d.fillRect(w1, 16*h_absolute, w, 17*h_absolute);
        
        Color color_else = new Color(200, 255, 200);
        g2d.setColor(color_else);
        g2d.fillRect(w1, 17*h_absolute, w, 18*h_absolute);
        
        g2d.setColor(Color.BLACK);
        g.setFont(new Font("default", Font.BOLD, 12));
        g2d.drawString("1", (w+w1)/2, (3*h_absolute - (3*h_absolute - 2*h_absolute)/4));
        g2d.drawString("2", (w+w1)/2, (4*h_absolute - (4*h_absolute - 3*h_absolute)/4));
        g2d.drawString("3", (w+w1)/2, (5*h_absolute - (5*h_absolute - 4*h_absolute)/4));
        g2d.drawString("4", (w+w1)/2, (6*h_absolute - (6*h_absolute - 5*h_absolute)/4));
        g2d.drawString("5", (w+w1)/2, (7*h_absolute - (7*h_absolute - 6*h_absolute)/4));
        g2d.drawString("6", (w+w1)/2, (8*h_absolute - (8*h_absolute - 7*h_absolute)/4));
        g2d.drawString("7", (w+w1)/2, (9*h_absolute - (9*h_absolute - 8*h_absolute)/4));
        g2d.drawString("8", (w+w1)/2, (10*h_absolute - (10*h_absolute - 9*h_absolute)/4));
        g2d.drawString("9", (w+w1)/2, (11*h_absolute - (11*h_absolute - 10*h_absolute)/4));
        g2d.drawString("10-19", (w+w1)/2, (12*h_absolute - (12*h_absolute - 11*h_absolute)/4));
        g2d.drawString("20-29", (w+w1)/2, (13*h_absolute - (13*h_absolute - 12*h_absolute)/4));
        g2d.drawString("30-39", (w+w1)/2, (14*h_absolute - (14*h_absolute - 13*h_absolute)/4));
        g2d.drawString("40-49", (w+w1)/2, (15*h_absolute - (15*h_absolute - 14*h_absolute)/4));
        g2d.drawString("50-59", (w+w1)/2, (16*h_absolute - (16*h_absolute - 15*h_absolute)/4));
        g2d.drawString("60-69", (w+w1)/2, (17*h_absolute - (17*h_absolute - 16*h_absolute)/4));
        g2d.drawString(">70", (w+w1)/2, (18*h_absolute - (18*h_absolute - 17*h_absolute)/200));
        
    }
}
