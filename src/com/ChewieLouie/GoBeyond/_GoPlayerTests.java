package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoPlayerTests {

	_TestableReferee referee;
	_TestableStrategy strategy;
	GoPlayer player;
	private Board board;

	@Before
	public void SetUp() {
		referee = new _TestableReferee();
		strategy = new _TestableStrategy();
		board = new _TestableBoard(9);
		player = new GoPlayer(referee, strategy, Move.Colour.White, board );
	}

	@Test
	public void generateMoveSubmitsTheMoveToTheReferee() {
		strategy.generateMoveReturnValue = new Move( new Coord( 4, 5 ), Move.Colour.Black );

		player.playMove();
		
		assertTrue( referee.submitMoveCalled );
		assertEquals( strategy.generateMoveReturnValue.coord(), referee.submitMoveArg.coord() );
	}

	@Test
	public void generateMoveUsesTheStrategyToGenerateAMove() {		
		player.playMove();

		assertTrue( "calls Strategy.generateMove()", strategy.generateMoveCalled );
		assertTrue( "passes board to strategy", strategy.generateMoveBoard == board );
		assertTrue( "passes player colour to strategy", strategy.generateMoveColour == Move.Colour.White );
	}
}
