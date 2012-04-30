package com.ChewieLouie.GoBeyond;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.ChewieLouie.GoBeyond.Referee.MoveStatus;

public class _GameTests {

	private Player player1;
	private Player player2;
	private Referee referee;
	private Game game;

	@Before
	public void SetUp() {
		player1 = mock( Player.class );
		player2 = mock( Player.class );
		referee = mock(Referee.class);
		game = new Game( player1, player2, referee );
	}

	@Test
	public void gameAsksPlayersToGenerateMovesUntilGameEnds() {
		when(referee.endDetected()).thenReturn(false, false, false, false, true);
		game.start();
		
		verify(player1, times(2)).playMove();
		verify(player2, times(2)).playMove();
	}

	@Test
	public void gameOfOneMoveOnlyAsksPlayer1ToGenerateAMove() {
		when(referee.endDetected()).thenReturn(false, true);
		game.start();

		verify(player1).playMove();
		verify(player2, never()).playMove();
	}
	
	@Test
	public void gameAsksSamePlayerForMoveUntilTheyReturnAMoveAcceptedByTheReferee() {
		when(referee.endDetected()).thenReturn(false, true);
		when(player1.playMove()).thenReturn(MoveStatus.IllegalMove);

		game.start();
		
		verify(player1, times(2)).playMove();
	}

	@Test
	public void gameCallsGameEndDetectorOnceForEachMovePlusOnceForTheStartOfTheGame() {
		when(referee.endDetected()).thenReturn(false, false, false, false, true);
		game.start();

		verify(referee, times(5)).endDetected();
	}
	
	@Test
	public void gameEndObserversAreCalledWhenTheGameHasEnded() {
		GameEndObserver observer1 = mock(GameEndObserver.class);
		GameEndObserver observer2 = mock(GameEndObserver.class);
		game.addObserver( observer1 );
		game.addObserver( observer2 );

		verify(observer1, never()).gameEnded();
		verify(observer2, never()).gameEnded();

		game.start();
		verify(observer1).gameEnded();
		verify(observer2).gameEnded();
	}
}
