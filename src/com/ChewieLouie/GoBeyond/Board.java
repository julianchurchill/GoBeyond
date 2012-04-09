package com.ChewieLouie.GoBeyond;

public interface Board {

	public static enum Point { Empty, BlackStone, WhiteStone }

	public abstract Point getContentsOfPoint(int x, int y);

	public abstract void playStone(Point p, int x, int y);

	public abstract void removeStone(int x, int y);

}