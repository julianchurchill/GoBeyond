package com.ChewieLouie.GoBeyond;

public class Application {

	public Application() {
		Board board = new SimpleBoard( 19 );
		Referee referee = new StrictReferee( new GoRules( new GoStringLifeAnalyzer() ), board );
		Player player1 = new GoPlayer( referee, new RandomStrategy( new PseudoRandomGenerator( 0 ) ), Move.Colour.Black, board );
		Player player2 = new GoPlayer( referee, new RandomStrategy( new PseudoRandomGenerator( 1 ) ), Move.Colour.White, board );
		Game g = new Game(player1, player2, referee);
		g.start();
	}
}
