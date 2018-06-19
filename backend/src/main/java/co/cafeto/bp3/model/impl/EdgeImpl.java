package co.cafeto.bp3.model.impl;

import com.bp3.Edge;

public class EdgeImpl implements Edge {
	
	String from;
	String to;
		
	public EdgeImpl() {
		super();
	}

	public EdgeImpl(String from, String to) {
		this.from = from;
		this.to = to;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
}
