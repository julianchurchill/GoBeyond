package com.ChewieLouie.GoBeyond;

public class Move {

	public enum Colour { Black, White };
	
	private int x;
	private int y;
	private Colour colour;

	public Move(int x, int y, Colour colour) {
		this.x = x;
		this.y = y;
		this.colour = colour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + x();
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
		Move other = (Move) obj;
		if (colour != other.colour)
			return false;
		if (x() != other.x())
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public Colour colour() {
		return colour;
	}

}
