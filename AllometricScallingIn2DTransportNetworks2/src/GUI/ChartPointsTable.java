package GUI;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ChartPointsTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	//static ChargesList StationaryChargesList = new ChargesList();
	public ChartPointsTable() {
		Object[] columns = {"L","2Log(L)","C","Log(C)", "MNT time", "MST time"}; 
	    model = new DefaultTableModel(); 
	    model.setColumnIdentifiers(columns);
	    //model.addRow(new Object[]{1,2,3,4});
	    setModel(model);						//Setting default model of table
	    setDefaultEditor(Object.class, null);
	}
	public void addRow(int a, String b, int c, String d, String MNTt, String MSTt){
		model.addRow(new Object[]{a,b,c,d, MNTt, MSTt});
	}
	public void clear(){
		model.setRowCount(0);
	}
}
