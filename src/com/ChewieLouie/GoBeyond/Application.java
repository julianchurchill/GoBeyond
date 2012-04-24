package com.ChewieLouie.GoBeyond;

public class Application {

	public Application() {
		Referee referee = new StrictReferee( new GoRules( new GoStringLifeAnalyzer() ), new SimpleBoard( 19 ) );
		Player player1 = new GoPlayer( referee, new RandomStrategy( new PseudoRandomGenerator( 0 ) ) );
		Player player2 = new GoPlayer( referee, new RandomStrategy( new PseudoRandomGenerator( 1 ) ) );
		Game g = new Game(player1, player2, referee);
		g.start();
	}
}
