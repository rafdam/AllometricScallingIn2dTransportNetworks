package TreeModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

import GUI.BasicFrame;
import GUI.ChartPointsTable;

public class IndexCalculus {
	private ArrayList<Double> logLVals;
	private ArrayList<Double> logCVals;
	private ArrayList<Integer> LVals;
	private ArrayList<Integer> CVals;
	private double aIndex;
	private double bIndex;
	public IndexCalculus(int startL, double prob, int neighbours, int jump, int jumpCount, int piece){
		logLVals = new ArrayList<Double>();
		logCVals = new ArrayList<Double>();
		LVals = new ArrayList<Integer>();
		CVals = new ArrayList<Integer>();
		int endL = startL + (jump * jumpCount);
		for (int ii = startL; ii < endL ; ii = ii+jump){
			TreeMap tmpTreeMap = new TreeMap(ii, prob, neighbours);
			MinimalSpanningTree tmpSpanTree = new MinimalSpanningTree(tmpTreeMap.getNetwork(), (int)(ii * ii  * 0.7 / (piece + 1)));
			logLVals.add(2 * Math.log10(ii));
			logCVals.add((Math.log10(tmpSpanTree.MinimalRequiredAmount())));
			LVals.add(ii);
			CVals.add(tmpSpanTree.MinimalRequiredAmount());
			DecimalFormat df = new DecimalFormat(".##");
			BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable();
			//ChartPointsTable.addRow(ii, df.format(2* Math.log10(ii)), tmpSpanTree.MinimalRequiredAmount(), df.format(Math.log10(tmpSpanTree.MinimalRequiredAmount())), tmpTreeMap.getMNTime(), tmpSpanTree.getMSTTime());
			BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addRow(ii, df.format(2* Math.log10(ii)), 
					tmpSpanTree.MinimalRequiredAmount(), 
					df.format(Math.log10(tmpSpanTree.MinimalRequiredAmount())), 
					tmpTreeMap.getMNTime(), tmpSpanTree.getMSTTime());
			BasicFrame.getPane().getCountTab().getChart().addPointsToChart(2*Math.log10(ii), Math.log10(tmpSpanTree.MinimalRequiredAmount()));
			if (ii > 2){
				calc();
				BasicFrame.getPane().getCountTab().getChart().refreshLinePlot(logLVals, aIndex, bIndex);
			}
		}
		
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
		double size = logLVals.size();
		for (int jj = 0; jj < size; jj++){
			averageL += logLVals.get(jj);
			averageC += logCVals.get(jj);
			//System.out.println("LOG L = " + logLVals.get(jj));
			//ystem.out.println("LOG C = " + logCVals.get(jj));
		}
		averageL = averageL / size;
		averageC = averageC / size;
		for (int ii = 0; ii < size; ii++){
			xysum += (logLVals.get(ii) - averageL)*(logCVals.get(ii) - averageC);
			xyQuadSum += Math.pow(logLVals.get(ii) - averageL, 2);
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
