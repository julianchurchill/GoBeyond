package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoRulesTests {

	private Board board;
	private GoRules rules;

	@Before
	public void SetUp() {
		board = new GoBoard( 19 );
		rules = new GoRules( board );
	}

	@Test
    public void MoveOnAnOccupiedIntersectionIsIllegal() {
		board.playStone( Board.Point.BlackStone, new Coord( 0, 0 ) );
		assertEquals( false, rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ) ) );
	}

	@Test
	public void MoveOnAnEmptyIntersectionWithNoNeighboursIsLegal() {
		assertEquals( true, rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ) ) );
		assertEquals( true, rules.isLegal( new Move( new Coord( 1, 0 ), Move.Colour.Black ) ) );
		assertEquals( true, rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.Black ) ) );
	}

	@Test
	public void AMoveAdjacentToAnEmptyPointIsLegal() {
		Coord whiteMove = new Coord( 1, 1 );
		Rules rules1 = new GoRules( makeBoardWithSingleLibertyPointForWhiteMove( whiteMove, whiteMove.up() ) );
		Rules rules2 = new GoRules( makeBoardWithSingleLibertyPointForWhiteMove( whiteMove, whiteMove.down() ) );
		Rules rules3 = new GoRules( makeBoardWithSingleLibertyPointForWhiteMove( whiteMove, whiteMove.left() ) );
		Rules rules4 = new GoRules( makeBoardWithSingleLibertyPointForWhiteMove( whiteMove, whiteMove.right() ) );

		assertEquals( true, rules1.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
		assertEquals( true, rules2.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
		assertEquals( true, rules3.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
		assertEquals( true, rules4.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
	}

 	private Board makeBoardWithSingleLibertyPointForWhiteMove( Coord potentialWhiteMove, Coord liberty ) {
		Board b = makeBoardWithEmptyPointSurroundedByBlack( potentialWhiteMove );
		b.removeStone( liberty );
		return b;
	}

	private Board makeBoardWithEmptyPointSurroundedByBlack(Coord potentialWhiteMove) {
		Board b = new GoBoard( 19 );
		surroundWithBlackStones(b, potentialWhiteMove);
		return b;
	}

	private void surroundWithBlackStones(Board b, Coord c) {
		b.playStone( Board.Point.BlackStone, c.up() );
		b.playStone( Board.Point.BlackStone, c.down() );
		b.playStone( Board.Point.BlackStone, c.left() );
		b.playStone( Board.Point.BlackStone, c.right() );
	}

	@Test
	public void SimpleSingleStoneSuicideIsIllegal() {
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );

		assertEquals( false, rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.White ) ) );
	}

	@Test
	public void MultiStoneSuicideIsIllegal() {
		Move whiteMove = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		Board board = new GoBoard( 19 );
		surroundWithBlackStones( board, whiteMove.coord() );
		surroundWithBlackStones( board, whiteMove.coord().right() );
		board.removeStone( whiteMove.coord() );
		board.playStone( Board.Point.WhiteStone, whiteMove.coord().right() );
		Rules rules1 = new GoRules( board );

		assertEquals( false, rules1.isLegal( whiteMove ) );
	}

	@Test
	public void PlayingAPointWithNoAdjacentEmptyPointsIsLegalIfConnectedStringHasAtLeastOneLiberty() {
//		fail("Write me!");
	}

//	public void TakingAKoIsLegal() {
}
