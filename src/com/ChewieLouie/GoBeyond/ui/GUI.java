package com.ChewieLouie.GoBeyond.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.ChewieLouie.GoBeyond.BoardObserver;
import com.ChewieLouie.GoBeyond.Game;
import com.ChewieLouie.GoBeyond.GoRules;
import com.ChewieLouie.GoBeyond.GoStringLifeAnalyzer;
import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.Player;
import com.ChewieLouie.GoBeyond.PseudoRandomGenerator;
import com.ChewieLouie.GoBeyond.RandomPlayer;
import com.ChewieLouie.GoBeyond.Rules;
import com.ChewieLouie.GoBeyond.SimpleBoard;
import com.ChewieLouie.GoBeyond.StrictReferee;

public class GUI extends JFrame implements BoardObserver {

	private static final long serialVersionUID = 1L;

	private SimpleBoard board;
	private Game game;

	private JLabel label;

	public static void main(String [] args) {
		new GUI();
	}
	
	public GUI() {
		game = setupGame();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		setupMainFrame();
        		showMainFrame();
        		playGame();
            }
        });
	}

	private Game setupGame() {
		Rules rules = new GoRules( new GoStringLifeAnalyzer() );
		board = new SimpleBoard( 9 );
		board.addObserver( this );
		StrictReferee referee = new StrictReferee( rules, board );
		Player player1 = new RandomPlayer( referee, Move.Colour.Black, new PseudoRandomGenerator( 0 ) );
		Player player2 = new RandomPlayer( referee, Move.Colour.White, new PseudoRandomGenerator( 1 ) );
		return new Game(player1, player2, referee);
	}

	private void setupMainFrame() {
		setTitle( "GoBeyond" );
		label = new JLabel();
	    getContentPane().add(label);
	}

	private void showMainFrame() {
		setSize(400, 400);
		setVisible(true);
	}

	private void playGame() {
		game.start();
		setTitle( "GoBeyond: " + board.toString() );
	}

	@Override
	public void boardChanged() {
		label.setText( board.toString() );
	}

}
