package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public class PassingMoveSource implements MoveSource {

	@Override
	public Move getMove(Colour colour) {
		return Move.passMove(colour);
	}

}
