package com.ChewieLouie.GoBeyond.ui;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ChewieLouie.GoBeyond.util.Coord;

public class _BoardCoordConverterTests {

	BoardCoordConverter converter;

	@Before
	public void SetUp() {
		converter = new BoardCoordConverter(100, new Coord( 0, 0 ) );
	}

	@Test
	public void screenOriginCoordIsConvertedToBoardOriginCoord() {
		assertEquals( "0,0 coord on screen is 0,0 on board", new Coord( 0, 0 ), converter.toBoard( new Coord( 0, 0 ) ) );

		BoardCoordConverter converterWithAlternativeOrigin = new BoardCoordConverter( 100, new Coord( 100, 100 ) );
		assertEquals( "screen with origin of 100,100 has origin on board of 0,0", 
				new Coord( 0, 0 ), converterWithAlternativeOrigin.toBoard( new Coord( 100, 100 ) ) );
	}

	@Test
	public void screenCoordIsConvertedToBoardCoordUsingGridUnitSize() {
		assertEquals( "100,99 coord on screen is 1,0 on board for grid unit size 100", new Coord( 1, 0 ), converter.toBoard( new Coord( 100, 99 ) ) );
		assertEquals( "99,100 coord on screen is 0,1 on board for grid unit size 100", new Coord( 0, 1 ), converter.toBoard( new Coord( 99, 100 ) ) );
		assertEquals( "100,100 coord on screen is 1,1 on board for grid unit size 100", new Coord( 1, 1 ), converter.toBoard( new Coord( 100, 100 ) ) );
		assertEquals( "150,150 coord on screen is 1,1 on board for grid unit size 100", new Coord( 1, 1 ), converter.toBoard( new Coord( 150, 150 ) ) );
		assertEquals( "200,199 coord on screen is 2,1 on board for grid unit size 100", new Coord( 2, 1 ), converter.toBoard( new Coord( 200, 199 ) ) );
		assertEquals( "199,200 coord on screen is 1,2 on board for grid unit size 100", new Coord( 1, 2 ), converter.toBoard( new Coord( 199, 200 ) ) );
		assertEquals( "200,200 coord on screen is 2,2 on board for grid unit size 100", new Coord( 2, 2 ), converter.toBoard( new Coord( 200, 200 ) ) );
	}

	@Test
	public void boardOriginCoordIsConvertedToScreenOriginCoord() {
		assertEquals( "0,0 coord on board is 0,0 on screen", new Coord( 0, 0 ), converter.toScreen( new Coord( 0, 0 ) ) );

		BoardCoordConverter converterWithAlternativeOrigin = new BoardCoordConverter( 100, new Coord( 100, 100 ) );
		assertEquals( "screen with origin of 100,100 converts board coord of 0,0 to 100,100", 
				new Coord( 100, 100 ), converterWithAlternativeOrigin.toScreen( new Coord( 0, 0 ) ) );
	}

	@Test
	public void boardCoordIsConvertedToScreenCoordUsingGridUnitSize() {
		assertEquals( "1,0 coord on board is 100,00 on screen for grid unit size 100", new Coord( 100, 0 ), converter.toScreen( new Coord( 1, 0 ) ) );
		assertEquals( "0,1 coord on board is 0,100 on screen for grid unit size 100", new Coord( 0, 100 ), converter.toScreen( new Coord( 0, 1 ) ) );
		assertEquals( "1,1 coord on board is 100,100 on screen for grid unit size 100", new Coord( 100, 100 ), converter.toScreen( new Coord( 1, 1 ) ) );
		assertEquals( "2,1 coord on board is 200,100 on screen for grid unit size 100", new Coord( 200, 100 ), converter.toScreen( new Coord( 2, 1 ) ) );
		assertEquals( "1,2 coord on board is 100,200 on screen for grid unit size 100", new Coord( 100, 200 ), converter.toScreen( new Coord( 1, 2 ) ) );
		assertEquals( "2,2 coord on board is 200,200 on screen for grid unit size 100", new Coord( 200, 200 ), converter.toScreen( new Coord( 2, 2 ) ) );
	}
}
