package com.ChewieLouie.GoBeyond;

public class GoBoard implements Board {

	private int size = 19;
	private Point[][] contents;

	public GoBoard(int size) {
		this.size = size;
		this.contents = new Point[size][size];
	}

	@Override
	public Point getContentsOfPoint(Coord c) {
		if( c.x() < 0 || c.y() < 0 || c.x() >= size || c.y() >= size )
			return Board.Point.OffBoard;
		if( contents[c.x()][c.y()] == null )
			contents[c.x()][c.y()] = Point.Empty;
		return contents[c.x()][c.y()];
	}

	@Override
	public void playStone(Point p, Coord c ) {
		contents[c.x()][c.y()] = p;
	}

	@Override
	public void removeStone(Coord c) {
		contents[c.x()][c.y()] = null;
	}
}
