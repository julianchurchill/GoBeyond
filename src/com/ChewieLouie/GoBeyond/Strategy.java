package com.ChewieLouie.GoBeyond;

import com.ChewieLouie.GoBeyond.Move.Colour;

public interface Strategy {

	public abstract Move generateMove(Board board, Colour colour);

}