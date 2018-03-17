package GUI;

import java.awt.HeadlessException;

import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

public class BasicFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static TabbedPane tabbedPane;
	public BasicFrame() throws HeadlessException {
		super();
		setSize(1300,900);
		setLayout(new MigLayout());
		setTitle("Allometric Scalling in distribution networks");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		tabbedPane = new TabbedPane(); //pane with cards" Charges, Help..."
		add(tabbedPane,"width 100%, height 100%");
	}
	
	public static TabbedPane getPane(){
		return tabbedPane;
	}
}
