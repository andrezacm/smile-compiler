package br.ufrn.smile.domain;

public class CustomError {
	private Actor actor;
	private int charPositionInLine;
	private int line;
	private String message;
	
	public CustomError() {
		this.charPositionInLine = 0;
		this.line = 0;
		this.message = "";
	}
	
	public CustomError(Actor actor) {
		this.actor = actor;
		this.charPositionInLine = 0;
		this.line = 0;
		this.message = "";
	}
	
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
	
	public void setAssociationError(Association association) {
		StringBuilder msg = new StringBuilder("error on " + actor.getName() + 
				  " association: " + association.getType().getDescription() + " (");

		Actor actor = association.getActor();
		
		msg.append(actor.getType().getDescription() + " " + actor.getName());
		msg.append(")");
		
		this.message = msg.toString();
	}
	
	public void setDependencyError(Dependency dependency) {
		this.message = dependency.getPerspective().getDescription() + " (" + 
					   dependency.getActor().getName() + ") " +
					   dependency.getActor().getType().getDescription() + " for (" +
					   dependency.getType().getDescription() + " " +
					   dependency.getName() + ")";
	}
	
	public void setMissingActorError() {
		this.message = "missing actor definition for " + actor.getName() + 
					   " " + actor.getType();
	}
	
	public void setFileNameError(String actorName, String fileName) {
		this.message = "the file " + fileName + 
					   ".smile should have the same name as the actor on it: " + actorName;
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
