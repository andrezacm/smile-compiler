package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;
import br.ufrn.smile.domain.ErrorHandler;

public class SmileCompiler {
	private List<ActorStatementFactory> actors;
	
	public SmileCompiler() {
		this.actors = new ArrayList<ActorStatementFactory>();
	}
	
	public void buildActor(InputStream input) {
		try {
			ActorStatementFactory actorStatement = new ActorStatementFactory();
			actorStatement.build(input);
			actors.add(actorStatement);
		} catch (IOException e) {
			ErrorHandler.getErrorHandler().addError(new CustomError(e.getMessage()));
		}
	}
	
	public void verifyErrors() {
		actors.forEach(actor -> actor.verifyAssociationErrors());
	}
	
	public void print() {
		actors.forEach(actor -> actor.print());
	}
	
	public String toXML() {
		StringBuilder str = new StringBuilder();
		
		actors.forEach(actor -> str.append(actor.toXML()));
		
		XStream xstream = new XStream();
		str.append(xstream.toXML(ErrorHandler.getErrorHandler().getErrors()));
		
		return str.toString();
	}
}
