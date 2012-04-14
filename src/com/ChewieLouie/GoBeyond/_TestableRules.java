package com.ChewieLouie.GoBeyond;

public class _TestableRules implements Rules {

	public boolean isLegalCalled = false;
	public Move isLegalCalledWithMove;
	public boolean isLegalReturnValue = true;
	public boolean isLegalMoveAvailableCalled = false;
	public boolean isLegalMoveAvailableReturnValue = true;

	@Override
	public boolean isLegal( Move m ) {
		isLegalCalled = true;
		isLegalCalledWithMove = m;
		return isLegalReturnValue;
	}
	
	@Override
	public boolean isLegalMoveAvailable() {
		isLegalMoveAvailableCalled = true;
		return isLegalMoveAvailableReturnValue;
	}
}
