package br.ufrn.smile.domain;

public class CustomError {
	private String fileName;
	private String message;
	private Position position;
	
	private CustomError(String fileName, String message) {
		this.fileName = fileName;
		this.message = message;
	}
	
	public static CustomError CustomErrorFromFileName(String fileName) {
		return new CustomError(fileName + ".smile", "");
	}
	
	public static CustomError CustomErrorFromMessage(String message) {
		return new CustomError("", message);
	}
	
	public void setAssociationError(Association association) {
		this.position = association.getPosition();
		
		this.message = "filed association on " + fileName + ": " 
						+ association.getType().getDescription() + " (" 
						+ association.getActor().getType().getDescription() + " " 
						+ association.getActor().getName() + ")";
		
		this.setPositionInMessage();
	}
	
	public void setDependencyError(Dependency dependency) {
		this.position = dependency.getPosition();
		
		this.message = "filed dependency on " + fileName + ": " 
						+ dependency.getPerspective().getDescription() + " (" 
						+ dependency.getActor().getName() + ") " 
						+ dependency.getActor().getType().getDescription() + " for (" 
						+ dependency.getType().getDescription() + " " 
						+ dependency.getName() + ")";
		
		this.setPositionInMessage();
	}
	
	public void setMissingActorError(Actor actor) {
		this.position = actor.getPosition();
		
		this.message = "missing actor definition on " + fileName + ": "
						+ actor.getType().getDescription() + " " 
						+ actor.getName();
		
		this.setPositionInMessage();
	}
	
	public void setFileNameError(String actorName) {
		this.message = "the file " 
						+ fileName + " should have the same name as the actor on it: " 
						+ actorName;
	}
	
	private void setPositionInMessage() {
		StringBuilder sb = new StringBuilder(message);
		sb.append(" at line " + position.getStartInLine() 
					+ ":" + position.getStartCharPosition() 
					+ " till line " + position.getStopInLine() 
					+ ":" + position.getStopCharPosition());
		
		this.message = sb.toString();
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getMessage() {
		return message;
	}

	public Position getPosition() {
		return position;
	}
}
