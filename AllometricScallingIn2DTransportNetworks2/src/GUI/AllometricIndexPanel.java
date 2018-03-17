package GUI;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class AllometricIndexPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double a = 1;
	AllometricIndexText resultLabel;
	public AllometricIndexPanel() {
		Color color = new Color(235,235,252);
		setBackground(color);
		setBorder(new LineBorder(Color.BLACK, 2));
		//String text = "f = "+ a +"x" + " + b";
		resultLabel = new AllometricIndexText();
		add(resultLabel);
	}
	
	public void setText(String text){
		
	}
	
	public AllometricIndexText getLabel(){
		return resultLabel;
	}
	
	
}
