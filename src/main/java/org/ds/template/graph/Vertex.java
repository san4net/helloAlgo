package org.ds.template.graph;

public class Vertex<V> {
	V data;
	boolean visited;

	public Vertex(V data) {
		super();
		this.data = data;
	}

	public void markVisited() {
		this.visited = true;
	}

	public void markUnVisited() {
		this.visited = false;
	}

	public boolean isVisited() {
		return visited;
	}

	static <V> Vertex<V> create(V data) {
		return new Vertex(data);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [data=" + data + "]";
	}

}
