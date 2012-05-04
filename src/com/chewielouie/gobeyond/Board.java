package com.chewielouie.gobeyond;

import com.chewielouie.gobeyond.util.Coord;

public interface Board {

	public static enum Point { Empty, BlackStone, WhiteStone, OffBoard }

	public abstract Point getContentsOfPoint(Coord c);

	public abstract void playStone(Point p, Coord c);

	public abstract void removeStone(Coord c);

	public abstract Board duplicate();

	public abstract int size();
}