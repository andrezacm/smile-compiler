package br.ufrn.smile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;
import br.ufrn.smile.service.BuildXML;
import br.ufrn.smile.service.ErrorHandler;

public class SmileCompiler {
	private HashMap<String, ActorStatementFactory> actors;
	private List<File> filesInFolder;
	
	public SmileCompiler(String path) throws IOException {
		this.actors = new HashMap<String, ActorStatementFactory>();
		
		this.filesInFolder = Files.walk(Paths.get(path))
						          .filter(Files::isRegularFile)
						          .map(Path::toFile)
						          .collect(Collectors.toList());
	}
	
	public void compile() {
		filesInFolder.forEach(file -> {
			this.buildActor(file.getAbsolutePath());
		});
		
		this.verifyErrors();
	}
	
	private void buildActor(String input) {
		try {
			ActorStatementFactory actorStatement = new ActorStatementFactory();
			actorStatement.build(input);
			actors.put(actorStatement.getMainActor().getName(), actorStatement);
		} catch (IOException e) {
			ErrorHandler.getErrorHandler().addError(new CustomError(e.getMessage()));
		}
	}
	
	private void verifyErrors() {
		actors.values().forEach(actor -> {
			HashMap<String, ActorStatementFactory> copy = this.getActorsCopy(actor);
			
			actor.verifyFileName();
			actor.verifyAssociationErrors(copy);
			actor.verifyExternalRelationshipsErrors(copy);
		});
	}
	
	public int getNumberOfActors() {
		return actors.size();
	}
	
	public int getNumberOfAssociations() {
		return actors.values()
				 	 .stream()
				 	 .mapToInt(actor -> actor.getMainActor().getNumberOfAssociations())
				 	 .sum();
	}
	
	public List<Actor> getActors() {
		return actors.values()
					 .stream()
					 .map(actor -> actor.getMainActor())
					 .collect(Collectors.toList());
	}
	
	public void print() {
		actors.values().forEach(actor -> actor.print());
		
		System.out.println("-----------------------------------------------\n" + 
	   	   		   "ERRORS");

		ErrorHandler.getErrorHandler().getErrors().forEach(error -> {
			System.out.println(error.getMessage());
		});
	}
	
	public void toXML() {
		BuildXML.call(this.getActors());
	}
	
	private HashMap<String, ActorStatementFactory> getActorsCopy(ActorStatementFactory actor) {
		HashMap<String, ActorStatementFactory> copy = (HashMap<String, ActorStatementFactory>) actors.clone();
		copy.remove(actor.getMainActor().getName());
		return copy;
	}
}
