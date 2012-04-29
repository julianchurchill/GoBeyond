package com.ChewieLouie.GoBeyond.ui;

import com.ChewieLouie.GoBeyond.util.Coord;

public class BoardCoordConverter {

	private int gridUnitSizeInPixels;

	public BoardCoordConverter(int gridUnitSizeInPixels) {
		this.gridUnitSizeInPixels = gridUnitSizeInPixels;
	}

	public Coord toBoard(Coord c) {
		return new Coord( c.x()/gridUnitSizeInPixels, c.y()/gridUnitSizeInPixels );
	}

	public Coord toScreen(Coord c) {
		return new Coord( c.x()*gridUnitSizeInPixels, c.y()*gridUnitSizeInPixels );
	}

}
