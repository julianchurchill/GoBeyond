package com.chewielouie.gobeyond;

import static org.junit.Assert.*;

import org.junit.Test;

public class _PseudoRandomGeneratorTests {

	@Test
	public void generatesRandomNumbersInRange() {
		PseudoRandomGenerator r = new PseudoRandomGenerator( 0 );
		// When seeded with 0 random numbers should be... 
		assertEquals( "random number is as expected in seeded sequence", 8, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 2, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 7, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 6, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 6, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 3, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 4, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 10, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 9, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 10, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 3, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 1, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 1, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 0, r.generate( 0, 10 ) );
		assertEquals( "random number is as expected in seeded sequence", 6, r.generate( 0, 10 ) );
	}

}
