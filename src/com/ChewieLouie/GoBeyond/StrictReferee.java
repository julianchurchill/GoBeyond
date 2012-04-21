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
		if( rules.isLegal( m, board, null ) == false )
			return MoveStatus.IllegalMove;
		board.playStone( Move.toStone( m.colour() ), m.coord() );
		gameEndDetector.movePlayed( m );
		return MoveStatus.LegalMove;
	}
	
	@Override
	public boolean endDetected() {
		return gameEndDetector.endDetected();
	}
}
