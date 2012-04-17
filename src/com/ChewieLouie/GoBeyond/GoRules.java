package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Board.Point;

public class GoRules implements Rules {

	private Board board;
	private BoardAnalyzer boardAnalyzer;

	public GoRules(Board board, BoardAnalyzer boardAnalyzer) {
		this.board = board;
		this.boardAnalyzer = boardAnalyzer;
	}

	@Override
	public boolean isLegal(Move m) {
		return isEmptyBoardPoint(m.coord()) && ( isNotSuicide(m) || capturesEnemies(m) );
	}

	private boolean isEmptyBoardPoint(Coord c) {
		return board.getContentsOfPoint( c ) == Board.Point.Empty;
	}

	private boolean isNotSuicide(Move m) {
		Board.Point colour = moveColourToBoardPoint(m);
		return boardAnalyzer.isStringAlive( makeBoardWithMovePlayed(m, colour), m.coord() );
	}

	private Board.Point moveColourToBoardPoint(Move m) {
		return m.colour() == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone;
	}

	private Board makeBoardWithMovePlayed(Move m, Board.Point colour) {
		Board testBoard = board.duplicate();
		testBoard.playStone( colour, m.coord() );
		return testBoard;
	}

	private boolean capturesEnemies(Move m) {
		Board.Point colour = moveColourToBoardPoint(m);
		return areAnyAdjacentStringsDead( makeBoardWithMovePlayed(m, colour), m.coord(), enemyBoardPoint(colour) );
	}

	private Board.Point enemyBoardPoint(Board.Point colour) {
		return colour == Board.Point.BlackStone ? Board.Point.WhiteStone : Board.Point.BlackStone;
	}

	private boolean areAnyAdjacentStringsDead(Board board, Coord coord, Point enemyColour) {
		return isDeadStringOfColour(board, coord.up(), enemyColour) ||
			isDeadStringOfColour(board, coord.down(), enemyColour) ||
			isDeadStringOfColour(board, coord.right(), enemyColour) ||
			isDeadStringOfColour(board, coord.left(), enemyColour);
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
