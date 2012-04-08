package com.ChewieLouie.GoBeyond;

public class Stone {

	public static enum Colour { Black, White };
	
	private Colour colour;

	public Stone( Colour colour) {
		this.colour = colour;
	}

	public Colour colour() {
		return colour;
	}
}
