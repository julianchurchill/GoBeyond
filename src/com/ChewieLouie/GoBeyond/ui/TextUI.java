package com.ChewieLouie.GoBeyond.ui;

import com.ChewieLouie.GoBeyond.Board;
import com.ChewieLouie.GoBeyond.DelegatingPlayer;
import com.ChewieLouie.GoBeyond.Game;
import com.ChewieLouie.GoBeyond.GoRules;
import com.ChewieLouie.GoBeyond.GoStringLifeAnalyzer;
import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.Player;
import com.ChewieLouie.GoBeyond.PseudoRandomGenerator;
import com.ChewieLouie.GoBeyond.RandomMoveSource;
import com.ChewieLouie.GoBeyond.RefereeMoveObserver;
import com.ChewieLouie.GoBeyond.Rules;
import com.ChewieLouie.GoBeyond.SimpleBoard;
import com.ChewieLouie.GoBeyond.StrictReferee;

public class TextUI implements RefereeMoveObserver {

	public static void main(String [] args) {
		new TextUI();
	}

	public TextUI() {
		Rules rules = new GoRules( new GoStringLifeAnalyzer() );
		SimpleBoard board = new SimpleBoard( 9 );
		StrictReferee referee = new StrictReferee( rules, board );
		referee.addObserver( this );
		Player player1 = new DelegatingPlayer( referee, Move.Colour.Black, new RandomMoveSource( new PseudoRandomGenerator( 0 ), referee ) );
		Player player2 = new DelegatingPlayer( referee, Move.Colour.White, new RandomMoveSource( new PseudoRandomGenerator( 1 ), referee ) );
		Game g = new Game(player1, player2, referee);
		g.start();
		
		System.out.println( "" );
		System.out.println( "Game over!" );
		System.out.println( "" );
		System.out.println( "Final board:" );
		System.out.println( board.toString() );
	}

	@Override
	public void moveAccepted(Move m, Board b) {
		System.out.println( "" );
		System.out.println( m.toString() );
		System.out.println( b.toString() );
		System.out.println( "" );
	}
}
