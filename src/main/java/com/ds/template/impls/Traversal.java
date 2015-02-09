package com.ds.template.impls;

public enum Traversal {
	PRE_ORDER(0), IN_ORDER(1), POST_ORDER(2);
	private Traversal(int value) {
		this.value = value;
	}
	int value;
	public int getValue() {
		return value;
	}
}
