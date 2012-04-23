package com.ChewieLouie.GoBeyond;

import java.util.HashSet;
import java.util.Set;

public class _TestableBoardAnalyzer implements StringLifeAnalyzer {

	public boolean isStringAliveCalled = false;
	public boolean isStringAliveReturnDefault = false;
	public Set<Coord> stringIsDead = new HashSet<Coord>();

	@Override
	public boolean isStringAlive(Board b, Coord c) {
		isStringAliveCalled  = true;
		if( stringIsDead.contains(c) )
			return false;
		return isStringAliveReturnDefault;
	}

}
