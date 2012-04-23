package com.ChewieLouie.GoBeyond;

import java.util.HashSet;
import java.util.Set;

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
		Set<Coord> deadStones = new HashSet<Coord>();
		collectDeadStones(c.up(), enemyColour, deadStones);
		collectDeadStones(c.down(), enemyColour, deadStones);
		collectDeadStones(c.left(), enemyColour, deadStones);
		collectDeadStones(c.right(), enemyColour, deadStones);
		for( Coord stone: deadStones )
			removeStone( stone );
	}

	private void collectDeadStones(Coord c, Point enemyColour, Set<Coord> deadStones) {
		if( isStringDead( c, enemyColour ) )
			analyzer.stonesOfString( c, board ).addTo(deadStones);
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
