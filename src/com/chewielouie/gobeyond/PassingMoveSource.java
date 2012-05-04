package com.chewielouie.gobeyond;

import com.chewielouie.gobeyond.Move.Colour;

public class PassingMoveSource implements MoveSource {

	@Override
	public Move getMove(Colour colour, Board board) {
		return Move.passMove(colour);
	}

}
