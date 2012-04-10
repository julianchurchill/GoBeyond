package com.ChewieLouie.GoBeyond;

public class Game {

	private Player player1;
	private Player player2;
	private Player currentPlayer;
	private GameEndDetector gameEndDetector;

	public Game(Player player1, Player player2, GameEndDetector gameEndDetector) {
		this.player1 = player1;
		this.player2 = player2;
		this.gameEndDetector = gameEndDetector;
	}

	public void start() {
		currentPlayer = player1;
		while( gameEndDetector.endDetected() == false ) {
			gameEndDetector.addMove();
			currentPlayer.generateMove();
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
