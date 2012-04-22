package com.ChewieLouie.GoBeyond;

import java.util.Arrays;

public class GoBoard implements Board {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(contents);
		result = prime * result + size;
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
		GoBoard other = (GoBoard) obj;
		if (!Arrays.deepEquals(contents, other.contents))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String str = new String();
		for( int y = 0; y < size; ++y ) {
			for( int x = 0; x < size; ++x )
				str += pointToString( contents[x][y] );
			str += "\n";
		}
		return str;
	}

	private String pointToString(Point point) {
		if( point == Point.BlackStone )
			return "b";
		else if( point == Point.WhiteStone )
			return "w";
		return ".";
	}

	protected int size = 19;
	protected Point[][] contents;

	public GoBoard(int size) {
		this.size = size;
		this.contents = new Point[size][size];
		for( int y = 0; y < size; ++y )
			for( int x = 0; x < size; ++x )
				contents[x][y] = Point.Empty;
	}

	@Override
	public Point getContentsOfPoint(Coord c) {
		if( c.x() < 0 || c.y() < 0 || c.x() >= size || c.y() >= size )
			return Board.Point.OffBoard;
		return contents[c.x()][c.y()];
	}

	@Override
	public void playStone(Point p, Coord c ) {
		contents[c.x()][c.y()] = p;
	}

	@Override
	public void removeStone(Coord c) {
		contents[c.x()][c.y()] = Point.Empty;
	}

	@Override
	public Board duplicate() {
		Board b = new GoBoard( size );
		for( int x = 0; x < size; ++x )
			for( int y = 0; y < size; ++y )
				b.playStone( contents[x][y], new Coord( x, y ) );
		return b;
	}

	public static GoBoard makeBoard(String string) {
		int size = (int) Math.sqrt( string.length() );
		GoBoard b = new GoBoard( size );
		int i = 0;
		for( int y = 0; y < size; ++y )
			for( int x = 0; x < size; ++x )
				b.playStone( charToPoint( string.charAt( i++ ) ), new Coord( x, y ) );
		return b;
	}

	private static Point charToPoint(char c) {
		if( c == 'b' )
			return Board.Point.BlackStone;
		else if( c == 'w' )
			return Board.Point.WhiteStone;
		return Board.Point.Empty;
	}
}
