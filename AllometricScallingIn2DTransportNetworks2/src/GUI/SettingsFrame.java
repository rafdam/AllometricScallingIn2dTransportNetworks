package GUI;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class SettingsFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField startLField, jumpsField, jumpSizeField, probabilityField, kNearestNeighboursField;
	JLabel startLLabel, jumpsLabel, jumpSizeLabel, probabilityLabel, kNearestNeighboursLabel;
	int startL, jumps, jumpSize, probability, kNearestNeighbours;
	JButton set, setNClose;

	public SettingsFrame() throws HeadlessException {
		setSize(300,250);
		setLayout(new MigLayout());
		
		startLField = new JTextField("50");
		jumpsField = new JTextField("25");
		jumpSizeField = new JTextField("10");
		probabilityField = new JTextField("0.2");
		kNearestNeighboursField = new JTextField("5");
		
		startLLabel = new JLabel("<html> <b>Set Start L Value");
		jumpsLabel = new JLabel("<html> <b>Set number of jumps");
		jumpSizeLabel = new JLabel("<html> <b>Set size of jump");
		probabilityLabel = new JLabel ("<html> <b>Set density of the network");
		kNearestNeighboursLabel = new JLabel("<html> <b>Set number of neighbours");
		
		set = new JButton("set Values");
		setNClose = new JButton("set And Close");
		
		ActionListener setPressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpNumber(Integer.parseInt(jumpsField.getText()));
					BasicFrame.getPane().getSimTab().getConsolePanel().setStartL(Integer.parseInt(startLField.getText()));
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpSiz(Integer.parseInt(jumpSizeField.getText()));
					if (Double.parseDouble(probabilityField.getText()) > 1 || Double.parseDouble(probabilityField.getText()) == 0){
						JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"Density must be in set (0;1>");
						return;
					}
					BasicFrame.getPane().getSimTab().getConsolePanel().setProbability(Double.parseDouble(probabilityField.getText()));
					BasicFrame.getPane().getSimTab().getConsolePanel().setNeighbours(Integer.parseInt(kNearestNeighboursField.getText()));
					
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpNumb("<html> <b>Number of jumps: " + jumpsField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setStL("<html> <b>Start L size: " + startLField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpSize("<html> <b>Size of jump: " + jumpSizeField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setProb("<html> <b>Density of network: " + probabilityField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setNeighb("<html> <b>Number of neighbours: " + kNearestNeighboursField.getText());
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"Enter numbers, not letters");
				}
				
			}
		};
		
		ActionListener setAndClosePressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpNumber(Integer.parseInt(jumpsField.getText()));
					BasicFrame.getPane().getSimTab().getConsolePanel().setStartL(Integer.parseInt(startLField.getText()));
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpSiz(Integer.parseInt(jumpSizeField.getText()));
					if (Double.parseDouble(probabilityField.getText()) > 1 || Double.parseDouble(probabilityField.getText()) == 0){
						JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"Density must be in set (0;1>");
						return;
					}
					BasicFrame.getPane().getSimTab().getConsolePanel().setProbability(Double.parseDouble(probabilityField.getText()));
					BasicFrame.getPane().getSimTab().getConsolePanel().setNeighbours(Integer.parseInt(kNearestNeighboursField.getText()));
					
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpNumb("<html> <b>Number of jumps: " + jumpsField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setStL("<html> <b>Start L size: " + startLField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setJumpSize("<html> <b>Size of jump: " + jumpSizeField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setProb("<html> <b>Density of network: " + probabilityField.getText());
					BasicFrame.getPane().getSimTab().getConsolePanel().setNeighb("<html> <b>Number of neighbours: " + kNearestNeighboursField.getText());
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"Enter numbers, not letters");
				}
				dispose();
			}
		};
		set.addActionListener(setPressed);
		setNClose.addActionListener(setAndClosePressed);
		add(startLLabel, "width 60%, height 15%");
		add(startLField, "width 40%, height 15%, wrap");
		add(jumpsLabel, "width 60%, height 15%");
		add(jumpsField, "width 40%, height 15%, wrap");
		add(jumpSizeLabel, "width 60%, height 15%");
		add(jumpSizeField, "width 40%, height 15%, wrap");
		add(probabilityLabel, "width 60%, height 15%");
		add(probabilityField, "width 40%, height 15%, wrap");
		add(kNearestNeighboursLabel, "width 60%, height 15%");
		add(kNearestNeighboursField, "width 40%, height 15%, wrap");
		add(set);
		add(setNClose);
		
		
		
		
	}
}
