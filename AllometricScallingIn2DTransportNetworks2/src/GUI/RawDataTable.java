package GUI;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import TreeModel.HistoricalCalcs;

public class RawDataTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	ArrayList<HistoricalCalcs> dataBase;
	public RawDataTable() {
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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

}
