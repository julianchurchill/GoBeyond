package com.ChewieLouie.GoBeyond;

public class GoRules implements Rules {

	private Board board;
	private Board testBoard;
	private boolean useTestBoard = false;

	public GoRules(Board board) {
		this.board = board;
	}

	@Override
	public boolean isLegal(Move m) {
		return isEmptyBoardPoint(m.coord()) && isNotSuicide(m);
	}

	private boolean isNotSuicide(Move m) {
		return stringAddedToWillStillHaveLiberties( m );
	}

	private boolean stringAddedToWillStillHaveLiberties(Move m) {
		Board.Point colour = m.colour() == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone;
		testBoard = board.duplicate();
		testBoard.playStone( colour, m.coord() );
		useTestBoard = true;
		boolean libertiesFound = false;
		if( pointOrNeighboursHaveLiberties(m.coord(), colour) )
			libertiesFound = true;
		useTestBoard = false;
		return libertiesFound;
	}

	private boolean pointOrNeighboursHaveLiberties(Coord c, Board.Point colour) {
		return contributesOneOrMoreLibertiesToString(c, colour) ||
		    contributesOneOrMoreLibertiesToString(c.up(), colour) ||
			contributesOneOrMoreLibertiesToString(c.down(), colour) ||
			contributesOneOrMoreLibertiesToString(c.left(), colour) ||
			contributesOneOrMoreLibertiesToString(c.right(), colour);
	}

	private boolean contributesOneOrMoreLibertiesToString(Coord c, Board.Point colour) {
		return boardPointContains( c, colour ) && hasAnyEmptyNeighbours( c );
	}

	private boolean boardPointContains(Coord c, Board.Point expectedColour) {
		return board().getContentsOfPoint( c ) == expectedColour;
	}

	private Board board() {
		if( useTestBoard  )
			return testBoard;
		return board;
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
