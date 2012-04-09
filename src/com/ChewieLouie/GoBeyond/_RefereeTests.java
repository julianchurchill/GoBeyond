package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _RefereeTests {

	@Test
	public void RefereeChecksWithRulesWhetherMoveIsLegal() {
		TestableRules rules = new TestableRules();
		Referee referee = new Referee( rules );
		referee.submitMove( new Move( 1, 1 ) );
		assertEquals( true, rules.isLegalCalled );
		assertEquals( new Move( 1, 1 ), rules.isLegalCalledWithMove );
	}

	@Test
	public void IfMoveIsLegalRefereeReturnsLegalStatus() {
		TestableRules rules = new TestableRules();
		rules.isLegalReturnValue = true;
		Referee referee = new Referee( rules );
		assertEquals( Referee.MoveStatus.LegalMove, referee.submitMove( new Move( 1, 1 ) ) );
	}

	@Test
	public void IfMoveIsIllegalRefereeReturnsIllegalStatus() {
		TestableRules rules = new TestableRules();
		rules.isLegalReturnValue = false;
		Referee referee = new Referee( rules );
		assertEquals( Referee.MoveStatus.IllegalMove, referee.submitMove( new Move( 1, 1 ) ) );
	}
}
