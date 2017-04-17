package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.*;

import br.ufrn.smile.listeners.ActorListener;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStream input = Main.class.getResourceAsStream("/actor_one.smile");
		
		SmileLexer lexer = new SmileLexer(new ANTLRInputStream(input));
		
		SmileParser parser = new SmileParser(new CommonTokenStream(lexer));
		
		ActorListener actorListener = new ActorListener();
		
		parser.actorDeclaration().enterRule(actorListener);
		
		System.out.println(actorListener.getParsedActor().getName());
	}
}