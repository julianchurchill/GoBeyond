package com.ChewieLouie.GoBeyond.ui;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.ChewieLouie.GoBeyond.Move;
import com.ChewieLouie.GoBeyond.SimpleBoard;
import com.ChewieLouie.GoBeyond.util.Coord;

public class _GUIMoveSourceTests {

	@Test
	public void getMoveReturnsMoveBasedOnLastClickedPointOnBoardWidget() {
		BoardWidget boardWidget = mock( BoardWidget.class );
		when( boardWidget.clickAvailable() ).thenReturn(true);
		when( boardWidget.removeLastClickedBoardPoint() ).thenReturn( new Coord( 123, 543 ) );
		GUIMoveSource g = new GUIMoveSource( boardWidget );

		assertEquals( "last clicked point is used as next move",
				new Coord( 123, 543 ), g.getMove( Move.Colour.Black, new SimpleBoard(9) ).coord() );
	}

	@Test
	public void enqueingAPassReturnsAPassOnNextGetMoveRequest() {
		GUIMoveSource g = new GUIMoveSource( mock( BoardWidget.class ) );
		g.enqueuePass();
		assertEquals( "a pass is returned after it has been enqueued", 
				Move.passMove(Move.Colour.Black), g.getMove(Move.Colour.Black, new SimpleBoard(9)) );
	}

}
