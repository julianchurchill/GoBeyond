package com.chewielouie.gobeyond;

import com.chewielouie.gobeyond.Move.Colour;
import com.chewielouie.gobeyond.Referee.MoveStatus;

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
