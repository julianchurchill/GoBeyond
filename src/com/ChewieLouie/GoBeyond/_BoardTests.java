package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _BoardTests {

	private Board b;
	
	@Before
	public void SetUp() {
		b = new GoBoard();
	}
	
	@Test
	public void APointWhichHasNotBeenPlayedOnIsEmpty() {
		assertEquals( Board.Point.Empty, b.getContentsOfPoint( 0, 0 ) );
	}

	@Test
	public void PlayingAStoneAddsItToTheBoard() {
		b.playStone( Board.Point.BlackStone, 0, 0 );
		assertEquals( Board.Point.BlackStone, b.getContentsOfPoint( 0, 0 ) );

		b.playStone( Board.Point.WhiteStone, 0, 0 );
		assertEquals( Board.Point.WhiteStone, b.getContentsOfPoint( 0, 0 ) );
	}

	@Test
	public void RemovingAStoneLeavesAnEmptyPoint() {
		b.playStone( Board.Point.BlackStone, 0, 0 );
		b.removeStone( 0, 0 );
		assertEquals( Board.Point.Empty, b.getContentsOfPoint( 0, 0 ) );
	}
}
