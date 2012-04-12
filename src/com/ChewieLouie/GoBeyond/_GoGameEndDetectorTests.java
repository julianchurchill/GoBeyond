package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _GoGameEndDetectorTests {

	private GoGameEndDetector gameEndDetector;

	@Before
	public void SetUp() {
		gameEndDetector = new GoGameEndDetector( 10 );
	}

	@Test
	public void endIsNotDetectedBeforeMaxMovesReached() {
		for( int i = 0; i < 10; ++i ) {
			assertEquals( false, gameEndDetector.endDetected() );
			gameEndDetector.movePlayed( new Move( 0, 0, Move.Colour.Black ) );
		}
	}

	@Test
	public void endIsDetectedIfMaximumMovesReached() {
		for( int i = 0; i < 10; ++i )
			gameEndDetector.movePlayed( new Move( 0, 0, Move.Colour.Black ) );

		assertEquals( true, gameEndDetector.endDetected() );
	}
	
	@Test
	public void endIsDetectedIfLastTwoMovesArePasses() {
		gameEndDetector.movePlayed( Move.passMove( Move.Colour.Black ) );
		gameEndDetector.movePlayed( Move.passMove( Move.Colour.White ) );
		
		assertEquals( true, gameEndDetector.endDetected() );
	}

	@Test
	public void endIsNotDetectedIfTwoMovesConsecutivePassesOccurWhichAreNotTheLastMoves() {
		gameEndDetector.movePlayed( Move.passMove( Move.Colour.Black ) );
		gameEndDetector.movePlayed( Move.passMove( Move.Colour.White ) );
		gameEndDetector.movePlayed( new Move( 0, 0, Move.Colour.Black ) );
	
		assertEquals( false, gameEndDetector.endDetected() );
	}
}
