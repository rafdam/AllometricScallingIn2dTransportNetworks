package TreeModel;

import java.util.ArrayList;

public class MinimalSpanningTree {
private EdgeList edges;
private String MSTtime;
	public MinimalSpanningTree(HubList list, int hubNumber) {
		int iterAdress = hubNumber;
		int tmpAdress;
		edges = new EdgeList();
		ArrayList<Integer> checkedHubAdresses = new ArrayList<Integer>(); // list of hubs reached by the network edges
		ArrayList<Integer> adressesToCheck = new ArrayList<Integer>(); //list of hubs to check their neighbours
		boolean[] ifChecked = new boolean[list.size()];
		adressesToCheck.add(iterAdress);
		checkedHubAdresses.add(hubNumber);
		ifChecked[hubNumber] = true;
		long start = System.currentTimeMillis();
		for (int ii = 0; ii < adressesToCheck.size(); ii++){
			try{
				for (int jj = 0; jj < list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().size();jj++){
					tmpAdress = list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().get(jj);
					if(ifChecked[tmpAdress] == true){
						//do nothing, get another adress
					}
					else{
						//checkedHubAdresses.add(tmpAdress);
						ifChecked[tmpAdress] = true;
						list.get(tmpAdress).setLevel(list.get(adressesToCheck.get(ii)).getLevel() + 1);
						edges.add(new Edge(adressesToCheck.get(ii), tmpAdress, list.get(adressesToCheck.get(ii)).getLevel()+1,
								list.get(adressesToCheck.get(ii)).getxCartCoord(), 
								list.get(tmpAdress).getxCartCoord(), 
								list.get(adressesToCheck.get(ii)).getyCartCoord(), 
								list.get(tmpAdress).getyCartCoord()));
						adressesToCheck.add(tmpAdress);
						//System.out.println(tmpAdress);
					}
				}
			}
			catch(IndexOutOfBoundsException ee){
				System.out.println("Koniec");
			}
			//System.out.println("-------------------------------");
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
	
	public EdgeList getEdges(){
		return edges;
	}
	
	
}
