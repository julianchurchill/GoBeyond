package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoRulesTests {

	private GoBoard board;
	private GoRules rules;
	private _TestableBoardAnalyzer boardAnalyzer;

	@Before
	public void SetUp() {
		board = new GoBoard( 19 );
		boardAnalyzer = new _TestableBoardAnalyzer();
		rules = new GoRules( boardAnalyzer );
	}

	@Test
    public void shouldUseBoardAnalyzerDuringLegalityCheck() {
		rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ), board, null );
		assertTrue( boardAnalyzer.isStringAliveCalled );
	}

	@Test
    public void moveOnAnOccupiedIntersectionIsIllegal() {
		board.playStone( Board.Point.BlackStone, new Coord( 0, 0 ) );
		assertFalse( rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ), board, null ) );
	}

	// the following four test check capturing in each direction
	// I don't like them but not sure how to compress...
	@Test
	public void suicideToCaptureIsLegal_eastCapture() {
		// .bw..
		// b*bw.
		// .bw..
		// .....
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 3, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 2 ) );
		boardAnalyzer.isStringAliveReturnDefault = true;
		boardAnalyzer.stringIsDead.add( new Coord( 2, 1 ) );
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void suicideToCaptureIsLegal_westCapture() {
		// wwbb.
		// b*wb.
		// wbb..
		// .....
		board.playStone( Board.Point.WhiteStone, new Coord( 0, 0 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 0, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 2 ) );
		boardAnalyzer.isStringAliveReturnDefault = true;
		boardAnalyzer.stringIsDead.add( new Coord( 0, 1 ) );
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void suicideToCaptureIsLegal_northCapture() {
		// wbwb.
		// w*wb.
		// bbb..
		// .....
		board.playStone( Board.Point.WhiteStone, new Coord( 0, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 0 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 2 ) );
		boardAnalyzer.isStringAliveReturnDefault = true;
		boardAnalyzer.stringIsDead.add( new Coord( 1, 0 ) );
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void suicideToCaptureIsLegal_southCapture() {
		// .b...
		// b*b..
		// wbw..
		// .w...
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 0, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 2 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 1, 3 ) );
		boardAnalyzer.isStringAliveReturnDefault = true;
		boardAnalyzer.stringIsDead.add( new Coord( 1, 2 ) );
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void ImmediatelyTakingAKoIsIllegal() {
		// .bw..
		// b*bw.
		// .bw..
		// .....
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 3, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 2 ) );
		boardAnalyzer.isStringAliveReturnDefault = true;
		boardAnalyzer.stringIsDead.add( new Coord( 2, 1 ) );
		boardAnalyzer.stringIsDead.add( new Coord( 1, 1 ) );

		_TestableBoard tb1 = new _TestableBoard( 19 );
		tb1.equalsReturn = true;
		_TestableBoard tb2 = new _TestableBoard( 19 );
		GameHistory history = new GameHistory();
		history.add( tb1, null );
		history.add( tb2, null );

		assertFalse( "taking a ko immediately is illegal", 
				rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, history ) );
	}

	@Test
	public void TakingAKoAfterADifferentMoveIsLegal() {
		// .bw..
		// b*bw.
		// .bw..
		// .....
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 3, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 2 ) );
		boardAnalyzer.isStringAliveReturnDefault = true;
		boardAnalyzer.stringIsDead.add( new Coord( 2, 1 ) );
		boardAnalyzer.stringIsDead.add( new Coord( 1, 1 ) );

		_TestableBoard tb1 = new _TestableBoard( 19 );
		_TestableBoard tb2 = new _TestableBoard( 19 );
		GameHistory history = new GameHistory();
		history.add( tb1, null );
		history.add( tb2, null );

		assertTrue( "taking a Ko after a different move is legal",
				rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, history ) );
	}

//	@Test
//	public void endIsNotDetectedBeforeMaxMovesReached() {
//		for( int i = 0; i < 10; ++i ) {
//			assertEquals( false, rules.endDetected( gameHistory ) );
//			gameHistory.add( null, new Move( new Coord( 0, 0 ), Move.Colour.Black ) );
//		}
//	}
}
