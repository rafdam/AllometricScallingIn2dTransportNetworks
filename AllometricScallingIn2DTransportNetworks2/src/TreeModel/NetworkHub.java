package TreeModel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import GUI.BasicFrame;

public class NetworkHub {
private short xCartCoord; // coordinates defined in cartesian metric system
private short yCartCoord;
//private short zCartCoord;
private int level; // defines the level of the hub specified by spanning tree
private ArrayList<Integer> neighbourIndexesList;
	
	public NetworkHub(short x, short y) {
		xCartCoord = x;
		yCartCoord = y;
		//zCartCoord = z;
		level = 0;
		new ArrayList<NetworkHub>();
		neighbourIndexesList = new ArrayList<Integer>();
	}

	public short getxCartCoord() {
		return xCartCoord;
	}

	public void setxCartCoord(short xCartCoord) {
		this.xCartCoord = xCartCoord;
	}

	public short getyCartCoord() {
		return yCartCoord;
	}

	public void setyCartCoord(short yCartCoord) {
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
	
	public ArrayList<Integer> getNeighbourIndexesList(){
		return neighbourIndexesList;
	}
	
	public void draw(Graphics g, double x, double y) { // drawing a stationary charge as a pink oval (moved x/y oval coords are centering the oval to the right place)
		g.setColor(Color.YELLOW);
		g.fillOval((int)((xCartCoord) * x + 1), (int)((yCartCoord) * y + 1), 6, 6);
	}
}
