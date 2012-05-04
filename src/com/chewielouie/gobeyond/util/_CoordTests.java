package com.chewielouie.gobeyond.util;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class _CoordTests {

	@Test
	public void CoordHasCorrectXAndYValues() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 1, c.x() );
		assertEquals( 2, c.y() );
	}

	@Test
	public void CoordLeftCreatesNewCoordOneUnitToTheLeft() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 0, c.left().x() );
		assertEquals( 2, c.left().y() );
	}

	@Test
	public void CoordRightCreatesNewCoordOneUnitToTheRight() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 2, c.right().x() );
		assertEquals( 2, c.right().y() );
	}

	@Test
	public void CoordUpCreatesNewCoordOneUnitUp() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 1, c.up().x() );
		assertEquals( 1, c.up().y() );
	}

	@Test
	public void CoordDownCreatesNewCoordOneUnitDown() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 1, c.down().x() );
		assertEquals( 3, c.down().y() );
	}

	@Test
	public void CoordUpLeftCreatesNewCoordOneUnitUpAndOneLeft() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 0, c.upLeft().x() );
		assertEquals( 1, c.upLeft().y() );
	}

	@Test
	public void CoordUpRightCreatesNewCoordOneUnitUpAndOneRight() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 2, c.upRight().x() );
		assertEquals( 1, c.upRight().y() );
	}

	@Test
	public void CoordDownLeftCreatesNewCoordOneUnitDownAndOneLeft() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 0, c.downLeft().x() );
		assertEquals( 3, c.downLeft().y() );
	}

	@Test
	public void CoordDownRightCreatesNewCoordOneUnitDownAndOneRight() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 2, c.downRight().x() );
		assertEquals( 3, c.downRight().y() );
	}

	@Test
	public void OrthogonalCoordsReturnsAllOrthogonallyAdjacentCoords() {
		Coord c = new Coord( 1, 1 );
		List<Coord> coords = c.orthogonalCoords();
		assertEquals( "there are always 8 orthogonally adjacent points", 8, coords.size() );
		assertTrue( "the up coord is included", find( coords, c.up() ) );
		assertTrue( "the up left coord is included", find( coords, c.upLeft() ) );
		assertTrue( "the up right coord is included", find( coords, c.upRight() ) );
		assertTrue( "the down coord is included", find( coords, c.down() ) );
		assertTrue( "the down left coord is included", find( coords, c.downLeft() ) );
		assertTrue( "the down right coord is included", find( coords, c.downRight() ) );
		assertTrue( "the left coord is included", find( coords, c.left() ) );
		assertTrue( "the right coord is included", find( coords, c.right() ) );
	}

	public boolean find(List<Coord> coords, Coord target) {
		for(Coord c: coords )
			if( c.equals( target ) )
				return true;
		return false;
	}

	@Test
	public void ValueObject() {
		Coord c1 = new Coord( 4, 8 );
		Coord c2 = new Coord( 4, 8 );
		Coord c3 = new Coord( 2, 3 );
		
		assertEquals( true, c1.equals( c2 ) );
		assertEquals( false, c1.equals( c3 ) );
	}
}
