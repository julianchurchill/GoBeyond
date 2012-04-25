package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _RandomStrategyTests {

	_TestableRandomGenerator rand;
	RandomStrategy r;

	@Before
	public void SetUp() {
		rand = new _TestableRandomGenerator();
		rand.primeWith( 1 );
		rand.primeWith( 0 );
		r = new RandomStrategy( rand );
	}

	@Test
	public void generateMoveProducesARandomMove() {
		assertTrue( "random move generated is not null", r.generateMove( new SimpleBoard( 5 ), null ) != null );
	}

	@Test
	public void generatedMoveUsesRandomGenerator() {
		r.generateMove( new SimpleBoard( 3 ), null );

		assertTrue( "random generator is used", rand.generateCalled );
		assertEquals( "minimum bounds of random number is zero", 0, rand.generateMin );
	}
	
	@Test
	public void doesNotFillInCompletelySurroundedEmptyPoints() {
		SimpleBoard board = SimpleBoard.makeBoard( "wwww" +
												   ".w.w" +
											 	   "wwww" );
		Move m = r.generateMove( board, Move.Colour.White );
		assertEquals( "pass when only empty points are friendly eyes", m, Move.passMove(Move.Colour.White) );
	}
}
