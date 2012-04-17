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
    public void UseBoardAnalyzerToTestStringLifeDuringLegalityCheck() {
		rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ) );
		assertEquals( true, boardAnalyzer.isStringAliveCalled );
	}

	@Test
    public void MoveOnAnOccupiedIntersectionIsIllegal() {
		board.playStone( Board.Point.BlackStone, new Coord( 0, 0 ) );
		assertEquals( false, rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ) ) );
	}

//  public void SuicideToCaptureIsLegal() {
//	public void TakingAKoIsLegal() {
}
