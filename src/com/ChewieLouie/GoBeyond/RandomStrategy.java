package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

import com.ChewieLouie.GoBeyond.Board.Point;
import com.ChewieLouie.GoBeyond.Move.Colour;

public class RandomStrategy implements Strategy {

	private RandomGenerator randGenerator;
	private Board board;
	private Colour colour;
	private Rules rules;

	public RandomStrategy(RandomGenerator rand, Rules rules) {
		this.randGenerator = rand;
		this.rules = rules;
	}

	@Override
	public Move generateMove(Board board, Colour colour) {
		this.board = board;
		this.colour = colour;
		List<Coord> points = availablePoints();
		if( points.size() == 0 )
			return Move.passMove(colour);
		return new Move( points.get( randGenerator.generate(0, points.size()-1 ) ), colour );
	}

	private List<Coord> availablePoints() {
		List<Coord> availablePoints = new ArrayList<Coord>();
		for( int y = 0; y < board.size(); ++y ) {
			for( int x = 0; x < board.size(); ++x ) {
				Coord c = new Coord( x, y );
				if( isEmpty(c) && isNotAFriendlyEye( c ) && rules.isLegal( new Move( c, colour ), board, null ) )
					availablePoints.add( c );
			}
		}
		return availablePoints;
	}

	private boolean isEmpty(Coord c) {
		return board.getContentsOfPoint( c ) == Board.Point.Empty;
	}

	private boolean isNotAFriendlyEye(Coord c) {
		int numberOfSurroundingStones = 0;
		Point stoneColour = Move.toStone(colour);
		for( Coord a : c.orthogonalCoords() )
			numberOfSurroundingStones += countStoneOrEdge( a, stoneColour );
		return numberOfSurroundingStones < 7;
	}

	private int countStoneOrEdge(Coord c, Point stoneColour) {
		return ( board.getContentsOfPoint(c) == stoneColour ||
				 board.getContentsOfPoint(c) == Point.OffBoard ) ? 1 : 0;
	}
}
