package com.ChewieLouie.GoBeyond;

public class Referee {

	public static enum MoveStatus { IllegalMove };

	public MoveStatus submitMove(int i, int j) {
		return MoveStatus.IllegalMove;
	};

}
