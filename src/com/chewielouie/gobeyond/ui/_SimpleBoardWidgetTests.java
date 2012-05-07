package com.chewielouie.gobeyond.ui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.BorderLayout;
import java.awt.Container;

import org.junit.Before;
import org.junit.Test;

import com.chewielouie.gobeyond.Board;
import com.chewielouie.gobeyond.GameBrowser;
import com.chewielouie.gobeyond.Move;
import com.chewielouie.gobeyond.SimpleBoard;
import com.chewielouie.gobeyond.StrictReferee;
import com.chewielouie.gobeyond.util.Coord;

public class _SimpleBoardWidgetTests {

	private SimpleBoard board;
	private Container container;
	private StrictReferee referee;
	private SimpleBoardCanvas canvas;
	private SimpleBoardWidget widget;

	@Before
	public void SetUp() {
		board = mock( SimpleBoard.class );
		container = mock( Container.class );
		referee = mock( StrictReferee.class );
		canvas = mock( SimpleBoardCanvas.class );

		widget = new SimpleBoardWidget(board, container, referee, canvas);
	}

	@Test
	public void widgetAddsSelfToContainer() {
		verify(container).add(canvas, BorderLayout.CENTER);
	}
	
	@Test
	public void widgetObservesBoard() {
		verify(board).addObserver(widget);
	}

	@Test
	public void widgetObservesReferee() {
		verify(referee).addObserver(widget);
	}

	@Test
	public void widgetObservesGameBrowser() {
		GameBrowser gameBrowser = mock( GameBrowser.class );
		widget.addGameBrowser(gameBrowser);
		
		verify(gameBrowser).addObserver(widget);
	}

	@Test
	public void boardChangedCausesCanvasRedraw() {
		widget.boardChanged();

		verify(canvas).redrawWithBoard(board);
	}

	@Test
	public void browserPositionChangedCausesCanvasRedraw() {
		GameBrowser gameBrowser = mock( GameBrowser.class );
		Board browserBoard = mock( Board.class );
		when(gameBrowser.currentBoard()).thenReturn(browserBoard );
		widget.addGameBrowser(gameBrowser);

		widget.browserPositionChanged();

		verify(canvas).redrawWithBoard(browserBoard);
	}
	
	@Test
	public void removeLastClickedBoardPointCallsCanvas() {
		when(canvas.getLastClickedBoardPoint()).thenReturn( new Coord( 2, 4 ) );
		
		assertEquals( new Coord( 2, 4 ), widget.removeLastClickedBoardPoint() );
		verify(canvas).getLastClickedBoardPoint();
	}
	
	@Test
	public void clickAvailableCallsCanvas() {
		when(canvas.clickAvailable()).thenReturn( true );
		
		assertTrue( widget.clickAvailable() );
		verify(canvas).clickAvailable();

		when(canvas.clickAvailable()).thenReturn( false );
		assertFalse( widget.clickAvailable() );
	}
	
	@Test
	public void allowBoardClicksCallsCanvas() {
		widget.allowBoardClicks();

		verify(canvas).allowBoardClicks();
	}
	
	@Test
	public void moveAcceptedCallsCanvas() {
		widget.moveAccepted( new Move( new Coord( 5, 4 ), Move.Colour.Black ), board );

		verify(canvas).lastMove( new Coord( 5, 4 ) );
	}
}
