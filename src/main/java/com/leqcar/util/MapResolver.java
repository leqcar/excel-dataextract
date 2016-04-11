package com.leqcar.util;

import java.util.Map;

public class MapResolver<K, V> implements Resolver<K, V> {

	private Map<K, V> map;
	
	@Override
	public V resolve(K key) {	
		return map.get(key);
	}

}
