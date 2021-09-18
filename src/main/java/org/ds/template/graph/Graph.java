package org.ds.template.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ds.impls.StackImpl;
import org.ds.template.Queue;
import org.ds.template.Stack;
import org.ds.template.graph.impl.Edge;
import org.ds.template.impls.QueueImpl;

public class Graph<V> {
	private List<Vertex<V>> vertex;
	private Map<Vertex<V>, Set<Edge<Vertex<V>>>> vToEdges;
	private Stack<Vertex<V>> stack ;

	public Graph(List<Vertex<V>> vertex) {
		super();
		this.vertex = vertex;
		this.vToEdges = new HashMap<>();
		this.stack = new StackImpl<>(vertex.size());
	}

	public void addEdge(Vertex<V> start, Vertex<V> end) {
		Set<Edge<Vertex<V>>> edges = this.vToEdges.get(start);
		if (edges == null) {
			edges = new HashSet<>();
			vToEdges.put(start, edges);
		}
		edges.add(Edge.create(start, end));

	}
	/**
	 * 
	 * @param start
	 * @throws InterruptedException
	 */
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
				for (Vertex<V> n : getAllUnVisited(v)) {
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

	private List<Vertex<V>> getAllUnVisited(Vertex<V> vertex) {
		List<Vertex<V>> unvisited = new ArrayList<>();
		Set<Edge<Vertex<V>>> edges = vToEdges.get(vertex);
		if (edges != null) {
			for (Edge<Vertex<V>> edge : edges) {
				boolean ab = edge.getEnd().isVisited() ? false : unvisited.add(edge.getEnd());
			}
		}
		return unvisited;
	}
	
	private Vertex<V> getNextUnVisited(Vertex<V> vertex) {
		Set<Edge<Vertex<V>>> edges = vToEdges.get(vertex);
		if (edges != null) {
			for (Edge<Vertex<V>> edge : edges) {
				if (!edge.getEnd().isVisited()) {
					return edge.getEnd();
				}
			}
		}
		return null;
	}
	
	public void dfs(Vertex<V> vertex) {
		stack.push(vertex);

		while (!stack.isEmpty()) {
			Vertex<V> temp = stack.peek();
			if (temp.visited == false) {
				temp.visited = true;
				System.out.println(temp);
			}

			Vertex<V> unvisted = getNextUnVisited(temp);
			if (unvisted == null) {
				stack.pop();
			} else {
				stack.push(unvisted);
			}
		}
	}
	
	@Override
	public String toString() {
		return "Graph [vertex=" + vertex + ", vToEdges=" + vToEdges + "]";
	}

	public void reset() {
		vertex.forEach(Vertex::markUnVisited);
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
		System.out.println("bfs");
		g.bfs(A);
		g.reset();
		System.out.println("dfs");
		g.dfs(A);
	}
	
}
