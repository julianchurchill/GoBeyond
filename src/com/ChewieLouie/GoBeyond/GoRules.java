package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Board.Point;

public class GoRules implements Rules {

	private Board board;
	private BoardAnalyzer boardAnalyzer;
	private Move move;
	private GameHistory history;
	private Point moveStoneColour;

	public GoRules(BoardAnalyzer boardAnalyzer) {
		this.boardAnalyzer = boardAnalyzer;
	}

	@Override
	public boolean isLegal(Move m, Board b, GameHistory history) {
		this.board = b;
		this.move = m;
		this.moveStoneColour = Move.toStone(move.colour());
		this.history = history;
		return isEmptyBoardPoint() && ( isNotSuicide() || ( capturesEnemies() && isNotIllegalKo() ) );
	}

	private boolean isNotIllegalKo() {
		if( boardAvailableForTwoMovesAgo() && history.lastButOneBoard().equals( makeBoardWithMovePlayed() ) )
				return false;
		return true;
	}

	private boolean boardAvailableForTwoMovesAgo() {
		return history != null && history.lastButOneBoard() != null;
	}

	private boolean isEmptyBoardPoint() {
		return board.getContentsOfPoint( move.coord() ) == Board.Point.Empty;
	}

	private boolean isNotSuicide() {
		return boardAnalyzer.isStringAlive( makeBoardWithMovePlayed(), move.coord() );
	}

	private Board makeBoardWithMovePlayed() {
		Board testBoard = board.duplicate();
		testBoard.playStone( moveStoneColour, move.coord() );
		return testBoard;
	}

	private boolean capturesEnemies() {
		return areAnyAdjacentStringsDead( makeBoardWithMovePlayed(), enemyBoardPoint() );
	}

	private Board.Point enemyBoardPoint() {
		return moveStoneColour == Board.Point.BlackStone ? Board.Point.WhiteStone : Board.Point.BlackStone;
	}

	private boolean areAnyAdjacentStringsDead(Board board, Point enemyColour) {
		return isDeadStringOfColour(board, move.coord().up(), enemyColour) ||
			isDeadStringOfColour(board, move.coord().down(), enemyColour) ||
			isDeadStringOfColour(board, move.coord().right(), enemyColour) ||
			isDeadStringOfColour(board, move.coord().left(), enemyColour);
	}

	private boolean isDeadStringOfColour(Board board, Coord coord, Point enemyColour) {
		return board.getContentsOfPoint(coord) == enemyColour &&
			boardAnalyzer.isStringAlive(board, coord) == false;
	}

	@Override
	public boolean isLegalMoveAvailable() {
		return false;
	}
}
