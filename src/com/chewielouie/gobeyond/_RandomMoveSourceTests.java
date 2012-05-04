package com.ChewieLouie.GoBeyond;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ChewieLouie.GoBeyond.util.Coord;

public class _RandomMoveSourceTests {

	Referee referee;
	RandomGenerator rand;
	RandomMoveSource moveSource;
	SimpleBoard board;

	@Before
	public void SetUp() {
		rand = mock( RandomGenerator.class );
		when(rand.generate(anyInt(), anyInt())).thenReturn(1, 0);
		referee = mock( Referee.class );
		when(referee.isLegal((Move)any(), (Board)any())).thenReturn( true );
		board = new SimpleBoard(9);
		when(referee.board()).thenReturn( board );
		moveSource = new RandomMoveSource( rand, referee );
	}

	@Test
	public void getMoveProducesARandomMove() {
		assertTrue( "random move generated is not null", moveSource.getMove( Move.Colour.White, new SimpleBoard(9) ) != null );
	}

	@Test
	public void getMoveUsesRandomGenerator() {
		moveSource.getMove( Move.Colour.Black, new SimpleBoard(9) );

		verify(rand).generate(eq(0),  anyInt());
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
		when(referee.isLegal((Move)any(), (Board)any())).thenReturn(false);
		Board board = SimpleBoard.makeBoard( "..." +
												   "..." +
												   "..." );

		Move move = moveSource.getMove( Move.Colour.White, board );
		assertEquals( "pass when only illegal moves available", Move.passMove(Move.Colour.White), move );
	}
	
	@Test
	public void getMoveUsesRefereeToCheckLegalityOfPotentialMoves() {
		moveSource.getMove( Move.Colour.White, new SimpleBoard(9) );

		verify(referee, atLeastOnce()).isLegal((Move)any(), eq(board));
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
		
		when(rand.generate(anyInt(), anyInt())).thenReturn( 0 );

		Move move = moveSource.getMove( Move.Colour.White, board );		
		assertEquals( "empty points with one non friendly neighbour is considered to be a playable move",
				new Move( new Coord( 1, 0 ), Move.Colour.White ), move );
	}
}