package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _BoardAnalyzerTests {

	private Board board;
	private BoardAnalyzer analyzer;

	@Before
	public void SetUp() {
		board = new GoBoard( 19 );
		analyzer = new BoardAnalyzer(board);
	}

	@Test
	public void singleStoneWithOnlyEmptyNeighboursIsAlive() {
		board.playStone( Board.Point.BlackStone, new Coord( 1, 1 ) );
		assertEquals( true, analyzer.isStringAlive( new Coord( 1, 1 ) ) );
	}

	@Test
	public void multiStoneStringWithOnlyEmptyNeighboursIsAlive() {
		board.playStone( Board.Point.BlackStone, new Coord( 1, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 1 ) );
		assertEquals( true, analyzer.isStringAlive( new Coord( 1, 1 ) ) );
	}
}
