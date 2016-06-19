package com.ds.template.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ds.template.Queue;
import com.ds.template.graph.impl.Edge;
import com.ds.template.impls.QueueImpl;

public class Graph<V> {
	List<Vertex<V>> vertex;
	Map<Vertex<V>, Set<Edge<Vertex<V>>>> vToEdges;

	public Graph(List<Vertex<V>> vertex) {
		super();
		this.vertex = vertex;
		this.vToEdges = new HashMap<>();
	}

	public void addEdge(Vertex<V> start, Vertex<V> end) {
		Set<Edge<Vertex<V>>> edges = this.vToEdges.get(start);
		if (edges == null) {
			edges = new HashSet<>();
			vToEdges.put(start, edges);
		}
		edges.add(Edge.create(start, end));

	}

	public void bfs(Vertex<V> start) throws InterruptedException {
		// pseudo
		// 1. push start to queue
		// 2. do till queue is not empty
		// 3. dequeue all vertex and mark them visited get unvisited to queue
		Queue<Vertex<V>> q = new QueueImpl<>();
		q.enqueue(start);

		while (!q.isEmpty()) {
			List<Vertex<V>> nodes = new ArrayList<>();
			while (!q.isEmpty()) {
				nodes.add(q.dequeue());
			}
			display(nodes, true);
			for (Vertex<V> v : nodes) {
				for (Vertex<V> n : getUnVisited(v)) {
					q.enqueue(n);
				}
			}
		}
	}

	public static <V> void display(List<Vertex<V>> data, boolean markVisited) {
		for (Vertex<V> v : data) {
			if (markVisited) {
				v.markVisited();
			}
			System.out.println(v);
		}
	}

	private List<Vertex<V>> getUnVisited(Vertex<V> vertex) {
		List<Vertex<V>> unvisited = new ArrayList<>();
		Set<Edge<Vertex<V>>> edges = vToEdges.get(vertex);
		if (edges != null) {
			for (Edge<Vertex<V>> edge : edges) {
				boolean ab = edge.getEnd().isVisited() ? false : unvisited.add(edge.getEnd());
			}
		}
		return unvisited;
	}

	@Override
	public String toString() {
		return "Graph [vertex=" + vertex + ", vToEdges=" + vToEdges + "]";
	}

	public static void main(String[] args) throws InterruptedException {
		List<Vertex<Character>> vertexes = new ArrayList<>();
		Vertex A = new Vertex('A');
		Vertex B = new Vertex('B');
		Vertex C = new Vertex('C');
		Vertex D = new Vertex('D');
		Vertex E = new Vertex('E');

		vertexes.add(A);
		vertexes.add(B);
		vertexes.add(C);
		vertexes.add(D);
		vertexes.add(E);

		Graph g = new Graph(vertexes);
		g.addEdge(A, B);
		g.addEdge(A, E);
		g.addEdge(B, C);
		g.addEdge(C, D);
		g.addEdge(D, A);
		g.bfs(A);
	}
}
