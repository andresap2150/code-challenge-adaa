import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bp3.NodeType;

import co.cafeto.bp3.model.Impl.AdjacentItem;
import co.cafeto.bp3.model.Impl.AdjacentList;
import co.cafeto.bp3.model.Impl.NodeImpl;

public class AjacentListTest {
	@Test
	public void testISolateNode(){
		NodeImpl node0 = new NodeImpl("0","Start",NodeType.START);
		NodeImpl node1 = new NodeImpl("1","ServiceTask",NodeType.SERVICE_TASK);
		NodeImpl node2 = new NodeImpl("2","End",NodeType.END);
		
		AdjacentItem edge0 = new AdjacentItem(node0, new NodeImpl[] {node1});
		AdjacentItem edge1 = new AdjacentItem(node1, new NodeImpl[] {node2});
		
		AdjacentList process = new AdjacentList(new NodeImpl[] {node0,node1,node2} ,new AdjacentItem[] {edge0,edge1});
		
		AdjacentItem edgeRes0 = new AdjacentItem(node0, new NodeImpl[] {node2});
		AdjacentList processRes = new AdjacentList(new NodeImpl[] {node0,node1,node2}, new AdjacentItem[] {edgeRes0});
		
		process.isolateNode(node1);
		
		assertEquals(process, processRes);
		
		NodeImpl processGatewayNode0 = new NodeImpl("0", "Start", NodeType.START);
		NodeImpl processGatewayNode1 = new NodeImpl("1", "A", NodeType.SERVICE_TASK);
		NodeImpl processGatewayNode2 = new NodeImpl("2", "B", NodeType.HUMAN_TASK);
		NodeImpl processGatewayNode3 = new NodeImpl("3", "G1", NodeType.GATEWAY);
		NodeImpl processGatewayNode4 = new NodeImpl("4", "C", NodeType.HUMAN_TASK);
		NodeImpl processGatewayNode5 = new NodeImpl("5", "D", NodeType.HUMAN_TASK);
		NodeImpl processGatewayNode6 = new NodeImpl("6", "G2", NodeType.GATEWAY);
		NodeImpl processGatewayNode7 = new NodeImpl("7", "#", NodeType.SERVICE_TASK);
		NodeImpl processGatewayNode8 = new NodeImpl("8", "end", NodeType.END);
		
		AdjacentItem edgeGW0 = new AdjacentItem(processGatewayNode0, new NodeImpl[] {processGatewayNode1});
		AdjacentItem edgeGW1 = new AdjacentItem(processGatewayNode1, new NodeImpl[] {processGatewayNode2});
		AdjacentItem edgeGW2 = new AdjacentItem(processGatewayNode2, new NodeImpl[] {processGatewayNode3});
		AdjacentItem edgeGW3 = new AdjacentItem(processGatewayNode3, new NodeImpl[] {processGatewayNode4, processGatewayNode5});
		AdjacentItem edgeGW4 = new AdjacentItem(processGatewayNode4, new NodeImpl[] {processGatewayNode6});
		AdjacentItem edgeGW5 = new AdjacentItem(processGatewayNode5, new NodeImpl[] {processGatewayNode6});	
		AdjacentItem edgeGW6 = new AdjacentItem(processGatewayNode6, new NodeImpl[] {processGatewayNode2,processGatewayNode7});
		AdjacentItem edgeGW8 = new AdjacentItem(processGatewayNode7, new NodeImpl[] {processGatewayNode8});
		
		NodeImpl[] nodeList =new NodeImpl[] {processGatewayNode0,processGatewayNode1,processGatewayNode2,processGatewayNode3,processGatewayNode4,processGatewayNode5,processGatewayNode6,processGatewayNode7,processGatewayNode8};
		AdjacentItem[] edgeGWList = new AdjacentItem[] {edgeGW0,edgeGW1,edgeGW2,edgeGW3,edgeGW4,edgeGW5,edgeGW6,edgeGW8};
		AdjacentList gateWprocess = new AdjacentList(nodeList, edgeGWList);
		gateWprocess.deleteAdjacentItem(edgeGW3);
	}
}
