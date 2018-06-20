package co.cafeto.bp3.model.impl;

import java.util.Objects;

import com.bp3.Node;
import com.bp3.NodeType;

public class NodeImpl implements Node {
	String id;
	String name;
	NodeType type;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NodeType getType() {
		return type;
	}
	public void setType(NodeType type) {
		this.type = type;
	}
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this.getClass() != o.getClass())
			return false;
		NodeImpl node = (NodeImpl) o;
		return this.id.equals(node.id);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	public boolean equalsType(NodeType type) {
		return this.type.equals(type);
	}
	
	public NodeImpl() {
		super();
	}
	
	public NodeImpl(String id, String name, NodeType type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}
}
