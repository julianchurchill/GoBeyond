package com.ChewieLouie.GoBeyond;

public class GoBoard implements Board {

	private Point[][] contents = new Point[19][19];

	@Override
	public Point getContentsOfPoint(int x, int y) {
		if( contents[x][y] == null )
			contents[x][y] = Point.Empty;
		return contents[x][y];
	}

	@Override
	public void playStone(Point p, int x, int y ) {
		contents[x][y] = p;
	}

	@Override
	public void removeStone(int x, int y) {
		contents[x][y] = null;
	}
}
