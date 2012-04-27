package com.ChewieLouie.GoBeyond;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class _StrictRefereeTests {

	private _TestableRules rules;
	private _TestableBoard board;
	private StrictReferee referee;

	@Before
	public void SetUp() {
		rules = new _TestableRules();
		board = new _TestableBoard( 19 );
		referee = new StrictReferee( rules, board );
	}
	
	@Test
	public void boardReturnsTheBoardThatTheRefereeIsConstructedWith() {
		assertTrue( "board constructed with is the same as the one returned by board()", referee.board() == board );
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
		board.duplicateReturn = new SimpleBoard(5);
		Move m = new Move( new Coord( 1, 1 ), null );
		GameHistory history = new GameHistory();
		history.add( board.duplicate(), m );

		referee.submitMove( m );
		rules.isLegalReturnValue = false;
		referee.submitMove( new Move( new Coord( 2, 3 ), null ) );

		assertEquals( "updated history is passed to rules", history, rules.isLegalCalledWithHistory );
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
	public void IfMoveIsPassMoveRefereeDoesNotUpdateTheBoard() {
		referee.submitMove( Move.passMove( Move.Colour.White ) );
		assertFalse( "pass moves do not cause the board to be updated", board.playStoneCalled );
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
		board.duplicateReturn = new SimpleBoard(5);
		Move m = new Move( new Coord( 1, 1 ), null );
		GameHistory history = new GameHistory();
		history.add( board.duplicate(), m );

		referee.submitMove( m );
		referee.endDetected();
	
		assertEquals( "updated history is passed to rules", history, rules.endDetectedHistory );
	}
	
	@Test
	public void DeadStonesAreRemovedFromBoardAfterLegalSubmittedMove() {
		Board newBoard = SimpleBoard.makeBoard("bw." +
										   "..." +
						  				   "..." );
		referee = new StrictReferee(rules, newBoard);

		referee.submitMove( new Move( new Coord( 0, 1 ), Move.Colour.White ) );

		Board expectedBoard = SimpleBoard.makeBoard(".w." +
												"w.." +
											    "..." );
		assertEquals( "dead stones have been removed from board", expectedBoard, newBoard );
	}
	
	@Test
	public void IsLegalUsesRules() {
		Move move = new Move( new Coord( 1, 2 ), Move.Colour.Black );
		rules.isLegalReturnValue = true;

		boolean result = referee.isLegal( move, board );

		assertTrue( "referee delegates isLegal calls to rules", rules.isLegalCalled );
		assertEquals( "referee passes move straight through to rules for isLegal calls", move, rules.isLegalCalledWithMove );
		assertTrue( "referee passes board straight through to rules for isLegal calls", board == rules.isLegalCalledWithBoard );
		assertEquals( "referee returns value from rules.isLegal()", rules.isLegalReturnValue, result );
	}
	
	@Test
	public void RefereeObserverIsNotifiedWhenMovesAreAccepted() {
		_TestableRefereeMoveObserver observer1 = new _TestableRefereeMoveObserver();
		_TestableRefereeMoveObserver observer2 = new _TestableRefereeMoveObserver();
		referee.addObserver( observer1 );
		referee.addObserver( observer2 );
		
		Move move = new Move( new Coord( 1, 2 ), Move.Colour.Black );
		referee.submitMove( move );

		assertTrue( "when referee accepts a move observers are notified", observer1.moveAcceptedCalled );
		assertTrue( "when referee accepts a move observers are notified", observer2.moveAcceptedCalled );
		assertEquals( "when referee accepts a move observers are notified with move", move, observer1.moveAcceptedCalledWithMove );
		assertEquals( "when referee accepts a move observers are notified with move", move, observer2.moveAcceptedCalledWithMove );
		assertTrue( "when referee accepts a move observers are notified with new board", board == observer1.moveAcceptedCalledWithBoard );
		assertTrue( "when referee accepts a move observers are notified with new board", board == observer2.moveAcceptedCalledWithBoard );
	}
	
	@Test
	public void GameHistoryMatchesWhatHasBeenPlayed() {
		board.duplicateReturn = new _TestableBoard( 19 );
		Move move1 = new Move( new Coord( 1, 2 ), Move.Colour.Black ); 
		Move move2 = new Move( new Coord( 0, 3 ), Move.Colour.White ); 
		referee.submitMove( move1 );
		referee.submitMove( move2 );

		GameHistory h = referee.gameHistory();

		assertEquals( "history has size equal to the number of submitted moves", 2, h.size() );
		assertTrue( "board 1 matches the first board", board.duplicateReturn == h.boardNumber( 0 ) );
		assertTrue( "board 2 matches the second board", board.duplicateReturn == h.boardNumber( 1 ) );
		assertEquals( "move 1 matches the first move played", move1, h.moveNumber( 0 ) );
		assertEquals( "move 2 matches the second move played", move2, h.moveNumber( 1 ) );
	}
}
