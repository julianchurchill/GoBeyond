package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public class PassingMoveSource implements MoveSource {

	@Override
	public Move getMove(Colour colour, Board board) {
		return Move.passMove(colour);
	}

}
