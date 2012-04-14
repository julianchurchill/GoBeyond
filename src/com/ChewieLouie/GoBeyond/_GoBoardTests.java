package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoBoardTests {

	private Board b;
	
	@Before
	public void SetUp() {
		b = new GoBoard( 19 );
	}
	
	@Test
	public void APointWhichHasNotBeenPlayedOnIsEmpty() {
		assertEquals( Board.Point.Empty, b.getContentsOfPoint( new Coord( 0, 0 ) ) );
	}

	@Test
	public void PlayingAStoneAddsItToTheBoard() {
		b.playStone( Board.Point.BlackStone, new Coord( 0, 0 ) );
		assertEquals( Board.Point.BlackStone, b.getContentsOfPoint( new Coord( 0, 0 ) ) );

		b.playStone( Board.Point.WhiteStone, new Coord( 0, 0 ) );
		assertEquals( Board.Point.WhiteStone, b.getContentsOfPoint( new Coord( 0, 0 ) ) );
	}

	@Test
	public void RemovingAStoneLeavesAnEmptyPoint() {
		b.playStone( Board.Point.BlackStone, new Coord( 0, 0 ) );
		b.removeStone( new Coord( 0, 0 ) );
		assertEquals( Board.Point.Empty, b.getContentsOfPoint( new Coord( 0, 0 ) ) );
	}

	@Test
	public void GetContentsOfPointOffBoardReturnsOffBoard() {
		assertEquals( Board.Point.OffBoard, b.getContentsOfPoint( new Coord( 0, -1 ) ) );
		assertEquals( Board.Point.OffBoard, b.getContentsOfPoint( new Coord( -1, 0 ) ) );
		assertEquals( Board.Point.OffBoard, b.getContentsOfPoint( new Coord( -1, -1 ) ) );
		assertEquals( Board.Point.OffBoard, b.getContentsOfPoint( new Coord( 0, 19 ) ) );
		assertEquals( Board.Point.OffBoard, b.getContentsOfPoint( new Coord( 19, 0 ) ) );
		assertEquals( Board.Point.OffBoard, b.getContentsOfPoint( new Coord( 19, 19 ) ) );
	}
}
