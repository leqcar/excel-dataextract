package com.leqcar.util;

public interface Factory<M, N> {

	N create(M context);
}
