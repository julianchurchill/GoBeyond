package com.ChewieLouie.GoBeyond.ui;

import com.ChewieLouie.GoBeyond.util.Coord;

public class BoardCoordConverter {

	private int gridUnitSizeInPixels;
	private Coord origin;

	public BoardCoordConverter(int gridUnitSizeInPixels, Coord origin) {
		this.gridUnitSizeInPixels = gridUnitSizeInPixels;
		this.origin = origin;
	}

	public Coord toBoard(Coord c) {
		return new Coord( (c.x() - origin.x())/gridUnitSizeInPixels, (c.y() - origin.y())/gridUnitSizeInPixels );
	}

	public Coord toScreen(Coord c) {
		return new Coord( c.x()*gridUnitSizeInPixels + origin.x(), c.y()*gridUnitSizeInPixels + origin.y());
	}

}
