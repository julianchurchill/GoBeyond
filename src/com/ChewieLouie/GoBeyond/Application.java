package com.ChewieLouie.GoBeyond;

public class Application {

	public Application() {
		Board board = new GoBoard( 19 );
		Rules rules = new GoRules( board );
		Referee referee = new StrictReferee( rules, board, new GoGameEndDetector( 300, rules ) );
//		Referee referee = new StrictReferee( new ChineseRules(), new GoBoard() );
		Player player1 = new GoPlayer( referee, new _TestableStrategy() );
		Player player2 = new GoPlayer( referee, new _TestableStrategy() );
//		Player player1 = new GoPlayer( referee, new RandomStrategy() );
//		Player player2 = new GoPlayer( referee, new RandomStrategy() );
		Game g = new Game(player1, player2, referee);
		g.start();
	}
}
