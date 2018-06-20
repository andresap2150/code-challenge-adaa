import static org.junit.Assert.assertEquals;

import javax.print.attribute.standard.Sides;

import org.junit.Assert;
import org.junit.Test;

import com.bp3.NodeType;

import co.cafeto.bp3.model.impl.NodeImpl;

public class NodeImplTest {
	
	@Test 
	public void NodeImplComparatorTest(){
		NodeImpl singleNode = new NodeImpl();
		singleNode.setId("0");
		singleNode.setName("start");
		singleNode.setType(NodeType.START);
		
		assertEquals("0", singleNode.getId());
		assertEquals("start", singleNode.getName());
		assertEquals(NodeType.START, singleNode.getType());
		assertEquals(true, singleNode.equalsType(NodeType.START));		
		
		assertEquals(false, singleNode.equals(null));
		assertEquals(false, singleNode.equals(new Object()));
		
		NodeImpl singleNode2 = new NodeImpl("0","INICIO",NodeType.END);
		
		assertEquals(singleNode, singleNode2);
		assertEquals(singleNode.hashCode(), singleNode2.hashCode());
	}
}
