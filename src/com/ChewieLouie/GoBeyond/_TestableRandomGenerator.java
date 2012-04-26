package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

public class _TestableRandomGenerator implements RandomGenerator {

	public boolean generateCalled = false;
	public int generateMin = 0;
	public int generateMax = 0;
	private List<Integer> randomNumbers = new ArrayList<Integer>();

	@Override
	public int generate( int min, int max ) {
		generateCalled = true;
		generateMin = min;
		generateMax = max;
		return randomNumbers.remove( 0 );
	}

	public void primeWith(int i) {
		randomNumbers .add( i );
	}

	public void clearPrimedNumbers() {
		randomNumbers.clear();
	}

}
