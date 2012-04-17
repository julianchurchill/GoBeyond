package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoBoardAnalyzerTests {

	private Board board;
	private BoardAnalyzer analyzer;

	@Before
	public void SetUp() {
		board = new GoBoard( 19 );
		analyzer = new GoBoardAnalyzer(board);
	}

	@Test
	public void singleStoneWithOnlyEmptyNeighboursIsAlive() {
		board.playStone( Board.Point.BlackStone, new Coord( 1, 1 ) );
		assertEquals( true, analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void multiStoneStringWithOnlyEmptyNeighboursIsAlive() {
		board.playStone( Board.Point.BlackStone, new Coord( 1, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 1 ) );
		assertEquals( true, analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void singleStoneWithNoEmptyNeighboursIsDead() {
		// .b...
		// bwb..
		// .b...
		// .....
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		assertEquals( false, analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void multiStoneStringWithNoEmptyNeighboursIsDead() {
		// .bbb.
		// bwwwb
		// .bbb.
		// .....
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 1, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 3, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 4, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 2 ) );
		assertEquals( false, analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void multiStoneStringWithOneEmptyNeighbourIsAlive() {
		// .bbb.
		// bwww.
		// .bbb.
		// .....
		board.playStone( Board.Point.BlackStone, new Coord( 1, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 0 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 0, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 1, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 2, 1 ) );
		board.playStone( Board.Point.WhiteStone, new Coord( 3, 1 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 1, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 2, 2 ) );
		board.playStone( Board.Point.BlackStone, new Coord( 3, 2 ) );
		assertEquals( true, analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}
}
