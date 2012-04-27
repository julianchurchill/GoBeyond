package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _RandomMoveSourceTests {

	_TestableReferee referee;
	_TestableRandomGenerator rand;
	RandomMoveSource moveSource;

	@Before
	public void SetUp() {
		rand = new _TestableRandomGenerator();
		rand.primeWith( 1 );
		rand.primeWith( 0 );
		referee = new _TestableReferee();
		referee.isLegalReturn = true;
		referee.boardReturn = new SimpleBoard(9);
		moveSource = new RandomMoveSource( rand, referee );
	}

	@Test
	public void getMoveProducesARandomMove() {
		assertTrue( "random move generated is not null", moveSource.getMove( Move.Colour.White, referee.boardReturn ) != null );
	}

	@Test
	public void getMoveUsesRandomGenerator() {
		moveSource.getMove( Move.Colour.Black, referee.boardReturn );

		assertTrue( "random generator is used", rand.generateCalled );
		assertEquals( "minimum bounds of random number is zero", 0, rand.generateMin );
	}
	
	@Test
	public void doesNotFillInCompletelySurroundedEmptyPoints() {
		Board board = SimpleBoard.makeBoard( "wwww" +
											   ".w.w" +
										 	   "wwww" +
											   "wwww" );

		Move move = moveSource.getMove( Move.Colour.White, board );
		assertEquals( "pass when only empty points are friendly eyes", Move.passMove(Move.Colour.White), move );
	}

	@Test
	public void returnsAPassIfNoLegalMoveAvailable() {
		referee.isLegalReturn = false;
		Board board = SimpleBoard.makeBoard( "..." +
												   "..." +
												   "..." );

		Move move = moveSource.getMove( Move.Colour.White, board );
		assertEquals( "pass when only illegal moves available", Move.passMove(Move.Colour.White), move );
	}
	
	@Test
	public void getMoveUsesRefereeToCheckLegalityOfPotentialMoves() {
		moveSource.getMove( Move.Colour.White, referee.boardReturn );

		assertTrue( "get move calls referee.isLegal()", referee.isLegalCalled );
		assertEquals( "call to isLegal uses current board", referee.boardReturn, referee.isLegalCalledWithBoard );
	}
	
	@Test
	public void getMoveWillConsiderEmptyPointWithOneEmptyNeighbour() {
		Board board = SimpleBoard.makeBoard( "w..w" +
													 "wwww" +
												 	 "wwww" +
													 ".www" );

		Move move = moveSource.getMove( Move.Colour.White, board );		
		assertEquals( "empty points with one empty neighbour is considered to be a playable move",
				new Move( new Coord( 2, 0 ), Move.Colour.White ), move );
	}
	
	@Test
	public void getMoveWillConsiderEmptyPointWithOneNonFriendlyNeighbour() {
		Board board = SimpleBoard.makeBoard( "w.bw" +
													 "wwww" +
												 	 "wwww" +
													 ".www" );
		
		rand.clearPrimedNumbers();
		rand.primeWith( 0 );

		Move move = moveSource.getMove( Move.Colour.White, board );		
		assertEquals( "empty points with one non friendly neighbour is considered to be a playable move",
				new Move( new Coord( 1, 0 ), Move.Colour.White ), move );
	}
}