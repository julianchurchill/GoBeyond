package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _MoveTests {

	@Test
	public void MoveIsValueObject() {
		Move move1 = new Move( new Coord( 2, 3 ), Move.Colour.Black );
		Move move2 = new Move( new Coord( 2, 3 ), Move.Colour.Black );
		Move move3 = new Move( new Coord( 5, 4 ), Move.Colour.Black );

		assertTrue( move1.equals( move2 ) );
		assertFalse( move1.equals( move3 ) );
		assertTrue( move1.hashCode() == move2.hashCode() );
	}

	@Test
	public void PassMove() {
		assertEquals( Move.Colour.Black, Move.passMove( Move.Colour.Black ).colour() ); 
		assertEquals( new Coord( -1, -1 ), Move.passMove( Move.Colour.Black ).coord() ); 
	}
}
