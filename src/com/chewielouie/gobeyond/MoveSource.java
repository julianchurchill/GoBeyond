package com.chewielouie.gobeyond;

import com.chewielouie.gobeyond.Move.Colour;

public interface MoveSource {

	public abstract Move getMove(Colour colour, Board board);

}