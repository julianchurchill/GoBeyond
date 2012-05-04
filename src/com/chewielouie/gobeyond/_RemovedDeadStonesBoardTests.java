package com.chewielouie.gobeyond;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.chewielouie.gobeyond.util.Coord;

public class _RemovedDeadStonesBoardTests {

	@Test
	public void originalBoardCanBeRetrieved() {
		SimpleBoard board = SimpleBoard.makeBoard( "wbw" + 
			       "b.b" +
			       "wbw" );				
		RemovedDeadStonesBoard consistentBoard = new RemovedDeadStonesBoard( board, null );
		assertTrue( "original board can be retrieved", board == consistentBoard.originalBoard() );
	}
	
	@Test
	public void ShouldRemoveSingleStoneDeadStringsAfterMovePlayedOnBoard() {
		SimpleBoard board = SimpleBoard.makeBoard( "wbw" + 
									       "b.b" +
									       "wbw" );				
		StringOfStones stones = new StringOfStones();
		stones.add( new Coord( 1, 0 ) );
		stones.add( new Coord( 0, 1 ) );
		stones.add( new Coord( 2, 1 ) );
		stones.add( new Coord( 1, 2 ) );
		StringLifeAnalyzer analyzer = mock( StringLifeAnalyzer.class );
		when(analyzer.stonesOfString((Coord)any(), (Board)any())).thenReturn(stones);
		RemovedDeadStonesBoard consistentBoard = new RemovedDeadStonesBoard( board, analyzer );
		consistentBoard.playStone( Board.Point.WhiteStone, new Coord( 1, 1 ) );

		SimpleBoard expectedBoard = SimpleBoard.makeBoard( "w.w" + 
												   ".w." +
			    								   "w.w" );
		assertEquals( "single stone dead strings are removed and live ones remain", expectedBoard, board );
	}

	@Test
	public void playStoneUsesAnalyzerToFindStringLifeOfAdjacentEnemyStrings() {
		Coord moveCoord = new Coord( 1, 1 );
		StringLifeAnalyzer analyzer = mock( StringLifeAnalyzer.class );
		when(analyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn( false );
		when(analyzer.stonesOfString((Coord)any(), (Board)any())).thenReturn( new StringOfStones() );
		SimpleBoard board = SimpleBoard.makeBoard( "wbw" + 
			       "b.b" +
			       "wbw" );				
		RemovedDeadStonesBoard consistentBoard = new RemovedDeadStonesBoard( board, analyzer );
		consistentBoard.playStone( Board.Point.WhiteStone, moveCoord );
		
		verify(analyzer).isStringAlive(board, moveCoord.up());
		verify(analyzer).isStringAlive(board, moveCoord.down());
		verify(analyzer).isStringAlive(board, moveCoord.left());
		verify(analyzer).isStringAlive(board, moveCoord.right());
	}

	@Test
	public void playStoneUsesAnalyzerToRemoveDeadAdjacentEnemyStrings() {
		Coord moveCoord = new Coord( 1, 1 );
		StringLifeAnalyzer analyzer = mock( StringLifeAnalyzer.class );
		when(analyzer.isStringAlive((Board)any(), (Coord)any())).thenReturn( false );
		StringOfStones stones = new StringOfStones();
		stones.add( new Coord( 1, 0 ) );
		stones.add( new Coord( 0, 1 ) );
		stones.add( new Coord( 2, 1 ) );
		stones.add( new Coord( 1, 2 ) );
		stones.add( new Coord( 1, 3 ) );
		when(analyzer.stonesOfString((Coord)any(), (Board)any())).thenReturn(stones);
		SimpleBoard board = SimpleBoard.makeBoard( "wbw." + 
									       "b.bw" +
										   "wbw." +				
										   "wbw." );				
		RemovedDeadStonesBoard consistentBoard = new RemovedDeadStonesBoard( board, analyzer );
		consistentBoard.playStone( Board.Point.WhiteStone, moveCoord );

		verify(analyzer).stonesOfString(moveCoord.up(), board);
		verify(analyzer).stonesOfString(moveCoord.down(), board);
		verify(analyzer).stonesOfString(moveCoord.left(), board);
		verify(analyzer).stonesOfString(moveCoord.right(), board);
		SimpleBoard expectedBoard = SimpleBoard.makeBoard( "w.w." + 
												   ".w.w" +
												   "w.w." +
												   "w.w." );
		assertEquals( "all stones returned by analyzer are removed from board", expectedBoard, board );
	}
}
