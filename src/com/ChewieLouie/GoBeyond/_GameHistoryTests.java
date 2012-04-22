package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GameHistoryTests {

	GameHistory history = new GameHistory();

	@Before
	public void SetUp() {
		history = new GameHistory();
	}

	@Test
	public void lastbutOneBoardReturnsNullIfLessThanTwoBoardInTheHistory() {
		assertEquals( null, history.lastButOneBoard() );
		history.add( new _TestableBoard( 19 ), null );
		assertNull( history.lastButOneBoard() );
	}

	@Test
	public void lastbutOneBoardReturnsBoardSubmittedTwoMovesAgo() {
		_TestableBoard b = new _TestableBoard( 19 );
		history.add( b, null );
		history.add( new _TestableBoard( 19 ), null );
		assertTrue( history.lastButOneBoard() == b );
	}

	@Test
	public void lastMoveReturnsNullIfNoMovesInTheHistory() {
		assertNull( history.lastMove() );
	}

	@Test
	public void lastMoveReturnsLastMostSubmitted() {
		Move move = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		history.add( new _TestableBoard( 19 ), move );
		assertTrue( history.lastMove() == move );
	}

	@Test
	public void lastButOneMoveReturnsNullIfLessThanTwoMovesInTheHistory() {
		assertNull( history.lastButOneMove() );
		history.add( new _TestableBoard( 19 ), new Move( new Coord( 1, 1 ), Move.Colour.Black ) );
		assertNull( history.lastButOneMove() );
	}

	@Test
	public void lastButOneMoveReturnsLastButOneMoveSubmitted() {
		Move move = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		history.add( new _TestableBoard( 19 ), move );
		history.add( new _TestableBoard( 19 ), new Move( new Coord( 1, 1 ), Move.Colour.Black ) );
		assertTrue( history.lastButOneMove() == move );
	}
}
