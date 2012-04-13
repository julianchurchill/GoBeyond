package com.ChewieLouie.GoBeyond;

public class Game {

	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private Referee referee;

	public Game(Player player1, Player player2, Referee referee ) {
		this.player1 = player1;
		this.player2 = player2;
		this.referee = referee;
	}

	public void start() {
		currentPlayer = player1;
		while( referee.endDetected() == false ) {
			currentPlayer.playMove();
			swapCurrentPlayer();
		}
	}

	private void swapCurrentPlayer() {
		if( currentPlayer == player1 )
			currentPlayer = player2;
		else
			currentPlayer = player1;
	}
}
