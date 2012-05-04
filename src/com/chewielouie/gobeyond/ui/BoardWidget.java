package com.chewielouie.gobeyond.ui;

import com.chewielouie.gobeyond.GameBrowser;
import com.chewielouie.gobeyond.util.Coord;

public interface BoardWidget {

	public abstract void addGameBrowser(GameBrowser gameBrowser);

	public abstract Coord removeLastClickedBoardPoint();

	public abstract boolean clickAvailable();

	public abstract void allowBoardClicks();

}