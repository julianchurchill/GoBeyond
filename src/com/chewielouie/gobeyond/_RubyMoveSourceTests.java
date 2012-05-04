package com.chewielouie.gobeyond;

import static org.mockito.Mockito.*;

import java.io.BufferedReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

public class _RubyMoveSourceTests {
	
	@Test
	public void UsesJRubyScriptingEngineOnConstruction() {
		ScriptEngineManager scriptEngineManager = mock(ScriptEngineManager.class);
		ScriptEngine scriptEngine = mock( ScriptEngine.class );
		when(scriptEngineManager.getEngineByName("jruby")).thenReturn( scriptEngine );
		BufferedReader bufferedReader = mock(BufferedReader.class);

		new RubyMoveSource( bufferedReader, scriptEngineManager);

		verify(scriptEngineManager).getEngineByName("jruby");
		try {
			verify(scriptEngine).eval(bufferedReader);
			verify(scriptEngine).eval("MoveSource.new");
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

}
