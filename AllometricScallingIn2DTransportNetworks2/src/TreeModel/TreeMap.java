package TreeModel;

public class TreeMap {
private HubList map; // 3d matrix of hubs dependent on probability
private EdgeList edges; // list containing all the edges with their weights
private int size; // true / false cube representing if the hub is in the spot or not
public double hubbingProbability; //just a container to later statistics
private String MNtime;
	public TreeMap(int siz, double prob, int neighbours) {
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
		for (int ii = 0; ii < siz; ii++){
			for (int jj = 0; jj < siz; jj++){
				if (Math.random() <= prob){
					map.add(new NetworkHub((short)ii, (short)jj));
					try{
						cube[ii][jj] = (int) (map.getSize());
						//System.out.println(ii + " " + jj + " " + zz);
					}
					catch(NullPointerException ee){
						cube[ii][jj] = maxVal;
						System.out.print("dickinson");
						//Giving 1st value that would never be reached, guarantee this point wouldn't be treated as a hub
					}
				}
				else{
					cube[ii][jj] = maxVal;
				}
				
			}
		}
		
		//Generating k-nearest neighbours for given graph defined by HubList map and cube[][][] keeping indexes
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
										if (map.get(cube[tmpXCoord+ii][tmpYCoord+jj] - 1).getNeighbourIndexesList().size() >= (int) neighbours){
											//next minicube
										}
										else{
											try{	
												map.get(listIndex).addToNeighbourIndexesList(cube[tmpXCoord+ii][tmpYCoord+jj] - 1);
												map.get(cube[tmpXCoord+ii][tmpYCoord+jj] - 1).addToNeighbourIndexesList(listIndex);
												tmpNeighboursCount = tmpNeighboursCount + 1;
												edges.add(new Edge(listIndex,cube[tmpXCoord+ii][tmpYCoord+jj], 1));
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
