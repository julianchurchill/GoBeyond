package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.util.Coord;

public class _TestableBoard implements Board {

	public boolean playStoneCalled = false;
	public Point playStonePoint;
	public int playStoneX = 0;
	public int playStoneY = 0;
	public boolean equalsReturn = false;
	public Board duplicateReturn = null;

	public _TestableBoard(int size) {
	}

	public boolean equals(Object obj) {
		return equalsReturn;
	}

	public void playStone(Point p, Coord c) {
		playStoneCalled = true;
		playStonePoint = p;
		playStoneX = c.x();
		playStoneY = c.y();
	}

	@Override
	public Point getContentsOfPoint(Coord c) {
		return null;
	}

	@Override
	public void removeStone(Coord c) {
	}

	@Override
	public Board duplicate() {
		return duplicateReturn;
	}

	@Override
	public int size() {
		return 0;
	}
}
