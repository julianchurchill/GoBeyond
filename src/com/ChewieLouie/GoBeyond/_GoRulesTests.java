package com.ChewieLouie.GoBeyond;

 import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoRulesTests {

	private Board board;
	private GoRules rules;
	private _TestableBoardAnalyzer boardAnalyzer;

	@Before
	public void SetUp() {
		board = new GoBoard( 19 );
		boardAnalyzer = new _TestableBoardAnalyzer();
		rules = new GoRules( board, boardAnalyzer );
	}

	@Test
    public void shouldUseBoardAnalyzerDuringLegalityCheck() {
		rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ) );
		assertEquals( true, boardAnalyzer.isStringAliveCalled );
	}

	@Test
    public void moveOnAnOccupiedIntersectionIsIllegal() {
		board.playStone( Board.Point.BlackStone, new Coord( 0, 0 ) );
		assertEquals( false, rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ) ) );
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
		boardAnalyzer.isStringAliveReturn.put( new Coord( 1, 0 ), true );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 0, 1 ), true );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 2, 1 ), false );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 1, 2 ), true );
		assertEquals( true, rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ) ) );
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
		boardAnalyzer.isStringAliveReturn.put( new Coord( 0, 1 ), false );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 1, 2 ), true );
		assertEquals( true, rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ) ) );
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
		boardAnalyzer.isStringAliveReturn.put( new Coord( 1, 0 ), false );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 1, 2 ), true );
		assertEquals( true, rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ) ) );
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
		boardAnalyzer.isStringAliveReturn.put( new Coord( 1, 0 ), true );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 0, 1 ), true );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 2, 1 ), true );
		boardAnalyzer.isStringAliveReturn.put( new Coord( 1, 2 ), false );
		assertEquals( true, rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ) ) );
	}

//	public void TakingAKoIsLegal() {
}
