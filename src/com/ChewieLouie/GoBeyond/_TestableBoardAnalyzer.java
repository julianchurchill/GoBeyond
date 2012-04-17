package com.ChewieLouie.GoBeyond;

import java.util.HashMap;
import java.util.Map;

public class _TestableBoardAnalyzer implements BoardAnalyzer {

	public boolean isStringAliveCalled = false;
	public Map<Coord, Boolean> isStringAliveReturn = new HashMap<Coord, Boolean>();

	@Override
	public boolean isStringAlive(Board b, Coord c) {
		isStringAliveCalled  = true;
		if( isStringAliveReturn.containsKey(c) )
			return (Boolean) isStringAliveReturn.get(c);
		return false;
	}

}
