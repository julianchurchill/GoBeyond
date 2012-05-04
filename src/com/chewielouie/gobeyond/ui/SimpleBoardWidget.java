package com.chewielouie.gobeyond.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import com.chewielouie.gobeyond.Board;
import com.chewielouie.gobeyond.BoardObserver;
import com.chewielouie.gobeyond.GameBrowser;
import com.chewielouie.gobeyond.GameBrowserObserver;
import com.chewielouie.gobeyond.Move;
import com.chewielouie.gobeyond.RefereeMoveObserver;
import com.chewielouie.gobeyond.SimpleBoard;
import com.chewielouie.gobeyond.StrictReferee;
import com.chewielouie.gobeyond.util.Coord;

public class SimpleBoardWidget implements BoardWidget, GameBrowserObserver, BoardObserver, RefereeMoveObserver {

	private SimpleBoard board;
	private GameBrowser gameBrowser;
	private SimpleBoardCanvas canvas;

	public SimpleBoardWidget(SimpleBoard board, Container container, StrictReferee referee) {
		this.board = board;
		this.board.addObserver(this);
		this.canvas = new SimpleBoardCanvas();
	    container.add(canvas, BorderLayout.CENTER);
	    referee.addObserver( this );
	}

	@Override
	public void addGameBrowser(GameBrowser gameBrowser) {
		this.gameBrowser = gameBrowser;
		this.gameBrowser.addObserver(this);
	}


	@Override
	public void boardChanged() {
		canvas.redrawWithBoard( board );
	}

	@Override
	public void browserPositionChanged() {
		canvas.redrawWithBoard( gameBrowser.currentBoard() );
	}

	@Override
	public Coord removeLastClickedBoardPoint() {
		return canvas.getLastClickedBoardPoint();
	}

	@Override
	public boolean clickAvailable() {
		return canvas.clickAvailable();
	}

	@Override
	public void allowBoardClicks() {
		canvas.allowBoardClicks();
	}

	@Override
	public void moveAccepted(Move m, Board b) {
		canvas.lastMove( m.coord() );
	}
}
