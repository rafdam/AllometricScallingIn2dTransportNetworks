package GUI;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class CountResultsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ChartPointsPanel chartPoints;
	AllometricIndexPanel indexPanel;
	AllometricIndexPanel singleIndexPanel;
	SingleNetworkChartPointsPan singleChart;
	public CountResultsPanel() {
		setLayout(new MigLayout());
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		chartPoints = new ChartPointsPanel();
		indexPanel = new AllometricIndexPanel();
		singleChart = new SingleNetworkChartPointsPan();
		singleIndexPanel = new AllometricIndexPanel();
		add(chartPoints, "width 100%, height 45%, wrap");
		add(indexPanel, "width 100%, height 5%, wrap");
		add(singleChart, "width 100%, height 45%, wrap");
		add(singleIndexPanel, "width 100%, height 5%");
		
		
	}
	
	public AllometricIndexPanel getIndexPanel(){
		return indexPanel;
	}
	
	public ChartPointsPanel getChartTable(){
		return chartPoints;
	}
	
	public SingleNetworkChartPointsPan getSingleNetworkChartTable(){
		return singleChart;
	}
	public AllometricIndexPanel getSingleIndexPanel(){
		return singleIndexPanel;
	}
}
