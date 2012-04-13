package com.ChewieLouie.GoBeyond;

public class StrictReferee implements Referee {

	private Rules rules;
	private Board board;
	private GameEndDetector gameEndDetector;

	public StrictReferee(Rules rules, Board board, GameEndDetector gameEndDetector) {
		this.rules = rules;
		this.board = board;
		this.gameEndDetector = gameEndDetector;
	}

	@Override
	public MoveStatus submitMove( Move m ) {
		if( rules.isLegal( m ) == false )
			return MoveStatus.IllegalMove;
		board.playStone( moveColourToStone( m.colour() ), m.x(), m.y() );
		gameEndDetector.movePlayed( m );
		return MoveStatus.LegalMove;
	}

	private Board.Point moveColourToStone( Move.Colour c ) {
		return c == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone;
	}
	
	@Override
	public boolean endDetected() {
		return gameEndDetector.endDetected();
	}
}
