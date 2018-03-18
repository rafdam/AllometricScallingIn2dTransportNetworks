package TreeModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Edge {
private int startHubAdress; // Like below the direction of the graph gonna be added with grad of the hubs;
private int endHubAdress;
private int hub1XCoord, hub1YCoord, hub2XCoord, hub2YCoord;
private int weight; //First the edge weight gonna be calculated just by euclidean distance;
					// After that it will be changed to grad between the hubs on the Z axis;
	public Edge(int hub1Adress, int hub2Adress, int weightLevel, int x1, int x2, int y1, int y2) {
		//weight = Math.sqrt((list.get(hub1Adress).getxCartCoord() - list.get(hub2Adress).getxCartCoord()) ^ 2
		//		+ (list.get(hub1Adress).getyCartCoord() - list.get(hub2Adress).getyCartCoord()) ^ 2
		//		+ (list.get(hub1Adress).getzCartCoord() - list.get(hub2Adress).getzCartCoord()) ^ 2);
		weight = weightLevel;
		startHubAdress = hub1Adress;
		endHubAdress = hub2Adress;
		hub1XCoord = x1;
		hub1YCoord = y1;
		hub2XCoord = x2;
		hub2YCoord = y2;
	}
	
	public int getStartHubAdress(){
		return startHubAdress;
	}
	
	public int getEndHubAdress(){
		return endHubAdress;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void draw(Graphics g, double x, double y, int xOffset, int yOffset) { 
		g.setColor(Color.GRAY);
		g.drawLine((int)((hub1XCoord) * x + 13 + xOffset), (int)((hub1YCoord) * y + 13 + yOffset),
				(int)((hub2XCoord) * x + 13 + xOffset), (int)((hub2YCoord) * y + 13 + yOffset));
	}
	
	public void drawArrow(Graphics2D g2, double x, double y, int xOffset, int yOffset){
		
		if (weight == 1){
			g2.setColor(Color.RED);
		}
		else{
			try{
				Color color = new Color((int)(255 / 15) * weight , 255 - ((int)(255 / 15) * weight), (int)(255 / 15) * weight);
				g2.setColor(color);
			}
			catch(IllegalArgumentException ee){
				g2.setColor(Color.ORANGE);
			}
		}
		g2.drawLine((int)((hub1XCoord) * x + 13 + xOffset), (int)((hub1YCoord) * y + 13 + yOffset),
				(int)((hub2XCoord) * x + 13 + xOffset), (int)((hub2YCoord) * y + 13 + yOffset));
		double dy = hub1YCoord - hub2YCoord;
		double dx = hub1XCoord - hub2XCoord;
		double theta = Math.atan2(dy, dx);
		int barb = 10;
		double X, Y, rho = theta + Math.toRadians(15);
		for (int jj = 0 ; jj < 2; jj++){
			X = (hub1XCoord + xOffset)*x - barb*Math.cos(rho);
			Y = (hub1YCoord + yOffset)*y- barb*Math.sin(rho);
			g2.draw(new Line2D.Double((hub1XCoord + xOffset)*x + 13, (hub1YCoord + yOffset) * y + 13, X, Y));
			rho = theta - Math.toRadians(15);
		}
	}
}