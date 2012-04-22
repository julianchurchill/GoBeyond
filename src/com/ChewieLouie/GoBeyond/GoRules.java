package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Board.Point;

public class GoRules implements Rules {

	public static class LegalityChecker {
		private Board board;
		private Move move;
		private GameHistory history;
		private Point moveStoneColour;
		private BoardAnalyzer boardAnalyzer;

		public LegalityChecker( Move m, Board b, GameHistory h, BoardAnalyzer a ) {
			this.move = m;
			this.board = b;
			this.history = h;
			this.moveStoneColour = Move.toStone(move.colour());
			this.boardAnalyzer = a;
		}
		
		public boolean check() {
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
	}

	private BoardAnalyzer boardAnalyzer;

	public GoRules(BoardAnalyzer boardAnalyzer) {
		this.boardAnalyzer = boardAnalyzer;
	}

	@Override
	public boolean isLegal(Move m, Board b, GameHistory h) {
		return new LegalityChecker( m, b, h, boardAnalyzer ).check();
	}

	@Override
	public boolean endDetected(GameHistory history) {
		return history.lastMove().equals(Move.passMove(history.lastMove().colour())) &&
			history.lastButOneMove().equals(Move.passMove(history.lastButOneMove().colour()));
	}
}
