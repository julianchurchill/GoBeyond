package com.ChewieLouie.GoBeyond.ui;

import com.ChewieLouie.GoBeyond.Board;
import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.MoveSource;
import com.ChewieLouie.GoBeyond.Move.Colour;

public class GUIMoveSource implements MoveSource {

	private BoardWidget boardWidget;
	private boolean enqueuePass = false;

	public GUIMoveSource(BoardWidget boardWidget) {
		this.boardWidget = boardWidget;
	}

	@Override
	public Move getMove(Colour colour, Board board) {
		while( boardWidget.clickAvailable() == false && enqueuePass == false ) {
			try {
				Thread.sleep( 1 );
			} catch (InterruptedException e) {
			}
		}
		Move m = new Move( boardWidget.removeLastClickedBoardPoint(), colour );
		if( enqueuePass )
			m = Move.passMove(colour);
		enqueuePass = false;
		return m;
	}

	public void enqueuePass() {
		enqueuePass = true;
	}

}
