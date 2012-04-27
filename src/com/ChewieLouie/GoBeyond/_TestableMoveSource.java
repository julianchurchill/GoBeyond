package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public class _TestableMoveSource implements MoveSource {

	public Move getMoveReturn;
	public boolean getMoveCalled = false;
	public Move.Colour getMoveCalledWithColour;
	public Board getMoveCalledWithBoard;

	@Override
	public Move getMove(Colour colour, Board board) {
		getMoveCalled = true;
		getMoveCalledWithColour = colour;
		getMoveCalledWithBoard = board;
		return getMoveReturn;
	}
}
