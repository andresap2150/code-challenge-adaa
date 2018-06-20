import org.junit.Test;

import co.cafeto.bp3.model.impl.EdgeImpl;

public class EdgeImplTest {
	@Test
	public void testEdgeComparator() {
		EdgeImpl singleEdge = new EdgeImpl();
		singleEdge.setFrom("0");
		singleEdge.setTo("1");
	}
}
