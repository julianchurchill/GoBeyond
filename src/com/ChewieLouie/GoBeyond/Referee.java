package com.ChewieLouie.GoBeyond;

public interface Referee {

	public static enum MoveStatus { IllegalMove, LegalMove }

	public abstract MoveStatus submitMove(Move m);

	public abstract boolean endDetected();

	public abstract Board board();

	public abstract boolean isLegal(Move move, Board board, GameHistory history);

}