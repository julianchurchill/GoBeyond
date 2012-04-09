package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _RefereeTests {

	private TestableRules rules;
	private Board board;
	private Referee referee;

	@Before
	public void SetUp() {
		rules = new TestableRules();
		board = new Board();
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
		referee.submitMove( new Move( 1, 1, Stone.Colour.Black ) );
		assertEquals( Stone.Colour.Black, board.getContentsOfPoint( 1, 1 ).colour() );
	}
}
