package GUI;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import TreeModel.EdgeList;
import TreeModel.HistoricalCalcs;
import TreeModel.HubList;
import TreeModel.MinimalSpanningTree;

public class RawDataTable extends JTable {

	/**
	 * 
	 */
	HistoricalCalcs networkToDraw;
	public int selectedIndex;
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	ArrayList<HistoricalCalcs> dataBase;
	public RawDataTable() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		networkToDraw = new HistoricalCalcs();
		selectedIndex = 0;
		dataBase = new ArrayList<HistoricalCalcs>();
		Object[] columns = {"n.o.p.","L","N"};
	    model = new DefaultTableModel(); 
	    model.setColumnIdentifiers(columns);
	    setModel(model);						//Setting default model of table
	    setDefaultEditor(Object.class, null);
	}
	public void addRow(int a, int b, int c){
		model.addRow(new Object[]{a,b,c});
	}
	public void addHistData(HistoricalCalcs data){
		dataBase.add(data);
	}
	
	public ArrayList<HistoricalCalcs> getDataBase(){
		return dataBase;
	}
	
	public HistoricalCalcs getNetworkToDraw(){
		return networkToDraw;
	}
	
	public int recalcSubNetwork(int subHub){
		MinimalSpanningTree tmpSpanTree;
		HubList list = dataBase.get(selectedIndex).getVerticleList();
		list.get(subHub).setLevel(-1);
		if(list.get(subHub).getMinimalNeighbourIndexesList().size() < 1){
			return 0;
		}
		for (int ii = 0; ii < list.size(); ii++){
			list.get(ii).setLevel(0);
		}
		double prob = BasicFrame.getPane().getSimTab().getConsolePanel().getProbability();
		DecimalFormat df = new DecimalFormat(".##");
		tmpSpanTree = new MinimalSpanningTree(list, subHub, true, new EdgeList());
		model.addRow(new Object[]{dataBase.size() + 1, Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob), tmpSpanTree.getSubNetwork().size()});
		dataBase.add(new HistoricalCalcs(list, tmpSpanTree.getSubNetwork(), dataBase.get(selectedIndex).getMaximalNetworkEdgeList(), tmpSpanTree.getEdges(), dataBase.get(selectedIndex).getMaximalMinimalTree()));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addRow(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob),
				df.format(2* Math.log10(Math.sqrt(tmpSpanTree.getSubNetwork().size() / prob))), 
				tmpSpanTree.MinimalRequiredAmount(), 
				df.format(Math.log10(tmpSpanTree.MinimalRequiredAmount())), 
				(String)BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getValueAt(selectedIndex, 4),
				tmpSpanTree.getMSTTime());
		BasicFrame.getPane().getCountTab().getChart().addPointsToChart(2* Math.log10(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob)),
				Math.log10(tmpSpanTree.MinimalRequiredAmount()));
		if(BasicFrame.getPane().getSimTab().getRawDataPanel().drawSubNetworkSpecs.isSelected()){
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(list,
					dataBase.get(selectedIndex).getMaximalNetworkEdgeList(),
					tmpSpanTree.getEdges());
		}
		else{
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(tmpSpanTree.getSubNetwork(),
					dataBase.get(selectedIndex).getMaximalNetworkEdgeList(),
					tmpSpanTree.getEdges());
		}
			
		//BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(list,
		//		dataBase.get(selectedIndex).getMaximalNetworkEdgeList(),
		//		tmpSpanTree.getEdges());
		BasicFrame.getPane().getSimTab().repaint();
		setRowSelectionInterval(dataBase.size() - 1, dataBase.size() - 1);
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogLVals(2 * Math.log10(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob)));//logLVals.add(2 * Math.log10(ii));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogCVals((Math.log10(tmpSpanTree.MinimalRequiredAmount())));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLVals(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addCVals(tmpSpanTree.MinimalRequiredAmount());
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
		double aIndex = xysum/xyQuadSum;
		double bIndex = averageC - (aIndex * averageL);
		DecimalFormat df2 = new DecimalFormat(".##");
		BasicFrame.getPane().getCountTab().getResults().getIndexPanel().getLabel().setString(df2.format(aIndex));
		BasicFrame.getPane().getCountTab().getChart().refreshLinePlot(BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals()
				, aIndex, bIndex);
		return 1;
	}
	public void redrawMaximalNetwork(){
		HubList list = dataBase.get(selectedIndex).getVerticleList();
		BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(list,
				dataBase.get(selectedIndex).getMaximalNetworkEdgeList(),
				dataBase.get(selectedIndex).getMaximalMinimalTree());
	}
	
	public int recalcSubSubNetwork(int subHub){
		HubList list;
		MinimalSpanningTree tmpSpanTree;
		list = dataBase.get(selectedIndex).getVerticleList();
		list.get(subHub).setLevel(-1);
		if(list.get(subHub).getMinimalNeighbourIndexesList().size() < 1){
			return 0;
		}
		for (int ii = 0; ii < list.size(); ii++){
			list.get(ii).setLevel(0);
		}
		double prob = BasicFrame.getPane().getSimTab().getConsolePanel().getProbability();
		DecimalFormat df = new DecimalFormat(".##");
		tmpSpanTree = new MinimalSpanningTree(list, subHub, true, new EdgeList());
		model.addRow(new Object[]{dataBase.size()+1, Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob), tmpSpanTree.getSubNetwork().size()});
		dataBase.add(new HistoricalCalcs(dataBase.get(selectedIndex).getVerticleList(), tmpSpanTree.getSubNetwork(), dataBase.get(selectedIndex).getMaximalNetworkEdgeList() , tmpSpanTree.getEdges(), dataBase.get(selectedIndex).getMaximalMinimalTree()));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addRow(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob),
				df.format(2* Math.log10(Math.sqrt(tmpSpanTree.getSubNetwork().size() / prob))), 
				tmpSpanTree.MinimalRequiredAmount(), 
				df.format(Math.log10(tmpSpanTree.MinimalRequiredAmount())), 
				(String)BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getValueAt(selectedIndex, 4),
				tmpSpanTree.getMSTTime());
		BasicFrame.getPane().getCountTab().getChart().addPointsToChart(2* Math.log10(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob)),
				Math.log10(tmpSpanTree.MinimalRequiredAmount()));
		//BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(list, dataBase.get(selectedIndex).getMaximalNetworkEdgeList(), tmpSpanTree.getEdges());
		if(BasicFrame.getPane().getSimTab().getRawDataPanel().drawSubNetworkSpecs.isSelected()){
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(list,
					dataBase.get(selectedIndex).getMaximalNetworkEdgeList(),
					tmpSpanTree.getEdges());
		}
		else{
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(tmpSpanTree.getSubNetwork(),
					dataBase.get(selectedIndex).getMaximalNetworkEdgeList(),
					tmpSpanTree.getEdges());
		}
		BasicFrame.getPane().getSimTab().repaint();
		setRowSelectionInterval(0, dataBase.size() - 1);
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogLVals(2 * Math.log10(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob)));//logLVals.add(2 * Math.log10(ii));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogCVals((Math.log10(tmpSpanTree.MinimalRequiredAmount())));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLVals(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addCVals(tmpSpanTree.MinimalRequiredAmount());
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
		double aIndex = xysum/xyQuadSum;
		double bIndex = averageC - (aIndex * averageL);
		DecimalFormat df2 = new DecimalFormat(".##");
		BasicFrame.getPane().getCountTab().getResults().getIndexPanel().getLabel().setString(df2.format(aIndex));
		BasicFrame.getPane().getCountTab().getChart().refreshLinePlot(BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals()
				, aIndex, bIndex);
		return 1;
	}
	
	public void recalcMSTForSelectedNetwork(int startHub){
		HubList list = dataBase.get(selectedIndex).getVerticleList();
		for (int ii = 0; ii < list.size(); ii++){
			list.get(ii).setLevel(0);
		}
		DecimalFormat df = new DecimalFormat(".##");
		MinimalSpanningTree tmpSpanTree = new MinimalSpanningTree(list, startHub, false, new EdgeList());
		model.addRow(new Object[]{dataBase.size() + 1, model.getValueAt(selectedIndex, 1), list.size()});
		dataBase.add(new HistoricalCalcs(list, new HubList(), dataBase.get(selectedIndex).getMaximalNetworkEdgeList() , tmpSpanTree.getEdges(), dataBase.get(selectedIndex).getMinimalSpanningEdgeList()));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addRow((int)model.getValueAt(selectedIndex, 1),
				df.format(2* Math.log10((int)model.getValueAt(selectedIndex, 1))), 
				tmpSpanTree.MinimalRequiredAmount(), 
				df.format(Math.log10(tmpSpanTree.MinimalRequiredAmount())), 
				(String)BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getValueAt(selectedIndex, 4),
				tmpSpanTree.getMSTTime());
		BasicFrame.getPane().getCountTab().getChart().addPointsToChart(2*Math.log10((int)model.getValueAt(selectedIndex, 1)),
				Math.log10(tmpSpanTree.MinimalRequiredAmount()));
		//IndexCalculus index = new IndexCalculus();
		//index.calc();
		BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(list, dataBase.get(selectedIndex).getMaximalNetworkEdgeList(), tmpSpanTree.getEdges());
		BasicFrame.getPane().getSimTab().repaint();
		setRowSelectionInterval(0, dataBase.size() - 1);
		
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogLVals(2 * Math.log10((int)model.getValueAt(selectedIndex, 1)));//logLVals.add(2 * Math.log10(ii));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLogCVals((Math.log10(tmpSpanTree.MinimalRequiredAmount())));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addLVals((int)model.getValueAt(selectedIndex, 1));
		BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().addCVals(tmpSpanTree.MinimalRequiredAmount());
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
		double aIndex = xysum/xyQuadSum;
		double bIndex = averageC - (aIndex * averageL);
		DecimalFormat df2 = new DecimalFormat(".##");
		BasicFrame.getPane().getCountTab().getResults().getIndexPanel().getLabel().setString(df2.format(aIndex));
		BasicFrame.getPane().getCountTab().getChart().refreshLinePlot(BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getLogLVals()
				, aIndex, bIndex);
		//System.out.println(aIndex);
	}
	
	public void clear(){
		model.setRowCount(0);
		networkToDraw = new HistoricalCalcs();
		dataBase.clear();
	}

}
