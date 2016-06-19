package com.sync.systems.transfer;

import java.nio.file.Path;

public interface Copy<T> {
	T copy() throws Exception;
	boolean isCopied();
	long size();
}
