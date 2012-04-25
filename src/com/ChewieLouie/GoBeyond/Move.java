package com.ChewieLouie.GoBeyond;

public class Move {

	@Override
	public String toString() {
		return colour + "[" + c.x() + "," + c.y() + "]";
	}

	public enum Colour { Black, White };
	
	private Coord c;
	private Colour colour;

	static public Board.Point toStone( Move.Colour c ) {
		return c == Move.Colour.Black ? Board.Point.BlackStone : Board.Point.WhiteStone;
	}
	
	public Move(Coord c, Colour colour) {
		this.c = c;
		this.colour = colour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		result = prime * result + x();
		result = prime * result + y();
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
		if (y() != other.y())
			return false;
		return true;
	}
	
	public Coord coord() {
		return c;
	}

	private int x() {
		return c.x();
	}

	private int y() {
		return c.y();
	}

	public Colour colour() {
		return colour;
	}

	public static Move passMove(Colour c) {
		return new Move( new Coord( -1, -1 ), c );
	}

}
