package br.ufrn.smile.domain;

import java.util.StringJoiner;

public class CustomError {
	private Actor actor;
	private int charPositionInLine;
	private int line;
	private String message;
	
	public CustomError(Actor actor, int charPositionInLine, int line, String message) {
		this.actor = actor;
		this.charPositionInLine = charPositionInLine;
		this.line = line;
		this.message = message;
	}
	
	public CustomError(int charPositionInLine, int line, String message) {
		this.charPositionInLine = charPositionInLine;
		this.line = line;
		this.message = message;
	}
	
	public CustomError(String message) {
		this.charPositionInLine = 0;
		this.line = 0;
		this.message = message;
	}
	
	public CustomError(Actor mainActor, Association association) {
		this.actor = mainActor;
		this.charPositionInLine = 0;
		this.line = 0;
		
		StringBuilder msg = new StringBuilder("error on " + mainActor.getName() + 
					 						  " association: " + association.getType().getDescription() + " (");
		
		StringJoiner sj = new StringJoiner(", ");
		Actor actor = association.getActor();
		
		sj.add(actor.getType().getDescription() + " " + actor.getName());
		
		msg.append(sj);
		msg.append(")");
		
		this.message = msg.toString();
	}

	public Actor getActor() {
		return actor;
	}
	
	public int getCharPositionInLine() {
		return charPositionInLine;
	}
	
	public int getLine() {
		return line;
	}
	
	public String getMessage() {
		return message;
	}
}
