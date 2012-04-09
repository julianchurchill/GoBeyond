package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _RefereeTests {

	@Test
	public void submittingAnIllegalMoveToTheRefereeReturnsIllegalStatus() {
		Referee referee = new Referee();
		assertEquals( Referee.MoveStatus.IllegalMove, referee.submitMove( 0, 0 ) );
	}
}
