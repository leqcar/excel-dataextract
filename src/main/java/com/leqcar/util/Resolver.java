package com.leqcar.util;

public interface Resolver<K, V> {

	V resolve(K key);
}
