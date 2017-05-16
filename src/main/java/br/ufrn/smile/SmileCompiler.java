package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;
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
	
	public SmileCompiler() {
		this.actors = new HashMap<String, ActorStatementFactory>();
	}
	
	public void buildActor(InputStream input) {
		try {
			ActorStatementFactory actorStatement = new ActorStatementFactory();
			actorStatement.build(input);
			actors.put(actorStatement.getMainActor().getName(), actorStatement);
		} catch (IOException e) {
			ErrorHandler.getErrorHandler().addError(new CustomError(e.getMessage()));
		}
	}
	
	public void verifyErrors() {
		actors.values().forEach(actor -> {
			HashMap<String, ActorStatementFactory> copy = this.getActorsCopy(actor);
			
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
