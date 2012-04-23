package com.ChewieLouie.GoBeyond;

public class Application {

	public Application() {
		Referee referee = new StrictReferee( new GoRules( new GoStringLifeAnalyzer() ), new GoBoard( 19 ) );
		Player player1 = new GoPlayer( referee, new _TestableStrategy() );
		Player player2 = new GoPlayer( referee, new _TestableStrategy() );
//		Player player1 = new GoPlayer( referee, new RandomStrategy() );
//		Player player2 = new GoPlayer( referee, new RandomStrategy() );
		Game g = new Game(player1, player2, referee);
		g.start();
	}
}
