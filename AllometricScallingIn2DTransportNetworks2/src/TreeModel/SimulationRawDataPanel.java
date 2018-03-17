package TreeModel;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import GUI.RawDataTable;
import net.miginfocom.swing.MigLayout;

public class SimulationRawDataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RawDataTable previousData;
	public SimulationRawDataPanel() {
		setLayout(new MigLayout());
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		previousData = new RawDataTable();
		add(previousData);
		JScrollPane pane = new JScrollPane(previousData);  // Scroll for table of charges
		add(pane, "height 80%");
	}
	
	public RawDataTable getPreviousData(){
		return previousData;
	}
}
