package TreeModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

import GUI.BasicFrame;

public class IndexCalculus extends Thread{
	private Thread t;
	private ArrayList<Double> logLVals;
	private ArrayList<Double> logCVals;
	private ArrayList<Integer> LVals;
	private ArrayList<Integer> CVals;
	private double aIndex;
	private double bIndex;
	private int startLVal, kNeighbours, jumpVal, jumps, pieceOne;
	private double probability;
	public IndexCalculus(int startL, double prob, int neighbours, int jump, int jumpCount, int piece){
		startLVal = startL;
		kNeighbours = neighbours;
		jumpVal = jump;
		jumps = jumpCount;
		pieceOne = piece;
		probability = prob;
		logLVals = new ArrayList<Double>();
		logCVals = new ArrayList<Double>();
		LVals = new ArrayList<Integer>();
		CVals = new ArrayList<Integer>();
	}
	public IndexCalculus(){
	}
	
	
	public void run(){
		int endL = startLVal + (jumpVal * jumps);
		for (int ii = startLVal; ii < endL ; ii = ii+jumpVal){
			TreeMap tmpTreeMap = null;
			tmpTreeMap = new TreeMap(ii, probability, kNeighbours);
			pieceOne = (int)tmpTreeMap.getNetwork().size()/2;
			MinimalSpanningTree tmpSpanTree = new MinimalSpanningTree(tmpTreeMap.getNetwork(), pieceOne, false);
			BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogLVals(2 * Math.log10(ii));//logLVals.add(2 * Math.log10(ii));
			BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogCVals((Math.log10(tmpSpanTree.MinimalRequiredAmount())));
			BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLVals(ii);
			BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addCVals(tmpSpanTree.MinimalRequiredAmount());
			DecimalFormat df = new DecimalFormat(".##");
			//BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable();
			BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addRow(ii, df.format(2* Math.log10(ii)), 
					tmpSpanTree.MinimalRequiredAmount(), 
					df.format(Math.log10(tmpSpanTree.MinimalRequiredAmount())), 
					tmpTreeMap.getMNTime(), tmpSpanTree.getMSTTime());
			BasicFrame.getPane().getCountTab().getChart().addPointsToChart(2*Math.log10(ii), Math.log10(tmpSpanTree.MinimalRequiredAmount()));
			if (BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals().size() > 2){
				calc();
				BasicFrame.getPane().getCountTab().getChart().refreshLinePlot(BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals()
						, aIndex, bIndex);
			}
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().addHistData(new HistoricalCalcs(
																							tmpTreeMap.getNetwork(),
																							tmpTreeMap.getEdges(),
																							tmpSpanTree.getEdges()
																							));
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().addRow(BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().size(),
																						tmpTreeMap.getSize(),
																						tmpTreeMap.getNetwork().size());
		}
	}
	
	public void start(){
		t = new Thread(this);
		t.start();
	}
	
	public ArrayList<Double> getLogLVals(){
		return logLVals;
	}
	
	public ArrayList<Double> getLogCVals(){
		return logCVals;
	}
	
	public ArrayList<Integer> getLVals(){
		return LVals;
	}
	
	public ArrayList<Integer> getCVals(){
		return CVals;
	}
	
	public double getIndex(){
		return aIndex;
	}
	
	public double calc(){
		double averageL = 0;
		double averageC = 0;
		double xysum = 0;
		double xyQuadSum = 0;
		double size = BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals().size();
		for (int jj = 0; jj < size; jj++){
			averageL += BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals().get(jj);
			averageC += BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogCVals().get(jj);
		}
		averageL = averageL / size;
		averageC = averageC / size;
		for (int ii = 0; ii < size; ii++){
			xysum += (BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals().get(ii) - averageL)*
					(BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogCVals().get(ii) - averageC);
			xyQuadSum += Math.pow(BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals().get(ii) - averageL, 2);
		}
		//System.out.println("średnie 3*Log(L) = " + averageL);
		//System.out.println("średnie Log(C) = " + averageC);
		aIndex = xysum/xyQuadSum;
		bIndex = averageC - (aIndex * averageL);
		DecimalFormat df2 = new DecimalFormat(".##");
		BasicFrame.getPane().getCountTab().getResults().getIndexPanel().getLabel().setString(df2.format(aIndex));
		return aIndex;
	}
}
