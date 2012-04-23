package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _StrictRefereeTests {

	private _TestableRules rules;
	private _TestableBoard board;
	private Referee referee;

	@Before
	public void SetUp() {
		rules = new _TestableRules();
		board = new _TestableBoard( 19 );
		referee = new StrictReferee( rules, board );
	}
	
	@Test
	public void RefereeChecksWithRulesWhetherMoveIsLegal() {
		rules.isLegalReturnValue = false;
		Move m = new Move( new Coord( 1, 1 ), null );
		referee.submitMove( m );

		assertTrue( "rules.isLegal() is called", rules.isLegalCalled );
		assertEquals( "move is passed to rules", m, rules.isLegalCalledWithMove );
	}
	
	@Test
	public void UpdateGameHistoryIsPassedToRulesWhenMoveIsCheckedForLegalality() {
		board.duplicateReturn = new GoBoard(5);
		Move m = new Move( new Coord( 1, 1 ), null );
		GameHistory history = new GameHistory();
		history.add( board.duplicate(), m );

		referee.submitMove( m );
		rules.isLegalReturnValue = false;
		referee.submitMove( new Move( new Coord( 2, 3 ), null ) );

		assertEquals( "updated history is passed to rules", history, rules.isLegalHistory );
	}

	@Test
	public void IfMoveIsLegalRefereeReturnsLegalStatus() {
		assertEquals( Referee.MoveStatus.LegalMove, referee.submitMove( new Move( new Coord( 1, 1 ), null ) ) );
	}

	@Test
	public void IfMoveIsIllegalRefereeReturnsIllegalStatus() {
		rules.isLegalReturnValue = false;
		assertEquals( Referee.MoveStatus.IllegalMove, referee.submitMove( new Move( new Coord( 1, 1 ), null ) ) );
	}

	@Test
	public void IfMoveIsLegalRefereeUpdatesTheBoard() {
		referee.submitMove( new Move( new Coord( 1, 2 ), Move.Colour.White ) );
		assertEquals( true, board.playStoneCalled );
		assertEquals( Board.Point.WhiteStone, board.playStonePoint );
		assertEquals( 1, board.playStoneX );
		assertEquals( 2, board.playStoneY );
	}

	@Test
	public void IfMoveIsIllegalRefereeDoesNotUpdateTheBoard() {
		rules.isLegalReturnValue = false;
		referee.submitMove( new Move( new Coord( 1, 2 ), Move.Colour.White ) );
		assertEquals( false, board.playStoneCalled );
	}

	@Test
	public void UsesRulesToDetermineIfGameHasEnded() {
		assertEquals( "rules.endDetected() is not called on creation", 0, rules.endDetectedCalledCount );
		referee.endDetected();
		assertEquals( "calling endDetected() calls rules", 1, rules.endDetectedCalledCount );
		rules.endDetectedReturnValue = true;
		assertEquals( "referee returns value given by rules - true", true, referee.endDetected() );
		rules.endDetectedReturnValue = false;
		assertEquals( "referee returns value given by rules - false", false, referee.endDetected() );
	}
	
	@Test
	public void UpdateGameHistoryIsPassedToRulesWhenGameEndIsBeingChecked() {
		board.duplicateReturn = new GoBoard(5);
		Move m = new Move( new Coord( 1, 1 ), null );
		GameHistory history = new GameHistory();
		history.add( board.duplicate(), m );

		referee.submitMove( m );
		referee.endDetected();
	
		assertEquals( "updated history is passed to rules", history, rules.endDetectedHistory );
	}
	
}
