import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bp3.NodeType;

import co.cafeto.adaa.business.AdjacentListUtil;
import co.cafeto.adaa.business.ProcessAdjacentTransform;
import co.cafeto.bp3.model.Impl.AdjacentItem;
import co.cafeto.bp3.model.Impl.AdjacentList;
import co.cafeto.bp3.model.Impl.Bp3process;
import co.cafeto.bp3.model.Impl.EdgeImpl;
import co.cafeto.bp3.model.Impl.NodeImpl;

public class ProcessAdjacentTransformTest {
	@Test
	public void testTransformProcessIntoAdjacentList() {
		Bp3process inputProcess = new Bp3process();
		
		NodeImpl processGatewayNode0 = new NodeImpl("0", "Start", NodeType.START);
		NodeImpl processGatewayNode1 = new NodeImpl("1", "A", NodeType.SERVICE_TASK);
		NodeImpl processGatewayNode2 = new NodeImpl("2", "B", NodeType.HUMAN_TASK);
		NodeImpl processGatewayNode3 = new NodeImpl("3", "G1", NodeType.GATEWAY);
		NodeImpl processGatewayNode4 = new NodeImpl("4", "C", NodeType.HUMAN_TASK);
		NodeImpl processGatewayNode5 = new NodeImpl("5", "D", NodeType.HUMAN_TASK);
		NodeImpl processGatewayNode6 = new NodeImpl("6", "G2", NodeType.GATEWAY);
		NodeImpl processGatewayNode7 = new NodeImpl("7", "#", NodeType.SERVICE_TASK);
		NodeImpl processGatewayNode8 = new NodeImpl("8", "end", NodeType.END);		
		NodeImpl[] processNodes = new NodeImpl[] {processGatewayNode0,processGatewayNode1,processGatewayNode2,processGatewayNode3,processGatewayNode4,processGatewayNode5,processGatewayNode6,processGatewayNode7,processGatewayNode8};
		
		inputProcess.setNodes(processNodes);
		
		EdgeImpl edge0 = new EdgeImpl("0","1");
		EdgeImpl edge1 = new EdgeImpl("1","2");
		EdgeImpl edge2 = new EdgeImpl("2","3");
		EdgeImpl edge3 = new EdgeImpl("3","4");
		EdgeImpl edge4 = new EdgeImpl("3","5");
		EdgeImpl edge5 = new EdgeImpl("4","6");
		EdgeImpl edge6 = new EdgeImpl("5","6");
		EdgeImpl edge7 = new EdgeImpl("6","7");
		EdgeImpl edge8 = new EdgeImpl("7","8");		
		EdgeImpl[] processEdges = new EdgeImpl[] {edge0,edge1,edge2,edge3,edge4,edge5,edge6,edge7,edge8};
		
		inputProcess.setEdges(processEdges);
		
		AdjacentItem edgeGW0 = new AdjacentItem(processGatewayNode0, new NodeImpl[] {processGatewayNode1});
		AdjacentItem edgeGW1 = new AdjacentItem(processGatewayNode1, new NodeImpl[] {processGatewayNode2});
		AdjacentItem edgeGW2 = new AdjacentItem(processGatewayNode2, new NodeImpl[] {processGatewayNode3});
		AdjacentItem edgeGW3 = new AdjacentItem(processGatewayNode3, new NodeImpl[] {processGatewayNode4, processGatewayNode5});
		AdjacentItem edgeGW4 = new AdjacentItem(processGatewayNode4, new NodeImpl[] {processGatewayNode6});
		AdjacentItem edgeGW5 = new AdjacentItem(processGatewayNode5, new NodeImpl[] {processGatewayNode6});	
		AdjacentItem edgeGW6 = new AdjacentItem(processGatewayNode6, new NodeImpl[] {processGatewayNode7});
		AdjacentItem edgeGW7 = new AdjacentItem(processGatewayNode7, new NodeImpl[] {processGatewayNode8});
		AdjacentItem[] adjacentEdges = new AdjacentItem[] {edgeGW0,edgeGW1,edgeGW2,edgeGW3,edgeGW4,edgeGW5,edgeGW6,edgeGW7};
		
		AdjacentList adjacentList = new AdjacentList(processNodes, adjacentEdges);
		
		AdjacentList actualAdjacentList = null;
		try {
			actualAdjacentList = ProcessAdjacentTransform.transformProcessIntoAdjacentList(inputProcess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(adjacentList, actualAdjacentList);
	}
}
