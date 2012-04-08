package com.ChewieLouie.GoBeyond;

public class Board {

	public static enum PointStatus { Empty, Occupied };

	private Stone[][] contents = new Stone[19][19];

	public Stone getContentsOfPoint(int i, int j) {
		return contents[i][j];
	}

	public void playStone(Stone stone, int x, int y ) {
		contents[x][y] = stone;
	}

	public PointStatus getPointStatus(int i, int j) {
		return contents[i][j] == null ? PointStatus.Empty : PointStatus.Occupied;
	}

	public void removeStone(int i, int j) {
		contents[i][j] = null;
	}

}
