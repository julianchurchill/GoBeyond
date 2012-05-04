package com.chewielouie.gobeyond;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.chewielouie.gobeyond.util.Coord;

public class _GameBrowserTests {

	GameBrowser g;
	Board board1;
	Board board2;
	Move move1;
	Move move2;
	private GameHistory history;

	@Before
	public void SetUp() {
		board1 = new SimpleBoard(9);
		board2 = new SimpleBoard(9);
		move1 = new Move( new Coord( 1, 2 ), Move.Colour.Black );
		move2 = new Move( new Coord( 0, 3 ), Move.Colour.White );
		history = new GameHistory();
		history.add( board1, move1 );
		history.add( board2, move2 );

		g = new GameBrowser( history );
	}

	@Test
	public void gameBrowserCanReturnCurrentBoardAndMove() {
		assertTrue( g.currentBoard() == board1 );
		assertTrue( g.currentMove() == move1 );
	}

	@Test
	public void gameBrowserCanAdvanceToNextBoardAndMove() {
		g.next();
		assertTrue( g.currentBoard() == board2 );
		assertTrue( g.currentMove() == move2 );		
	}

	@Test
	public void gameBrowserCanGoBackToPreviousBoardAndMove() {
		g.next();
		g.previous();
		assertTrue( g.currentBoard() == board1 );
		assertTrue( g.currentMove() == move1 );
	}

	@Test
	public void gameBrowserCanGoToLastBoardAndMove() {
		Board board3 = new SimpleBoard(9);
		Move move3 = new Move( new Coord( 0, 3 ), Move.Colour.White );
		history.add( board3, move3 );

		g.goToLastPosition();
		assertTrue( g.currentBoard() == board3 );
		assertTrue( g.currentMove() == move3 );		
	}

	@Test
	public void gameBrowserCannotAdvanceBeyondLastMove() {
		g.next();
		g.next();
		assertTrue( g.currentBoard() == board2 );
		assertTrue( g.currentMove() == move2 );		
	}

	@Test
	public void gameBrowserCannotGoBackBeforeFirstMove() {
		g.previous();
		assertTrue( g.currentBoard() == board1 );
		assertTrue( g.currentMove() == move1 );
	}

	@Test
	public void observersAreNotNotifiedUponBeingAdded() {
		GameBrowserObserver observer = mock( GameBrowserObserver.class );
		g.addObserver( observer );
		
		verify(observer, never()).browserPositionChanged();
	}

	@Test
	public void observersAreNotifiedWhenCurrentPositionAdvances() {
		GameBrowserObserver observer = mock( GameBrowserObserver.class );
		g.addObserver( observer );
		
		g.next();
		verify(observer).browserPositionChanged();
	}

	@Test
	public void observersAreNotNotifiedWhenBrowserAttemptsToAdvanceBeyondLastPosition() {
		GameBrowserObserver observer = mock( GameBrowserObserver.class );
		g.addObserver( observer );		
		g.next();

		g.next();
		verify(observer, times(1)).browserPositionChanged();
	}
	
	@Test
	public void observersAreNotifiedWhenCurrentPositionGoesBack() {
		GameBrowserObserver observer = mock( GameBrowserObserver.class );
		g.addObserver( observer );
		g.next();

		g.previous();
		verify(observer, times(2)).browserPositionChanged();
	}
	
	@Test
	public void observersAreNotNotifiedWhenCurrentPositionAttemptsToGoBackBeforeFirstPosition() {
		GameBrowserObserver observer = mock( GameBrowserObserver.class );
		g.addObserver( observer );

		g.previous();
		verify(observer, never()).browserPositionChanged();
	}
	
	@Test
	public void observersAreNotifiedWhenCurrentPositionChangesToLastPosition() {
		GameBrowserObserver observer = mock( GameBrowserObserver.class );
		g.addObserver( observer );

		g.goToLastPosition();
		verify(observer).browserPositionChanged();
	}
	
	@Test
	public void observersAreNotNotifiedOnGoToLastPositionIfCurrentPositionIsAlreadyTheLastPosition() {
		GameBrowserObserver observer = mock( GameBrowserObserver.class );
		g.addObserver( observer );
		g.goToLastPosition();

		g.goToLastPosition();
		verify(observer, times(1)).browserPositionChanged();
	}
		
	@Test
	public void multipleObserversAreNotifiedWhenCurrentPositionChanges() {
		GameBrowserObserver observer1 = mock( GameBrowserObserver.class );
		GameBrowserObserver observer2 = mock( GameBrowserObserver.class );
		g.addObserver( observer1 );
		g.addObserver( observer2 );

		g.next();
		verify(observer1).browserPositionChanged();
		verify(observer2).browserPositionChanged();
	}
}
