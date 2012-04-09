package com.ChewieLouie.GoBeyond;

public class Referee {

	public static enum MoveStatus { IllegalMove, LegalMove }

	private Rules rules;

	public Referee(Rules rules) {
		this.rules = rules;
	}

	public MoveStatus submitMove( Move m ) {
		return rules.isLegal( m ) ? MoveStatus.LegalMove : MoveStatus.IllegalMove;
	};

}
