package co.cafeto.adaa.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.coyote.ajp.AjpAprProtocol;

import co.cafeto.bp3.model.Impl.AdjacentItem;
import co.cafeto.bp3.model.Impl.AdjacentList;
import co.cafeto.bp3.model.Impl.Bp3process;
import co.cafeto.bp3.model.Impl.EdgeImpl;
import co.cafeto.bp3.model.Impl.NodeImpl;

public class ProcessAdjacentTransform {
	
	public static AdjacentList transformProcessIntoAdjacentList(Bp3process process) throws Exception {
		if (process != null && process.getNodes() != null && 
				process.getNodes().length >= 2 && 
				process.getEdges() != null && 
				process.getEdges().length >= 1) {
			
			ArrayList<EdgeImpl> edgeList = new ArrayList<>(Arrays.asList(process.getEdges()));
			ArrayList<AdjacentItem> edges = new ArrayList<>();
			Map<String, NodeImpl> nodeMap = getNodeMap(process.getNodes());
			
			for (int i = 0; i < process.getNodes().length; i++) {		
				NodeImpl actualNode = process.getNodes()[i];
				
				ArrayList<EdgeImpl> temporalEdgeList = new ArrayList<>(edgeList.stream().filter(a -> a.getFrom() == actualNode.getId()).collect(Collectors.toList()));
				
				if (temporalEdgeList.size() > 0) {
					NodeImpl[] LocalAdjacentNodes = new NodeImpl[temporalEdgeList.size()];
					for (int j = 0; j < temporalEdgeList.size(); j++) {
						LocalAdjacentNodes[j] = nodeMap.get(temporalEdgeList.get(j).getTo());
					}
					edges.add(new AdjacentItem(nodeMap.get(actualNode.getId()), LocalAdjacentNodes));
				}
				
			}
			
			return new AdjacentList(process.getNodes(),edges.toArray(new AdjacentItem[edges.size()]));
		}
		throw new Exception("Could not trasform input into an Adjacent List");
	}
	
	private static Map<String, NodeImpl> getNodeMap(NodeImpl[] nodes) {
		Map<String, NodeImpl> returnMap= new HashMap<>();
		for (NodeImpl nodeImpl : nodes) {
			returnMap.put(nodeImpl.getId(), nodeImpl);
		}
		return returnMap;
	}

	public static Bp3process transformListIntoProcess(AdjacentList adjacentList) {
		ArrayList<EdgeImpl> temporalEdgeList = new ArrayList<>();
		
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
