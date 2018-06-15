package co.cafeto.bp3.model.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bp3.NodeType;

public class AdjacentList {
	private NodeImpl[] nodeList;
	private AdjacentItem[] process;
	
	public AdjacentList(NodeImpl[] nodeList, AdjacentItem[] process) {
		super();
		this.nodeList = nodeList;
		this.process = process;		
	}

	public AdjacentList() {
		super();
	}
	
	public AdjacentItem[] getProcess() {
		return process;
	}

	public void setProcess(AdjacentItem[] process) {
		this.process = process;
	}

	public NodeImpl[] getNodeList() {
		return nodeList;
	}

	public void setNodeList(NodeImpl[] nodeList) {
		this.nodeList = nodeList;
	}

	public void isolateNode(NodeImpl nodeToIsolate){
		
		AdjacentItem source = null;   //items to delete
		ArrayList<AdjacentItem> destination = new ArrayList<>(); // items to modify
		
		for (AdjacentItem adjacentItem : process) {
			if (adjacentItem.getNode().equals(nodeToIsolate)) {
				source = adjacentItem; //we have to keep the destination of this
			}
			for (NodeImpl nodeImpl : adjacentItem.getAdjacent()) {
				if(nodeImpl.equals(nodeToIsolate)) {
					destination.add(adjacentItem); //we have to keep the origin of this
					if (adjacentItem.getNode().equalsType(NodeType.GATEWAY)) //if the type is gateway, we have to aisle the gateway too
						isolateNode(adjacentItem.getNode()); //we do that with recursion
				}
			}
		}
		
		ArrayList<AdjacentItem> temporalList = new ArrayList<>();
		for (AdjacentItem adjacentItem : destination) {
			for (NodeImpl node : adjacentItem.getAdjacent()) {
				if (node.equals(nodeToIsolate))
					temporalList.add(new AdjacentItem(adjacentItem.getNode(),source.getAdjacent()));
			}
		}
		
		ArrayList<AdjacentItem> processAsList = new ArrayList<>(Arrays.asList(process));
		destination.add(source);
		processAsList.removeAll(destination);
		processAsList.addAll(temporalList);
		
		this.process = processAsList.toArray(new AdjacentItem[processAsList.size()]);	
	}

	
	@Override
	public boolean equals(Object obj) {
		AdjacentList adja = (AdjacentList) obj;
		if (this.nodeList.length != adja.nodeList.length)
			return false;
		for (int i = 0; i < this.nodeList.length; i++) {
			if (!this.nodeList[i].equals(adja.nodeList[i]))
				return false;
		}
		if(this.process.length != adja.process.length)
			return false;
		for (int j = 0; j < process.length; j++) {
			if (!this.process[j].equals(adja.process[j]))
				return false;
		}
		return true;
	}

	public void deleteAdjacentItem(AdjacentItem nodeAndEdges) {
		//serch the nodes who have references to that node
		NodeImpl deleteThis = nodeAndEdges.getNode();
		ArrayList<AdjacentItem> edges = new ArrayList<>(Arrays.asList(process));
		//return the new edges with the references updated
		List<AdjacentItem> result = edges.stream()//.filter( a -> a.adjacentListHasNode(deleteThis))
				.map(s->  reemplazar(s, nodeAndEdges))						 
				.collect(Collectors.toList());
		
		process = result.toArray(new AdjacentItem[result.size()]);
		
		ArrayList<NodeImpl> nodes = new ArrayList<>(Arrays.asList(nodeList));
		nodes.remove(nodeAndEdges.getNode());
		
		nodeList = nodes.toArray(new NodeImpl[nodes.size()]);
	}

	public static AdjacentItem reemplazar(AdjacentItem s, AdjacentItem deleteObject) {
		if (s.adjacentListHasNode(deleteObject.getNode())) {
			NodeImpl deleteThis = deleteObject.getNode();
			NodeImpl[] updateThis = deleteObject.getAdjacent();
			for (NodeImpl nodeImpl : s.getAdjacent()) {
				if (nodeImpl.equals(deleteThis))
					s.addEdges(updateThis);//we have to insert and review if the object is already in the list									
			}
			s.removeEdge(deleteThis);
		}
		return s;
	}
}

