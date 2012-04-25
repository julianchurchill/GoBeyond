package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public class _TestableStrategy implements Strategy {

	public Move generateMoveReturnValue;

	@Override
	public Move generateMove(SimpleBoard simpleBoard, Colour colour) {
		return generateMoveReturnValue;
	}

}
