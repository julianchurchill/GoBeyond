package com.chewielouie.gobeyond.ui;

import com.chewielouie.gobeyond.Board;
import com.chewielouie.gobeyond.DelegatingPlayer;
import com.chewielouie.gobeyond.Game;
import com.chewielouie.gobeyond.GoRules;
import com.chewielouie.gobeyond.GoStringLifeAnalyzer;
import com.chewielouie.gobeyond.Move;
import com.chewielouie.gobeyond.Player;
import com.chewielouie.gobeyond.PseudoRandomGenerator;
import com.chewielouie.gobeyond.RandomMoveSource;
import com.chewielouie.gobeyond.RefereeMoveObserver;
import com.chewielouie.gobeyond.Rules;
import com.chewielouie.gobeyond.SimpleBoard;
import com.chewielouie.gobeyond.StrictReferee;

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
