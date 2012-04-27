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
import com.ChewieLouie.GoBeyond.GoRules;
import com.ChewieLouie.GoBeyond.GoStringLifeAnalyzer;
import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.Player;
import com.ChewieLouie.GoBeyond.PseudoRandomGenerator;
import com.ChewieLouie.GoBeyond.RandomPlayer;
import com.ChewieLouie.GoBeyond.Rules;
import com.ChewieLouie.GoBeyond.SimpleBoard;
import com.ChewieLouie.GoBeyond.StrictReferee;

public class GUI extends JFrame implements BoardObserver, ActionListener {

	private static final long serialVersionUID = 1L;

	private SimpleBoard board;
	private Game game;

	private JTextArea boardAsTextBox;

	public static void main(String [] args) {
		new GUI();
	}
	
	public GUI() {
		game = setupGame();

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		setupMainFrame();
        		showMainFrame();
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

	    addPlayButton();
	    addBoard();
	}

	private void addBoard() {
		boardAsTextBox = new JTextArea();
	    boardAsTextBox.setFont(new Font("Courier", Font.PLAIN, 12));
	    getContentPane().add(boardAsTextBox, BorderLayout.CENTER);
	}

	private void addPlayButton() {
		JButton playButton = new JButton("Play");
		playButton.setActionCommand("play");
		playButton.addActionListener(this);
	    getContentPane().add(playButton, BorderLayout.NORTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.start();
	}

	private void showMainFrame() {
		setSize(400, 400);
		setVisible(true);
	}

	@Override
	public void boardChanged() {
		boardAsTextBox.setText( board.toString() );
	}

}
