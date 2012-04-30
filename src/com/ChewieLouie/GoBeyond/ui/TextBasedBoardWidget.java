package com.ChewieLouie.GoBeyond.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JTextArea;

import com.ChewieLouie.GoBeyond.BoardObserver;
import com.ChewieLouie.GoBeyond.GameBrowser;
import com.ChewieLouie.GoBeyond.GameBrowserObserver;
import com.ChewieLouie.GoBeyond.SimpleBoard;
import com.ChewieLouie.GoBeyond.util.Coord;

public class TextBasedBoardWidget implements BoardObserver, GameBrowserObserver, BoardWidget {

	private SimpleBoard board;
	private GameBrowser gameBrowser;
	private JTextArea textArea;

	public TextBasedBoardWidget(SimpleBoard board, Container container) {
		this.board = board;
		this.board.addObserver(this);
		this.textArea = new JTextArea();
	    textArea.setFont(new Font("Courier", Font.PLAIN, 12));
	    container.add(textArea, BorderLayout.CENTER);
	}

	@Override
	public void addGameBrowser( GameBrowser gameBrowser ) {
		this.gameBrowser = gameBrowser;
		this.gameBrowser.addObserver(this);
	}

	@Override
	public void boardChanged() {
		textArea.setText( board.toString() );
	}

	@Override
	public void browserPositionChanged() {
		textArea.setText( gameBrowser.currentBoard().toString() );
	}

	@Override
	public Coord removeLastClickedBoardPoint() {
		return null;
	}

	@Override
	public boolean clickAvailable() {
		return false;
	}
}
