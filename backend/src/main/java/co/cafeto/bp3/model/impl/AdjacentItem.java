package co.cafeto.bp3.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjacentItem {
	private NodeImpl node;
	private NodeImpl[] adjacent;
	
	private List<NodeImpl> adjacents;
	
	public AdjacentItem(NodeImpl node, NodeImpl[] adjacent) {
		super();
		this.node = node;
		this.adjacent = adjacent;
		this.adjacents = new ArrayList<>(Arrays.asList(adjacent));
	}
	
	public NodeImpl getNode() {
		return node;
	}
	public void setNode(NodeImpl node) {
		this.node = node;
	}
	public NodeImpl[] getAdjacent() {
		return adjacent;
	}
	public void setAdjacent(NodeImpl[] adjacent) {
		this.adjacent = adjacent;
	}
	@Override
	public boolean equals(Object o) {		
		AdjacentItem item = (AdjacentItem) o;
		if (!this.node.equals(item.node))
			return false;
		if (item.adjacent.length != this.adjacent.length)
			return false;
		for (int i = 0; i < this.adjacent.length; i++) {
			if(!this.adjacent[i].equals(item.adjacent[i]))
				return false;
		}
		return true;
	}

	public void addEdges(NodeImpl[] updateThis) {
		adjacents = new ArrayList<>(Arrays.asList(this.adjacent));
		for (NodeImpl nodeImpl : updateThis) {
			if(!adjacents.contains(nodeImpl)) 
				adjacents.add(nodeImpl);
		}
		this.adjacent = adjacents.toArray(new NodeImpl[adjacents.size()]);
	}
	
	public boolean adjacentListHasNode(NodeImpl node) {
		return adjacents.contains(node);
	}

	public void removeEdge(NodeImpl nodeImpl) {
		adjacents.remove(nodeImpl);	
		adjacent = adjacents.toArray(new NodeImpl[adjacents.size()]);
	}
}
