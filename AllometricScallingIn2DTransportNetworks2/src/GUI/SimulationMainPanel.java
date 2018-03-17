package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;


public class SimulationMainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	VisualizationMainPanel visu;
	ConsoleVisPanel console;
	public SimulationMainPanel() {
		setLayout(new MigLayout());
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));	
		visu = new VisualizationMainPanel();
		console = new ConsoleVisPanel();
		add(visu, "width 100%, height 90%, wrap");
		add(console, "width 100%, height 10%");
	}
	
	public VisualizationMainPanel getVisPanel(){
		return visu;
	}
	
	public ConsoleVisPanel getConsolePanel(){
		return console;
	}
}
