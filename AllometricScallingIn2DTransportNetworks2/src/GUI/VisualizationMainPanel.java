package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import TreeModel.NetworkHub;

public class VisualizationMainPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VisualizationMainPanel() {
		Color color = new Color(0,0,0);
		setBackground(color);
		setBorder(new LineBorder(Color.WHITE, 4));	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		int maxII = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().size();
		double width = BasicFrame.getPane().getSimTab().getVisPanel().getWidth()-10;
		double height = BasicFrame.getPane().getSimTab().getVisPanel().getHeight()-10;
		try{
			int maxJJ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().size();
			double xMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getxCartCoord)).get().getxCartCoord();
			double yMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getyCartCoord)).get().getyCartCoord();
			double xCoeff = width/xMaxVal;
			double yCoeff = height/yMaxVal;
			int maxZZ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMaximalNetworkEdgeList().size();
			for (int jj = 0 ; jj < maxJJ; jj++){
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).draw(g, xCoeff, yCoeff);
			}
			for (int zz = 0 ; zz < maxZZ; zz++){
				g.setColor(Color.WHITE);
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMaximalNetworkEdgeList().get(zz).draw(g, xCoeff, yCoeff);
				//System.out.println("kek");
			}
		}
		catch(NullPointerException ee){
			
		}
		
		/*
		for (int ii = 0; ii < maxII; ii++){
			int maxJJ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getVerticleList().size();
			double xMaxVal = BasicFrame.getPane().getSimTab().
					getRawDataPanel().getPreviousData().getDataBase().
					get(ii).getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getxCartCoord)).get().getxCartCoord();
			double yMaxVal = BasicFrame.getPane().getSimTab().
					getRawDataPanel().getPreviousData().getDataBase().
					get(ii).getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getyCartCoord)).get().getyCartCoord();
			
			//int xMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getVerticleList().get(maxJJ-1).getxCartCoord();
			//int yMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getVerticleList().get(maxJJ-1).getyCartCoord();
			double xCoeff = width/xMaxVal;
			double yCoeff = height/yMaxVal;
			int maxZZ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getMaximalNetworkEdgeList().size();
			for (int zz = 0 ; zz < maxZZ; zz++){
				//g.setColor(Color.WHITE);
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getMaximalNetworkEdgeList().get(zz).draw(g, xCoeff, yCoeff);
				//System.out.println("kek");
			}
			
			for (int jj = 0 ; jj < maxJJ; jj++){
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getVerticleList().get(jj).draw(g, xCoeff, yCoeff);
			}
			int maxZZ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getMaximalNetworkEdgeList().size();
			for (int zz = 0 ; zz < maxZZ; zz++){
				g.setColor(Color.WHITE);
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getDataBase().get(ii).getMaximalNetworkEdgeList().get(zz).draw(g, xCoeff, yCoeff);
				//System.out.println("kek");
			}
			*/
		}
		
	}
	
	

