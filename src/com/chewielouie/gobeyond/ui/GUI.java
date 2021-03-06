package com.chewielouie.gobeyond.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.chewielouie.gobeyond.DelegatingPlayer;
import com.chewielouie.gobeyond.Game;
import com.chewielouie.gobeyond.GameBrowser;
import com.chewielouie.gobeyond.GameEndObserver;
import com.chewielouie.gobeyond.GoRules;
import com.chewielouie.gobeyond.GoStringLifeAnalyzer;
import com.chewielouie.gobeyond.Move;
import com.chewielouie.gobeyond.Player;
import com.chewielouie.gobeyond.PseudoRandomGenerator;
import com.chewielouie.gobeyond.RandomMoveSource;
import com.chewielouie.gobeyond.Rules;
import com.chewielouie.gobeyond.SimpleBoard;
import com.chewielouie.gobeyond.StrictReferee;

public class GUI extends JFrame implements ActionListener, GameEndObserver {

	private static final long serialVersionUID = 2978611770794322681L;

	private SimpleBoard board;
	private Game game;
	private StrictReferee referee;
	private GameBrowser gameBrowser;
	private BoardWidget boardWidget;
	private GUIMoveSource guiMoveSource;

	public static void main(String [] args) {
		new GUI();
	}
	
	public GUI() {
		setupReferee();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		setupFrameForPlay();
        		showMainFrame();
            }
        });
	}

	private void setupReferee() {
		Rules rules = new GoRules( new GoStringLifeAnalyzer() );
		board = new SimpleBoard( 5 );
		referee = new StrictReferee( rules, board );
	}

	private void setupFrameForPlay() {
		setTitle( "GoBeyond" );

		addButton("Play", "play", BorderLayout.NORTH);
		addButton("Pass", "pass", BorderLayout.SOUTH);
	    addBoard();
	}

	private void addButton(String buttonText, String actionCommand, String borderLayoutDirection) {
		JButton button = new JButton(buttonText);
		button.setActionCommand(actionCommand);
		button.addActionListener(this);
	    getContentPane().add(button, borderLayoutDirection);
	}

	private void addBoard() {
		boardWidget = new SimpleBoardWidget( board, getContentPane(), referee, new SimpleBoardCanvas() );
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if( event.getActionCommand() == "play" ) {
			setupGame();
			startGame();
		}
		else if( event.getActionCommand() == "pass" )
			guiMoveSource.enqueuePass();
		else if( event.getActionCommand() == "next" )
			gameBrowser.next();
		else if( event.getActionCommand() == "previous" )
			gameBrowser.previous();
	}

	private void startGame() {
		Thread t = new Thread(new Runnable() {
            public void run() {
            	boardWidget.allowBoardClicks();
        		game.start();
            }
        });
		t.start();
	}

	private void setupGame() {
		Player player1 = new DelegatingPlayer( referee, Move.Colour.Black, new RandomMoveSource( new PseudoRandomGenerator( 0 ), referee ) );
//		Player player1 = new DelegatingPlayer( referee, Move.Colour.White, new RubyMoveSource( "ruby/movesource.rb" ) );
//		Player player2 = new DelegatingPlayer( referee, Move.Colour.White, new RandomMoveSource( new PseudoRandomGenerator( 1 ), referee ) );
		guiMoveSource = new GUIMoveSource( boardWidget );
		Player player2 = new DelegatingPlayer( referee, Move.Colour.White, guiMoveSource );
		game = new Game(player1, player2, referee);
		game.addObserver(this);
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

	@Override
	public void gameEnded() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		setupFrameForBrowsing();
            }
        });
	}
}
