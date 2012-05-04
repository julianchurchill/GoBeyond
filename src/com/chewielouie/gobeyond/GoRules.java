package com.chewielouie.gobeyond;

import com.chewielouie.gobeyond.Board.Point;
import com.chewielouie.gobeyond.util.Coord;

public class GoRules implements Rules {

	public static class LegalityChecker {
		private Board board;
		private Move move;
		private GameHistory history;
		private Point moveStoneColour;
		private StringLifeAnalyzer boardAnalyzer;

		public LegalityChecker( Move m, Board b, GameHistory h, StringLifeAnalyzer a ) {
			this.move = m;
			this.board = b;
			this.history = h;
			this.moveStoneColour = Move.toStone(move.colour());
			this.boardAnalyzer = a;
		}
		
		public boolean isLegal() {
			return isPassMove() || isNotSuicide() || isLegalCapture();
		}

		private boolean isPassMove() {
			return move.equals( Move.passMove(move.colour() ) );
		}

		private boolean isNotSuicide() {
			return isEmptyBoardPoint() && boardAnalyzer.isStringAlive( makeBoardWithMovePlayed(), move.coord() );
		}

		private boolean isEmptyBoardPoint() {
			return board.getContentsOfPoint( move.coord() ) == Board.Point.Empty;
		}

		private boolean isLegalCapture() {
			return isEmptyBoardPoint() && capturesEnemies() && isAnIllegalKo() == false;
		}

		private boolean capturesEnemies() {
			return areAnyAdjacentStringsDead( makeBoardWithMovePlayed(), enemyBoardPoint() );
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

		private boolean isAnIllegalKo() {
			Board boardWithoutDeadStones = makeBoardWithMovePlayedAndDeadStonesRemoved();
			return boardAvailableForTwoMovesAgo() && history.lastButOneBoard().equals( boardWithoutDeadStones );
		}

		private Board makeBoardWithMovePlayedAndDeadStonesRemoved() {
			RemovedDeadStonesBoard testBoard = new RemovedDeadStonesBoard(board.duplicate(), boardAnalyzer);
			testBoard.playStone( moveStoneColour, move.coord() );
			return testBoard.originalBoard();
		}

		private boolean boardAvailableForTwoMovesAgo() {
			return history != null && history.lastButOneBoard() != null;
		}

		private Board makeBoardWithMovePlayed() {
			Board testBoard = board.duplicate();
			testBoard.playStone( moveStoneColour, move.coord() );
			return testBoard;
		}

		private Board.Point enemyBoardPoint() {
			return moveStoneColour == Board.Point.BlackStone ? Board.Point.WhiteStone : Board.Point.BlackStone;
		}
	}

	private StringLifeAnalyzer boardAnalyzer;

	public GoRules(StringLifeAnalyzer boardAnalyzer) {
		this.boardAnalyzer = boardAnalyzer;
	}

	@Override
	public boolean isLegal(Move m, Board b, GameHistory h) {
		return new LegalityChecker( m, b, h, boardAnalyzer ).isLegal();
	}

	@Override
	public boolean endDetected(GameHistory history) {
		if( history.size() < 2 )
			return false;
		return isPassMove(history.lastMove()) && isPassMove(history.lastButOneMove());
	}

	private boolean isPassMove(Move m) {
		return m.equals(Move.passMove(m.colour()));
	}
}
