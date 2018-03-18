package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import TreeModel.EdgeList;
import TreeModel.HubList;
import net.miginfocom.swing.MigLayout;

public class SimulationRawDataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RawDataTable previousData;
	public SimulationRawDataPanel() {
		setLayout(new MigLayout());
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		previousData = new RawDataTable();
		add(previousData);
		JScrollPane pane = new JScrollPane(previousData);  // Scroll for table 
		add(pane, "height 80%, wrap");
		JButton drawSpecifiedNetwork = new JButton("<html> <b>Draw Selected Network");
		add(drawSpecifiedNetwork, "width 100%");
		ActionListener drawPressed = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				HubList list = previousData.getDataBase().get(previousData.getSelectedRow()).getVerticleList();
				EdgeList maxNetwork = previousData.getDataBase().get(previousData.getSelectedRow()).getMaximalNetworkEdgeList();
				EdgeList minimalSpann = previousData.getDataBase().get(previousData.getSelectedRow()).getMinimalSpanningEdgeList();
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().setParams(list, maxNetwork, minimalSpann);
			}
		};
		drawSpecifiedNetwork.addActionListener(drawPressed);
	}
	
	public RawDataTable getPreviousData(){
		return previousData;
	}
}
