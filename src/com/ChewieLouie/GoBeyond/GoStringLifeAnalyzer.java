package com.ChewieLouie.GoBeyond;

import java.util.HashSet;
import java.util.Set;

import com.ChewieLouie.GoBeyond.Board.Point;

public class GoStringLifeAnalyzer implements StringLifeAnalyzer {

	private Board board;

	public GoStringLifeAnalyzer() {
	}

	@Override
	public boolean isStringAlive(Board b, Coord c ) {
		this.board = b;
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

	@Override
	public StringOfStones stonesOfString(Coord c, Board b) {
		Point colour = b.getContentsOfPoint(c);
		StringOfStones stones = new StringOfStones();
		if( colour == Point.BlackStone || colour == Point.WhiteStone )
			findConnectedStones( c, colour, b, stones );
		return stones;
	}

	private void findConnectedStones(Coord c, Point colour, Board b, StringOfStones stones) {
		if( b.getContentsOfPoint(c) != colour || stones.find( c ) )
			return;
		stones.add(c);
		findConnectedStones( c.up(), colour, b, stones );
		findConnectedStones( c.down(), colour, b, stones );
		findConnectedStones( c.left(), colour, b, stones );
		findConnectedStones( c.right(), colour, b, stones );
	}

}
