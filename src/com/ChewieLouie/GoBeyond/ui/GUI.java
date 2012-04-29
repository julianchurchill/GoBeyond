package com.ChewieLouie.GoBeyond.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ChewieLouie.GoBeyond.DelegatingPlayer;
import com.ChewieLouie.GoBeyond.Game;
import com.ChewieLouie.GoBeyond.GameBrowser;
import com.ChewieLouie.GoBeyond.GoRules;
import com.ChewieLouie.GoBeyond.GoStringLifeAnalyzer;
import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.Player;
import com.ChewieLouie.GoBeyond.PseudoRandomGenerator;
import com.ChewieLouie.GoBeyond.RandomMoveSource;
import com.ChewieLouie.GoBeyond.Rules;
import com.ChewieLouie.GoBeyond.SimpleBoard;
import com.ChewieLouie.GoBeyond.StrictReferee;

public class GUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private SimpleBoard board;
	private Game game;
	private StrictReferee referee;
	private GameBrowser gameBrowser;
	private BoardWidget boardWidget;

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
		referee = new StrictReferee( rules, board );
		Player player1 = new DelegatingPlayer( referee, Move.Colour.Black, new RandomMoveSource( new PseudoRandomGenerator( 0 ), referee ) );
		Player player2 = new DelegatingPlayer( referee, Move.Colour.White, new RandomMoveSource( new PseudoRandomGenerator( 1 ), referee ) );
//		Player player2 = new DelegatingPlayer( referee, Move.Colour.White, new PassingMoveSource() );
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
//		boardWidget = new TextBasedBoardWidget( board, getContentPane() );
		boardWidget = new SimpleBoardWidget( board, getContentPane() );
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
		boardWidget.addGameBrowser(gameBrowser);
		gameBrowser.goToLastPosition();
	}

	private void showMainFrame() {
		setSize(400, 400);
		setVisible(true);
	}
}
