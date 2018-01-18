package com.ds.template.graph;

public class CormanVertex<V> extends Vertex<V> {
	private Color color;
	private int distance;
	private CormanVertex<V> parent;
	
	public CormanVertex(V data) {
		super(data);
	}
	
	public static enum Color {
		WHITE, GREY, BLACK
	}

	@Override
	public String toString() {
		return "CormanVertex [color=" + color + ", distance=" + distance + ", data=" + data + ", visited=" + visited
				+ "]";
	}
	
}
