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
		rand.primeWith( 2 );
		rand.primeWith( 1 );
		r = new RandomStrategy( rand );
	}

	@Test
	public void generateMoveProducesARandomMove() {
		assertTrue( "random move generated is not null", r.generateMove( new SimpleBoard( 5 ) ) != null );
	}

	@Test
	public void generatedMoveUsesRandomGenerator() {
		Coord c = r.generateMove( new SimpleBoard( 3 ) );

		assertTrue( "random generator is used", rand.generateCalled );
		assertEquals( "minimum bounds of random number is zero", 0, rand.generateMin );
		assertEquals( "maximum bounds of random number is one less than board size", 2, rand.generateMax );
		assertEquals( "x coord of move equals random number generated", 2, c.x() );
		assertEquals( "y coord of move equals random number generated", 1, c.y() );
	}
}
