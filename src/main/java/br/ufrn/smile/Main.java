package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;

import br.ufrn.smile.domain.Actor;
import org.antlr.v4.runtime.*;

import com.thoughtworks.xstream.XStream;

import br.ufrn.smile.listeners.ActorStatementListener;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStream input = Main.class.getResourceAsStream("/actor_one.smile");
		
		SmileLexer lexer = new SmileLexer(new ANTLRInputStream(input));
		
		SmileParser parser = new SmileParser(new CommonTokenStream(lexer));
		
		ActorStatementListener actorStatementListener = new ActorStatementListener();
		
		parser.actorStatement().enterRule(actorStatementListener);

		Actor mainActor = actorStatementListener.getMainActor();

		System.out.println("MAIN ACTOR: " + 
		                    mainActor.getName() + 
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
	}
}