package br.ufrn.smile.domain;

public class Position {
	private int startCharPosition;
	private int startInLine;
	private int stopCharPosition;
	private int stopInLine;
	
	public Position(int startCharPosition, int startInLine, int stopCharPosition, int stopInLine) {
		this.startCharPosition = startCharPosition;
		this.startInLine = startInLine;
		this.stopCharPosition = stopCharPosition;
		this.stopInLine = stopInLine;
	}

	public int getStartCharPosition() {
		return startCharPosition;
	}

	public int getStartInLine() {
		return startInLine;
	}

	public int getStopCharPosition() {
		return stopCharPosition;
	}

	public int getStopInLine() {
		return stopInLine;
	}
}
