package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

public class RemovedDeadStonesBoard implements Board {

	private Board board;
	private StringLifeAnalyzer analyzer;

	public RemovedDeadStonesBoard(Board b, StringLifeAnalyzer a) {
		this.board = b;
		this.analyzer = a;
	}

	@Override
	public Point getContentsOfPoint(Coord c) {
		return board.getContentsOfPoint(c);
	}

	@Override
	public void playStone(Point p, Coord c) {
		board.playStone(p, c);
		removeDeadStrings(p, c);
	}

	private void removeDeadStrings(Point p, Coord c) {
		Point enemyColour = p == Point.BlackStone ? Point.WhiteStone : Point.BlackStone;
		List<Coord> deadStones = new ArrayList<Coord>();
		if( isStringDead( c.up(), enemyColour ) )
			deadStones.addAll( analyzer.stonesOfString( c.up() ) );
		if( isStringDead( c.down(), enemyColour ) )
			deadStones.addAll( analyzer.stonesOfString( c.down() ) );
		if( isStringDead( c.left(), enemyColour ) )
			deadStones.addAll( analyzer.stonesOfString( c.left() ) );
		if( isStringDead( c.right(), enemyColour ) )
			deadStones.addAll( analyzer.stonesOfString( c.right() ) );
		for( Coord stone: deadStones )
			removeStone( stone );
	}

	private boolean isStringDead(Coord c, Point enemyColour) {
		return board.getContentsOfPoint( c ) == enemyColour && analyzer.isStringAlive( board, c ) == false;
	}

	@Override
	public void removeStone(Coord c) {
		board.removeStone(c);
	}

	@Override
	public Board duplicate() {
		return board.duplicate();
	}

}
