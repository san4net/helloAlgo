package com.rise.pool;

public class PoolFactoryImpl<T> implements PoolFactory {

	@Override
	public Pool newPool() {
		// TODO Auto-generated method stub
		return new ThreadPool<T>();
	}

}
