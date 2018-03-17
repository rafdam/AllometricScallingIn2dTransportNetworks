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
		adressesToCheck.add(iterAdress);
		checkedHubAdresses.add(hubNumber);
		long start = System.currentTimeMillis();
		for (int ii = 0; ii < adressesToCheck.size(); ii++){
			for (int jj = 0; jj < list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().size();jj++){
				tmpAdress = list.get(adressesToCheck.get(ii)).getNeighbourIndexesList().get(jj);
				if(checkedHubAdresses.contains(tmpAdress)){
					//do nothing, get another adress
				}
				else{
					checkedHubAdresses.add(tmpAdress);
					list.get(tmpAdress).setLevel(list.get(adressesToCheck.get(ii)).getLevel() + 1);
					edges.add(new Edge(adressesToCheck.get(ii), tmpAdress, list.get(adressesToCheck.get(ii)).getLevel()+1));
					adressesToCheck.add(tmpAdress);
					//System.out.println(tmpAdress);
				}
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
