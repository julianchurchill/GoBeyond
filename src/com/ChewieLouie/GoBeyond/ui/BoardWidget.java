package com.ChewieLouie.GoBeyond.ui;

import com.ChewieLouie.GoBeyond.GameBrowser;
import com.ChewieLouie.GoBeyond.util.Coord;

public interface BoardWidget {

	public abstract void addGameBrowser(GameBrowser gameBrowser);

	public abstract Coord getLastClickedBoardPoint();

}