package com.ChewieLouie.GoBeyond;

public class GoPlayer implements Player {

	private Referee referee;
	private Strategy strategy;
	
	public GoPlayer(Referee referee, Strategy strategy) {
		this.referee = referee;
		this.strategy = strategy;
	}

	@Override
	public void playMove() {
		referee.submitMove( strategy.generateMove(null, Move.Colour.Black ) );
	}

}
