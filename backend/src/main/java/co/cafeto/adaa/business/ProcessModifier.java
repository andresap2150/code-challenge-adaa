package co.cafeto.adaa.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bp3.NodeType;

import co.cafeto.bp3.model.Impl.AdjacentItem;
import co.cafeto.bp3.model.Impl.AdjacentList;
import co.cafeto.bp3.model.Impl.Bp3process;
import co.cafeto.bp3.model.Impl.NodeImpl;

public class ProcessModifier {
	
	private Bp3process inputprocess;

	public ProcessModifier(Bp3process process) {
		super();
		this.inputprocess = process;
	}

	public Bp3process eraseNodesByTypeConfig() throws Exception {
		AdjacentList adjacentList = ProcessAdjacentTransform.transformProcessIntoAdjacentList(inputprocess);
		
		List<AdjacentItem> edgeList = new ArrayList<>(Arrays.asList(adjacentList.getProcess()));
		//in order to deliver a fast solution, first we get all the nodes to erase
		List<AdjacentItem> eraseEdgeList = edgeList.stream()
														.filter(s -> s.getNode().equalsType(NodeType.SERVICE_TASK))
														.collect(Collectors.toList());
		//we erase one by one
		for (AdjacentItem adjacentItem : eraseEdgeList) {
			adjacentList.deleteAdjacentItem(adjacentItem);
		}

		return null;
	}
	
		
}
