package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public class _TestableMoveSource implements MoveSource {

	public Move getMoveReturn;
	public boolean getMoveCalled = false;
	public Move.Colour getMoveCalledWithColour;

	@Override
	public Move getMove(Colour colour) {
		getMoveCalled = true;
		getMoveCalledWithColour = colour;
		return getMoveReturn;
	}
}
