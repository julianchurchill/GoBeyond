package com.ChewieLouie.GoBeyond;

public class Application {

	public Application() {
		Board board = new SimpleBoard( 19 );
		Rules rules = new GoRules( new GoStringLifeAnalyzer() );
		Referee referee = new StrictReferee( rules, board );
		Player player1 = new GoPlayer( referee, new RandomStrategy( new PseudoRandomGenerator( 0 ), rules ), Move.Colour.Black, board );
		Player player2 = new GoPlayer( referee, new RandomStrategy( new PseudoRandomGenerator( 1 ), rules ), Move.Colour.White, board );
		Game g = new Game(player1, player2, referee);
		g.start();
	}
}
