package com.ChewieLouie.GoBeyond;

import java.util.HashSet;
import java.util.Set;

import com.ChewieLouie.GoBeyond.Referee.MoveStatus;

public class Game {

	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Referee referee;
	private Set<GameEndObserver> observers = new HashSet<GameEndObserver>();

	public Game(Player player1, Player player2, Referee referee ) {
		this.player1 = player1;
		this.player2 = player2;
		this.referee = referee;
	}

	public void start() {
		currentPlayer = player1;
		while( referee.endDetected() == false ) {
			playMoveByCurrentPlayer();
			swapCurrentPlayer();
		}
		for( GameEndObserver o : observers )
			o.gameEnded();
	}

	private void playMoveByCurrentPlayer() {
		MoveStatus s;
		do {
			s = currentPlayer.playMove();
		} while( s != MoveStatus.LegalMove );
	}

	private void swapCurrentPlayer() {
		if( currentPlayer == player1 )
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}

	public void addObserver(GameEndObserver o) {
		observers .add( o );
	}
}
