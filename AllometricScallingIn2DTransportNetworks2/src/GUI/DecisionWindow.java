package GUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class DecisionWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int decision;
	public JButton subNetwork;
	public JButton subSubNetwork;
	public DecisionWindow(int jj) throws HeadlessException {
		super("Choose Network Type");
		decision = -1;
		setLayout(new MigLayout());
		setSize(450, 150);
		setLocationRelativeTo(null);
		setVisible(true);
		
		JLabel text1 = new JLabel("Sub Network of Maximal Network");
		JLabel text2 = new JLabel("Sub Network of drawn Sub Network");
		JButton subNetwork = new JButton("Sub Network");
		JButton subSubNetwork = new JButton("SubSub Network");
		add(text1, "width 50%, height 50%");
		add(text2, "width 50%, height 50%, wrap");
		add(subNetwork, "width 50%, height 50%");
		add(subSubNetwork, "width 50%, height 50%");
		ActionListener subNetworkAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				decision = 1;
				//setVisible(false);
			}
		};
		
		ActionListener subSubNetworkAction = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				decision = 2;
				//setVisible(false);
			}
		};
		
		if(decision == 1){
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().recalcSubNetwork(jj);
			BasicFrame.getPane().getSimTab().getVisPanel().repaint();
			dispose();
			
		}
		else if(decision == 2){
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().recalcSubSubNetwork(jj);
			BasicFrame.getPane().getSimTab().getVisPanel().repaint();
			dispose();
		}
		
		subNetwork.addActionListener(subNetworkAction);
		subSubNetwork.addActionListener(subSubNetworkAction);
	}
}
