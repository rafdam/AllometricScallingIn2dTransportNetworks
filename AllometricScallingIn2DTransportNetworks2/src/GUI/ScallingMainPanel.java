package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class ScallingMainPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CountChartPanel chart;
	CountResultsPanel results;
	public ScallingMainPanel() {
		setLayout(new MigLayout());
		setBorder(new LineBorder(Color.BLACK, 2));
		chart = new CountChartPanel();
		results = new CountResultsPanel();
		add(chart, "width 60%, height 100%");
		add(results, "width 40%, height 100%");
	}
	
	public CountResultsPanel getResults(){
		return results;
	}
	
	public CountChartPanel getChart(){
		return chart;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}
}
