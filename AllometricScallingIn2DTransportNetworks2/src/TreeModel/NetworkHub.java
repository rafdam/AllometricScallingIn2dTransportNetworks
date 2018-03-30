package TreeModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class NetworkHub {
private int xCartCoord; // coordinates defined in cartesian metric system
private int yCartCoord;
private int zCartCoord;
private int level; // defines the level of the hub specified by spanning tree
public boolean inTriangle;
private ArrayList<Integer> neighbourIndexesList;
private ArrayList<Integer> minimalTreeNeighbourIndexesList;
	
	public NetworkHub(int x, int y) {
		xCartCoord = x;
		yCartCoord = y;
		level = 0;
		zCartCoord = 0;
		new ArrayList<NetworkHub>();
		neighbourIndexesList = new ArrayList<Integer>();
		minimalTreeNeighbourIndexesList = new ArrayList<Integer>();
	}
	public NetworkHub(){
		
	}
	
	public int getxCartCoord() {
		return xCartCoord;
	}

	public void setxCartCoord(int xCartCoord) {
		this.xCartCoord = xCartCoord;
	}
	
	public void setzCartCoord(int zCartCoord) {
		this.zCartCoord = zCartCoord;
	}
	
	public int getzCartCoord() {
		return zCartCoord;
	}

	public int getyCartCoord() {
		return yCartCoord;
	}

	public void setyCartCoord(int yCartCoord) {
		this.yCartCoord = yCartCoord;
	}

	public void setLevel(int weight){
		level = weight;
	}
	public int getLevel(){
		return level;
	}
	public void addToNeighbourIndexesList(int index){
		neighbourIndexesList.add(index);
	}
	public void addToMinimalNeighbourIndexesList(int index){
		minimalTreeNeighbourIndexesList.add(index);
	}
	public ArrayList<Integer> getMinimalNeighbourIndexesList(){
		return minimalTreeNeighbourIndexesList;
	}
	public void setInTriangle(boolean a){
		inTriangle = a;
	}
	public boolean getInTriangle(){
		return inTriangle;
	}
	public ArrayList<Integer> getNeighbourIndexesList(){
		return neighbourIndexesList;
	}
	
	public void draw(Graphics g, double x, double y, int xOffset, int yOffset) { // drawing a stationary charge as a pink oval (moved x/y oval coords are centering the oval to the right place)
		Graphics2D g2d = (Graphics2D) g;
		Color color = new Color(0,255,0);
		g2d.setColor(color);
		g2d.fillOval((int)((xCartCoord) * x + 13 + xOffset), (int)((yCartCoord ) * y + 13 + yOffset), 10, 10);
	}
}
