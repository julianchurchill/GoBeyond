package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Board.Point;

public class StrictReferee implements Referee {

	private Rules rules;
	private Board board;
	private GameHistory history = new GameHistory();
	private BoardAnalyzer boardAnalyzer;

	public StrictReferee(Rules rules, Board board ) {
		this.rules = rules;
		this.board = board;
		this.boardAnalyzer = new GoBoardAnalyzer();
	}

	@Override
	public MoveStatus submitMove( Move m ) {
		if( rules.isLegal( m, board, history ) == false )
			return MoveStatus.IllegalMove;
		board.playStone( Move.toStone( m.colour() ), m.coord() );
		removeDeadStones( m );
		history.add( board.duplicate(), m );
		return MoveStatus.LegalMove;
	}

	private void removeDeadStones(Move m) {
		Point enemyColour = Move.toStone(m.colour()) == Point.BlackStone ? Point.WhiteStone : Point.BlackStone;
		if( isEnemyStringDead( m.coord().up(), enemyColour ) )
			board.removeStone( m.coord().up() );
		if( isEnemyStringDead( m.coord().down(), enemyColour ) )
			board.removeStone( m.coord().down() );
		if( isEnemyStringDead( m.coord().left(), enemyColour ) )
			board.removeStone( m.coord().left() );
		if( isEnemyStringDead( m.coord().right(), enemyColour ) )
			board.removeStone( m.coord().right() );
	}

	private boolean isEnemyStringDead(Coord c, Point enemyColour) {
		return board.getContentsOfPoint( c ) == enemyColour && boardAnalyzer.isStringAlive( board, c ) == false;
	}

	@Override
	public boolean endDetected() {
		return rules.endDetected( history );
	}
}
