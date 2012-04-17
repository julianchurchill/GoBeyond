package com.ChewieLouie.GoBeyond;

public class GoRules implements Rules {

	private Board board;
	private BoardAnalyzer boardAnalyzer;

	public GoRules(Board board, BoardAnalyzer boardAnalyzer) {
		this.board = board;
		this.boardAnalyzer = boardAnalyzer;
	}

	@Override
	public boolean isLegal(Move m) {
		return isEmptyBoardPoint(m.coord()) && isNotSuicide(m);
	}

	private boolean isNotSuicide(Move m) {
		Board testBoard = board.duplicate();
		testBoard.playStone( m.colour() == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone,
							 m.coord() );
		return boardAnalyzer.isStringAlive( testBoard, m.coord() );
	}

	private boolean isEmptyBoardPoint(Coord c) {
		return board.getContentsOfPoint( c ) == Board.Point.Empty;
	}

	@Override
	public boolean isLegalMoveAvailable() {
		return false;
	}

}
