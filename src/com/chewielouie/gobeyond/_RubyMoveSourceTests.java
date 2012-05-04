package com.chewielouie.gobeyond;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chewielouie.gobeyond.util.Coord;

public class _RubyMoveSourceTests {

	@Test
	public void UsesJRubyScriptingEngine() {
		RubyMoveSource m = new RubyMoveSource("ruby/testMoveSource.rb");

		Move move = m.getMove(Move.Colour.Black, new SimpleBoard(9));
		assertEquals( "Ruby move source returns expected move", move, new Move( new Coord( 1, 2 ), Move.Colour.Black ) );
	}

}
