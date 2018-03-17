package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

public class TabbedPane extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel simulationTab, chartAndCountTab;	
	SimulationMainPanel simulationPanel;
	ScallingMainPanel calculusPanel;
	
	public TabbedPane() {
		chartAndCountTab = new JPanel();
		addTab("<html> <b>Allometric index", chartAndCountTab);
		simulationTab = new JPanel();		
		addTab("<html> <b>Visualization", simulationTab);	
		
		
		simulationPanel = new SimulationMainPanel();
		simulationTab.setLayout(new MigLayout());
		simulationTab.add(simulationPanel, "width 100%, height 100%");
		
		calculusPanel = new ScallingMainPanel();
		chartAndCountTab.setLayout(new MigLayout());
		chartAndCountTab.add(calculusPanel, "width 100%, height 100%");
	}
	
	public ScallingMainPanel getCountTab(){
		return calculusPanel;
	}
}
