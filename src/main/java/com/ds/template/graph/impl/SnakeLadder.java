package com.ds.template.graph.impl;

import java.util.LinkedList;

public class SnakeLadder {
	static class BoardNode {
		int vertex;
		int distance;

		public BoardNode(int vertex, int distance) {
			super();
			this.vertex = vertex;
			this.distance = distance;
		}

		@Override
		public String toString() {
			return "BoardNode [vertex=" + vertex + ", distance=" + distance + "]";
		}
		
	}

	private static BoardNode minimum(int[] board, int N) {
		boolean[] visited = new boolean[N + 1];
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		// start with first
		visited[1] = true;
		BoardNode node = new BoardNode(1, 0);
		LinkedList<BoardNode> q = new LinkedList<>();
		q.add(node);

		while (!q.isEmpty()) {
			node = q.remove();
			if (node.vertex == N) {
				break;
			}
			int v = node.vertex;
			for (int j = 1; j < 7 && (v+j)<=N; j++) {
				if (!visited[v + j]) {
					// create new node
					BoardNode n = new BoardNode(v + j, node.distance + 1);
					// if snake or ladder is there
					visited[n.vertex] = true;
					if (board[v + j] != -1) {
						n.vertex = board[v + j];
					}
					q.add(n);
				}
			}

		}

		return node;
	}
	
	public static void main(String[] args) {
		/*
		3
		32 62
		42 68
		12 98
		7
		95 13
		97 25
		93 37
		79 27
		75 19
		49 47
		67 17
		*/
		int N = 100;
	    int moves[] = new int[N+1];
	    
	    for (int i = 0; i<N+1; i++)
	        moves[i] = -1;
	 
	    // Ladders
	    moves[2] = 21;
	    moves[4] = 7;
	    moves[10] = 25;
	    moves[19] = 28;
	 
	    // Snakes
	    moves[26] = 1;
	    moves[20] = 8;
	    moves[16] = 3;
	    moves[18] = 6;
	 
	    BoardNode n =  minimum(moves, N);
	    System.out.println(n);
	  
	}
}
