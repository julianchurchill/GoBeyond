package com.chewielouie.gobeyond.util;

import java.util.ArrayList;
import java.util.List;

public class Coord {

	private int x;
	private int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public Coord left() {
		return new Coord( x - 1, y );
	}

	public Coord right() {
		return new Coord( x + 1, y );
	}

	public Coord up() {
		return new Coord( x, y - 1 );
	}

	public Coord down() {
		return new Coord( x, y + 1 );
	}

	public Coord upLeft() {
		return new Coord( x - 1, y - 1 );
	}

	public Coord upRight() {
		return new Coord( x + 1, y - 1 );
	}

	public Coord downLeft() {
		return new Coord( x - 1, y + 1 );
	}

	public Coord downRight() {
		return new Coord( x + 1, y + 1 );
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public List<Coord> orthogonalCoords() {
		List<Coord> coords = new ArrayList<Coord>();
		coords.add( this.up() );
		coords.add( this.upLeft() );
		coords.add( this.upRight() );
		coords.add( this.down() );
		coords.add( this.downLeft() );
		coords.add( this.downRight() );
		coords.add( this.left() );
		coords.add( this.right() );
		return coords;
	}
}
