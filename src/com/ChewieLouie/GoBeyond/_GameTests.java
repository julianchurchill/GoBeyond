package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GameTests {

	private _TestablePlayer player1;
	private _TestablePlayer player2;
	private _TestableGameEndDetector gameEndDetector;
	private Game game;

	@Before
	public void SetUp() {
		player1 = new _TestablePlayer();
		player2 = new _TestablePlayer();
		gameEndDetector = new _TestableGameEndDetector();
		player1.notifyOfMovesPlayed( gameEndDetector );
		player2.notifyOfMovesPlayed( gameEndDetector );
		game = new Game( player1, player2, gameEndDetector );
	}

	@Test
	public void gameAsksPlayersToGenerateMovesUntilGameEnds() {
		gameEndDetector.endAfterThisManyMoves = 10;
		game.start();
		
		assertEquals( 5, player1.generateMoveCalledCount );
		assertEquals( 5, player2.generateMoveCalledCount );
	}

	@Test
	public void gameOfOneMoveOnlyAsksPlayer1ToGenerateAMove() {
		gameEndDetector.endAfterThisManyMoves = 1;
		game.start();

		assertEquals( 1, player1.generateMoveCalledCount );
		assertEquals( 0, player2.generateMoveCalledCount );
	}

	@Test
	public void gameCallsGameEndDetectorOnceForEachMovePlusOnceForTheStartOfTheGame() {
		gameEndDetector.endAfterThisManyMoves = 10;
		game.start();

		assertEquals( 11, gameEndDetector.endDetectedCalledCount );
	}
}
