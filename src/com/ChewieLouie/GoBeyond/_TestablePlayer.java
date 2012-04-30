package com.ChewieLouie.GoBeyond;

import java.util.ArrayList;
import java.util.List;

import com.ChewieLouie.GoBeyond.Referee.MoveStatus;

public class _TestablePlayer implements Player {

	public int generateMoveCalledCount = 0;
	public List<Referee.MoveStatus> playMoveReturnQueue = new ArrayList<Referee.MoveStatus>();

	@Override
	public MoveStatus playMove() {
		generateMoveCalledCount++;
		if( playMoveReturnQueue.size() > 0 )
			return playMoveReturnQueue.remove(0);
		return MoveStatus.LegalMove;
	}

}
