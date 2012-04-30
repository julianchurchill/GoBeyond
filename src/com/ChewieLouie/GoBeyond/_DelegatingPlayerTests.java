package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.ChewieLouie.GoBeyond.Referee.MoveStatus;
import com.ChewieLouie.GoBeyond.util.Coord;

public class _DelegatingPlayerTests {

	private Referee referee;
	private DelegatingPlayer player;
	private MoveSource moveSource;

	@Before
	public void setUp() {
		referee = mock( Referee.class );
		moveSource = mock( MoveSource.class );
		player = new DelegatingPlayer( referee, Move.Colour.White, moveSource );
	}

	@Test
	public void playMovePassesCorrectColourAndBoardFromRefereeToMoveSource() {
		SimpleBoard board = new SimpleBoard(9);
		when(referee.board()).thenReturn(board);

		player.playMove();
		verify(moveSource).getMove(Move.Colour.White, board);
	}

	@Test
	public void submitsMoveFromMoveSourceToReferee() {
		Move m = new Move( new Coord( 1, 1 ), Move.Colour.White );
		when(moveSource.getMove(Move.Colour.White, null)).thenReturn(m);
		
		player.playMove();
		verify(referee).submitMove(m);
	}
	
	@Test
	public void returnsMoveStatusFromReferee() {
		when(referee.submitMove(null)).thenReturn(MoveStatus.IllegalMove);
		assertEquals( "illegal status can be passed from referee", MoveStatus.IllegalMove, player.playMove() );
		
		when(referee.submitMove(null)).thenReturn(MoveStatus.LegalMove);
		assertEquals( "legal status can be passed from referee", MoveStatus.LegalMove, player.playMove() );
	}
}
