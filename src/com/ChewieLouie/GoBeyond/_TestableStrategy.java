package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public class _TestableStrategy implements Strategy {

	public Move generateMoveReturnValue;
	public boolean generateMoveCalled = false;
	public Move.Colour generateMoveColour = Move.Colour.Black;
	public Board generateMoveBoard;

	@Override
	public Move generateMove(Board simpleBoard, Colour colour) {
		generateMoveCalled = true;
		generateMoveColour = colour;
		generateMoveBoard = simpleBoard;
		return generateMoveReturnValue;
	}

}
