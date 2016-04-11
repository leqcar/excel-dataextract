package com.leqcar.util;

public interface Transformer<M, N> {

	N transform (M object);
}
