import org.junit.Test;

import com.bp3.NodeType;

import co.cafeto.adaa.business.ProcessAdjacentTransform;
import co.cafeto.adaa.exception.MappingProcessException;
import co.cafeto.bp3.model.impl.Bp3process;
import co.cafeto.bp3.model.impl.NodeImpl;

public class MappingProcessExceptionTest {
	
	@Test(expected = MappingProcessException.class)
	public void testMappingException(){
		NodeImpl singleNode = new NodeImpl("0", "Start", NodeType.START);
		NodeImpl[] nodeList = new NodeImpl[] {singleNode};
	    Bp3process actualProcess = new Bp3process();
	    actualProcess.setNodes(nodeList);
		ProcessAdjacentTransform.transformProcessIntoAdjacentList(actualProcess);
	}
}
