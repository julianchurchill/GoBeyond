package com.ChewieLouie.GoBeyond;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SimpleBoard implements Board {

	protected int size = 19;
	protected Point[][] contents;
	private Set<BoardObserver> observers = new HashSet<BoardObserver>();

	public SimpleBoard(int size) {
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
		updatePoint(p, c);
	}

	private void updatePoint(Point p, Coord c) {
		contents[c.x()][c.y()] = p;
		for( BoardObserver o : observers )
			o.boardChanged();
	}

	@Override
	public void removeStone(Coord c) {
		updatePoint(Point.Empty, c);
	}

	@Override
	public Board duplicate() {
		Board b = new SimpleBoard( size );
		for( int x = 0; x < size; ++x )
			for( int y = 0; y < size; ++y )
				b.playStone( contents[x][y], new Coord( x, y ) );
		return b;
	}

	public static SimpleBoard makeBoard(String string) {
		int size = (int) Math.sqrt( string.length() );
		if( size * size != string.length() )
			throw new RuntimeException("board must be square (width == height)");
		SimpleBoard b = new SimpleBoard( size );
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

	@Override
	public int size() {
		return size;
	}

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
		SimpleBoard other = (SimpleBoard) obj;
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

	public void addObserver(BoardObserver o) {
		this.observers.add( o );
	}

}
