package com.ChewieLouie.GoBeyond;

public class _TestablePlayer implements Player {

	public int generateMoveCalledCount = 0;
	private MoveObserver moveObserver = null;

	@Override
	public void playMove() {
		generateMoveCalledCount++;
		if( moveObserver != null )
			moveObserver.movePlayed( new Move( 0, 0, Move.Colour.Black ) );
	}

	public void notifyOfMovesPlayed(MoveObserver observer) {
		this.moveObserver = observer;
	}

}
