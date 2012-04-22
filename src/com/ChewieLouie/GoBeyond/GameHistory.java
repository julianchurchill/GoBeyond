package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boards == null) ? 0 : boards.hashCode());
		result = prime * result + ((moves == null) ? 0 : moves.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameHistory other = (GameHistory) obj;
		if (boards == null) {
			if (other.boards != null)
				return false;
		} else if (!boards.equals(other.boards))
			return false;
		if (moves == null) {
			if (other.moves != null)
				return false;
		} else if (!moves.equals(other.moves))
			return false;
		return true;
	}

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
