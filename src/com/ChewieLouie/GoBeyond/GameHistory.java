package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
	
	private List<Board> boards = new ArrayList<Board>();
	private List<Move> moves = new ArrayList<Move>();

	public void add( Board b, Move m ) {
		boards.add( b );
		moves.add( m );
	}

	public Object lastButOneBoard() {
		if( boards.size() > 1 )
			return boards.get( boards.size() - 2 );
		return null;
	}

	public Move lastMove() {
		if( moves.size() > 0 )
			return moves.get( moves.size() - 1 );
		return null;
	}

	public Move lastButOneMove() {
		if( moves.size() > 1 )
			return moves.get( moves.size() - 2 );
		return null;
	}

}
