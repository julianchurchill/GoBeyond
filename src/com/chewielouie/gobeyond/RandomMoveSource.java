package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

import com.ChewieLouie.GoBeyond.Board.Point;
import com.ChewieLouie.GoBeyond.Move.Colour;
import com.ChewieLouie.GoBeyond.util.Coord;

public class RandomMoveSource implements MoveSource {

	private RandomGenerator randGenerator;
	private Board board;
	private Referee referee;
	private Colour colour;

	public RandomMoveSource(RandomGenerator rand, Referee referee) {
		this.randGenerator = rand;
		this.referee = referee;
	}

	@Override
	public Move getMove(Colour colour, Board board) {
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
				if( isPlayable(c) )
					availablePoints.add( c );
			}
		}
		return availablePoints;
	}

	private boolean isPlayable(Coord c) {
		return isEmpty(c) && isATrueEye( c ) == false && referee.isLegal( new Move( c, colour ), board );
	}

	private boolean isEmpty(Coord c) {
		return board.getContentsOfPoint( c ) == Board.Point.Empty;
	}

	private boolean isATrueEye(Coord c) {
		Point stoneColour = Move.toStone(colour);
		if( hasAdjacentEmptyOrEnemyPoint(c, enemyColour(stoneColour)) )
			return false;
		return countOrthogonallyAdjacentFriendlyPoints(c, stoneColour) >= 7;
	}

	private int countOrthogonallyAdjacentFriendlyPoints(Coord c,
			Point stoneColour) {
		int numberOfSurroundingStones = 0;
		for( Coord a : c.orthogonalCoords() )
			numberOfSurroundingStones += countStoneOrEdge( a, stoneColour );
		return numberOfSurroundingStones;
	}

	private Point enemyColour(Point stoneColour) {
		return stoneColour == Point.BlackStone ? Point.WhiteStone : Point.BlackStone;
	}

	private boolean hasAdjacentEmptyOrEnemyPoint(Coord c, Point enemyColour) {
		return board.getContentsOfPoint(c.up()) == Point.Empty || board.getContentsOfPoint(c.up()) == enemyColour ||
				board.getContentsOfPoint(c.down()) == Point.Empty || board.getContentsOfPoint(c.down()) == enemyColour ||
				board.getContentsOfPoint(c.left()) == Point.Empty || board.getContentsOfPoint(c.left()) == enemyColour ||
				board.getContentsOfPoint(c.right()) == Point.Empty || board.getContentsOfPoint(c.right()) == enemyColour;
	}

	private int countStoneOrEdge(Coord c, Point stoneColour) {
		return ( board.getContentsOfPoint(c) == stoneColour ||
				 board.getContentsOfPoint(c) == Point.OffBoard ) ? 1 : 0;
	}

}
