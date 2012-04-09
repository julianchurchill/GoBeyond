package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _MoveTests {

	@Test
	public void MoveIsValueObject() {
		Move move1 = new Move( 2, 3 );
		Move move2 = new Move( 2, 3 );
		Move move3 = new Move( 5, 4 );

		assertTrue( move1.equals( move2 ) );
		assertFalse( move1.equals( move3 ) );
		assertTrue( move1.hashCode() == move2.hashCode() );
	}

}
