package com.ChewieLouie.GoBeyond;

import java.util.HashSet;
import java.util.Set;

import com.ChewieLouie.GoBeyond.Board.Point;

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
		return stringAddedToWillStillBeAlive( m );
	}

	private boolean stringAddedToWillStillBeAlive(Move m) {
		Board.Point colour = m.colour() == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone;
		testBoard = board.duplicate();
		testBoard.playStone( colour, m.coord() );
		useTestBoard = true;
		boolean libertiesFound = false;
		if( stringHasLiberties(m.coord(), colour) )
			libertiesFound = true;
		useTestBoard = false;
		return libertiesFound;
	}

	private boolean stringHasLiberties(Coord c, Board.Point colour) {
		Set<Coord> visitedPoints = new HashSet<Coord>();
		return stringHasLibertiesRecursive(c, colour, visitedPoints);
	}

	private boolean stringHasLibertiesRecursive(Coord c, Point colour, Set<Coord> visitedPoints) {
		if( boardPointContains( c, colour ) == false || visitedPoints.contains( c ) )
			return false;
		visitedPoints.add( c );
		if( hasAnyEmptyNeighbours(c) )
			return true;

		return stringHasLibertiesRecursive(c.up(), colour, visitedPoints) ||
			stringHasLibertiesRecursive(c.down(), colour, visitedPoints) ||
			stringHasLibertiesRecursive(c.left(), colour, visitedPoints) ||
			stringHasLibertiesRecursive(c.right(), colour, visitedPoints);
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
