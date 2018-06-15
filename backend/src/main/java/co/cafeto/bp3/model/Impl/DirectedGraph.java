package co.cafeto.bp3.model.Impl;

import java.util.HashMap;
import java.util.HashSet;

import com.bp3.NodeType;

public class DirectedGraph {

	// node class consists of node name a.k.a id of node ,set of directed edges a.k.a arcs
	
	public static class NodeClass{
		 
		 int  id;
		 String name;
		 NodeType type; 
		 HashSet<edgeContents> edges;
		 
		 
		
		 public NodeClass(int id, String name, NodeType type) {
			super();
			this.id = id;
			this.name = name;
			this.type = type;
			this.edges = new HashSet<DirectedGraph.edgeContents>();
		}



		// copy constructor
		 public NodeClass(NodeClass anotherNode){
			 this.id = anotherNode.id;
			 this.name = anotherNode.name;
			 this.type = anotherNode.type;
			 this.edges = anotherNode.edges;
		 }
	}
	
	// definition of arc a.k.a edge
	public static class edgeContents{
		
		public edgeContents( NodeClass startingNode, NodeClass finishingNode){
			this.startingNode = startingNode;
			this.finishingNode = finishingNode;
		}
			
		@Override
	    public boolean equals(Object obj) {
	      edgeContents e = (edgeContents)obj;
	      return e.finishingNode == finishingNode && e.startingNode == startingNode;
	    }

		NodeClass startingNode;
		NodeClass finishingNode;
	}
	
	public static void addNode(DirectedGraph g,int id, String name, NodeType type){
		NodeClass nodeToAdd = new NodeClass(id,name,type);
		g.nodes.add(nodeToAdd);
		g.nodeMap.put(id, nodeToAdd);
	}
	
	/** 
	 * @param graph
	 * @param nodeToAdd
	 * Adds a node to the graph. Node is added to the HashMap nodeMap of the graph,node is added to HashSet nodes in graph 
	 */
	
	public  void addNode(DirectedGraph g,NodeClass nodeToAdd){

		g.nodes.add(nodeToAdd);
		g.nodeMap.put(nodeToAdd.id, nodeToAdd);
		
	}
	
	public void addEdges(DirectedGraph g,NodeClass startingNode,NodeClass finishingNode ){
		edgeContents edge = new edgeContents(startingNode, finishingNode);
		g.edges.add(edge);
		startingNode.edges.add(edge);
		nodes.add(startingNode);
	}
	
	public void isolateNode(DirectedGraph g,NodeClass nodeToIsolate){
		
	}
	
	public HashMap<Integer, NodeClass> getNodeMap(){
		return nodeMap;
	}
	
	public void setNodeMap(HashMap<Integer, NodeClass> nodeMap){
		
		this.nodeMap = nodeMap;
		
	}
	
	public  NodeClass getParticularNode(int id){
		if (nodeMap.containsKey(id))
			return nodeMap.get(id);
		
		return null;
	}
	
	public HashSet<NodeClass> getAllNodes(){
		return nodes;
	}
	
	public HashSet<edgeContents> getEdges(){
		return edges;
	}
	
	// contains set of nodes
	private  HashSet<NodeClass> nodes = new HashSet<DirectedGraph.NodeClass>();
	
	// set of directed edges
	private  HashSet<edgeContents> edges = new HashSet<DirectedGraph.edgeContents>();
	
	// maps a nodeId -> node
	private  HashMap<Integer, NodeClass> nodeMap = new HashMap<Integer, DirectedGraph.NodeClass>();
}
