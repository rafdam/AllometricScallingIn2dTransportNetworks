package TreeModel;

import java.util.ArrayList;

public class NetworkHub {
private short xCartCoord; // coordinates defined in cartesian metric system
private short yCartCoord;
private short zCartCoord;
private int level; // defines the level of the hub specified by spanning tree
private ArrayList<Integer> neighbourIndexesList;
	
	public NetworkHub(short x, short y, short z) {
		xCartCoord = x;
		yCartCoord = y;
		zCartCoord = z;
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

	public short getzCartCoord() {
		return zCartCoord;
	}

	public void setzCartCoord(short zCartCoord) {
		this.zCartCoord = zCartCoord;
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
}
