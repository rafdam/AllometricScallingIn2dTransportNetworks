package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.miginfocom.swing.MigLayout;

public class SimulationRawDataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RawDataTable previousData;
	public JCheckBox drawSubNetworkSpecs;
	public SimulationRawDataPanel() {
		setLayout(new MigLayout());
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		previousData = new RawDataTable();
		add(previousData);
		JScrollPane pane = new JScrollPane(previousData);
		add(pane, "height 50%, wrap");
		JButton drawSpecifiedNetwork = new JButton("<html> <b>Draw Selected Network");
		drawSubNetworkSpecs = new JCheckBox("<html> <b> Draw SubNetwork with Maximal Network Hubs");
		add(drawSubNetworkSpecs, "wrap");
		ColorLegend colors = new ColorLegend();
		add(colors, "height 40%, width 100%");
		drawSubNetworkSpecs.setSelected(true);
		
		drawSpecifiedNetwork.setEnabled(false);
		previousData.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(previousData.getSelectedRow() > -1) {
					//drawSpecifiedNetwork.setEnabled(true);
					//BasicFrame.getPane().getSimTab().getConsolePanel().getRecalcBox().setEnabled(true);
					try{
						if(previousData.selectedIndex != previousData.getSelectedRow()){
							BasicFrame.getPane().getSimTab().getVisPanel().ClearClicked();
						}
						previousData.selectedIndex = previousData.getSelectedRow();
						
						if(drawSubNetworkSpecs.isSelected()){
							BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(
									previousData.getDataBase().get(previousData.getSelectedRow()).getVerticleList(),
									previousData.getDataBase().get(previousData.getSelectedRow()).getMaximalNetworkEdgeList(),
									previousData.getDataBase().get(previousData.getSelectedRow()).getMinimalSpanningEdgeList());
							BasicFrame.getPane().getSimTab().getVisPanel().repaint();
						}
						else{
							BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(
									previousData.getDataBase().get(previousData.getSelectedRow()).getSubNetwork(),
									previousData.getDataBase().get(previousData.getSelectedRow()).getMaximalNetworkEdgeList(),
									previousData.getDataBase().get(previousData.getSelectedRow()).getMinimalSpanningEdgeList());
							BasicFrame.getPane().getSimTab().getVisPanel().repaint();
						}
						BasicFrame.getPane().getSimTab().repaint();
						
					}
					catch(ArrayIndexOutOfBoundsException ee){
						JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"Select one of historical calcs");
					}
				}
				else{
					//drawSpecifiedNetwork.setEnabled(false);
					//BasicFrame.getPane().getSimTab().getConsolePanel().getRecalcBox().setEnabled(false);
					
				}
			}	
		});
		ActionListener drawPressed = new ActionListener(){
		
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(previousData.selectedIndex != previousData.getSelectedRow()){
						BasicFrame.getPane().getSimTab().getVisPanel().ClearClicked();
					}
					previousData.selectedIndex = previousData.getSelectedRow();
					
					if(drawSubNetworkSpecs.isSelected()){
						BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(
								previousData.getDataBase().get(previousData.getSelectedRow()).getVerticleList(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMaximalNetworkEdgeList(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMinimalSpanningEdgeList());
						BasicFrame.getPane().getSimTab().getVisPanel().repaint();
					}
					else{
						BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(
								previousData.getDataBase().get(previousData.getSelectedRow()).getSubNetwork(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMaximalNetworkEdgeList(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMinimalSpanningEdgeList());
						BasicFrame.getPane().getSimTab().getVisPanel().repaint();
					}
					BasicFrame.getPane().getSimTab().repaint();
					
				}
				catch(ArrayIndexOutOfBoundsException ee){
					JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"Select one of historical calcs");
				}
			}
		};
		drawSpecifiedNetwork.addActionListener(drawPressed);
		
		ActionListener networkSpecsChanged = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(previousData.getSelectedRow() > -1){
					if(drawSubNetworkSpecs.isSelected()){
						BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(
								previousData.getDataBase().get(previousData.getSelectedRow()).getVerticleList(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMaximalNetworkEdgeList(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMinimalSpanningEdgeList());
						BasicFrame.getPane().getSimTab().getVisPanel().repaint();
					}
					else{
						BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(
								previousData.getDataBase().get(previousData.getSelectedRow()).getSubNetwork(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMaximalNetworkEdgeList(),
								previousData.getDataBase().get(previousData.getSelectedRow()).getMinimalSpanningEdgeList());
						BasicFrame.getPane().getSimTab().getVisPanel().repaint();
					}
				}
				else{
					
				}
				BasicFrame.getPane().getSimTab().getVisPanel().repaint();
			}
		};
		drawSubNetworkSpecs.addActionListener(networkSpecsChanged);
		
	}
	
	public RawDataTable getPreviousData(){
		return previousData;
	}
}
