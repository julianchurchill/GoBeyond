package com.ChewieLouie.GoBeyond;

public interface StringLifeAnalyzer {

	public abstract boolean isStringAlive(Board testBoard, Coord coord);

	public abstract StringOfStones stonesOfString(Coord c, Board board);

}