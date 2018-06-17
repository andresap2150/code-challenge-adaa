package co.cafeto.adaa.business;

import co.cafeto.bp3.model.Impl.AdjacentItem;
import co.cafeto.bp3.model.Impl.NodeImpl;

public class AdjacentListUtil {
	
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
