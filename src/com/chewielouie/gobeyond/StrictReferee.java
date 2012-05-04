package com.chewielouie.gobeyond;

import java.util.HashSet;
import java.util.Set;

public class StrictReferee implements Referee {

	private Rules rules;
	private RemovedDeadStonesBoard board;
	private GameHistory history = new GameHistory();
	private Board undecoratedBoard;
	private Set<RefereeMoveObserver> observers = new HashSet<RefereeMoveObserver>();

	public StrictReferee(Rules rules, Board board ) {
		this.rules = rules;
		this.undecoratedBoard = board;
		this.board = new RemovedDeadStonesBoard(board, new GoStringLifeAnalyzer());
	}

	@Override
	public MoveStatus submitMove( Move m ) {
		if( rules.isLegal( m, board, history ) == false )
			return MoveStatus.IllegalMove;
		playMove(m);
		return MoveStatus.LegalMove;
	}

	private void playMove(Move m) {
		if( m.equals( Move.passMove( m.colour() ) ) == false )
			board.playStone( Move.toStone( m.colour() ), m.coord() );
		history.add( board.duplicate(), m );
		for( RefereeMoveObserver o : observers )
			o.moveAccepted(m, board() );
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
	public boolean isLegal(Move move, Board board) {
		return rules.isLegal(move, board, history);
	}

	public void addObserver(RefereeMoveObserver observer) {
		this.observers.add( observer );
	}

	public GameHistory gameHistory() {
		return history;
	}
}
