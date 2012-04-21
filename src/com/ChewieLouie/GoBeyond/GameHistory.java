package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

public class GameHistory {
	
	private List<Board> boards = new ArrayList<Board>();

	public void add( Board b ) {
		boards.add( b );
	}

	public Object lastButOneBoard() {
		if( boards.size() > 1 )
			return boards.get( boards.size() - 2 );
		return null;
	}

}
