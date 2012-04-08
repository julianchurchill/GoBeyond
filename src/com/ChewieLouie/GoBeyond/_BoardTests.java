package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _BoardTests {

	private Board b;
	
	@Before
	public void SetUp() {
		b = new Board();
	}
	
	@Test
	public void APointWhichHasNotBeenPlayedOnIsEmpty() {
		assertEquals( Board.PointStatus.Empty, b.getPointStatus( 0, 0 ) );
	}

	@Test
	public void PlayingAStoneMakesThePointOccupied() {
		b.playStone( new Stone( Stone.Colour.Black ), 0, 0 );
		assertEquals( Board.PointStatus.Occupied, b.getPointStatus( 0, 0 ) );
	}

	@Test
	public void PlayingAStoneAddsItToTheBoard() {
		b.playStone( new Stone( Stone.Colour.Black ), 0, 0 );
		assertEquals( Stone.Colour.Black, b.getContentsOfPoint( 0, 0 ).colour() );

		b.playStone( new Stone( Stone.Colour.White ), 0, 0 );
		assertEquals( Stone.Colour.White, b.getContentsOfPoint( 0, 0 ).colour() );
	}

	@Test
	public void RemovingAStoneLeavesAnEmptyPoint() {
		b.playStone( new Stone( Stone.Colour.Black ), 0, 0 );
		b.removeStone( 0, 0 );
		assertEquals( Board.PointStatus.Empty, b.getPointStatus( 0, 0 ) );
	}
}
