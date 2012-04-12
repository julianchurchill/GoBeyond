package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _MoveTests {

	@Test
	public void MoveIsValueObject() {
		Move move1 = new Move( 2, 3, Move.Colour.Black );
		Move move2 = new Move( 2, 3, Move.Colour.Black );
		Move move3 = new Move( 5, 4, Move.Colour.Black );

		assertTrue( move1.equals( move2 ) );
		assertFalse( move1.equals( move3 ) );
		assertTrue( move1.hashCode() == move2.hashCode() );
	}

	@Test
	public void PassMove() {
		assertEquals( Move.Colour.Black, Move.passMove( Move.Colour.Black ).colour() ); 
		assertEquals( -1, Move.passMove( Move.Colour.Black ).x() ); 
		assertEquals( -1, Move.passMove( Move.Colour.Black ).y() ); 
	}
}
