package TreeModel;

import java.util.ArrayList;
import java.util.Comparator;

public class MinimalSpanningTree {
private EdgeList edges;
private String MSTtime;
private HubList subNetwork;
private ArrayList<Integer> lastHubs;
private HubList list;
private int lastHub;
	public MinimalSpanningTree(HubList hubList, int hubNumber, boolean ifSubNetwork) {
		lastHub = hubNumber;
		int tmpAdress;
		list = hubList;
		edges = new EdgeList();
		lastHubs = new ArrayList<Integer>();
		ArrayList<Integer> adressesToCheck = new ArrayList<Integer>(); //list of hubs to check their neighbours
		boolean[] ifChecked = new boolean[list.size()];
		adressesToCheck.add(hubNumber);
		ifChecked[hubNumber] = true;
		System.out.println(list.get(adressesToCheck.get(0)).getLevel());
		long start = System.currentTimeMillis();
		if(ifSubNetwork == false){
			for (int ii = 0; ii < adressesToCheck.size(); ii++){
				try{
					for (int jj = 0; jj < list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().size();jj++){
						tmpAdress = list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().get(jj);
						if(ifChecked[tmpAdress] == true){
							// do nothing, get another adress
						}
						else{
							ifChecked[tmpAdress] = true;
							list.get(tmpAdress).setLevel(list.get(adressesToCheck.get(ii)).getLevel() + 1);
							list.get(adressesToCheck.get(ii)).addToMinimalNeighbourIndexesList(tmpAdress);
							list.get(tmpAdress).addToReverseList(adressesToCheck.get(ii));
							/*
							edges.add(new Edge(adressesToCheck.get(ii), tmpAdress, list.get(adressesToCheck.get(ii)).getLevel()+1,
									list.get(adressesToCheck.get(ii)).getxCartCoord(), 
									list.get(tmpAdress).getxCartCoord(), 
									list.get(adressesToCheck.get(ii)).getyCartCoord(), 
									list.get(tmpAdress).getyCartCoord(),
									list.get(adressesToCheck.get(ii)).getLevel()+1));
							*/
							adressesToCheck.add(tmpAdress);
						}
					}
				}
				catch(IndexOutOfBoundsException ee){
					System.out.println("Koniec");
				}
			}
			weightFullNetworkEdges();
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
							/*
							edges.add(new Edge(adressesToCheck.get(ii), tmpAdress, list.get(adressesToCheck.get(ii)).getLevel()+1,
									list.get(adressesToCheck.get(ii)).getxCartCoord(), 
									list.get(tmpAdress).getxCartCoord(), 
									list.get(adressesToCheck.get(ii)).getyCartCoord(), 
									list.get(tmpAdress).getyCartCoord(),
									list.get(adressesToCheck.get(ii)).getLevel()+1));
							*/
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
		int amount = 0;
		for (int ii = 0; ii < edges.size(); ii++){
			amount += edges.get(ii).getWeight();
		}
		return amount;
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
	
	private void weightFullNetworkEdges(){
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
					ifChecked[tmpAdress][lastHubs.get(jj)] = true;
					for(int ww = 0; ww < list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().size(); ww++){
						weight += list.get(list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().get(ww)).getWeight();//list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().size();
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
						weight += list.get(list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().get(ww)).getWeight();//list.get(lastHubs.get(jj)).getMinimalNeighbourIndexesList().size();
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
	}
}
