package com.ChewieLouie.GoBeyond;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ChewieLouie.GoBeyond.util.Coord;

public class _GoRulesTests {

	private SimpleBoard board;
	private Rules rules;
	private StringLifeAnalyzer boardAnalyzer;

	@Before
	public void SetUp() {
		board = new SimpleBoard( 19 );
		boardAnalyzer = mock( StringLifeAnalyzer.class );
		rules = new GoRules( boardAnalyzer );
	}

	@Test
    public void shouldUseBoardAnalyzerDuringLegalityCheck() {
		rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ), board, null );
		verify(boardAnalyzer).isStringAlive((Board)any(), (Coord)any() );
	}
	
	@Test
	public void aPassIsAlwaysLegal() {
		assertTrue( "pass move is always legal", rules.isLegal(Move.passMove(Move.Colour.Black), null, null) );
	}

	@Test
    public void moveOnAnOccupiedIntersectionIsIllegal() {
		board.playStone( Board.Point.BlackStone, new Coord( 0, 0 ) );
		assertFalse( rules.isLegal( new Move( new Coord( 0, 0 ), Move.Colour.Black ), board, null ) );
	}

	// the following four test check capturing in each direction
	// I don't like them but not sure how to compress...
	@Test
	public void suicideToCaptureIsLegal_eastCapture() {
		board = SimpleBoard.makeBoard( ".bw.." +
									   "b.bw." +
									   ".bw.." +
									   "....." +
									   "....." );
		when(boardAnalyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn(true);
		when(boardAnalyzer.isStringAlive((Board)any(), eq(new Coord( 2, 1 )))).thenReturn(false);
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void suicideToCaptureIsLegal_westCapture() {
		board = SimpleBoard.makeBoard( "wwbb." +
									   "b.wb." +
									   "wbb.." +
									   "....." +
									   "....." );
		when(boardAnalyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn(true);
		when(boardAnalyzer.isStringAlive((Board)any(), eq( new Coord( 0, 1 )))).thenReturn(false);
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void suicideToCaptureIsLegal_northCapture() {
		board = SimpleBoard.makeBoard( "wbwb." +
									   "w.wb." +
									   "bbb.." +
									   "....." +
									   "....." );
		when(boardAnalyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn(true);
		when(boardAnalyzer.isStringAlive((Board)any(), eq(new Coord( 1, 0 )))).thenReturn(false);
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void suicideToCaptureIsLegal_southCapture() {
		board = SimpleBoard.makeBoard( ".b..." +
									   "b.b.." +
									   "wbw.." +
									   ".w..." +
									   "....." );
		when(boardAnalyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn(true);
		when(boardAnalyzer.isStringAlive((Board)any(), eq( new Coord( 1, 2 )))).thenReturn(false);
		assertTrue( rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, null ) );
	}

	@Test
	public void ImmediatelyTakingAKoIsIllegal() {
		board = SimpleBoard.makeBoard( ".bw.." +
									   "b.bw." +
									   ".bw.." +
									   "....." +
									   "....." );
		when(boardAnalyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn(true);
		when(boardAnalyzer.isStringAlive((Board)any(), eq( new Coord( 1, 1 )))).thenReturn(false);
		when(boardAnalyzer.isStringAlive((Board)any(), eq( new Coord( 2, 1 )))).thenReturn(false);
		StringOfStones stones = new StringOfStones();
		stones.add( new Coord( 2, 1 ) );
		when(boardAnalyzer.stonesOfString((Coord)any(), (Board)any())).thenReturn( stones );
		
		Board tb1 = SimpleBoard.makeBoard( ".bw.." +
										   "bw.w." +
										   ".bw.." +
										   "....." +
										   "....." );
		GameHistory history = new GameHistory();
		history.add( tb1, null );
		history.add( new SimpleBoard( 19 ), null );

		assertFalse( "taking a ko immediately is illegal", 
				rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, history ) );
	}

	@Test
	public void TakingAKoAfterADifferentMoveIsLegal() {
		board = SimpleBoard.makeBoard( ".bw.." +
									   "b.bw." +
									   ".bw.." +
									   "....." +
									   "....." );
		when(boardAnalyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn(true);
		when(boardAnalyzer.isStringAlive((Board)any(), eq( new Coord( 1, 1 )))).thenReturn(false);
		when(boardAnalyzer.isStringAlive((Board)any(), eq( new Coord( 2, 1 )))).thenReturn(false);
		when(boardAnalyzer.stonesOfString((Coord)any(), (Board)any())).thenReturn( new StringOfStones() );

		SimpleBoard tb1 = new SimpleBoard( 19 );
		SimpleBoard tb2 = new SimpleBoard( 19 );
		GameHistory history = new GameHistory();
		history.add( tb1, null );
		history.add( tb2, null );

		assertTrue( "taking a Ko after a different move is legal",
				rules.isLegal( new Move( new Coord( 1, 1 ), Move.Colour.White ), board, history ) );
	}

	@Test
	public void endIsNotDetectedIfLastTwoMovesAreNotPasses() {
		GameHistory history = new GameHistory();
		history.add( null, new Move( new Coord( 1, 1 ), Move.Colour.Black ) );
		history.add( null, new Move( new Coord( 1, 1 ), Move.Colour.White ) );
		
		assertEquals( "no passes does not end the game", false, rules.endDetected( history ) );
	}

	@Test
	public void endIsNotDetectedIfEitherLastTwoMovesAreNotPasses() {
		GameHistory history = new GameHistory();
		history.add( null, new Move( new Coord( 1, 1 ), Move.Colour.White ) );
		history.add( null, Move.passMove( Move.Colour.Black ) );
		assertEquals( "move then pass does not end the game", false, rules.endDetected( history ) );

		history.add( null, Move.passMove( Move.Colour.Black ) );
		history.add( null, new Move( new Coord( 1, 1 ), Move.Colour.White ) );
		assertEquals( "pass the move does not end the game", false, rules.endDetected( history ) );
	}
	
	@Test
	public void endIsDetectedIfLastTwoMovesArePasses() {
		GameHistory history = new GameHistory();
		history.add( null, Move.passMove( Move.Colour.Black ) );
		history.add( null, Move.passMove( Move.Colour.White ) );
		
		assertEquals( "two passes ends the game", true, rules.endDetected( history ) );
	}
	
	@Test
	public void endIsNotDetectedUntilAtLeastTwoMovesHaveBeenPlayed() {
		GameHistory history = new GameHistory();
		assertFalse( "no moves played yet means end is not detected", rules.endDetected( history ) );
		assertFalse( "one move played means end is not detected", rules.endDetected( history ) );
	}
}
