package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _GoPlayerTests {

	@Test
	public void generateMoveSubmitsTheMoveToTheReferee() {
		_TestableReferee referee = new _TestableReferee();
		_TestableStrategy s = new _TestableStrategy();
		s.generateMoveReturnValue = new Move( new Coord( 4, 5 ), Move.Colour.Black );
		GoPlayer p = new GoPlayer( referee, s );

		p.playMove();
		
		assertEquals( true, referee.submitMoveCalled );
		assertEquals( s.generateMoveReturnValue.coord(), referee.submitMoveArg.coord() );
	}

}
