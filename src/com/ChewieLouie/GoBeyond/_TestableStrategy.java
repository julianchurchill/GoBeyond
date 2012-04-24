package com.ChewieLouie.GoBeyond;

public class _TestableStrategy implements Strategy {

	public Coord generateMoveReturnValue;

	@Override
	public Coord generateMove(SimpleBoard simpleBoard) {
		return generateMoveReturnValue;
	}

}
