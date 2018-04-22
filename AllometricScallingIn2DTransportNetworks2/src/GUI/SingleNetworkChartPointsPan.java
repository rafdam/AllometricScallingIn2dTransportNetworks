package GUI;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class SingleNetworkChartPointsPan extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ChartPointsTable table;
	public SingleNetworkChartPointsPan() {
		JLabel title = new JLabel("<html> <b>Table with data gathered by resimulating 1 chosen network");
		setLayout(new MigLayout("", "[] []","[]"));
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		table = new ChartPointsTable();	
		add(table, "width 100%, height 100%");
		JScrollPane pane = new JScrollPane(table);  // Scroll for table of charges
		add(title, "width 100%, height 5%, wrap");
		add(pane, "width 100%, height 100%");
		
		
	}
	
	
	public ChartPointsTable getTable(){
		return table;
	}
}
