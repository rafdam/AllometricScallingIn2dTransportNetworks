package GUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class ChartPointsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ChartPointsTable table;
	public ChartPointsPanel() {
		setLayout(new MigLayout("", "[] []","[]"));
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		table = new ChartPointsTable();	
		add(table, "width 100%, height 100%");
		JScrollPane pane = new JScrollPane(table);  // Scroll for table of charges
		add(pane, "width 100%, height 100%");
	}
	
	public ChartPointsTable getTable(){
		return table;
	}
}
