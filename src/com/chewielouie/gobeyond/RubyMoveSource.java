package com.chewielouie.gobeyond;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.chewielouie.gobeyond.Move.Colour;

public class RubyMoveSource implements MoveSource {

	private ScriptEngine jruby;

	public RubyMoveSource(String script) {
		loadJRubyEngine();
        readScript(script);
    }

	private void loadJRubyEngine() {
		jruby = new ScriptEngineManager().getEngineByName("jruby");
		if( jruby == null )
			System.err.println("No script engine found for jruby");
	}

	private void readScript(String script) {
		try {
			jruby.eval(new BufferedReader(new FileReader(script)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Move getMove(Colour colour, Board board) {
        try {
	        MoveSource m = (MoveSource) jruby.eval( "MoveSource.new" );
	        return m.getMove(colour, board);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
        return null;
	}

}
