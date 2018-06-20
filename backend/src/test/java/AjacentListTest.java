import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.omg.CORBA.NO_IMPLEMENT;

import com.bp3.NodeType;

import co.cafeto.adaa.business.AdjacentListUtil;
import co.cafeto.adaa.exception.MappingProcessException;
import co.cafeto.bp3.model.impl.AdjacentItem;
import co.cafeto.bp3.model.impl.AdjacentList;
import co.cafeto.bp3.model.impl.NodeImpl;

public class AjacentListTest {
	@Test
	public void testISolateNode(){
		
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
		AdjacentList actualGateWProcess = new AdjacentList(nodeList, edgeGWList);
		
		NodeImpl[] expcNodeList = new NodeImpl[] {processGatewayNode0,processGatewayNode1,processGatewayNode2,processGatewayNode4,processGatewayNode5,processGatewayNode6,processGatewayNode7,processGatewayNode8};
		AdjacentItem expcEdge2 = new AdjacentItem(processGatewayNode2, new NodeImpl[] {processGatewayNode4, processGatewayNode5});
		AdjacentItem[] expcEdgeGWList = new AdjacentItem[] {edgeGW0,edgeGW1,expcEdge2,edgeGW4,edgeGW5,edgeGW6,edgeGW8};
		AdjacentList expectedGateWProcess = new AdjacentList(expcNodeList,expcEdgeGWList);
		
		actualGateWProcess.deleteAdjacentItem(edgeGW3);
		assertEquals(expectedGateWProcess, actualGateWProcess);		
	}
	
	@Test
	public void comparatorTest() {
		AdjacentList singleList = new AdjacentList();
		
		NodeImpl singleProcessNode0 = new NodeImpl("0", "Start", NodeType.START);
		NodeImpl singleProcessNode1 = new NodeImpl("1", "Start", NodeType.END);
		
		singleList.setNodeList(new NodeImpl[] {singleProcessNode0,singleProcessNode1});
		
		AdjacentItem singleEdge = new AdjacentItem(singleProcessNode0, new NodeImpl[] {singleProcessNode1});
		
		singleList.setProcess(new AdjacentItem[] {singleEdge});
		
		assertArrayEquals(singleList.getNodeList(), new NodeImpl[] {singleProcessNode0,singleProcessNode1});
		assertArrayEquals(singleList.getProcess(), new AdjacentItem[] {singleEdge});
		
		assertEquals(false, singleList.equals(null));
		assertEquals(false, singleList.equals(singleEdge));
		
		AdjacentList singleList2 = new AdjacentList(new NodeImpl[] {singleProcessNode0,singleProcessNode1}, new AdjacentItem[] {singleEdge});
		
		assertEquals(singleList.hashCode(), singleList2.hashCode());
	}
	
	/*@Test(expected = Error.class)
	public void constructorTest(){
		AdjacentListUtil util = new AdjacentListUtil();
	}*/
	
}
