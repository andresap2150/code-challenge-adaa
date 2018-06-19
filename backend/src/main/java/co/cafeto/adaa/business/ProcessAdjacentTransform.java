package co.cafeto.adaa.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import co.cafeto.adaa.exception.MappingProcessException;
import co.cafeto.bp3.model.impl.AdjacentItem;
import co.cafeto.bp3.model.impl.AdjacentList;
import co.cafeto.bp3.model.impl.Bp3process;
import co.cafeto.bp3.model.impl.EdgeImpl;
import co.cafeto.bp3.model.impl.NodeImpl;

public class ProcessAdjacentTransform {
	
	private ProcessAdjacentTransform() {
		throw new IllegalStateException("Utility class");
	}
	
	public static AdjacentList transformProcessIntoAdjacentList(Bp3process process) throws RuntimeException {
		if (process != null && process.getNodes() != null && 
				process.getNodes().length >= 2 && 
				process.getEdges() != null && 
				process.getEdges().length >= 1) {
			
			List<EdgeImpl> edgeList = new ArrayList<>(Arrays.asList(process.getEdges()));
			List<AdjacentItem> edges = new ArrayList<>();
			
			//in order to deliver a fast solution, nested for loop are implemented
			Map<String, NodeImpl> nodeMap = getNodeMap(process.getNodes()); 
			for (int i = 0; i < process.getNodes().length; i++) {		
				
				NodeImpl actualNode = process.getNodes()[i]; //get the nodes for easy referentiation
				//we get all the edges which start in the node to evaluate
				List<EdgeImpl> temporalEdgeList = new ArrayList<>(edgeList.stream().filter(a -> a.getFrom() == actualNode.getId()).collect(Collectors.toList()));
				
				if (!temporalEdgeList.isEmpty()) { // if we have at least one edge
					NodeImpl[] localAdjacentNodes = new NodeImpl[temporalEdgeList.size()];
					for (int j = 0; j < temporalEdgeList.size(); j++) {
						//we get all the destination of the starting edge and add to the destination list
						//a map collection is used because we only have the node id 
						localAdjacentNodes[j] = nodeMap.get(temporalEdgeList.get(j).getTo());
					}
					//the adjacent item is added to the process
					edges.add(new AdjacentItem(nodeMap.get(actualNode.getId()), localAdjacentNodes));
				}				
			}
			//we return and create the nodes and the adjacent list to the caller
			return new AdjacentList(process.getNodes(),edges.toArray(new AdjacentItem[edges.size()]));
		}
		throw new MappingProcessException();
	}
	
	private static Map<String, NodeImpl> getNodeMap(NodeImpl[] nodes) {
		//this logic is remove from the transformProcessIntoAdjacentList method to make the code more readable 
		Map<String, NodeImpl> returnMap= new HashMap<>();
		for (NodeImpl nodeImpl : nodes) {
			returnMap.put(nodeImpl.getId(), nodeImpl);
		}
		return returnMap;
	}
	
	
	public static Bp3process transformListIntoProcess(AdjacentList adjacentList) {
		List<EdgeImpl> temporalEdgeList = new ArrayList<>();
		//nested for is used because the matrix like nature of the object
		for (AdjacentItem adjacentItem : adjacentList.getProcess()) {
			for (NodeImpl adjacentNode : adjacentItem.getAdjacent()) {
				temporalEdgeList.add(new EdgeImpl(adjacentItem.getNode().getId(),adjacentNode.getId()));
			}
		}
		
		Bp3process output = new Bp3process();
		output.setNodes(adjacentList.getNodeList());
		output.setEdges(temporalEdgeList.toArray(new EdgeImpl[temporalEdgeList.size()]));
				
		return output;		
	}
}
