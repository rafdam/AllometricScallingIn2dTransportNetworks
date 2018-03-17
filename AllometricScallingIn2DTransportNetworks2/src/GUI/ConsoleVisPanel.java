package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import TreeModel.IndexCalculus;
import net.miginfocom.swing.MigLayout;

public class ConsoleVisPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel stL, jumpNumb, jumpSize, prob, neighb;
	private int startL, jumpNumber, jumpSiz, neighbours;
	private double  probability;
	SettingsFrame setFrame;
	IndexCalculus index;
	public ConsoleVisPanel() {
		setLayout(new MigLayout());
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		JButton startAndSettingsButton = new JButton("<html> <b>Define Network");
		JButton startSimulation = new JButton("<html> <b>Run Simulation");
		JButton clearPreviousSims = new JButton("<html> <b>Clear Previous Data");
		stL = new JLabel("<html> <b>Start L size: ");
		jumpNumb = new JLabel("<html> <b>Number of jumps:");
		jumpSize = new JLabel("<html> <b>Size of jump:");
		prob = new JLabel("<html> <b>Density of network:");
		neighb = new JLabel("<html> <b>Number of neighbours:");
		
		ActionListener startAndSetPressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setFrame = new SettingsFrame();
				setFrame.setVisible(true);
				
			}
			
		};
		
		ActionListener startSimPressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					index = new IndexCalculus(startL, probability, neighbours, jumpSiz, jumpNumber, 5);
				}
				catch(NullPointerException nullE){
					JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"Define all the start Values before you run the simulation");
				}
			}
		};
		
		ActionListener clearDataPressed = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				BasicFrame.getPane().getCountTab().getResults().getChartTable().getTable().clear();
				BasicFrame.getPane().getCountTab().getChart().clearChart();
				BasicFrame.getPane().getCountTab().getResults().getIndexPanel().getLabel().setText("AllometricScaleIndex");
			}
		};
		
		startAndSettingsButton.addActionListener(startAndSetPressed);
		startSimulation.addActionListener(startSimPressed);
		clearPreviousSims.addActionListener(clearDataPressed);
		add(startAndSettingsButton);
		add(startSimulation);
		add(clearPreviousSims, " wrap");
		add(stL, "width 20%, height 40%");
		add(jumpNumb, "width 20%, height 40%");
		add(jumpSize, "width 20%, height 40%");
		add(prob, "width 20%, height 40%");
		add(neighb, "width 20%, height 40%");
	}
	public JLabel getStL() {
		return stL;
	}
	
	public void setStL(String text){
		stL.setText(text);
	}
	
	public JLabel getJumpNumb() {
		return jumpNumb;
	}
	public void setJumpNumb(String text) {
		jumpNumb.setText(text);
	}
	public JLabel getJumpSize() {
		return jumpSize;
	}
	public void setJumpSize(String text) {
		jumpSize.setText(text);
	}
	public JLabel getProb() {
		return prob;
	}
	public void setProb(String text) {
		prob.setText(text);
	}
	public JLabel getNeighb() {
		return neighb;
	}
	public void setNeighb(String text) {
		neighb.setText(text);
	}
	public int getStartL() {
		return startL;
	}
	public void setStartL(int startL) {
		this.startL = startL;
	}
	public int getJumpNumber() {
		return jumpNumber;
	}
	public void setJumpNumber(int jumpNumber) {
		this.jumpNumber = jumpNumber;
	}
	public int getJumpSiz() {
		return jumpSiz;
	}
	public void setJumpSiz(int jumpSiz) {
		this.jumpSiz = jumpSiz;
	}
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}
	public int getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}
}
