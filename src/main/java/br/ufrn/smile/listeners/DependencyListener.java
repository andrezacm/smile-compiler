package br.ufrn.smile.listeners;

import br.ufrn.smile.SmileBaseListener;
import br.ufrn.smile.SmileParser;
import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.Dependency;

public class DependencyListener extends SmileBaseListener {
	private Dependency dependency;
	
	@Override 
	public void enterDependerPerspective(SmileParser.DependerPerspectiveContext context) {
		String perspective = context.dependencyDependerPerspective().getText();
		String name = context.dependerDependencyDeclaration().dependencyName().getText();
		String type = context.dependerDependencyDeclaration().dependencyType().getText();
		
		ActorListener actorListener = new ActorListener();
		context.dependerDependencyDeclaration().actorDeclaration().enterRule(actorListener);
		Actor actor = actorListener.getParsedActor();

		dependency = new Dependency(actor, name, type, perspective);
	}
	
	@Override 
	public void enterDependeePerspective(SmileParser.DependeePerspectiveContext context) {
		String perspective = context.dependencyDependeePerspective().getText();
		String name = context.dependeeDependencyDeclaration().dependencyName().getText();
		String type = context.dependeeDependencyDeclaration().dependencyType().getText();
		
		ActorListener actorListener = new ActorListener();
		context.dependeeDependencyDeclaration().actorDeclaration().enterRule(actorListener);
		Actor actor = actorListener.getParsedActor();

		dependency = new Dependency(actor, name, type, perspective);
	}
	
	public Dependency getDependency() {
		return dependency;
	}
}
