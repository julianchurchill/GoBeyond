package com.ChewieLouie.GoBeyond;

public class StrictReferee implements Referee {

	private Rules rules;
	private RemovedDeadStonesBoard board;
	private GameHistory history = new GameHistory();
	private Board undecoratedBoard;

	public StrictReferee(Rules rules, Board board ) {
		this.rules = rules;
		this.undecoratedBoard = board;
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

	@Override
	public Board board() {
		return undecoratedBoard;
	}

	@Override
	public boolean isLegal(Move move, Board board, GameHistory history) {
		return rules.isLegal(move, board, history);
	}
}
