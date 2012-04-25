package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

import com.ChewieLouie.GoBeyond.Board.Point;
import com.ChewieLouie.GoBeyond.Move.Colour;

public class RandomStrategy implements Strategy {

	private RandomGenerator randGenerator;
	private SimpleBoard board;
	private Colour colour;

	public RandomStrategy(RandomGenerator rand) {
		this.randGenerator = rand;
	}

	@Override
	public Move generateMove(SimpleBoard board, Colour colour) {
		this.board = board;
		this.colour = colour;
		List<Coord> points = availablePoints();
		if( points.size() == 0 )
			return Move.passMove(colour);
		return new Move( points.get( randGenerator.generate(0, points.size()-1 ) ), colour );
	}

	private List<Coord> availablePoints() {
		List<Coord> availablePoints = new ArrayList<Coord>();
		for( int y = 0; y < board.size; ++y ) {
			for( int x = 0; x < board.size; ++x ) {
				Coord c = new Coord( x, y );
				if( isEmpty(c) && isNotAFriendlyEye( c ) )
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
		if( isStoneOrEdge(c.up(), stoneColour) )
			numberOfSurroundingStones++;
		if( isStoneOrEdge(c.upLeft(), stoneColour) )
			numberOfSurroundingStones++;
		if( isStoneOrEdge(c.upRight(), stoneColour) )
			numberOfSurroundingStones++;
		if( isStoneOrEdge(c.down(), stoneColour) )
			numberOfSurroundingStones++;
		if( isStoneOrEdge(c.downLeft(), stoneColour) )
			numberOfSurroundingStones++;
		if( isStoneOrEdge(c.downRight(), stoneColour) )
			numberOfSurroundingStones++;
		if( isStoneOrEdge(c.left(), stoneColour) )
			numberOfSurroundingStones++;
		if( isStoneOrEdge(c.right(), stoneColour) )
			numberOfSurroundingStones++;
		return numberOfSurroundingStones < 7;
	}

	private boolean isStoneOrEdge(Coord c, Point stoneColour) {
		return board.getContentsOfPoint(c) == stoneColour ||
				board.getContentsOfPoint(c) == Point.OffBoard;
	}

}
