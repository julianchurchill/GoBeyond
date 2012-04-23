package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _TestableStringLifeAnalyzer implements StringLifeAnalyzer {

	public boolean isStringAliveCalled = false;
	public boolean isStringAliveReturnDefault = false;
	public Set<Coord> stringIsDead = new HashSet<Coord>();
	public List<Coord> isStringAliveCoordAll = new ArrayList<Coord>();
	public boolean stonesOfStringCalled = false;
	public StringOfStones stonesOfStringReturn = new StringOfStones();
	public List<Coord> stonesOfStringCoordAll = new ArrayList<Coord>();

	@Override
	public boolean isStringAlive(Board b, Coord c) {
		isStringAliveCalled  = true;
		isStringAliveCoordAll.add( c );
		if( stringIsDead.contains(c) )
			return false;
		return isStringAliveReturnDefault;
	}

	@Override
	public StringOfStones stonesOfString(Coord c, Board b) {
		stonesOfStringCalled = true;
		stonesOfStringCoordAll.add( c );
		return stonesOfStringReturn;
	}

}
