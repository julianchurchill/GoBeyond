package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _RefereeTests {

	private _TestableRules rules;
	private _TestableBoard board;
	private Referee referee;

	@Before
	public void SetUp() {
		rules = new _TestableRules();
		board = new _TestableBoard();
		referee = new Referee( rules, board );
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
}
