package TreeModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Edge {
private int startHubAdress; 
private int endHubAdress;
public int hub1XCoord, hub1YCoord, hub2XCoord, hub2YCoord;
private int weight; 
	public Edge(int hub1Adress, int hub2Adress, int weightLevel, int x1, int x2, int y1, int y2) {
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
		g.setColor(new Color(33, 33, 33));
		g.drawLine((int)((hub1XCoord) * x + 18 + xOffset), (int)((hub1YCoord) * y + 18 + yOffset),
				(int)((hub2XCoord) * x + 18 + xOffset), (int)((hub2YCoord) * y + 18 + yOffset));
	}
	
	public void drawArrow(Graphics2D g2, double x, double y, int xOffset, int yOffset){
		if (weight == 1){
			g2.setColor(Color.RED);
		}
		else if(weight > 1 && weight < 10){
			Color color = new Color((int)(255 / 10) * weight , 255 - (int)(255 / 10) * weight, 125);
			g2.setColor(color);
		}
		else if(weight > 10  && weight < 20){
			Color color = new Color(255 - (int)(255 / 20) * weight, 125, (int)(255 / 20) * weight);
			g2.setColor(color);	
		}
		else if(weight > 20 && weight < 30){
			Color color = new Color(125, ((int)(255 / 30) * weight), 255 - (int)(255 / 30) * weight);
			g2.setColor(color);
		}
		else if(weight > 30 && weight < 40){
			Color color = new Color(255 - (int)(255 / 40) * weight ,0 , (int)(255 / 40) * weight);
			g2.setColor(color);
		}
		else{
			g2.setColor(Color.magenta);
		}
		
		g2.drawLine((int)((hub1XCoord) * x + 18 + xOffset), (int)((hub1YCoord) * y + 18 + yOffset),
				(int)((hub2XCoord) * x + 18 + xOffset), (int)((hub2YCoord) * y + 18 + yOffset));
		double dy = hub1YCoord - hub2YCoord;
		double dx = hub1XCoord - hub2XCoord;
		double theta = Math.atan2(dy, dx);
		int barb = 10;
		int xx[] = {(int)((hub1XCoord)*x + 18 + xOffset),
				(int)(((hub1XCoord) * x + 18) - (barb*Math.cos(theta + Math.toRadians(5)) * x / 20) + xOffset),
				(int)(((hub1XCoord) * x + 18) - (barb*Math.cos(theta - Math.toRadians(5)) * x / 20) + xOffset)};
		int yy[] = {(int)((hub1YCoord) * y + 18 + yOffset),
				(int)(((hub1YCoord) * y +  18) - (barb*Math.sin(theta + Math.toRadians(5)) * y / 20) + yOffset),
				(int)(((hub1YCoord) * y +  18) - (barb*Math.sin(theta - Math.toRadians(5)) * y / 20) + yOffset)};
		g2.fillPolygon(xx, yy, 3);
	}
}

















