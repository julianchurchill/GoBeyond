package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

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
		assertEquals( 2, c.left().x() );
		assertEquals( 2, c.left().y() );
	}

	@Test
	public void CoordRightCreatesNewCoordOneUnitToTheRight() {
		Coord c = new Coord( 1, 2 );
		assertEquals( 0, c.right().x() );
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
	public void ValueObject() {
		Coord c1 = new Coord( 4, 8 );
		Coord c2 = new Coord( 4, 8 );
		Coord c3 = new Coord( 2, 3 );
		
		assertEquals( true, c1.equals( c2 ) );
		assertEquals( false, c1.equals( c3 ) );
	}
}
