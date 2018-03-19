package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import TreeModel.NetworkHub;

public class VisualizationMainPanel extends JPanel implements MouseWheelListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double zoomScalling;
	private volatile int draggedAtX, draggedAtY;
	double xCoeff, yCoeff;
	//int xOffset, yOffset;
	int tmpXOffset, tmpYOffset;
	public VisualizationMainPanel() {
		zoomScalling = 1.0;
		Color color = new Color(0,0,0);
		setBackground(color);
		setBorder(new LineBorder(Color.WHITE, 4));
		addMouseWheelListener(this);
		
		addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                draggedAtX = e.getX();
                draggedAtY = e.getY();
                tmpXOffset = BasicFrame.getPane().getSimTab().getXOffset();
                tmpYOffset = BasicFrame.getPane().getSimTab().getYOffset();
            }
            /*
            public void mouseReleased(MouseEvent e){
            	BasicFrame.getPane().getSimTab().setXOffset((e.getX() - draggedAtX)/2);
            	BasicFrame.getPane().getSimTab().setYOffset((e.getY() - draggedAtY)/2);
            }
            */
		});
		
		addMouseMotionListener(new MouseMotionAdapter(){
            public void mouseDragged(MouseEvent e){
            	BasicFrame.getPane().getSimTab().setXOffset(tmpXOffset + (e.getX() - draggedAtX));
            	BasicFrame.getPane().getSimTab().setYOffset(tmpYOffset + (e.getY() - draggedAtY));
            	repaint();
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
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		double width = BasicFrame.getPane().getSimTab().getVisPanel().getWidth()-26;
		double height = BasicFrame.getPane().getSimTab().getVisPanel().getHeight()-26;
		try{
			int maxJJ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().size();
			double xMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getxCartCoord)).get().getxCartCoord();
			double yMaxVal = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().stream().
					max(Comparator.comparing(NetworkHub::getyCartCoord)).get().getyCartCoord();
			xCoeff = width/xMaxVal * zoomScalling;
			yCoeff = height/yMaxVal * zoomScalling;
			int maxWW = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMaximalNetworkEdgeList().size();
			for (int ww = 0 ; ww < maxWW ; ww++){
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMaximalNetworkEdgeList().get(ww).
				draw(g, xCoeff, yCoeff, BasicFrame.getPane().getSimTab().getXOffset(), BasicFrame.getPane().getSimTab().getYOffset());
			}
			int maxZZ = BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMinimalSpanningEdgeList().size();
			for (int zz = 0 ; zz < maxZZ; zz++){
				//g.setColor(Color.WHITE);
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getMinimalSpanningEdgeList().get(zz).
				drawArrow(g2, xCoeff, yCoeff, BasicFrame.getPane().getSimTab().getXOffset(), BasicFrame.getPane().getSimTab().getYOffset());
			}
			
			for (int jj = 0 ; jj < maxJJ; jj++){
				BasicFrame.getPane().getSimTab().getRawDataPanel().getPreviousData().getNetworkToDraw().getVerticleList().get(jj).
				draw(g, xCoeff, yCoeff, BasicFrame.getPane().getSimTab().getXOffset(), BasicFrame.getPane().getSimTab().getYOffset());
			}
		}
		catch(NullPointerException ee){
			//none of drawings specified
		}
	}
}
	
	

