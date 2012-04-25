package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public class GoPlayer implements Player {

	private Referee referee;
	private Strategy strategy;
	private Colour colour;
	private Board board;
	
	public GoPlayer(Referee referee, Strategy strategy, Colour colour, Board board) {
		this.referee = referee;
		this.strategy = strategy;
		this.colour = colour;
		this.board = board;
	}

	@Override
	public void playMove() {
		referee.submitMove( strategy.generateMove(board, colour ) );
	}

}
