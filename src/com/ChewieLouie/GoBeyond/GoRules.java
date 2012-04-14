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
		return allNeighboursAreColourOfMove( m ) || hasAnyEmptyNeighbours(m.coord());
	}

	private boolean allNeighboursAreColourOfMove(Move m) {
		return allNeighboursContain( m.coord(), 
				m.colour() == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone );
	}
	
	private boolean allNeighboursContain( Coord c, Board.Point expectedContent ) {
		return boardPointContains(c.up(), expectedContent) &&
			boardPointContains(c.down(), expectedContent) &&
			boardPointContains(c.left(), expectedContent) &&
			boardPointContains(c.right(), expectedContent);
	}

	private boolean boardPointContains(Coord c, Board.Point expectedColour) {
		return board.getContentsOfPoint( c ) == expectedColour;
	}

	private boolean hasAnyEmptyNeighbours(Coord c) {
		return isEmptyBoardPoint(c.left()) ||
				isEmptyBoardPoint(c.right()) ||
				isEmptyBoardPoint(c.up()) ||
				isEmptyBoardPoint(c.down());
	}

	private boolean isEmptyBoardPoint(Coord c) {
		return boardPointContains(c, Board.Point.Empty);
	}

	@Override
	public boolean isLegalMoveAvailable() {
		return false;
	}

}
