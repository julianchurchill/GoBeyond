package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GameTests {

	private _TestablePlayer player1;
	private _TestablePlayer player2;
	private _TestableReferee referee;
	private Game game;

	@Before
	public void SetUp() {
		player1 = new _TestablePlayer();
		player2 = new _TestablePlayer();
		referee = new _TestableReferee();
		game = new Game( player1, player2, referee );
	}

	@Test
	public void gameAsksPlayersToGenerateMovesUntilGameEnds() {
		referee.endAfterThisManyGameEndDetections = 11;
		game.start();
		
		assertEquals( 5, player1.generateMoveCalledCount );
		assertEquals( 5, player2.generateMoveCalledCount );
	}

	@Test
	public void gameOfOneMoveOnlyAsksPlayer1ToGenerateAMove() {
		referee.endAfterThisManyGameEndDetections = 2;
		game.start();

		assertEquals( 1, player1.generateMoveCalledCount );
		assertEquals( 0, player2.generateMoveCalledCount );
	}

	@Test
	public void gameCallsGameEndDetectorOnceForEachMovePlusOnceForTheStartOfTheGame() {
		referee.endAfterThisManyGameEndDetections = 11;
		game.start();

		assertEquals( 11, referee.endDetectedCalledCount );
	}
	
	@Test
	public void gameEndObserversAreCalledWhenTheGameHasEnded() {
		_TestableGameEndObserver observer1 = new _TestableGameEndObserver();
		_TestableGameEndObserver observer2 = new _TestableGameEndObserver();
		game.addObserver( observer1 );
		game.addObserver( observer2 );

		assertFalse( "observer is not called before game ends", observer1.gameEndedCalled );
		assertFalse( "observer is not called before game ends", observer2.gameEndedCalled );

		game.start();
		assertTrue( "observer is called when game ends", observer1.gameEndedCalled );
		assertTrue( "observer is called when game ends", observer2.gameEndedCalled );
	}
}
