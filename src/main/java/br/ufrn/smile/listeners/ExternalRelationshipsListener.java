package br.ufrn.smile.listeners;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.smile.SmileBaseListener;
import br.ufrn.smile.SmileParser;
import br.ufrn.smile.domain.Dependency;
import br.ufrn.smile.domain.ExternalRelationships;

public class ExternalRelationshipsListener extends SmileBaseListener {
	private ExternalRelationships externalRelationships;
	
	public ExternalRelationshipsListener() {
		this.externalRelationships = new ExternalRelationships();
	}

	@Override 
	public void enterExternalRelationships(SmileParser.ExternalRelationshipsContext context) {
		List<DependencyListener> dependencyListeners = new ArrayList<DependencyListener>();
		List<Dependency> dependencies = new ArrayList<Dependency>();
		
		context.dependerPerspective().forEach(depender -> {
			DependencyListener dependencyListener = new DependencyListener();
			depender.dependencyDeclaration().enterRule(dependencyListener);
			dependencyListeners.add(dependencyListener);
		});
		
		dependencyListeners.forEach(listener -> dependencies.add(listener.getDependency()));
		
		externalRelationships.setDependers(dependencies);
	}
	
	public ExternalRelationships getExternalRelationships() {
		return externalRelationships;
	}
}
