package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ChartPointsTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private ArrayList<Double> logLVals;
	private ArrayList<Double> logCVals;
	private ArrayList<Double> LVals;
	private ArrayList<Integer> CVals;
	//static ChargesList StationaryChargesList = new ChargesList();
	public ChartPointsTable() {
		logLVals = new ArrayList<Double>();
		logCVals = new ArrayList<Double>();
		LVals = new ArrayList<Double>();
		CVals = new ArrayList<Integer>();
		Object[] columns = {"L","2Log(L)","C","Log(C)", "MNT time", "MST time"}; 
	    model = new DefaultTableModel(); 
	    model.setColumnIdentifiers(columns);
	    //model.addRow(new Object[]{1,2,3,4});
	    setModel(model);						//Setting default model of table
	    setDefaultEditor(Object.class, null);
	}
	public void addRow(double a, String b, int c, String d, String MNTt, String MSTt){
		model.addRow(new Object[]{a,b,c,d, MNTt, MSTt});
	}
	public void clear(){
		model.setRowCount(0);
		logLVals.clear();
		logCVals.clear();
		LVals.clear();
		CVals.clear();
	}
	
	public ArrayList<Double> getLogLVals(){
		return logLVals;
	}
	public ArrayList<Double> getLogCVals(){
		return logCVals;
	}
	public ArrayList<Double> getLVals(){
		return LVals;
	}
	public ArrayList<Integer> getCVals(){
		return CVals;
	}
	public void addLogLVals(double logL){
		logLVals.add(logL);
	}
	public void addLogCVals(double logC){
		logCVals.add(logC);
	}
	public void addLVals(double L){
		LVals.add(L);
	}	
	public void addCVals(int C){
		CVals.add(C);
	}
}

