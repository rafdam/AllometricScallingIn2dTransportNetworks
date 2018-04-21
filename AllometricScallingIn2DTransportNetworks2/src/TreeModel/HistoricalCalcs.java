package TreeModel;

public class HistoricalCalcs {
	
	HubList verticleList;
	EdgeList maximalNetworkEdgeList;
	EdgeList minimalSpanningEdgeList;
	EdgeList maximalMinimal;
	HubList subNetwork;
	TreeMap tree;
	
	public HistoricalCalcs(HubList list, EdgeList maximalNetwork, EdgeList minimalTree) {
		verticleList = list;
		maximalNetworkEdgeList = maximalNetwork;
		minimalSpanningEdgeList = minimalTree;
		subNetwork = new HubList();
	}
	public HistoricalCalcs(HubList list, HubList subNet, EdgeList maximalNetwork, EdgeList minimalTree, EdgeList maximalMinimalTree) {
		verticleList = list;
		maximalNetworkEdgeList = maximalNetwork;
		minimalSpanningEdgeList = minimalTree;
		maximalMinimal = maximalMinimalTree;
		subNetwork = subNet;
	}
	
	public HistoricalCalcs(HubList list, HubList subNet, EdgeList maximalNetwork, EdgeList minimalTree) {
		verticleList = list;
		maximalNetworkEdgeList = maximalNetwork;
		minimalSpanningEdgeList = minimalTree;
		//maximalMinimal = maximalMinimalTree;
		subNetwork = subNet;
	}
	
	public HistoricalCalcs(){
		
	}
	
	public HubList getVerticleList(){
		return verticleList;
	}
	
	public HubList getSubNetwork(){
		return subNetwork;
	}
	
	public EdgeList getMaximalNetworkEdgeList(){
		return maximalNetworkEdgeList;
	}
	
	public EdgeList getMinimalSpanningEdgeList(){
		return minimalSpanningEdgeList;
	}
	public EdgeList getMaximalMinimalTree(){
		return maximalMinimal;
	}
	public TreeMap getTreeMap(){
		return tree;
	}
	
	public void setParams(HubList list, EdgeList maximalNetwork, EdgeList minimalTreeList){
		verticleList = list;
		maximalNetworkEdgeList = maximalNetwork;
		minimalSpanningEdgeList = minimalTreeList;
	}
}
