package br.ufrn.smile.listeners;

import br.ufrn.smile.SmileBaseListener;
import br.ufrn.smile.SmileParser;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Dependency;

public class DependencyListener extends SmileBaseListener {
	private Dependency dependency;
	
	@Override 
	public void enterDependencyDeclaration(SmileParser.DependencyDeclarationContext context) {
		System.out.println("alskjdfhalskjdhflaksjdhfalksjh");
		String name = context.dependencyName().getText();
		String type = context.dependencyType().getText();
		
		ActorListener actorListener = new ActorListener();
		context.actorDeclaration().enterRule(actorListener);
		Actor actor = actorListener.getParsedActor();

		dependency = new Dependency(actor, name, type);
	}

	public Dependency getDependency() {
		return dependency;
	}
}
