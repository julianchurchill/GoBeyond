package com.chewielouie.gobeyond.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.chewielouie.gobeyond.Board;
import com.chewielouie.gobeyond.Board.Point;
import com.chewielouie.gobeyond.SimpleBoard;
import com.chewielouie.gobeyond.util.Coord;

public class SimpleBoardCanvas extends JPanel {

	private static final long serialVersionUID = -5208599275421467295L;
	private int gridSizeInPixels;
	private BoardCoordConverter coordConverter;
	private Board board = new SimpleBoard(9);
	private Graphics graphics;
	private int lastClickedX = -1;
	private int lastClickedY = -1;
	private boolean mouseClickAvailable = false;
	private boolean allowBoardClicks = false;
	private Coord lastMoveCoord = null;

	public SimpleBoardCanvas() {
		super();
		this.gridSizeInPixels = 30;
		coordConverter = new BoardCoordConverter(gridSizeInPixels, new Coord( 0, 0 ) );
		addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	if( allowBoardClicks ) {
	            	lastClickedX = evt.getPoint().x;
	            	lastClickedY = evt.getPoint().y;
	            	mouseClickAvailable = true;
            	}
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
            }
        });
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.graphics = g;
		drawGrid();
		drawBoardContents();
		drawLastMoveHighlight();
		this.graphics = null;
	}

	private void drawGrid() {
		graphics.setColor(Color.black);
		for( int step = 0; step < board.size(); ++step ) {
			Coord c = coordConverter.toScreen( new Coord( step, step ) );
			verticalLine(c);
			horizontalLine(c);
		}
	}

	private void verticalLine(Coord c) {
		graphics.drawLine( c.x() + gridLeft(), gridTop(),
					c.x() + gridLeft(), gridBottom() );
	}

	private void horizontalLine(Coord c) {
		graphics.drawLine( gridLeft(), c.y() + gridTop(),
					gridRight(), c.y() + gridTop() );
	}

	private int gridLeft() {
		return gridOffsetFromEdge();
	}

	private int gridTop() {
		return gridOffsetFromEdge();
	}

	private int gridOffsetFromEdge() {
		return gridSizeInPixels/2;
	}

	private int gridRight() {
		return board.size()*gridSizeInPixels - gridLeft();
	}

	private int gridBottom() {
		return board.size()*gridSizeInPixels - gridTop();
	}

	private void drawBoardContents() {
		for( int y = 0; y < board.size(); ++y ) {
			for( int x = 0; x < board.size(); ++x ) {
				Coord boardCoord = new Coord( x, y );
				Coord screenCoord = coordConverter.toScreen( boardCoord );
				drawStone(screenCoord, board.getContentsOfPoint( boardCoord ));
			}
		}
	}

	private void drawStone(Coord screenCoord, Point colour) {
		setPenColour(colour);
		if( colour != Point.Empty )
			graphics.fillOval(screenCoord.x(), screenCoord.y(), gridSizeInPixels, gridSizeInPixels);
		drawStoneOutline(screenCoord, colour);
	}

	private void drawStoneOutline(Coord screenCoord, Point colour) {
		if( colour == Point.WhiteStone ) {
			graphics.setColor(Color.black);
			graphics.drawOval(screenCoord.x(), screenCoord.y(), gridSizeInPixels, gridSizeInPixels);
		}
	}

	private void drawLastMoveHighlight() {
		if( lastMoveCoord != null ) {
			Coord screenCoord = coordConverter.toScreen( lastMoveCoord  );
			graphics.setColor(Color.red);
			graphics.drawOval(screenCoord.x(), screenCoord.y(), gridSizeInPixels, gridSizeInPixels);
		}
	}

	private void setPenColour(Point point) {
		if( point == Point.BlackStone )
			graphics.setColor( Color.black );
		else if( point == Point.WhiteStone )
			graphics.setColor( Color.white );
	}

	public void redrawWithBoard(Board board) {
		this.board = board.duplicate();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
        		revalidate();
        		repaint();
            }
        });	
	}

	public Coord getLastClickedBoardPoint() {
		Coord c = coordConverter.toBoard( new Coord( lastClickedX, lastClickedY ) );
		mouseClickAvailable = false;
		return c;
	}
	
	public boolean clickAvailable() {
		return mouseClickAvailable;
	}

	public void allowBoardClicks() {
		allowBoardClicks  = true;
	}

	public void lastMove(Coord c) {
		lastMoveCoord = c;
	}

}
