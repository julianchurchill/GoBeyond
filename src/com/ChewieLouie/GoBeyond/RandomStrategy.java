package com.ChewieLouie.GoBeyond;

public class RandomStrategy implements Strategy {

	private RandomGenerator randGenerator;

	public RandomStrategy(RandomGenerator rand) {
		this.randGenerator = rand;
	}

	@Override
	public Coord generateMove(SimpleBoard simpleBoard) {
		return new Coord( randGenerator.generate(0, simpleBoard.size - 1),
						  randGenerator.generate(0, simpleBoard.size - 1) );
	}

}
