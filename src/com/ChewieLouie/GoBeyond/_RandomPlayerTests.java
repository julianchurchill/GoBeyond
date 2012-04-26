package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _RandomPlayerTests {

	_TestableReferee referee;
	_TestableRandomGenerator rand;
	_TestableRules rules;
	RandomPlayer player;

	@Before
	public void SetUp() {
		rand = new _TestableRandomGenerator();
		rand.primeWith( 1 );
		rand.primeWith( 0 );
		rules = new _TestableRules();
		referee = new _TestableReferee();
		referee.boardReturn = new SimpleBoard(9);
		player = new RandomPlayer(referee, Move.Colour.White, rand, rules);
	}

	@Test
	public void generateMoveSubmitsTheMoveToTheReferee() {
		player.playMove();
		
		assertTrue( referee.submitMoveCalled );
	}

	@Test
	public void generateMoveProducesARandomMove() {
		player.playMove();

		assertTrue( "random move generated is not null", referee.submitMoveArg != null );
	}

	@Test
	public void generatedMoveUsesRandomGenerator() {
		player.playMove();

		assertTrue( "random generator is used", rand.generateCalled );
		assertEquals( "minimum bounds of random number is zero", 0, rand.generateMin );
	}
	
	@Test
	public void doesNotFillInCompletelySurroundedEmptyPoints() {
		referee.boardReturn = SimpleBoard.makeBoard( "wwww" +
												   ".w.w" +
											 	   "wwww" +
												   "wwww" );
		player.playMove();

		assertEquals( "pass when only empty points are friendly eyes", Move.passMove(Move.Colour.White), referee.submitMoveArg );
	}

	@Test
	public void returnsAPassIfNoLegalMoveAvailable() {
		rules.isLegalReturnValue = false;
		referee.boardReturn = SimpleBoard.makeBoard( "..." +
												   "..." +
												   "..." );
		player = new RandomPlayer(referee, Move.Colour.Black, rand, rules);
		player.playMove();

		assertEquals( "pass when only illegal moves available", Move.passMove(Move.Colour.Black), referee.submitMoveArg );
	}
}
