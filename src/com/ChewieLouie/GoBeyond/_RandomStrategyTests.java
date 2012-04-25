package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _RandomStrategyTests {

	_TestableRandomGenerator rand;
	RandomStrategy r;
	private _TestableRules rules;

	@Before
	public void SetUp() {
		rand = new _TestableRandomGenerator();
		rand.primeWith( 1 );
		rand.primeWith( 0 );
		rules = new _TestableRules();
		r = new RandomStrategy( rand, rules );
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
											 	   "wwww" +
												   "wwww" );
		Move m = r.generateMove( board, Move.Colour.White );
		assertEquals( "pass when only empty points are friendly eyes", Move.passMove(Move.Colour.White), m );
	}

	@Test
	public void returnsAPassIfNoLegalMoveAvailable() {
		rules.isLegalReturnValue = false;
		SimpleBoard board = SimpleBoard.makeBoard( "..." +
												   "..." +
												   "..." );
		Move m = r.generateMove( board, Move.Colour.Black );
		assertEquals( "pass when only illegal moves available", Move.passMove(Move.Colour.Black), m );
	}
}
