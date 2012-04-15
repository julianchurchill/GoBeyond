package com.ChewieLouie.GoBeyond;

public class GoRules implements Rules {

	private Board board;

	public GoRules(Board board) {
		this.board = board;
	}

	@Override
	public boolean isLegal(Move m) {
		return isEmptyBoardPoint(m.coord()) && isNotSuicide(m);
	}

	private boolean isNotSuicide(Move m) {
		Board testBoard = board.duplicate();
		testBoard.playStone( m.colour() == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone,
							 m.coord() );
		return new BoardAnalyzer( testBoard ).isStringAlive( m.coord() );
	}

	private boolean isEmptyBoardPoint(Coord c) {
		return board.getContentsOfPoint( c ) == Board.Point.Empty;
	}

	@Override
	public boolean isLegalMoveAvailable() {
		return false;
	}

}
