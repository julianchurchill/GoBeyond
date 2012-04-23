package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoStringLifeAnalyzerTests {

	private StringLifeAnalyzer analyzer;

	@Before
	public void SetUp() {
		analyzer = new GoStringLifeAnalyzer();
	}

	@Test
	public void singleStoneWithOnlyEmptyNeighboursIsAlive() {
		Board board = GoBoard.makeBoard("..." +
									    ".b." +
					  				    "..." );
		assertTrue( analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void multiStoneStringWithOnlyEmptyNeighboursIsAlive() {
		Board board = GoBoard.makeBoard("....." +
								  ".b..." +
								  ".b..." +
								  ".b..." +
								  "....." );
		assertTrue( analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void singleStoneWithNoEmptyNeighboursIsDead() {
		Board board = GoBoard.makeBoard(".b.." +
				  				  "bwb." +
				  				  ".b.." +
								  "...." );
		assertFalse( analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void multiStoneStringWithNoEmptyNeighboursIsDead() {
		Board board = GoBoard.makeBoard(".bbb." +
								  "bwwwb" +
								  ".bbb." +
								  "....." +
								  "....." );
		assertFalse( analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}

	@Test
	public void multiStoneStringWithOneEmptyNeighbourIsAlive() {
		Board board = GoBoard.makeBoard(".bbb." +
								  "bwww." +
								  ".bbb." +
								  "....." +
								  "....." );
		assertTrue( analyzer.isStringAlive( board, new Coord( 1, 1 ) ) );
	}
	
	@Test
	public void stonesOfStringReturnsAllStonesInAString() {
		Board board = GoBoard.makeBoard("bb.." +
									    "b.b." +
									    "bbw." +
									    "...." );
		StringOfStones stones = analyzer.stonesOfString( new Coord( 0, 0 ), board );
		assertEquals( 5, stones.size() );
		assertTrue( stones.find( new Coord( 0, 0 ) ) );
		assertTrue( stones.find( new Coord( 1, 0 ) ) );
		assertTrue( stones.find( new Coord( 0, 1 ) ) );
		assertTrue( stones.find( new Coord( 0, 2 ) ) );
		assertTrue( stones.find( new Coord( 1, 2 ) ) );
	}
}
