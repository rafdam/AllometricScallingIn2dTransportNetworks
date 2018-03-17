package GUI;

import javax.swing.JLabel;

public class AllometricIndexText extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String a = "0";
	public AllometricIndexText() {
		super("AllometricScaleIndex");
	}
	
	public void setString(String text){
		setText(text);
	}
}
