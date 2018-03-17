package GUI;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import TreeModel.IndexCalculus;

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
		IndexCalculus index = new IndexCalculus(10,1,15,5);
	}

}
