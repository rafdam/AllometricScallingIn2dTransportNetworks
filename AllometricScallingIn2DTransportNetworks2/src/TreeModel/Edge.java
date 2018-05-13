package TreeModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Comparator;

public class Edge {
private int startHubAdress; 
private int endHubAdress;
public int hub1XCoord, hub1YCoord, hub2XCoord, hub2YCoord;
private int weight; 
private int level;
private double dist;
	public Edge(int hub1Adress, int hub2Adress, int weightLevel, int x1, int x2, int y1, int y2, int lvl) {
		weight = weightLevel;
		startHubAdress = hub1Adress;
		endHubAdress = hub2Adress;
		hub1XCoord = x1;
		hub1YCoord = y1;
		hub2XCoord = x2;
		hub2YCoord = y2;
		level = lvl;
		dist = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
	}
	
	public int getStartHubAdress(){
		return startHubAdress;
	}
	
	public double getDist(){
		return dist;
	}
	
	public int getEndHubAdress(){
		return endHubAdress;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getLevel(){
		return level;
	}
	
	public void setLevel(int lvl){
		level = lvl;
	}
	
	public static Comparator<Edge> EdgeWeight = new Comparator<Edge>() {
		@Override
		public int compare(Edge o1, Edge o2) {
			int weight1 = o1.getWeight();
			int weight2 = o2.getWeight();
			return weight1 - weight2;
	}};
	
	public static Comparator<Edge> EdgeDist = new Comparator<Edge>() {
		@Override
		public int compare(Edge o1, Edge o2) {
			double dist1 = o1.getDist();
			double dist2 = o2.getDist();
			//return  (int)(dist1 - dist2);
			if (dist1 < dist2)
				  return -1;
				else if (dist1 > dist2)
				  return 1;
				else
				  return 0;
		}};
	
	public void draw(Graphics g, double x, double y, int xOffset, int yOffset) { 
		g.setColor(new Color(33, 33, 33));
		g.drawLine((int)((hub1XCoord) * x + 18 + xOffset), (int)((hub1YCoord) * y + 18 + yOffset),
				(int)((hub2XCoord) * x + 18 + xOffset), (int)((hub2YCoord) * y + 18 + yOffset));
	}
	
	public void drawArrow(Graphics2D g2, double x, double y, int xOffset, int yOffset){
		if (weight == 1){
			g2.setColor(Color.RED);
		}
		else if (weight == 2){
			g2.setColor(Color.GREEN);
		}
		else if(weight == 3){
			g2.setColor(Color.BLUE);
		}
		else if(weight == 4){
			g2.setColor(Color.WHITE);
		}
		else if(weight == 5){
			g2.setColor(Color.CYAN);
		}
		else if(weight == 6){
			g2.setColor(Color.GRAY);
		}
		else if(weight == 7){
			g2.setColor(Color.YELLOW);
		}
		else if(weight == 8){
			g2.setColor(Color.ORANGE);
		}
		else if(weight == 9){
			g2.setColor(Color.PINK);
		}
		//else if(weight >= 1 && weight < 10){
		//	Color color = new Color((int)(255 / 10) * weight , 255 - (int)(255 / 10) * weight, 125);
		//	g2.setColor(color);
		//}
		else if(weight >= 10  && weight < 20){
			Color color = new Color(255 - (int)(255 / 20) * weight, 125, (int)(255 / 20) * weight);
			g2.setColor(color);	
		}
		else if(weight >= 20 && weight < 30){
			Color color = new Color(125, ((int)(255 / 30) * weight), 255 - (int)(255 / 30) * weight);
			g2.setColor(color);
		}
		else if(weight >= 30 && weight < 40){
			Color color = new Color(255 - (int)(255 / 40) * weight ,0 , (int)(255 / 40) * weight);
			g2.setColor(color);
		}
		else if(weight >= 40 && weight < 50){
			Color color = new Color(255 - (int)(255 / 50) * weight , 100, 255);
			g2.setColor(color);
		}
		else if(weight >= 50 && weight < 60){
			Color color = new Color(255 - (int)(255 / 60) * weight , (int)(255 / 60) * weight, (int)(255 / 60) * weight);
			g2.setColor(color);
		}
		else if(weight >= 60 && weight < 70){
			Color color = new Color(255, 255 - (int)(255 / 70) * weight , (int)(255 / 70) * weight);
			g2.setColor(color);
		}
		else{
			g2.setColor(new Color(200, 255, 200));
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

















