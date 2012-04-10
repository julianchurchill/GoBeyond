package com.ChewieLouie.GoBeyond;

public class StrictReferee implements Referee {

	private Rules rules;
	private Board board;

	public StrictReferee(Rules rules, Board board) {
		this.rules = rules;
		this.board = board;
	}

	@Override
	public MoveStatus submitMove( Move m ) {
		if( rules.isLegal( m ) == false )
			return MoveStatus.IllegalMove;
		board.playStone( 
				m.colour() == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone,
				m.x(), m.y() );
		return MoveStatus.LegalMove;
	};

}
