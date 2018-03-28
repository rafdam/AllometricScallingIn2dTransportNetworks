package TreeModel;

import java.util.ArrayList;
import java.util.Arrays;

public class TreeMap {
private HubList map; // 
private EdgeList edges; // list containing all the edges with their weights
private int size; // true / false cube representing if the hub is in the spot or not
public double hubbingProbability; //just a container to later statistics
private String MNtime;

	public TreeMap(int siz, double prob, int neighbours){
		map = new HubList();
		edges = new EdgeList();
		size = siz;
		hubbingProbability = prob;
		
		int[][] cuboid = new int[siz][siz];
		ArrayList<XYZ> points = new ArrayList<XYZ>();
		for (int ii = 0; ii < siz; ii++){
			for (int jj = 0; jj < siz; jj++){
				if (Math.random() <= prob){
					points.add(new XYZ(ii, jj, 0));
				}
			}
		}
		points.add(new XYZ(0, 0, 0));
		points.add(new XYZ(0, 0, 0));
		points.add(new XYZ(0, 0, 0));
		ITRIANGLE[]	 triangles 	= new ITRIANGLE[(points.size() - 3) * 3];
		for (int i=0; i<triangles.length; i++)
			triangles[i] = new ITRIANGLE();
		Triangulate triang = new Triangulate();
		int ntri = triang.Triangulator((points.size() - 3), points, triangles);
		for (int tt = 0; tt < ntri; tt++){
			//System.out.println("vertex( "+(int)points.get(triangles[tt].p1).x+","+(int)points.get(triangles[tt].p1).y+");");
			//System.out.println("vertex( "+(int)points.get(triangles[tt].p2).x+","+(int)points.get(triangles[tt].p2).y+");");
			//System.out.println("vertex( "+(int)points.get(triangles[tt].p3).x+","+(int)points.get(triangles[tt].p3).y+");");
			if(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] == 0){
				map.add(new NetworkHub((int)points.get(triangles[tt].p1).x, (int)points.get(triangles[tt].p1).y));
				cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] = map.size();
			}
			if(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] == 0){
				map.add(new NetworkHub((int)points.get(triangles[tt].p2).x, (int)points.get(triangles[tt].p2).y));
				cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] = map.size();
			}
			if(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] == 0){
				map.add(new NetworkHub((int)points.get(triangles[tt].p3).x, (int)points.get(triangles[tt].p3).y));
				cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] = map.size();
			}
		}
		boolean[][] edgoid = new boolean[map.size()][map.size()];
		//Arrays.fill(edgoid, false);
		for (int tt = 0; tt < ntri; tt++){
			if(edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
					 [cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1] != true){
				edges.add(new Edge(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1,
						cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1, 1,
						(int)points.get(triangles[tt].p1).x, (int)points.get(triangles[tt].p2).x,
						(int)points.get(triangles[tt].p1).y, (int)points.get(triangles[tt].p2).y));
				map.get(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1).addToNeighbourIndexesList(
						cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1);
				map.get(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1).addToNeighbourIndexesList(
						cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1);
				edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
						 [cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1] = true;
			}
			if(edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
					 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] != true){
				edges.add(new Edge(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1,
						cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1, 1,
						(int)points.get(triangles[tt].p1).x, (int)points.get(triangles[tt].p3).x,
						(int)points.get(triangles[tt].p1).y, (int)points.get(triangles[tt].p3).y));
				map.get(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1).addToNeighbourIndexesList(
						cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1);
				map.get(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1).addToNeighbourIndexesList(
						cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1);
				edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
						 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] = true;
			}
			if(edgoid[cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1]
					 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] != true){
				edges.add(new Edge(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1,
						cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1, 2,
						(int)points.get(triangles[tt].p2).x, (int)points.get(triangles[tt].p2).x,
						(int)points.get(triangles[tt].p2).y, (int)points.get(triangles[tt].p2).y));
				map.get(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1).addToNeighbourIndexesList(
						cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1);
				map.get(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1).addToNeighbourIndexesList(
						cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1);
				
				edgoid[cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1]
						 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] = true;
			}	
		}
		/*
			copy-paste the following output into free processing:
			http://processing.org/
		*/
		/*
		for (int tt=0; tt<ntri; tt++)
		{
			System.out.println("beginShape(TRIANGLES);");
			System.out.println("vertex( "+points.get(triangles[tt].p1).x+","+points.get(triangles[tt].p1).y+");");
			System.out.println("vertex( "+points.get(triangles[tt].p2).x+","+points.get(triangles[tt].p2).y+");");
			System.out.println("vertex( "+points.get(triangles[tt].p3).x+","+points.get(triangles[tt].p3).y+");");
			System.out.println("endShape();");
		}
		*/
		
		
		
		//Generating k-nearest neighbours for given graph defined by HubList map and cube[][] keeping indexes
		/*
		int tmpXCoord;
		int tmpYCoord;
		int loopCounter;
		int tmpNeighboursCount = 0;
		int maxVal = (siz * siz) + 1;
		int[][] cube = new int[siz][siz];
		
		size = siz;
		hubbingProbability = prob;
		map = new HubList();
		edges = new EdgeList();
		long start = System.currentTimeMillis();
		int[][] cuboid = new int[siz][siz];
		for (int ii = 0; ii < siz; ii++){
			for (int jj = 0; jj < siz; jj++){
				if (Math.random() <= prob){
					map.add(new NetworkHub((short)ii, (short)jj));
					try{
						cube[ii][jj] = (int) (map.getSize());
						cuboid[ii][jj] = 1;
					}
					catch(NullPointerException ee){
						cube[ii][jj] = maxVal;
						cuboid[ii][jj] = 0;
					}
				}
				else{
					cube[ii][jj] = maxVal;
					cuboid[ii][jj] = 0;
				}
			}
		}
		for (int listIndex = 0; listIndex < map.size(); listIndex++){
			loopCounter = 1;
			try{
				tmpNeighboursCount = (int) map.get(listIndex).getNeighbourIndexesList().size();
			}
			catch(NullPointerException e){
				//when given hub have no neighbours connected it has null neighbourList
			}
			tmpXCoord = map.get(listIndex).getxCartCoord();
			tmpYCoord = map.get(listIndex).getyCartCoord();
			//Looping over loopCounter lvl ring around given Hub
			while (tmpNeighboursCount < (int)(neighbours)){
			//for (int zz = 1; zz < (int) 1 / prob; zz++){
				for (int ii = -loopCounter; ii <= loopCounter; ii ++){
					if (tmpNeighboursCount == neighbours){
						break;
					}
					for (int jj = -loopCounter; jj <= loopCounter; jj++){
						if (tmpNeighboursCount == neighbours){
							break;
						}	
							try{
								if(ii == -loopCounter || ii == loopCounter //condition that guarantee that no point that has been checked before gonna be checked again
								|| jj == -loopCounter || jj == loopCounter){
									if(cube[tmpXCoord+ii][tmpYCoord+jj] != maxVal){
										if (map.get(cube[tmpXCoord+ii][tmpYCoord+jj] - 1).
												getNeighbourIndexesList().size() >= (int) neighbours){
											//next minicube
										}
										else{
											try{	
												map.get(listIndex).addToNeighbourIndexesList(cube[tmpXCoord+ii][tmpYCoord+jj] - 1);
												map.get(cube[tmpXCoord+ii][tmpYCoord+jj] - 1).addToNeighbourIndexesList(listIndex);
												tmpNeighboursCount = tmpNeighboursCount + 1;
												edges.add(new Edge(listIndex,cube[tmpXCoord+ii][tmpYCoord+jj], 0,
														tmpXCoord,
														map.get(cube[tmpXCoord+ii][tmpYCoord+jj]-1).getxCartCoord(),
														tmpYCoord,
														map.get(cube[tmpXCoord+ii][tmpYCoord+jj]-1).getyCartCoord()));
											}
											catch(NullPointerException yyy){
												//Shouldn't even be possible BUT just in case 
											}
										}
									}
									else{
										//do nothing and go for another miniCube
									}
								}
							}
							catch(ArrayIndexOutOfBoundsException zzz){
								//in case the algorithm would like to check -1 index in the cube
							}
						}
					}
				loopCounter = loopCounter + 1;
				if (loopCounter > siz){
					break;
				}
			}
		}
		MNtime = Long.toString(System.currentTimeMillis() - start);
		*/
	}	
	
	public String getMNTime(){
		return MNtime;
	}
	
	public HubList getNetwork(){
		return map;
	}
	
	public int getSize(){
		return size;
	}
	
	public EdgeList getEdges(){
		return edges;
	}
}
