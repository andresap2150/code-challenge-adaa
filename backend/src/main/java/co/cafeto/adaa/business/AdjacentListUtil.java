package co.cafeto.adaa.business;

import co.cafeto.bp3.model.impl.AdjacentItem;
import co.cafeto.bp3.model.impl.NodeImpl;

public class AdjacentListUtil {
	private AdjacentListUtil() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Returns an adjacent item with the destinations updated, if a edge 
	 * points to a node that is going to be erased, the destination on the 
	 * first node is replace with all the destination on the erased node 
	 * <p> 
	 *
	 * @param  s  				the adjacentItem to update
	 * @param  deleteObject 	the adjacentItem to erase, we keep the destinations of this
 	 * @return      			the first node with the destination updated
	 * @see         			AdjacentItem
	 */
	public static AdjacentItem replace(AdjacentItem s, AdjacentItem deleteObject) {
		if (s.adjacentListHasNode(deleteObject.getNode())) {
			NodeImpl deleteThis = deleteObject.getNode();
			NodeImpl[] updateThis = deleteObject.getAdjacent();
			for (NodeImpl nodeImpl : s.getAdjacent()) {
				if (nodeImpl.equals(deleteThis))
					s.addEdges(updateThis);//we have to insert and review if the object is already in the list									
			}
			s.removeEdge(deleteThis);//we delete the node
		}
		return s;
	}
}
