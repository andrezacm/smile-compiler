package br.ufrn.smile.domain;

public class CustomError {
	private int charPositionInLine;
	private int line;
	private String fileName;
	private String message;
	
	private CustomError(String fileName, String message) {
		this.charPositionInLine = 0;
		this.line = 0;
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
		this.message = "filed association on " + fileName + ": " 
						+ association.getType().getDescription() + " (" 
						+ association.getActor().getType().getDescription() + " " 
						+ association.getActor().getName() + ")";
	}
	
	public void setDependencyError(Dependency dependency) {
		this.message = "filed dependency on " + fileName + ": " 
						+ dependency.getPerspective().getDescription() + " (" 
						+ dependency.getActor().getName() + ") " 
						+ dependency.getActor().getType().getDescription() + " for (" 
						+ dependency.getType().getDescription() + " " 
						+ dependency.getName() + ")";
	}
	
	public void setMissingActorError(Actor actor) {
		this.message = "missing actor definition on " + fileName + ": "
						+ actor.getType().getDescription() + " " 
						+ actor.getName();
	}
	
	public void setFileNameError(String actorName) {
		this.message = "the file " 
						+ fileName + " should have the same name as the actor on it: " 
						+ actorName;
	}
	
	public int getCharPositionInLine() {
		return charPositionInLine;
	}
	
	public int getLine() {
		return line;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public String getMessage() {
		return message;
	}
}
