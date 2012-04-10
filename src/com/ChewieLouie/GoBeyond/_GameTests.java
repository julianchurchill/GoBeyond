package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GameTests {

	private Player player1;
	private Player player2;
	private GameEndDetector gameEndDetector;
	private Game game;

	@Before
	public void SetUp() {
		player1 = new Player();
		player2 = new Player();
		gameEndDetector = new GameEndDetector();
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

//	public void gameCallsGameEndDetectorToDetermineWhenToStopTheGame() {
}
