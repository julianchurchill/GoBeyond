package com.ChewieLouie.GoBeyond.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import com.ChewieLouie.GoBeyond.BoardObserver;
import com.ChewieLouie.GoBeyond.GameBrowser;
import com.ChewieLouie.GoBeyond.GameBrowserObserver;
import com.ChewieLouie.GoBeyond.SimpleBoard;

public class SimpleBoardWidget implements BoardWidget, GameBrowserObserver, BoardObserver {

	private SimpleBoard board;
	private GameBrowser gameBrowser;
	private SimpleBoardCanvas canvas;

	public SimpleBoardWidget(SimpleBoard board, Container container) {
		this.board = board;
		this.board.addObserver(this);
		this.canvas = new SimpleBoardCanvas();
	    container.add(canvas, BorderLayout.CENTER);
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
}
