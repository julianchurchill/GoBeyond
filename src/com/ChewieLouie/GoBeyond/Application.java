package com.ChewieLouie.GoBeyond;

public class Application {

	public Application() {
		Referee referee = new StrictReferee( new _TestableRules(), new GoBoard() );
//		Referee referee = new StrictReferee( new ChineseRules(), new GoBoard() );
		Player player1 = new GoPlayer( referee, new _TestableStrategy() );
		Player player2 = new GoPlayer( referee, new _TestableStrategy() );
//		Player player1 = new GoPlayer( referee, new RandomStrategy() );
//		Player player2 = new GoPlayer( referee, new RandomStrategy() );
		_TestableGameEndDetector gameEndDetector = new _TestableGameEndDetector();
//		GameEndDetector gameEndDetector = new GoGameEndDetector( 300 );
//		referee.notifyOfMovesPlayed( gameEndDetector );
		Game g = new Game(player1, player2, gameEndDetector);
		g.start();
	}
}
