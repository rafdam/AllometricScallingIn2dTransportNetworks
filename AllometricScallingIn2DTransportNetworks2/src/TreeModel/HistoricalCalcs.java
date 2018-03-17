package TreeModel;

public class HistoricalCalcs {
	
	HubList verticleList;
	EdgeList maximalNetworkEdgeList;
	EdgeList minimalSpanningEdgeList;
	
	public HistoricalCalcs(HubList list, EdgeList maximalNetwork, EdgeList minimalTree) {
		verticleList = list;
		maximalNetworkEdgeList = maximalNetwork;
		minimalSpanningEdgeList = minimalTree;
	}
	
	public HubList getVerticleList(){
		return verticleList;
	}
	
	public EdgeList getMaximalNetworkEdgeList(){
		return maximalNetworkEdgeList;
	}
	
	public EdgeList getMinimalSpanningEdgeList(){
		return minimalSpanningEdgeList;
	}

}
