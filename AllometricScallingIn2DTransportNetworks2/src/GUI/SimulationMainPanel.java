package GUI;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import TreeModel.SimulationRawDataPanel;
import net.miginfocom.swing.MigLayout;


public class SimulationMainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	VisualizationMainPanel visu;
	ConsoleVisPanel console;
	SimulationRawDataPanel rawData;
	public SimulationMainPanel() {
		setLayout(new MigLayout("", "[] []","[]"));
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));	
		visu = new VisualizationMainPanel();
		console = new ConsoleVisPanel();
		rawData = new SimulationRawDataPanel();
		JLabel author = new JLabel("Authors:");
		add(visu, "width 80%, height 90%");
		add(rawData, "width 20%, height 90%, span");
		add(console, "width 80%, height 10%");
		add(author);
	}
	
	public VisualizationMainPanel getVisPanel(){
		return visu;
	}
	
	public ConsoleVisPanel getConsolePanel(){
		return console;
	}
	
	public SimulationRawDataPanel getRawDataPanel(){
		return rawData;
	}
}
