package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;

import br.ufrn.smile.domain.Actor;
import org.antlr.v4.runtime.*;

import br.ufrn.smile.listeners.ActorStatementListener;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStream input = Main.class.getResourceAsStream("/actor_one.smile");
		
		SmileLexer lexer = new SmileLexer(new ANTLRInputStream(input));
		
		SmileParser parser = new SmileParser(new CommonTokenStream(lexer));
		
		ActorStatementListener actorStatementListener = new ActorStatementListener();
		
		parser.actorStatement().enterRule(actorStatementListener);

		Actor mainActor = actorStatementListener.getMainActor();

		System.out.println(actorStatementListener.getMainActor().getName());
		
		System.out.println(actorStatementListener.getMainActor().getType());
		
		actorStatementListener.getMainActor().getActorAssociatons().forEach(association -> {
			System.out.println(association.getType().toString());
			association.getActors().forEach(actor -> {
				System.out.println(actor.getType() + " " + actor.getName());
			});

			try {
				if (association.isValid(mainActor)) {
					System.out.println("valid association");
				} else {
					System.out.println("invalid association");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}