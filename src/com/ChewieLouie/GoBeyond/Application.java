package com.ChewieLouie.GoBeyond;

public class Application {

	public Application() {
		Rules rules = new GoRules( new GoStringLifeAnalyzer() );
		Referee referee = new StrictReferee( rules, new SimpleBoard( 19 ) );
		Player player1 = new RandomPlayer( referee, Move.Colour.Black, new PseudoRandomGenerator( 0 ), rules );
		Player player2 = new RandomPlayer( referee, Move.Colour.White, new PseudoRandomGenerator( 0 ), rules );
		Game g = new Game(player1, player2, referee);
		g.start();
	}
}
