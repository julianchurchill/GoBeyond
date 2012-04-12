package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _StrictRefereeTests {

	private _TestableRules rules;
	private _TestableBoard board;
	private Referee referee;

	@Before
	public void SetUp() {
		rules = new _TestableRules();
		board = new _TestableBoard();
		referee = new StrictReferee( rules, board );
	}
	
	@Test
	public void RefereeChecksWithRulesWhetherMoveIsLegal() {
		referee.submitMove( new Move( 1, 1, null ) );
		assertEquals( true, rules.isLegalCalled );
		assertEquals( new Move( 1, 1, null ), rules.isLegalCalledWithMove );
	}

	@Test
	public void IfMoveIsLegalRefereeReturnsLegalStatus() {
		assertEquals( Referee.MoveStatus.LegalMove, referee.submitMove( new Move( 1, 1, null ) ) );
	}

	@Test
	public void IfMoveIsIllegalRefereeReturnsIllegalStatus() {
		rules.isLegalReturnValue = false;
		assertEquals( Referee.MoveStatus.IllegalMove, referee.submitMove( new Move( 1, 1, null ) ) );
	}

	@Test
	public void IfMoveIsLegalRefereeUpdatesTheBoard() {
		referee.submitMove( new Move( 1, 2, Move.Colour.White ) );
		assertEquals( true, board.playStoneCalled );
		assertEquals( Board.Point.WhiteStone, board.playStonePoint );
		assertEquals( 1, board.playStoneX );
		assertEquals( 2, board.playStoneY );
	}

	@Test
	public void IfMoveIsIllegalRefereeDoesNotUpdateTheBoard() {
		rules.isLegalReturnValue = false;
		referee.submitMove( new Move( 1, 2, Move.Colour.White ) );
		assertEquals( false, board.playStoneCalled );
	}

	@Test
	public void NotifiesMultipleMoveObservers() {
		_TestableMoveObserver observer1 = new _TestableMoveObserver();
		_TestableMoveObserver observer2 = new _TestableMoveObserver();
		referee.subscribeForAcceptedMoves( observer1 );
		referee.subscribeForAcceptedMoves( observer2 );
		Move submittedMove = new Move( 1, 2, Move.Colour.White );

		referee.submitMove( submittedMove );

		assertEquals( true, observer1.movePlayedCalled );
		assertEquals( submittedMove, observer1.movePlayedMove );
		assertEquals( true, observer2.movePlayedCalled );
		assertEquals( submittedMove, observer2.movePlayedMove );
	}
}
