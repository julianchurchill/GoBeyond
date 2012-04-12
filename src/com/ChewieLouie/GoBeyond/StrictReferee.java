package com.ChewieLouie.GoBeyond;

import java.util.HashSet;

public class StrictReferee implements Referee {

	private Rules rules;
	private Board board;
	private HashSet<MoveObserver> observers = new HashSet<MoveObserver>();

	public StrictReferee(Rules rules, Board board) {
		this.rules = rules;
		this.board = board;
	}

	@Override
	public MoveStatus submitMove( Move m ) {
		if( rules.isLegal( m ) == false )
			return MoveStatus.IllegalMove;
		board.playStone( moveColourToStone( m.colour() ), m.x(), m.y() );
		notifyMoveObservers( m );
		return MoveStatus.LegalMove;
	}

	private Board.Point moveColourToStone( Move.Colour c ) {
		return c == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone;
	}
	
	private void notifyMoveObservers( Move m ) {
		for( MoveObserver o : observers )
			o.movePlayed( m );
	}

	@Override
	public void subscribeForAcceptedMoves(MoveObserver observer) {
		observers.add( observer );
	}
}
