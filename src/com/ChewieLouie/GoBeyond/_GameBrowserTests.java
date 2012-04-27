package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		_TestableGameBrowserObserver observer = new _TestableGameBrowserObserver();
		g.addObserver( observer );
		
		assertFalse( "observer is not notified before anything changes", observer.positionChangedCalled );
	}

	@Test
	public void observersAreNotifiedWhenCurrentPositionAdvances() {
		_TestableGameBrowserObserver observer = new _TestableGameBrowserObserver();
		g.addObserver( observer );
		
		g.next();
		assertTrue( "observer is notified when browser advances to next position", observer.positionChangedCalled );
	}

	@Test
	public void observersAreNotNotifiedWhenBrowserAttemptsToAdvanceBeyondLastPosition() {
		_TestableGameBrowserObserver observer = new _TestableGameBrowserObserver();
		g.addObserver( observer );		
		g.next();
		observer.positionChangedCalled = false;

		g.next();
		assertFalse( "observer is not notified when browser attempts to advance beyond last position", observer.positionChangedCalled );
	}
	
	@Test
	public void observersAreNotifiedWhenCurrentPositionGoesBack() {
		_TestableGameBrowserObserver observer = new _TestableGameBrowserObserver();
		g.addObserver( observer );
		g.next();
		observer.positionChangedCalled = false;

		g.previous();
		assertTrue( "observer is notified when browser goes back to previous position", observer.positionChangedCalled );
	}
	
	@Test
	public void observersAreNotNotifiedWhenCurrentPositionAttemptsToGoBackBeforeFirstPosition() {
		_TestableGameBrowserObserver observer = new _TestableGameBrowserObserver();
		g.addObserver( observer );

		g.previous();
		assertFalse( "observer is not notified when browser attempts to go back before first position", observer.positionChangedCalled );
	}
	
	@Test
	public void observersAreNotifiedWhenCurrentPositionChangesToLastPosition() {
		_TestableGameBrowserObserver observer = new _TestableGameBrowserObserver();
		g.addObserver( observer );

		g.goToLastPosition();
		assertTrue( "observer is notified when browser goes to last position", observer.positionChangedCalled );
	}
	
	@Test
	public void observersAreNotNotifiedOnGoToLastPositionIfCurrentPositionIsAlreadyTheLastPosition() {
		_TestableGameBrowserObserver observer = new _TestableGameBrowserObserver();
		g.addObserver( observer );
		g.goToLastPosition();
		observer.positionChangedCalled = false;

		g.goToLastPosition();
		assertFalse( "observer is not notified if browser attempts to go to last position but is already there", observer.positionChangedCalled );
	}
		
	@Test
	public void multipleObserversAreNotifiedWhenCurrentPositionChanges() {
		_TestableGameBrowserObserver observer1 = new _TestableGameBrowserObserver();
		_TestableGameBrowserObserver observer2 = new _TestableGameBrowserObserver();
		g.addObserver( observer1 );
		g.addObserver( observer2 );

		g.next();
		assertTrue( "first observer is notified when position changes", observer1.positionChangedCalled );
		assertTrue( "second observer is notified when position changes", observer2.positionChangedCalled );
	}
}
