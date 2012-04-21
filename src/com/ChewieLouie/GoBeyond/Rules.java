package com.ChewieLouie.GoBeyond;

public interface Rules {

	abstract boolean isLegal(Move m, Board b, GameHistory history);

	public abstract boolean isLegalMoveAvailable();

}