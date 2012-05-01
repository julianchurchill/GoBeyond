package com.ChewieLouie.GoBeyond;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ChewieLouie.GoBeyond.util.Coord;

public class _StrictRefereeTests {

	private Rules rules;
	private Board board;
	private StrictReferee referee;

	@Before
	public void SetUp() {
		rules = mock( Rules.class );
		when(rules.isLegal((Move)any(), (Board)any(), (GameHistory)any())).thenReturn( true );
		board = mock( SimpleBoard.class );
		when( board.duplicate()).thenReturn( new SimpleBoard(5) );
		referee = new StrictReferee( rules, board );
	}
	
	@Test
	public void boardReturnsTheBoardThatTheRefereeIsConstructedWith() {
		assertTrue( "board constructed with is the same as the one returned by board()", referee.board() == board );
	}

	@Test
	public void RefereeChecksWithRulesWhetherMoveIsLegal() {
		when(rules.isLegal((Move)any(), (Board)any(), (GameHistory)any())).thenReturn( false );
		Move m = new Move( new Coord( 1, 1 ), null );
		referee.submitMove( m );

		verify(rules).isLegal(eq(m), (Board)any(), (GameHistory)any());
	}
	
	@Test
	public void UpdateGameHistoryIsPassedToRulesWhenMoveIsCheckedForLegalality() {
		Move m = new Move( new Coord( 1, 1 ), null );
		GameHistory history = new GameHistory();
		history.add( board.duplicate(), m );

		referee.submitMove( m );
		when(rules.isLegal((Move)any(), (Board)any(), (GameHistory)any())).thenReturn( false );
		referee.submitMove( new Move( new Coord( 2, 3 ), null ) );

		verify(rules, atLeast(1)).isLegal((Move)any(), (Board)any(), eq(history));
	}

	@Test
	public void IfMoveIsLegalRefereeReturnsLegalStatus() {
		assertEquals( Referee.MoveStatus.LegalMove, referee.submitMove( new Move( new Coord( 1, 1 ), null ) ) );
	}

	@Test
	public void IfMoveIsIllegalRefereeReturnsIllegalStatus() {
		when(rules.isLegal((Move)any(), (Board)any(), (GameHistory)any())).thenReturn( false );
		assertEquals( Referee.MoveStatus.IllegalMove, referee.submitMove( new Move( new Coord( 1, 1 ), null ) ) );
	}

	@Test
	public void IfMoveIsLegalRefereeUpdatesTheBoard() {
		referee.submitMove( new Move( new Coord( 1, 2 ), Move.Colour.White ) );
		verify(board).playStone(Board.Point.WhiteStone, new Coord( 1, 2 ) );
	}

	@Test
	public void IfMoveIsPassMoveRefereeDoesNotUpdateTheBoard() {
		referee.submitMove( Move.passMove( Move.Colour.White ) );
		verify(board, never()).playStone((Board.Point)any(), (Coord)any());
	}

	@Test
	public void IfMoveIsIllegalRefereeDoesNotUpdateTheBoard() {
		when(rules.isLegal((Move)any(), (Board)any(), (GameHistory)any())).thenReturn( false );
		referee.submitMove( new Move( new Coord( 1, 2 ), Move.Colour.White ) );
		verify(board, never()).playStone((Board.Point)any(), (Coord)any());
	}

	@Test
	public void UsesRulesToDetermineIfGameHasEnded() {
		verify(rules, never()).endDetected((GameHistory)any());
		referee.endDetected();
		verify(rules, times(1)).endDetected((GameHistory)any());
		when(rules.endDetected((GameHistory)any())).thenReturn( true );
		assertEquals( "referee returns value given by rules - true", true, referee.endDetected() );
		when(rules.endDetected((GameHistory)any())).thenReturn( false );
		assertEquals( "referee returns value given by rules - false", false, referee.endDetected() );
	}
	
	@Test
	public void UpdateGameHistoryIsPassedToRulesWhenGameEndIsBeingChecked() {
		Move m = new Move( new Coord( 1, 1 ), null );
		GameHistory history = new GameHistory();
		history.add( board.duplicate(), m );

		referee.submitMove( m );
		referee.endDetected();
	
		verify(rules).endDetected(history);
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

		assertTrue( "referee forwards isLegal() result from rules", referee.isLegal( move, board ) );

		verify(rules).isLegal(eq(move), eq(board), (GameHistory)any());
	}
	
	@Test
	public void RefereeObserverIsNotifiedWhenMovesAreAccepted() {
		RefereeMoveObserver observer1 = mock(RefereeMoveObserver.class);
		RefereeMoveObserver observer2 = mock(RefereeMoveObserver.class);
		referee.addObserver( observer1 );
		referee.addObserver( observer2 );
		
		Move move = new Move( new Coord( 1, 2 ), Move.Colour.Black );
		referee.submitMove( move );

		verify(observer1).moveAccepted(move, board);
	}
	
	@Test
	public void GameHistoryMatchesWhatHasBeenPlayed() {
		SimpleBoard duplicateBoard = new SimpleBoard(9);
		when(board.duplicate()).thenReturn(duplicateBoard);
		Move move1 = new Move( new Coord( 1, 2 ), Move.Colour.Black ); 
		Move move2 = new Move( new Coord( 0, 3 ), Move.Colour.White ); 
		referee.submitMove( move1 );
		referee.submitMove( move2 );

		GameHistory h = referee.gameHistory();

		assertEquals( "history has size equal to the number of submitted moves", 2, h.size() );

		assertTrue( "board 1 matches the first board", duplicateBoard == h.boardNumber( 0 ) );
		assertTrue( "board 2 matches the second board", duplicateBoard == h.boardNumber( 1 ) );
		assertEquals( "move 1 matches the first move played", move1, h.moveNumber( 0 ) );
		assertEquals( "move 2 matches the second move played", move2, h.moveNumber( 1 ) );
	}
}
