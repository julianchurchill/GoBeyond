package com.ChewieLouie.GoBeyond;

import java.util.List;

public interface StringLifeAnalyzer {

	public abstract boolean isStringAlive(Board testBoard, Coord coord);

	public abstract List<Coord> stonesOfString(Coord c);

}