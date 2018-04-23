package GUI;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import TreeModel.HubList;
import TreeModel.MinimalSpanningTree;
import net.miginfocom.swing.MigLayout;

public class ChartPointsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ChartPointsTable table;
	public ChartPointsPanel() {
		JLabel title = new JLabel("<html> <b>Table with data gathered by simulating all networks");
		setLayout(new MigLayout("", "[] []","[]"));
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		table = new ChartPointsTable();	
		add(table, "width 100%, height 100%");
		JScrollPane pane = new JScrollPane(table);  // Scroll for table of charges
		add(title, "width 100%, height 5%, wrap");
		add(pane, "width 100%, height 100%");
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(table.getSelectedRow() > -1) {
					try{
						Object[] options = {"Re-calc allometric index for chosen network", "None"};
            			JFrame frame = new JFrame();
            			int decision = JOptionPane.showOptionDialog(frame, "What would you like to do?",
            					"Select your action", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
						if(decision == 0){
							BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().clear();
							BasicFrame.getPane().getCountTab().getChart().clearSingleChart();
							double prob = BasicFrame.getPane().getSimTab().getConsolePanel().getProbability();
							DecimalFormat df = new DecimalFormat(".##");
							MinimalSpanningTree tmpSpanTree;
							HubList list = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData()
									.dataBase.get(table.getSelectedRow()).getVerticleList();
							for(int ii = 0; ii < list.size(); ii++){
								for (int jj = 0; jj < list.size(); jj++){
									list.get(jj).setLevel(0);
								}
								if(list.get(ii).getMinimalNeighbourIndexesList().size() < 1){
									continue;
								}
								tmpSpanTree = new MinimalSpanningTree(list, ii, true);
								if(tmpSpanTree.MinimalRequiredAmount() == 0){
									continue;
								}
								
								BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().addRow(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob),
										df.format(2* Math.log10(Math.sqrt(tmpSpanTree.getSubNetwork().size() / prob))), 
										tmpSpanTree.MinimalRequiredAmount(), 
										df.format(Math.log10(tmpSpanTree.MinimalRequiredAmount())), 
										(String)BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().getValueAt(table.getSelectedRow(), 4),
										tmpSpanTree.getMSTTime());
								BasicFrame.getPane().getCountTab().getChart().addPointsToSingleChart(2* Math.log10(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob)),
										Math.log10(tmpSpanTree.MinimalRequiredAmount()));
								BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().addLogLVals(2 * Math.log10(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob)));//logLVals.add(2 * Math.log10(ii));
								BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().addLogCVals((Math.log10(tmpSpanTree.MinimalRequiredAmount())));
								BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().addLVals(Math.sqrt((tmpSpanTree.getSubNetwork().size()) / prob));
								BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().addCVals(tmpSpanTree.MinimalRequiredAmount());
							
							}
							double averageL = 0;
							double averageC = 0;
							double xysum = 0;
							double xyQuadSum = 0;
							double size = BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().getLogLVals().size();
							for (int jj = 0; jj < size; jj++){
								averageL += BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().getLogLVals().get(jj);
								averageC += BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().getLogCVals().get(jj);
							}
							averageL = averageL / size;
							averageC = averageC / size;
							for (int ii = 0; ii < size; ii++){
								xysum += (BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().getLogLVals().get(ii) - averageL)*
										(BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().getLogCVals().get(ii) - averageC);
								xyQuadSum += Math.pow(BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().getLogLVals().get(ii) - averageL, 2);
							}
							double aIndex = xysum/xyQuadSum;
							double bIndex = averageC - (aIndex * averageL);
							DecimalFormat df2 = new DecimalFormat(".##");
							BasicFrame.getPane().getCountTab().getResults().getSingleIndexPanel().getLabel().setString(df2.format(aIndex));
							BasicFrame.getPane().getCountTab().getChart().refreshSingleLinePlot(
									BasicFrame.getPane().getCountTab().getResults().getSingleNetworkChartTable().getTable().getLogLVals()
									, aIndex, bIndex);
						}
						else{
							//just do nothing
						}
					}
					catch(ArrayIndexOutOfBoundsException ee){
						//none row selected
					}
				}
				else{
					
				}
				
			}
		
		});
	}
	
	public ChartPointsTable getTable(){
		return table;
	}
}
