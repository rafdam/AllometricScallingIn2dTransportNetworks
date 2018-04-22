package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import TreeModel.NetworkHub;

public class VisualizationMainPanel extends JPanel implements MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double zoomScalling;
	private volatile int draggedAtX, draggedAtY;
	private volatile int clickedAtX, clickedAtY;
	double xCoeff, yCoeff;
	private int foundX, foundY;
	//int xOffset, yOffset;
	int tmpXOffset, tmpYOffset;
	private ArrayList<Ellipse2D> ovalList;
	boolean foundContainingOval;
	private ArrayList<NetworkHub> clickedHub;
	public VisualizationMainPanel() {
		ovalList= new ArrayList<Ellipse2D>();
		clickedHub = new ArrayList<NetworkHub>();
		zoomScalling = 1.0;
		Color color = new Color(0,0,0);
		foundContainingOval = false;
		setBackground(color);
		setBorder(new LineBorder(Color.WHITE, 4));
		addMouseWheelListener(this);
		
		addMouseListener(new MouseAdapter(){
			
            public void mousePressed(MouseEvent e){
            	if(SwingUtilities.isLeftMouseButton(e)){
	                draggedAtX = e.getX();
	                draggedAtY = e.getY();
	                tmpXOffset = BasicFrame.getPane().getSimTab().getXOffset();
	                tmpYOffset = BasicFrame.getPane().getSimTab().getYOffset();
            	}
            	if(SwingUtilities.isRightMouseButton(e)){
            		foundContainingOval = false;
            		clickedAtX = e.getX();
	                clickedAtY = e.getY();
	                if(BasicFrame.getPane().getSimTab().getConsolePanel().getRecalcBox().isSelected()){
	                	for(int ii = 0 ; ii < ovalList.size(); ii++){
		                	if(ovalList.get(ii).contains(clickedAtX, clickedAtY)){
		                		foundContainingOval = true;
		                		foundX = (int) Math.round(((ovalList.get(ii).getX() - 13 - BasicFrame.getPane().getSimTab().getXOffset())/xCoeff));
		                		foundY = (int) Math.round(((ovalList.get(ii).getY() - 13 - BasicFrame.getPane().getSimTab().getYOffset())/yCoeff));
		                		if(foundContainingOval == true){
		                			for(int jj = 0; jj < BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().size(); jj ++){
		    	                		if(BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).getxCartCoord() == foundX &&
		    	                			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).getyCartCoord() == foundY){
		    	                			clickedHub.clear();
		    	                			clickedHub.add(BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj));
		    	                			Object[] options = {"SubNetwork of MaximalNetwork", "SubNetwork of SubNetwork", "None"};
		    	                			JFrame frame = new JFrame();
		    	                			int decision = JOptionPane.showOptionDialog(frame, "What kind of SubNework would you like to count & draw",
		    	                					"Select SubNetwork Type", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		    	                			System.out.println(decision);
		    	                			if(decision == 0){
		    	                				int returner = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().recalcSubNetwork(jj);
		    	                				if (returner == 0){
		    	                					clickedHub.clear();
		    	                					JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"You pressed ending hub, none to recalculate or re-draw");
		    	                				}
		    	                			}
		    	                			else if(decision == 1){
		    	                				int returner = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().recalcSubSubNetwork(jj);
		    	                				if (returner == 0){
		    	                					clickedHub.clear();
		    	                					JOptionPane.showMessageDialog(BasicFrame.getPane().getSimTab(),"You are not in Sub Network to draw Sub Network of Sub Network");
		    	                				}
		    	                			}
		    	                			else{
		    	                				clickedHub.clear();
		    	                			}
		    	                			
		    	                			repaint();
		    	                			//foundContainingOval = false;
		    	                			break;
		    	                		}
		    	                	}
		                    	}
		                		break;
		                	}
		                	
		                }
	                	if(foundContainingOval == false){
	                		clickedHub.clear();
                			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().redrawMaximalNetwork();
                			repaint();
                			foundContainingOval = false;
	                	}
	                }
            	}
            }
		});
		
		addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
            	if(SwingUtilities.isLeftMouseButton(e)){
	            	BasicFrame.getPane().getSimTab().setXOffset(tmpXOffset + (e.getX() - draggedAtX));
	            	BasicFrame.getPane().getSimTab().setYOffset(tmpYOffset + (e.getY() - draggedAtY));
	            	repaint();
            	}
            }
        });

	}
	
	public void zeroOffsets(){
		BasicFrame.getPane().getSimTab().setXOffset(0);
		BasicFrame.getPane().getSimTab().setYOffset(0);
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {

        //Zoom in
        if(e.getWheelRotation()<0){
        	if (zoomScalling > 10){
        		
        	}
        	else{
	            zoomScalling = zoomScalling * 1.1;
	    		BasicFrame.getPane().getSimTab().setXOffset((int) ((-e.getX() * (0.1)) + (1.1 * BasicFrame.getPane().getSimTab().getXOffset())));
	        	BasicFrame.getPane().getSimTab().setYOffset((int) ((-e.getY() * (0.1)) + (1.1 * BasicFrame.getPane().getSimTab().getYOffset())));
	            repaint();
        	}
        }
        //Zoom out
        if(e.getWheelRotation()>0){
        	if(zoomScalling < 0.5){
        		
        	}
        	else{
	        	zoomScalling = zoomScalling * 0.9;
	        	BasicFrame.getPane().getSimTab().setXOffset((int) ((-e.getX() * (- 0.1)) + (0.9 * BasicFrame.getPane().getSimTab().getXOffset())));
	        	BasicFrame.getPane().getSimTab().setYOffset((int) ((-e.getY() * (- 0.1)) + (0.9 * BasicFrame.getPane().getSimTab().getYOffset())));
	        	repaint();
        	}
        }
    }
	
	public void ClearClicked(){
		clickedHub.clear();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		double width = BasicFrame.getPane().getSimTab().getVisPanel().getWidth()-26;
		double height = BasicFrame.getPane().getSimTab().getVisPanel().getHeight()-26;
		try{
			double xMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getxCartCoord)).get().getxCartCoord();
			double yMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getyCartCoord)).get().getyCartCoord();
			double xyMaxVal = Math.max(xMaxVal,  yMaxVal);
			xCoeff = width/xyMaxVal * zoomScalling;
			yCoeff = height/xyMaxVal * zoomScalling;
			drawMaximal(g2, xCoeff, yCoeff);
			drawMinimal(g2, xCoeff, yCoeff);
			drawVerticles(g2, xCoeff, yCoeff);
			drawClickedNetwork(g2, xCoeff, yCoeff);
		}
		catch(NullPointerException ee){
			// when running the program without simulated data there's no x and y maxVals;
		}
		catch (NoSuchElementException ww){
			//JOptionPane.showMessageDialog(BasicFrame.getPane(),"You have no sub Network specified");
		}
	}
	
	public void drawMaximal(Graphics g, double xCo, double yCo){
		int maxWW = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMaximalNetworkEdgeList().size();
		for (int ww = 0 ; ww < maxWW ; ww++){
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMaximalNetworkEdgeList().get(ww).
			draw(g, xCo, yCo, BasicFrame.getPane().getSimTab().getXOffset(), BasicFrame.getPane().getSimTab().getYOffset());
		}
	}
	
	public void drawMinimal(Graphics2D g, double xCo, double yCo){
		int maxZZ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMinimalSpanningEdgeList().size();
		for (int zz = 0 ; zz < maxZZ; zz++){
			//g.setColor(Color.WHITE);
			BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMinimalSpanningEdgeList().get(zz).
			drawArrow(g, xCo, yCo, BasicFrame.getPane().getSimTab().getXOffset(), BasicFrame.getPane().getSimTab().getYOffset());
		}
	}
	
	public void drawVerticles(Graphics2D g2, double xCo, double yCo){
		int maxJJ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().size();
		for (int jj = 0 ; jj < maxJJ; jj++){
			Color color = new Color(221,234,240);
			if(BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).getLevel() == 0){
				color = Color.RED;
			}
			if(BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).getReverseMinimalTree().size() == 0
					&& BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).getMinimalNeighbourIndexesList().size() == 0 ){
				//skip
			}
			else{
				g2.setColor(color);
				Ellipse2D oval = new Ellipse2D.Double(BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).getxCartCoord()
						* xCo + 13 + BasicFrame.getPane().getSimTab().getXOffset(),
						BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).getyCartCoord()
						* yCo + 13 + BasicFrame.getPane().getSimTab().getYOffset(), 10, 10);
				ovalList.add(oval);
				g2.fill(oval);
			}
		}
	}
	public void drawSubNetwork(){
		
	}
	
	public void drawClickedNetwork(Graphics2D g2, double xCo, double yCo){
		try{
			clickedHub.get(clickedHub.size() - 1).draw(g2, xCo, yCo,
					BasicFrame.getPane().getSimTab().getXOffset(), BasicFrame.getPane().getSimTab().getYOffset());
		}
		catch(ArrayIndexOutOfBoundsException ww){
			
		}
	}
}
	
	

