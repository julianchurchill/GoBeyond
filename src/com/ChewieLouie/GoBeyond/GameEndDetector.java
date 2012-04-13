package com.ChewieLouie.GoBeyond;

public interface GameEndDetector {

	public abstract boolean endDetected();

	public abstract void movePlayed(Move move);

}