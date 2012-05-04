package com.ChewieLouie.GoBeyond.util;

import java.util.List;


public class Utils {

	public static boolean find(Coord coord, List<Coord> coords) {
		for(Coord c: coords )
			if( c.equals( coord ) )
				return true;
		return false;
	}
}
