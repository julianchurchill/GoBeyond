package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _PassingMoveSourceTests {

	@Test
	public void alwaysPasses() {
		PassingMoveSource p = new PassingMoveSource();
		assertEquals( "Black passes are returned", Move.passMove(Move.Colour.Black), p.getMove( Move.Colour.Black ) );
		assertEquals( "White passes are returned", Move.passMove(Move.Colour.White), p.getMove( Move.Colour.White ) );
	}

}
