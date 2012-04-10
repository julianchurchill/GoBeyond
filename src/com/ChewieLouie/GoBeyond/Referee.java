package com.ChewieLouie.GoBeyond;

public interface Referee {

	public static enum MoveStatus { IllegalMove, LegalMove }

	public abstract MoveStatus submitMove(Move m);

}