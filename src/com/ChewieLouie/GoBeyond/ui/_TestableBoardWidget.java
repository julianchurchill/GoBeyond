package com.ChewieLouie.GoBeyond.ui;

import com.ChewieLouie.GoBeyond.GameBrowser;
import com.ChewieLouie.GoBeyond.util.Coord;

public class _TestableBoardWidget implements BoardWidget {

	public Coord getLastClickedBoardPointReturn = null;

	@Override
	public void addGameBrowser(GameBrowser gameBrowser) {
	}

	@Override
	public Coord removeLastClickedBoardPoint() {
		return getLastClickedBoardPointReturn;
	}

	@Override
	public boolean clickAvailable() {
		return true;
	}

	@Override
	public void allowBoardClicks() {
	}

}
