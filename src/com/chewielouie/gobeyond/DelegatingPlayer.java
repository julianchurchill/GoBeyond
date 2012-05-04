package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;
import com.ChewieLouie.GoBeyond.Referee.MoveStatus;

public class DelegatingPlayer implements Player {

	private Referee referee;
	private MoveSource moveSource;
	private Colour colour;

	public DelegatingPlayer(Referee referee, Colour colour, MoveSource moveSource) {
		this.referee = referee;
		this.colour = colour;
		this.moveSource = moveSource;
	}

	@Override
	public MoveStatus playMove() {
		return referee.submitMove(moveSource.getMove(colour, referee.board()));
	}

}
