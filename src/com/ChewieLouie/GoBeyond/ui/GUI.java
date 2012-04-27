package com.ChewieLouie.GoBeyond.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.ChewieLouie.GoBeyond.BoardObserver;
import com.ChewieLouie.GoBeyond.Game;
import com.ChewieLouie.GoBeyond.GameBrowser;
import com.ChewieLouie.GoBeyond.GameBrowserObserver;
import com.ChewieLouie.GoBeyond.GoRules;
import com.ChewieLouie.GoBeyond.GoStringLifeAnalyzer;
import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.Player;
import com.ChewieLouie.GoBeyond.PseudoRandomGenerator;
import com.ChewieLouie.GoBeyond.RandomPlayer;
import com.ChewieLouie.GoBeyond.Rules;
import com.ChewieLouie.GoBeyond.SimpleBoard;
import com.ChewieLouie.GoBeyond.StrictReferee;

public class GUI extends JFrame implements BoardObserver, ActionListener, GameBrowserObserver {

	private static final long serialVersionUID = 1L;

	private SimpleBoard board;
	private Game game;
	private StrictReferee referee;

	private JTextArea boardAsTextBox;

	private GameBrowser gameBrowser;

	public static void main(String [] args) {
		new GUI();
	}
	
	public GUI() {
		game = setupGame();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		setupFrameForPlay();
        		showMainFrame();
            }
        });
	}

	private Game setupGame() {
		Rules rules = new GoRules( new GoStringLifeAnalyzer() );
		board = new SimpleBoard( 9 );
		board.addObserver( this );
		referee = new StrictReferee( rules, board );
		Player player1 = new RandomPlayer( referee, Move.Colour.Black, new PseudoRandomGenerator( 0 ) );
		Player player2 = new RandomPlayer( referee, Move.Colour.White, new PseudoRandomGenerator( 1 ) );
		return new Game(player1, player2, referee);
	}

	private void setupFrameForPlay() {
		setTitle( "GoBeyond" );

		addButton("Play", "play", BorderLayout.NORTH);
	    addBoard();
	}

	private void addButton(String buttonText, String actionCommand, String borderLayoutDirection) {
		JButton button = new JButton(buttonText);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
	    getContentPane().add(button, borderLayoutDirection);
	}

	private void addBoard() {
		boardAsTextBox = new JTextArea();
	    boardAsTextBox.setFont(new Font("Courier", Font.PLAIN, 12));
	    getContentPane().add(boardAsTextBox, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if( event.getActionCommand() == "play" ) {
			game.start();
			setupFrameForBrowsing();
		}
		else if( event.getActionCommand() == "next" )
			gameBrowser.next();
		else if( event.getActionCommand() == "previous" )
			gameBrowser.previous();
	}

	private void setupFrameForBrowsing() {
		addButton("Next", "next", BorderLayout.EAST);
		addButton("Previous", "previous", BorderLayout.WEST);
		gameBrowser = new GameBrowser( referee.gameHistory() );
		gameBrowser.addObserver(this);
		gameBrowser.goToLastPosition();
	}

	private void showMainFrame() {
		setSize(400, 400);
		setVisible(true);
	}

	@Override
	public void boardChanged() {
		boardAsTextBox.setText( board.toString() );
	}

	@Override
	public void browserPositionChanged() {
		boardAsTextBox.setText( gameBrowser.currentBoard().toString() );
	}

}
