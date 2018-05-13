package TreeModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class TreeMap {
private HubList map; // list of all verticles
private EdgeList edges; // list containing all the edges with their weights
private int size; 
public double hubbingProbability; 
private String MNtime;

	public TreeMap(int siz, double prob, int neighbours){
		map = new HubList();
		edges = new EdgeList();
		size = siz;
		hubbingProbability = prob;
		long start = System.currentTimeMillis();
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
			if(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] == 0){
				map.add(new NetworkHub((int)points.get(triangles[tt].p1).x, (int)points.get(triangles[tt].p1).y, 0));
				cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] = map.size();
			}
			if(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] == 0){
				map.add(new NetworkHub((int)points.get(triangles[tt].p2).x, (int)points.get(triangles[tt].p2).y, 0));
				cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] = map.size();
			}
			if(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] == 0){
				map.add(new NetworkHub((int)points.get(triangles[tt].p3).x, (int)points.get(triangles[tt].p3).y, 0));
				cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] = map.size();
			}
		}
		
		boolean[][] edgoid = new boolean[map.size()][map.size()];
		double weight = 0;
		for (int tt = 0; tt < ntri; tt++){
			if(edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
					 [cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1] != true){
				weight = Math.sqrt(Math.pow(points.get(triangles[tt].p1).x - points.get(triangles[tt].p2).x, 2) +
						Math.pow(points.get(triangles[tt].p1).y - points.get(triangles[tt].p2).y, 2));
				edges.add(new Edge(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1,
						cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1, (int)weight,
						(int)points.get(triangles[tt].p1).x, (int)points.get(triangles[tt].p2).x,
						(int)points.get(triangles[tt].p1).y, (int)points.get(triangles[tt].p2).y,0));
				map.get(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1).
				addToNeighbourIndexesList(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1);
				map.get(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1).
				addToNeighbourIndexesList(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1);
				edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
						 [cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1] = true;
			}
			if(edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
					 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] != true){
				weight = Math.sqrt(Math.pow(points.get(triangles[tt].p1).x - points.get(triangles[tt].p3).x, 2) +
						Math.pow(points.get(triangles[tt].p1).y - points.get(triangles[tt].p3).y, 2));
				edges.add(new Edge(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1,
						cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1, (int)weight,
						(int)points.get(triangles[tt].p1).x, (int)points.get(triangles[tt].p3).x,
						(int)points.get(triangles[tt].p1).y, (int)points.get(triangles[tt].p3).y,0));
				map.get(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1).
				addToNeighbourIndexesList(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1);
				map.get(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1).
				addToNeighbourIndexesList(cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1);
				edgoid[cuboid[(int)points.get(triangles[tt].p1).x][(int)points.get(triangles[tt].p1).y] - 1]
						 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] = true;
			}
			if(edgoid[cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1]
					 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] != true){
				weight = Math.sqrt(Math.pow(points.get(triangles[tt].p2).x - points.get(triangles[tt].p3).x, 2) +
						Math.pow(points.get(triangles[tt].p2).y - points.get(triangles[tt].p3).y, 2));
				edges.add(new Edge(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1,
						cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1, (int)weight,
						(int)points.get(triangles[tt].p2).x, (int)points.get(triangles[tt].p3).x,
						(int)points.get(triangles[tt].p2).y, (int)points.get(triangles[tt].p3).y,0));
				map.get(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1).
				addToNeighbourIndexesList(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1);
				map.get(cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1).
				addToNeighbourIndexesList(cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1);
				edgoid[cuboid[(int)points.get(triangles[tt].p2).x][(int)points.get(triangles[tt].p2).y] - 1]
						 [cuboid[(int)points.get(triangles[tt].p3).x][(int)points.get(triangles[tt].p3).y] - 1] = true;
			}
		}
		
		
		MNtime = Long.toString(System.currentTimeMillis() - start);
		Collections.sort(edges, Edge.EdgeWeight);
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
