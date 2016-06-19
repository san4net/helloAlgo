package com.ds.template;

import java.util.ArrayList;
import java.util.List;

import com.ds.impls.StackImpl;

public class Graph {
	public static void main(String[] args) {
		Graph graph = new Graph(4);
		graph.addVertex(0);
		graph.addVertex(1);
		graph.addVertex(2);
		graph.addVertex(3);
		graph.addEdge(0, 1);
		graph.addEdge(0, 3);
		graph.addEdge(1, 2);
		graph.addEdge(2, 0);
		graph.addEdge(2, 3);
		graph.dfs();
	}

	class Vertex {
		int label;
		boolean visited = false;

		public Vertex(int label) {
			super();
			this.label = label;
		}

	}

	private int size;
	private List<Vertex> vertexList = new ArrayList<>();
	int adjMatrix[][];
	com.ds.template.Stack<Vertex> stack = new StackImpl<Vertex>(10);

	public Graph(int size) {
		super();
		this.size = size;
		this.vertexList = vertexList;
		this.adjMatrix = new int[size][size];
	}

	void addVertex(int label) {
		vertexList.add(new Vertex(label));
	}

	void addEdge(int s, int e) {
		adjMatrix[s][e] = 1;
		adjMatrix[e][s] = 1;
	}

	int getUnvisitedVertex(int v) {
		for (int i = 0; i < size; i++) {
			if (adjMatrix[v][i] == 1 && vertexList.get(i).visited == false) {
				return i;
			}
		}
		return -1;
	}

	void display(int index) {
		System.out.println(vertexList.get(index).label);
	}

	void dfs() {
		// vertexList.get(0).visited = true;
		// display(0);
		stack.push(vertexList.get(0));

		while (!stack.isEmpty()) {
			Vertex v = stack.pop();
			v.visited = true;
			System.out.println(v.label);
			int unvisted = getUnvisitedVertex(v.label);
			if (unvisted == -1) {
				return;
			} else {
				Vertex temp = vertexList.get(unvisted);
				stack.push(temp);
			}

		}

	}

}
