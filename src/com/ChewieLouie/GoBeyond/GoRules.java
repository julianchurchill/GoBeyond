package com.ChewieLouie.GoBeyond;

public class GoRules implements Rules {

	private Board board;

	public GoRules(Board board) {
		this.board = board;
	}

	@Override
	public boolean isLegal(Move m) {
		if( isOccupiedBoardPoint(m.coord()) || isSuicide(m) )
			return false;
		return true;
	}

	private boolean isOccupiedBoardPoint(Coord c) {
		return board.getContentsOfPoint( c ) != Board.Point.Empty;
	}

	private boolean isSuicide(Move m) {
		return !hasAnyEmptyNeighbours(m.coord());
	}

	private boolean hasAnyEmptyNeighbours(Coord c) {
		return isEmptyBoardPoint(c.left()) ||
				isEmptyBoardPoint(c.right()) ||
				isEmptyBoardPoint(c.up()) ||
				isEmptyBoardPoint(c.down());
	}

	private boolean isEmptyBoardPoint(Coord c) {
		return board.getContentsOfPoint( c ) == Board.Point.Empty;
	}

	@Override
	public boolean isLegalMoveAvailable() {
		return false;
	}

}
