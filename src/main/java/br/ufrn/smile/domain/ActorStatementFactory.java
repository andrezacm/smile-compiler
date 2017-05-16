package br.ufrn.smile.domain;

import java.io.IOException;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import br.ufrn.smile.SmileLexer;
import br.ufrn.smile.SmileParser;
import br.ufrn.smile.listeners.ActorStatementListener;
import br.ufrn.smile.listeners.ErrorListener;
import br.ufrn.smile.service.VerifyAssociations;
import br.ufrn.smile.service.VerifyExternalRelationships;
import br.ufrn.smile.service.VerifyFileName;

public class ActorStatementFactory {
	private Actor mainActor;
	private String fileName;
	
	public ActorStatementFactory() { }
	
	public void build(String input) throws IOException {
		SmileLexer lexer = new SmileLexer(new ANTLRFileStream(input));
		
		SmileParser parser = new SmileParser(new CommonTokenStream(lexer));
		
		parser.removeErrorListeners();
		parser.addErrorListener(new ErrorListener());
		
		ActorStatementListener actorStatementListener = new ActorStatementListener();
		
		parser.actorStatement().enterRule(actorStatementListener);

		this.mainActor = actorStatementListener.getMainActor();
		
		this.fileName = input.substring(input.lastIndexOf("/")+1, input.lastIndexOf(".smile"));
	}
	
	public void verifyAssociationErrors(HashMap<String, ActorStatementFactory> actorsList) {
		VerifyAssociations.call(mainActor, actorsList, fileName);
	}
	
	public void verifyExternalRelationshipsErrors(HashMap<String, ActorStatementFactory> actorsList) {
		VerifyExternalRelationships.call(mainActor, actorsList, fileName);
	}
	
	public void verifyFileName() {
		VerifyFileName.call(mainActor.getName(), fileName);
	}
	
	public void print() {
		System.out.println("MAIN ACTOR: " + 
			                mainActor.getName() + " " +
			                mainActor.getType().getDescription() +
			                "\n-----------------------------------------------\n" +
			                "ASSOCIATIONS");

		mainActor.getAssociations().forEach(association -> {
			System.out.print(association.getType().getDescription() + " (");
			
			Actor actor = association.getActor();
			System.out.print(actor.getType().getDescription() + " " + actor.getName() + ")\n");
		});
		
		System.out.println("-----------------------------------------------\n" + 
					   	   "EXTERNAL RELATIONSHIPS");
		
		mainActor.getExternalRelationships().getDependers().forEach(depender -> {
			System.out.println( depender.getPerspective().getDescription() + " (" + 
								depender.getActor().getName() + ") " +
								depender.getActor().getType().getDescription() + " for (" +
								depender.getType().getDescription() + " " +
								depender.getName() + ")");
		});
		
		mainActor.getExternalRelationships().getDependees().forEach(dependee -> {
			System.out.println( dependee.getPerspective().getDescription() + " (" + 
								dependee.getActor().getName() + ") " +
								dependee.getActor().getType().getDescription() + " for (" +
								dependee.getType().getDescription() + " " +
								dependee.getName() + ")");
		});
		
		System.out.println();
	}

	public Actor getMainActor() {
		return mainActor;
	}
}
