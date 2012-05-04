package com.chewielouie.gobeyond;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.chewielouie.gobeyond.util.Coord;

public class StringOfStones {

	private Set<Coord> stones = new HashSet<Coord>();

	public void add(Coord c) {
		stones.add( c );
	}

	public int size() {
		return stones.size();
	}

	public boolean find(Coord coord) {
		for(Coord c: stones )
			if( c.equals( coord ) )
				return true;
		return false;
	}

	public void addTo(Collection<Coord> stonesToAddTo) {
		stonesToAddTo.addAll( stones );
	}
}
