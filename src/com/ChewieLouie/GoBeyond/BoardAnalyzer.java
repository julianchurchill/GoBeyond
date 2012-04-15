package com.ChewieLouie.GoBeyond;

import java.util.HashSet;
import java.util.Set;

import com.ChewieLouie.GoBeyond.Board.Point;

public class BoardAnalyzer {

	private Board board;

	public BoardAnalyzer(Board board) {
		this.board = board;
	}

	public boolean isStringAlive(Coord c ) {
		Set<Coord> visitedPoints = new HashSet<Coord>();
		Point colour = board.getContentsOfPoint(c);
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

}
