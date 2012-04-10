package com.ChewieLouie.GoBeyond;

public class _TestableStrategy implements Strategy {

	public Move generateMoveReturnValue;

	@Override
	public Move generateMove() {
		return generateMoveReturnValue;
	}

}
