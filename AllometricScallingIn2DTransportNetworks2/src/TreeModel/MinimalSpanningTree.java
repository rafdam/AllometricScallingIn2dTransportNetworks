package TreeModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinimalSpanningTree {
private EdgeList edges;
private String MSTtime;
private HubList subNetwork;
private ArrayList<Integer> lastHubs;
private HubList list;
private int lastHub;
private int minimalReq;

	public MinimalSpanningTree(HubList hubList, int hubNumber, boolean ifSubNetwork, EdgeList edgesList) {
		lastHub = hubNumber;
		int tmpAdress;
		list = hubList;
		edges = new EdgeList();
		lastHubs = new ArrayList<Integer>();
		ArrayList<Integer> adressesToCheck = new ArrayList<Integer>(); //list of hubs to check their neighbours
		boolean[] ifChecked = new boolean[list.size()];
		adressesToCheck.add(hubNumber);
		ifChecked[hubNumber] = true;
		int reachedHubs = 1;
		list.get(adressesToCheck.get(0)).setLevel(-1);
		long start = System.currentTimeMillis();
		int minDistEdge = 0;
		if(ifSubNetwork == false){
			//new algorithm, taking verticles distances
			for(int ii = 0; ii < list.get(adressesToCheck.get(0)).getNeighbourIndexesList().size(); ii++){
				edges.add(new Edge(adressesToCheck.get(0), list.get(adressesToCheck.get(0)).getNeighbourIndexesList().get(ii),
						0, list.get(adressesToCheck.get(0)).getxCartCoord(),
						list.get(list.get(adressesToCheck.get(0)).getNeighbourIndexesList().get(ii)).getxCartCoord(),
						list.get(adressesToCheck.get(0)).getyCartCoord(),
						list.get(list.get(adressesToCheck.get(0)).getNeighbourIndexesList().get(ii)).getyCartCoord(), 1));
				
			}
			minDistEdge = 0;
			Collections.sort(edges, Edge.EdgeDist);
			while(reachedHubs < list.size()){
				if(ifChecked[edges.get(minDistEdge).getEndHubAdress()] == false){
					ifChecked[edges.get(minDistEdge).getEndHubAdress()] = true;
					list.get(edges.get(minDistEdge).getStartHubAdress()).addToMinimalNeighbourIndexesList(edges.get(minDistEdge).getEndHubAdress());
					list.get(edges.get(minDistEdge).getEndHubAdress()).addToReverseList(edges.get(minDistEdge).getStartHubAdress());
					list.get(edges.get(minDistEdge).getStartHubAdress()).setLevel(list.get(edges.get(minDistEdge).getEndHubAdress()).getLevel() + 1);
					for(int jj = minDistEdge; jj < list.get(edges.get(minDistEdge).getEndHubAdress()).getNeighbourIndexesList().size(); jj ++){
						edges.add(new Edge(edges.get(minDistEdge).getEndHubAdress(), list.get(edges.get(minDistEdge).getEndHubAdress()).getNeighbourIndexesList().get(jj),
								minDistEdge, list.get(edges.get(minDistEdge).getEndHubAdress()).getxCartCoord(),
								list.get(list.get(edges.get(minDistEdge).getEndHubAdress()).getNeighbourIndexesList().get(jj)).getxCartCoord(),
								list.get(edges.get(minDistEdge).getEndHubAdress()).getyCartCoord(),
								list.get(list.get(edges.get(minDistEdge).getEndHubAdress()).getNeighbourIndexesList().get(jj)).getyCartCoord(), 1));
					
					}
					edges.remove(minDistEdge);
					
					
					try{
						Collections.sort(edges, Edge.EdgeDist);
					}
					catch(IllegalArgumentException ee){
						break;
					}
					reachedHubs++;
				}
				else{
					edges.remove(minDistEdge);
				}
			}
			
			
			
			lastHub = hubNumber;
			list = hubList;
			edges = new EdgeList();
			lastHubs = new ArrayList<Integer>();
			adressesToCheck = new ArrayList<Integer>();
			ifChecked = new boolean[list.size()];
			adressesToCheck.add(hubNumber);
			ifChecked[hubNumber] = true;
			list.get(adressesToCheck.get(0)).setLevel(-1);
			subNetwork = new HubList();
			for (int ii = 0; ii < adressesToCheck.size(); ii++){
				try{
					for (int jj = 0; jj < list.get(adressesToCheck.get(ii)).getMinimalNeighbourIndexesList().size();jj++){
						tmpAdress = list.get(adressesToCheck.get(ii)).getMinimalNeighbourIndexesList().get(jj);
						if(ifChecked[tmpAdress] == true){
							//do nothing, get another adress
						}
						else{
							subNetwork.add(list.get(tmpAdress));
							ifChecked[tmpAdress] = true;
							list.get(tmpAdress).setLevel(list.get(adressesToCheck.get(ii)).getLevel() + 1);
							adressesToCheck.add(tmpAdress);
						}
					}
				}
				catch(IndexOutOfBoundsException ee){
					System.out.println("Koniec");
				}
			}
			edges.clear();
			weightFullNetworkEdges();
			
			
			
			//algorithm without checking the distance between 2 hubs in edge
			/*
			for (int ii = 0; ii < adressesToCheck.size(); ii++){
				try{
					for (int jj = 0; jj < list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().size();jj++){
						tmpAdress = list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().get(jj);
						if(ifChecked[tmpAdress] == true){
							
							// do nothing, get another adress
							// napisz algorytm który będzie brał WSZYSTKIE możliwe krawędzie na danym "poziomie" rozbudowy
							// drzewa i posortuje je po odległościach od ziomków. W ten sposób będzie minimal + directed
							// + trzeba rozkminić sposób na przechowywanie informacji o tym który ziomek jest początkowym a który końcowym
							// może jakiś 'if' sprawdzający czy jeden z wierzchołków krawędzi jest już użyty (troche drama)
						}
						else{
							ifChecked[tmpAdress] = true;
							list.get(tmpAdress).setLevel(list.get(adressesToCheck.get(ii)).getLevel() + 1);
							list.get(adressesToCheck.get(ii)).addToMinimalNeighbourIndexesList(tmpAdress);
							list.get(tmpAdress).addToReverseList(adressesToCheck.get(ii));
							adressesToCheck.add(tmpAdress);
						}
					}
				}
				catch(IndexOutOfBoundsException ee){
					//System.out.println("Koniec");
				}
			}
			weightFullNetworkEdges();
			*/
			 
		}
		else{
			subNetwork = new HubList();
			for (int ii = 0; ii < adressesToCheck.size(); ii++){
				try{
					for (int jj = 0; jj < list.get(adressesToCheck.get(ii)).getMinimalNeighbourIndexesList().size();jj++){
						tmpAdress = list.get(adressesToCheck.get(ii)).getMinimalNeighbourIndexesList().get(jj);
						if(ifChecked[tmpAdress] == true){
							//do nothing, get another adress
						}
						else{
							subNetwork.add(list.get(tmpAdress));
							ifChecked[tmpAdress] = true;
							list.get(tmpAdress).setLevel(list.get(adressesToCheck.get(ii)).getLevel() + 1);
							adressesToCheck.add(tmpAdress);
						}
					}
				}
				catch(IndexOutOfBoundsException ee){
					System.out.println("Koniec");
				}
			}
			weightSubNetworkEdges();
		}
		MSTtime = Long.toString(System.currentTimeMillis() - start);
	}
	public int MinimalRequiredAmount(){
		/*int amount = 0;
		int count = 0;
		for (int ii = 0; ii < edges.size(); ii++){
			amount += edges.get(ii).getWeight();
			count ++;
		}
		System.out.println("Ilość krawędzi: "+count);
		*/
		return minimalReq;
		
	}
	
	public String getMSTTime(){
		return MSTtime;
	}
	
	public HubList getSubNetwork(){
		return subNetwork;
	}
	
	public EdgeList getEdges(){
		return edges;
	}
	
	private void countMinimalSpan(){
		
	}
	private void weightFullNetworkEdges(){
		int tmpAdress;
		int weight = 0;
		edges.clear();
		boolean[][] ifChecked = new boolean[list.size()][list.size()];
		int maxLevelHub = list.stream().
				max(Comparator.comparing(NetworkHub::getLevel)).get().getLevel();
		for(int yy = 0; yy <= maxLevelHub; yy++){
			for (int ii = 0; ii< list.size() ; ii++){
				if(list.get(ii).getLevel() == maxLevelHub-yy){
					lastHubs.add(ii);
				}
			}
		}
		for(int jj = 0; jj < lastHubs.size(); jj++){
			weight = 0;
			for(int zz = 0; zz < list.get(lastHubs.get(jj)).getReverseMinimalTree().size(); zz++){
				tmpAdress = list.get(lastHubs.get(jj)).getReverseMinimalTree().get(zz);
				if(ifChecked[tmpAdress][lastHubs.get(jj)] == false){
					ifChecked[tmpAdress][lastHubs.get(jj)] = true;
					for(int ww = 0; ww < list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().size(); ww++){
						weight += list.get(list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().get(ww)).getWeight();
					}
					weight = weight  + 1;
					list.get(lastHubs.get(jj)).setWeight(weight);
					edges.add(new Edge(tmpAdress,lastHubs.get(jj),  weight,
							list.get(tmpAdress).getxCartCoord(),
							list.get(lastHubs.get(jj)).getxCartCoord(), 
							list.get(tmpAdress).getyCartCoord(),
							list.get(lastHubs.get(jj)).getyCartCoord(),
							list.get(lastHubs.get(jj)).getLevel()+1));
					lastHubs.add(tmpAdress);
				}
			}
			int amount = 0;
			for (int ii = 0; ii < edges.size(); ii++){
				amount += edges.get(ii).getWeight();
			}
			minimalReq = amount;
		}	
	}	
	
	private void weightSubNetworkEdges(){
		int tmpAdress;
		int weight = 0;
		boolean[][] ifChecked = new boolean[list.size()][list.size()];
		int maxLevelHub = list.stream().
				max(Comparator.comparing(NetworkHub::getLevel)).get().getLevel();
		for(int yy = 0; yy < maxLevelHub; yy++){
			for (int ii = 0; ii< list.size() ; ii++){
				if(list.get(ii).getLevel() == maxLevelHub-yy){
					lastHubs.add(ii);
				}
			}
		}
		for(int jj = 0; jj < lastHubs.size(); jj++){
			weight = 0;
			for(int zz = 0; zz < list.get(lastHubs.get(jj)).getReverseMinimalTree().size(); zz++){
				tmpAdress = list.get(lastHubs.get(jj)).getReverseMinimalTree().get(zz);
				if(ifChecked[tmpAdress][lastHubs.get(jj)] == false){
					if(lastHubs.get(jj) == lastHub){break;}
					ifChecked[tmpAdress][lastHubs.get(jj)] = true;
					for(int ww = 0; ww < list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().size(); ww++){
						weight += list.get(list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().get(ww)).getWeight();
					}
					weight = weight  + 1;
					list.get(lastHubs.get(jj)).setWeight(weight);
					edges.add(new Edge(tmpAdress,lastHubs.get(jj),  weight,
							list.get(tmpAdress).getxCartCoord(),
							list.get(lastHubs.get(jj)).getxCartCoord(), 
							list.get(tmpAdress).getyCartCoord(),
							list.get(lastHubs.get(jj)).getyCartCoord(),
							list.get(lastHubs.get(jj)).getLevel()+1));
					lastHubs.add(tmpAdress);
				}
			}
		}
		int amount = 0;
		for (int ii = 0; ii < edges.size(); ii++){
			amount += edges.get(ii).getWeight();
		}
		minimalReq = amount;
	}
}
