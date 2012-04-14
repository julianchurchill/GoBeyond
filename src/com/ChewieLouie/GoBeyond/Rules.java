package com.ChewieLouie.GoBeyond;

public interface Rules {

	abstract boolean isLegal(Move m);

	public abstract boolean isLegalMoveAvailable();

}