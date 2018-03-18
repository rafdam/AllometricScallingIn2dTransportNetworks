package GUI;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class TestConsolePrint {

	public TestConsolePrint() {
	}
         
	public static void main(String[] args) {
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		        }
		    }
		} catch (Exception e) {
		    System.out.println("kek");
		}
		BasicFrame frame = new BasicFrame();
		frame.setVisible(true);
	}

}
