package com.chewielouie.gobeyond;

public interface Rules {

	public abstract boolean isLegal(Move m, Board b, GameHistory history);

	public abstract boolean endDetected(GameHistory history);

}