package GUI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import net.miginfocom.swing.MigLayout;

public class CountChartPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	XYSeries dataSet;
	XYSeries linePlot;
	XYSeriesCollection xySeriesCollection;
	XYDataset xyDataset;
	ChartPanel chartPanel;
	JFreeChart scatterPlot;
	JFreeChart approxPlot;
	public CountChartPanel() {
		setLayout(new MigLayout());
		Color color = new Color(232,232,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
	
		chartPanel = new ChartPanel(null);
		add(chartPanel, "width 100%, height 100%");   
		linePlot = new XYSeries("Linear Regression");
	    dataSet= new XYSeries("Log/Log Allometric Scalling");
	   	xySeriesCollection = new XYSeriesCollection(dataSet);
	   	xySeriesCollection.addSeries(linePlot);
	   	xyDataset = xySeriesCollection;
	   	scatterPlot = ChartFactory.createScatterPlot
	        ("Allometric Scalling in Distribution Networks",  // Title
	          "2Log(L)",           // X-Axis label
	          "Log(C)",           // Y-Axis label
	          xyDataset,          // Dataset
	          PlotOrientation.VERTICAL,        //Plot orientation
	          true,                //show legend
	          true,                // Show tooltips
	          false               //url show
	         );
	   	//scatterPlot.getDataset.setRenderer(1, null);
	   	chartPanel.setChart(scatterPlot);
	}
	
	public XYSeries getDataSet(){
		return dataSet;
	}
	
	public XYSeriesCollection GetSeriesCollection(){
		return xySeriesCollection;
	}
	
	public ChartPanel getChartPanel(){
		return chartPanel;
	}
	
	public XYSeries getLinePlot(){
		return linePlot;
	}
	
	public void addPointsToChart(double x, double y){
		dataSet.add(x,y);
		xySeriesCollection = new XYSeriesCollection(dataSet);
		//linePlot.add(y,x);
		//xySeriesCollection.addSeries(linePlot);
		//xyDataset = xySeriesCollection;
		scatterPlot.getXYPlot().setDataset(xyDataset);
		scatterPlot.getXYPlot().getRangeAxis().setAutoRange(true);
	}
	
	public void clearChart(){
		dataSet.clear();
		linePlot.clear();
	}
	
	public void refreshLinePlot(ArrayList<Double> list, double a, double b){
		double xVal = 0;
		double yVal = 0;
		linePlot.clear();
		for (int jj = 0; jj < list.size(); jj++){
			xVal = list.get(jj);
			yVal = xVal * a + b;
			linePlot.add(xVal ,yVal);
		}
		
		//scatterPlot.getXYPlot().setDataset(new XYSeriesCollection(linePlot));
	}
	

}
