package com.ChewieLouie.GoBeyond.ui;

import com.ChewieLouie.GoBeyond.Board;
import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.MoveSource;
import com.ChewieLouie.GoBeyond.Move.Colour;

public class GUIMoveSource implements MoveSource {

	private BoardWidget boardWidget;

	public GUIMoveSource(BoardWidget boardWidget) {
		this.boardWidget = boardWidget;
	}

	@Override
	public Move getMove(Colour colour, Board board) {
		return new Move( boardWidget.getLastClickedBoardPoint(), colour );
	}

}
