package com.chewielouie.gobeyond.ui;

import com.chewielouie.gobeyond.Board;
import com.chewielouie.gobeyond.Move;
import com.chewielouie.gobeyond.MoveSource;
import com.chewielouie.gobeyond.Move.Colour;

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
