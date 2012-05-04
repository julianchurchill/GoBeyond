package com.ChewieLouie.GoBeyond;

import java.util.Random;

public class PseudoRandomGenerator implements RandomGenerator {

	private Random rand;

	public PseudoRandomGenerator(int i) {
		rand = new Random();
		rand.setSeed( i );
	}

	@Override
	public int generate(int min, int max) {
		return (int)(min + ((max+1)-min) * rand.nextDouble());
	}

}
