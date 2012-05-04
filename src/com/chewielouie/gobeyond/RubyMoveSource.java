package com.chewielouie.gobeyond;

import java.io.BufferedReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.chewielouie.gobeyond.Move.Colour;

public class RubyMoveSource implements MoveSource {

	private ScriptEngine jruby;
	private ScriptEngineManager scriptEngineManager;
	private MoveSource moveSource;

	public RubyMoveSource(BufferedReader b, ScriptEngineManager s) {
		this.scriptEngineManager = s;
		loadJRubyEngine();
        createMoveSource(b);
    }

	private void loadJRubyEngine() {
		jruby = scriptEngineManager.getEngineByName("jruby");
		if( jruby == null )
			System.err.println("No script engine found for jruby");
	}

	private void createMoveSource(BufferedReader b) {
		try {
			jruby.eval(b);
	        moveSource = (MoveSource) jruby.eval( "MoveSource.new" );
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Move getMove(Colour colour, Board board) {
        return moveSource.getMove(colour, board);
	}

}
