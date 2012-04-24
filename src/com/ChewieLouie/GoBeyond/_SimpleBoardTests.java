package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _SimpleBoardTests {

	private Board b;
	
	@Before
	public void SetUp() {
		b = new SimpleBoard( 19 );
	}
	
	@Test
	public void BoardIsValueObject() {
		SimpleBoard board1 = new SimpleBoard( 4 );
		board1.playStone(Board.Point.BlackStone, new Coord( 1, 1 ) );
		board1.playStone(Board.Point.WhiteStone, new Coord( 3, 2 ) );
		SimpleBoard board2 = new SimpleBoard( 4 );
		board2.playStone(Board.Point.BlackStone, new Coord( 1, 1 ) );
		board2.playStone(Board.Point.WhiteStone, new Coord( 3, 2 ) );
		SimpleBoard board3 = new SimpleBoard( 4 );
		board3.playStone(Board.Point.BlackStone, new Coord( 0, 2 ) );
		board3.playStone(Board.Point.WhiteStone, new Coord( 1, 2 ) );

		assertTrue( "same boards are equal to each other", board1.equals( board2 ) );
		assertFalse( "different boards are not equal to each other", board1.equals( board3 ) );
		assertTrue( "same boards have the same hash code", board1.hashCode() == board2.hashCode() );
		assertFalse( "different boards have different hash codes", board1.hashCode() == board3.hashCode() );
		assertEquals( "to string", "....\n.b..\n...w\n....\n", board1.toString() );
	}

	@Test
	public void CanDuplicate() {
		b.playStone( Board.Point.BlackStone, new Coord( 14, 7 ) );
		b.playStone( Board.Point.WhiteStone, new Coord( 5, 9 ) );
		Board g = b.duplicate();
		for( int x = 0; x < 19; ++x )
			for( int y = 0; y < 19; ++y )
				assertEquals( b.getContentsOfPoint( new Coord( x, y ) ), g.getContentsOfPoint( new Coord( x, y ) ) );
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

	@Test
	public void CanCreateGoBoardsFromText() {
		Board newBoard = SimpleBoard.makeBoard( "wbw" + 
			    							"b.b" +
			    							"wbw" );
		assertEquals( Board.Point.WhiteStone, newBoard.getContentsOfPoint( new Coord( 0, 0 ) ) );
		assertEquals( Board.Point.BlackStone, newBoard.getContentsOfPoint( new Coord( 1, 0 ) ) );
		assertEquals( Board.Point.WhiteStone, newBoard.getContentsOfPoint( new Coord( 2, 0 ) ) );
		assertEquals( Board.Point.BlackStone, newBoard.getContentsOfPoint( new Coord( 0, 1 ) ) );
		assertEquals( Board.Point.Empty, newBoard.getContentsOfPoint( new Coord( 1, 1 ) ) );
		assertEquals( Board.Point.BlackStone, newBoard.getContentsOfPoint( new Coord( 2, 1 ) ) );
		assertEquals( Board.Point.WhiteStone, newBoard.getContentsOfPoint( new Coord( 0, 2 ) ) );
		assertEquals( Board.Point.BlackStone, newBoard.getContentsOfPoint( new Coord( 1, 2 ) ) );
		assertEquals( Board.Point.WhiteStone, newBoard.getContentsOfPoint( new Coord( 2, 2 ) ) );
	}
}
