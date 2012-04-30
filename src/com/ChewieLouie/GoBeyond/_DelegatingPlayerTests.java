package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ChewieLouie.GoBeyond.Referee.MoveStatus;
import com.ChewieLouie.GoBeyond.util.Coord;

public class _DelegatingPlayerTests {

	_TestableReferee referee;
	_TestableMoveSource moveSource;
	DelegatingPlayer player;

	@Before
	public void setUp() {
		referee = new _TestableReferee();
		moveSource = new _TestableMoveSource();
		player = new DelegatingPlayer( referee, Move.Colour.White, moveSource );
	}

	@Test
	public void passesBoardFromRefereeToMoveSource() {
		referee.boardReturn = new SimpleBoard(9);
		player.playMove();
		assertTrue( referee.boardReturn == moveSource.getMoveCalledWithBoard );
	}

	@Test
	public void submitsMoveToReferee() {
		moveSource.getMoveReturn = new Move( new Coord( 1, 1 ), Move.Colour.Black );
		
		player.playMove();
		assertTrue( "submits move to referee", referee.submitMoveCalled );
		assertEquals( "submits move from move source to referee", moveSource.getMoveReturn, referee.submitMoveArg );
	}

	@Test
	public void retrievesMoveFromSource() {
		player.playMove();
		assertTrue( "retrieves move from source", moveSource.getMoveCalled );
		assertEquals( "passes colour to source", Move.Colour.White, moveSource.getMoveCalledWithColour );
	}
	
	@Test
	public void returnsMoveStatusFromReferee() {
		referee.submitMoveReturn = MoveStatus.IllegalMove;
		assertEquals( "", MoveStatus.IllegalMove, player.playMove() );
		referee.submitMoveReturn = MoveStatus.LegalMove;
		assertEquals( "", MoveStatus.LegalMove, player.playMove() );
	}
}
