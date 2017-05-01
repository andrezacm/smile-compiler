package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.ErrorHandler;

import org.antlr.v4.runtime.*;

import com.thoughtworks.xstream.XStream;

import br.ufrn.smile.listeners.ActorStatementListener;
import br.ufrn.smile.listeners.ErrorListener;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStream input = Main.class.getResourceAsStream("/actor_one.smile");
		
		ActorStatementFactory actorStatement = new ActorStatementFactory();
		actorStatement.build(input);
		actorStatement.verifyAssociationErrors();
		actorStatement.print();
		
		InputStream input2 = Main.class.getResourceAsStream("/actor_two.smile");
		
		ActorStatementFactory actorStatement2 = new ActorStatementFactory();
		actorStatement2.build(input2);
		actorStatement2.verifyAssociationErrors();
		actorStatement2.print();
	}
}