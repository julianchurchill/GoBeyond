package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class _RemovedDeadStonesBoardTests {

	@Test
	public void ShouldRemoveSingleStoneDeadStringsAfterMovePlayedOnBoard() {
		GoBoard board = GoBoard.makeBoard( "wbw" + 
									       "b.b" +
									       "wbw" );				
		_TestableStringLifeAnalyzer analyzer = new _TestableStringLifeAnalyzer();
		analyzer.stonesOfStringReturn = new ArrayList<Coord>();
		analyzer.stonesOfStringReturn.add( new Coord( 1, 0 ) );
		analyzer.stonesOfStringReturn.add( new Coord( 0, 1 ) );
		analyzer.stonesOfStringReturn.add( new Coord( 2, 1 ) );
		analyzer.stonesOfStringReturn.add( new Coord( 1, 2 ) );
		RemovedDeadStonesBoard consistentBoard = new RemovedDeadStonesBoard( board, analyzer );
		consistentBoard.playStone( Board.Point.WhiteStone, new Coord( 1, 1 ) );

		GoBoard expectedBoard = GoBoard.makeBoard( "w.w" + 
												   ".w." +
			    								   "w.w" );
		assertEquals( "single stone dead strings are removed and live ones remain", expectedBoard, board );
	}

	@Test
	public void playStoneUsesAnalyzerToFindStringLifeOfAdjacentEnemyStrings() {
		Coord moveCoord = new Coord( 1, 1 );
		_TestableStringLifeAnalyzer analyzer = new _TestableStringLifeAnalyzer();
		GoBoard board = GoBoard.makeBoard( "wbw" + 
			       "b.b" +
			       "wbw" );				
		RemovedDeadStonesBoard consistentBoard = new RemovedDeadStonesBoard( board, analyzer );
		consistentBoard.playStone( Board.Point.WhiteStone, moveCoord );
		
		assertTrue( "analyzer is used to check string life", analyzer.isStringAliveCalled );
		assertTrue( "string above move is checked", find( moveCoord.up(), analyzer.isStringAliveCoordAll ) );
		assertTrue( "string below move is checked", find( moveCoord.down(), analyzer.isStringAliveCoordAll ) );
		assertTrue( "string right of move is checked", find( moveCoord.right(), analyzer.isStringAliveCoordAll ) );
		assertTrue( "string left of move is checked", find( moveCoord.left(), analyzer.isStringAliveCoordAll ) );
	}

	private boolean find(Coord coord, List<Coord> coords) {
		for(Coord c: coords )
			if( c.equals( coord ) )
				return true;
		return false;
	}

	@Test
	public void playStoneUsesAnalyzerToRemoveDeadAdjacentEnemyStrings() {
		Coord moveCoord = new Coord( 1, 1 );
		_TestableStringLifeAnalyzer analyzer = new _TestableStringLifeAnalyzer();
		analyzer.stonesOfStringReturn = new ArrayList<Coord>();
		analyzer.stonesOfStringReturn.add( new Coord( 1, 0 ) );
		analyzer.stonesOfStringReturn.add( new Coord( 0, 1 ) );
		analyzer.stonesOfStringReturn.add( new Coord( 2, 1 ) );
		analyzer.stonesOfStringReturn.add( new Coord( 1, 2 ) );
		analyzer.stonesOfStringReturn.add( new Coord( 1, 3 ) );
		GoBoard board = GoBoard.makeBoard( "wbw." + 
									       "b.bw" +
										   "wbw." +				
										   "wbw." );				
		RemovedDeadStonesBoard consistentBoard = new RemovedDeadStonesBoard( board, analyzer );
		consistentBoard.playStone( Board.Point.WhiteStone, moveCoord );

		assertTrue( "analyzer is used to find all stones in string", analyzer.stonesOfStringCalled );
		assertTrue( "string above move is checked", find( moveCoord.up(), analyzer.stonesOfStringCoordAll ) );
		assertTrue( "string below move is checked", find( moveCoord.down(), analyzer.stonesOfStringCoordAll ) );
		assertTrue( "string right of move is checked", find( moveCoord.right(), analyzer.stonesOfStringCoordAll ) );
		assertTrue( "string left of move is checked", find( moveCoord.left(), analyzer.stonesOfStringCoordAll ) );
		GoBoard expectedBoard = GoBoard.makeBoard( "w.w." + 
												   ".w.w" +
												   "w.w." +
												   "w.w." );
		assertEquals( "all stones returned by analyzer are removed from board", expectedBoard, board );
	}
}
