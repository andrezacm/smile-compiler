package br.ufrn.smile.domain;

import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.thoughtworks.xstream.XStream;

import br.ufrn.smile.SmileLexer;
import br.ufrn.smile.SmileParser;
import br.ufrn.smile.listeners.ActorStatementListener;
import br.ufrn.smile.listeners.ErrorListener;

public class ActorStatementFactory {
	private Actor mainActor;
	
	public ActorStatementFactory() { }
	
	public void build(InputStream input) throws IOException {
		SmileLexer lexer = new SmileLexer(new ANTLRInputStream(input));
		
		SmileParser parser = new SmileParser(new CommonTokenStream(lexer));
		
		parser.removeErrorListeners();
		parser.addErrorListener(new ErrorListener());
		
		ActorStatementListener actorStatementListener = new ActorStatementListener();
		
		parser.actorStatement().enterRule(actorStatementListener);

		this.mainActor = actorStatementListener.getMainActor();
	}
	
	public void verifyAssociationErrors() {
		mainActor.getActorAssociatons().forEach(association -> {
			try {
				if (!association.isValid(mainActor)) {
					ErrorHandler.getErrorHandler().addError(new CustomError(mainActor, association));
				}
			} catch (Exception e1) {
				ErrorHandler.getErrorHandler().addError(new CustomError(e1.getMessage()));
			}
		});
	}
	
	public void print() {
		System.out.println("MAIN ACTOR: " + 
			                mainActor.getName() + " " +
			                mainActor.getType().getDescription() +
			                "\n-----------------------------------------------\n" +
			                "ASSOCIATIONS");

		mainActor.getActorAssociatons().forEach(association -> {
			System.out.print(association.getType().getDescription() + " ( ");
			
			association.getActors().forEach(actor -> {
				System.out.print(actor.getType().getDescription() + " " + actor.getName() + " ");
			});
			
			System.out.print(" )");
			
			try {
				if (association.isValid(mainActor)) {
					System.out.println(" > valid association");
				} else {
					System.out.println(" > invalid association");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
		
		XStream xstream = new XStream();
		String xml = xstream.toXML(mainActor);
		System.out.println("-----------------------------------------------\n" + 
					   	   "TO XML\n" + xml);
		
		String errors = xstream.toXML(ErrorHandler.getErrorHandler().getErrors());
		System.out.println("-----------------------------------------------\n" + 
						   "ERRORS\n" + errors);
	}

	public Actor getMainActor() {
		return mainActor;
	}
}
