package TreeModel;

import java.awt.HeadlessException;
import java.util.ArrayList;

public class HubList extends ArrayList<NetworkHub> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HubList() throws HeadlessException {
		super();
	}
	
	public boolean add(NetworkHub hub){
		return super.add(hub);
	}
	
	public int getSize(){
		return super.size();
	}
}
