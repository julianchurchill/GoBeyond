package com.chewielouie.gobeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.chewielouie.gobeyond.util.Coord;

public class _GameHistoryTests {

	GameHistory history = new GameHistory();

	@Before
	public void SetUp() {
		history = new GameHistory();
	}
	
	@Test
	public void valueObject() {
		GameHistory h1 = new GameHistory();
		SimpleBoard b = new SimpleBoard(5);
		Move m = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		h1.add( b, m );
		GameHistory h2 = new GameHistory();
		h2.add( b, m );
		GameHistory h3 = new GameHistory();
		h3.add( new SimpleBoard(5), new Move( new Coord( 2, 3 ), Move.Colour.Black ) );
		
		assertEquals( "game histories with same content are equal", h1, h2 );
		assertFalse( "game histories with differing content are not equal", h1.equals( h3 ) );
		assertEquals( "game histories with same content have the same hash code", h1.hashCode(), h2.hashCode() );
		assertTrue( "game histories with differing content do not have the same hash code", h1.hashCode() != h3.hashCode() );
	}
	
	@Test
	public void historySizeIncreasesAsMovesAreAdded() {
		assertEquals( "empty history has size 0", 0, history.size() );

		history.add( new SimpleBoard(5), new Move( new Coord( 2, 3 ), Move.Colour.Black ) );
		assertEquals( "history with one move has size 1", 1, history.size() );
		
		history.add( new SimpleBoard(5), new Move( new Coord( 2, 3 ), Move.Colour.Black ) );
		assertEquals( "history with two moves has size 2", 2, history.size() );
	}

	@Test
	public void lastbutOneBoardReturnsNullIfLessThanTwoBoardInTheHistory() {
		assertEquals( null, history.lastButOneBoard() );
		history.add( new SimpleBoard( 19 ), null );
		assertNull( history.lastButOneBoard() );
	}

	@Test
	public void lastbutOneBoardReturnsBoardSubmittedTwoMovesAgo() {
		SimpleBoard b = new SimpleBoard( 19 );
		history.add( b, null );
		history.add( new SimpleBoard( 19 ), null );
		assertTrue( history.lastButOneBoard() == b );
	}

	@Test
	public void lastMoveReturnsNullIfNoMovesInTheHistory() {
		assertNull( history.lastMove() );
	}

	@Test
	public void lastMoveReturnsLastMostSubmitted() {
		Move move = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		history.add( new SimpleBoard( 19 ), move );
		assertTrue( history.lastMove() == move );
	}

	@Test
	public void lastButOneMoveReturnsNullIfLessThanTwoMovesInTheHistory() {
		assertNull( history.lastButOneMove() );
		history.add( new SimpleBoard( 19 ), new Move( new Coord( 1, 1 ), Move.Colour.Black ) );
		assertNull( history.lastButOneMove() );
	}

	@Test
	public void lastButOneMoveReturnsLastButOneMoveSubmitted() {
		Move move = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		history.add( new SimpleBoard( 19 ), move );
		history.add( new SimpleBoard( 19 ), new Move( new Coord( 1, 1 ), Move.Colour.Black ) );
		assertTrue( history.lastButOneMove() == move );
	}
	
	@Test
	public void boardNumberAtIndexRetrievesTheBoardStored() {
		SimpleBoard tb1 = new SimpleBoard( 9 );
		SimpleBoard tb2 = new SimpleBoard( 9 );

		history.add( tb1, new Move( new Coord( 1, 1 ), Move.Colour.Black ) );
		history.add( tb2, new Move( new Coord( 1, 1 ), Move.Colour.Black ) );

		assertTrue( "board 0 is the same as the first one added to history", history.boardNumber(0) == tb1 );
		assertTrue( "board 1 is the same as the first one added to history", history.boardNumber(1) == tb2 );
	}
	
	@Test
	public void moveNumberAtIndexRetrievesTheMoveStored() {
		Move move1 = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		Move move2 = new Move( new Coord( 1, 1 ), Move.Colour.Black );

		history.add( new SimpleBoard( 9 ), move1 );
		history.add( new SimpleBoard( 9 ), move2 );

		assertTrue( "move 0 is the same as the first one added to history", history.moveNumber(0) == move1 );
		assertTrue( "move 1 is the same as the first one added to history", history.moveNumber(1) == move2 );
	}
}
