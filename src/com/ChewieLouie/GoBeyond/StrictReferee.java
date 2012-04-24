package com.ChewieLouie.GoBeyond;

public class StrictReferee implements Referee {

	private Rules rules;
	private RemovedDeadStonesBoard board;
	private GameHistory history = new GameHistory();

	public StrictReferee(Rules rules, Board board ) {
		this.rules = rules;
		this.board = new RemovedDeadStonesBoard(board, new GoStringLifeAnalyzer());
	}

	@Override
	public MoveStatus submitMove( Move m ) {
		if( rules.isLegal( m, board, history ) == false )
			return MoveStatus.IllegalMove;
		board.playStone( Move.toStone( m.colour() ), m.coord() );
		history.add( board.duplicate(), m );
		return MoveStatus.LegalMove;
	}

	@Override
	public boolean endDetected() {
		return rules.endDetected( history );
	}
}
