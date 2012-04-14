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
		Rules rules1 = new GoRules( makeBoardWithWhiteSingleLibertyPoint( whiteMove, whiteMove.up() ) );
		Rules rules2 = new GoRules( makeBoardWithWhiteSingleLibertyPoint( whiteMove, whiteMove.down() ) );
		Rules rules3 = new GoRules( makeBoardWithWhiteSingleLibertyPoint( whiteMove, whiteMove.left() ) );
		Rules rules4 = new GoRules( makeBoardWithWhiteSingleLibertyPoint( whiteMove, whiteMove.right() ) );

		assertEquals( true, rules1.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
		assertEquals( true, rules2.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
		assertEquals( true, rules3.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
		assertEquals( true, rules4.isLegal( new Move( whiteMove, Move.Colour.White ) ) );
	}

	private Board makeBoardWithWhiteSingleLibertyPoint( Coord potentialWhiteMove, Coord liberty ) {
		Board b = new GoBoard( 19 );
		b.playStone( Board.Point.BlackStone, potentialWhiteMove.up() );
		b.playStone( Board.Point.BlackStone, potentialWhiteMove.down() );
		b.playStone( Board.Point.BlackStone, potentialWhiteMove.left() );
		b.playStone( Board.Point.BlackStone, potentialWhiteMove.right() );
		b.removeStone( liberty );
		return b;
	}

	@Test
	public void SimpleSingleStoneSuicideIsIllegal() {
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );

		assertEquals( false, rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.White ) ) );
	}

//	public void PlayingAPointWithNoAdjacentEmptyPointsIsLegalIfAllNeighboursAreYourColour() {
//	public void MultiStoneSuicideIsIllegal() {
//	public void TakingAKoIsLegal() {
}
