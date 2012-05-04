package com.ChewieLouie.GoBeyond;

import java.util.HashSet;
import java.util.Set;

public class GameBrowser {

	private GameHistory history;
	private int currentPosition = 0;
	private Set<GameBrowserObserver> observers = new HashSet<GameBrowserObserver>();

	public GameBrowser(GameHistory history) {
		this.history = history;
	}

	public Board currentBoard() {
		return history.boardNumber(currentPosition);
	}

	public Move currentMove() {
		return history.moveNumber(currentPosition);
	}

	public void next() {
		currentPosition++;
		if( currentPosition == history.size() )
			currentPosition = lastPosition();
		else
			notifyObservers();
	}

	private int lastPosition() {
		return history.size() - 1;
	}

	private void notifyObservers() {
		for( GameBrowserObserver o : observers )
			o.browserPositionChanged();
	}

	public void previous() {
		currentPosition--;
		if( currentPosition < 0 )
			currentPosition = 0;
		else
			notifyObservers();
	}

	public void goToLastPosition() {
		if( currentPosition != lastPosition() ) {
			currentPosition = lastPosition();
			notifyObservers();
		}
	}

	public void addObserver(GameBrowserObserver observer) {
		this.observers.add( observer ); 
	}

}
