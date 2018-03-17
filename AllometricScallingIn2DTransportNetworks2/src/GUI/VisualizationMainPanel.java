package GUI;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class VisualizationMainPanel extends JPanel {

	public VisualizationMainPanel() {
		Color color = new Color(0,0,0);
		setBackground(color);
		setBorder(new LineBorder(Color.WHITE, 4));	
	}
}
