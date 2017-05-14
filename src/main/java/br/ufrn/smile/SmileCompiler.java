package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.thoughtworks.xstream.XStream;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.ActorStatementFactory;
import br.ufrn.smile.domain.CustomError;
import br.ufrn.smile.domain.ErrorHandler;
import br.ufrn.smile.service.BuildXML;
import br.ufrn.smile.service.VerifyExternalRelationships;

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
			actor.verifyAssociationErrors();
			actor.verifyExternalRelationshipsErrors(this.getActorsCopy(actor));
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
