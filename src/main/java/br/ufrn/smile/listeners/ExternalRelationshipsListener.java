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
		List<DependencyListener> dependerListeners = new ArrayList<DependencyListener>();
		List<Dependency> dependers = new ArrayList<Dependency>();
		
		context.dependerPerspective().forEach(depender -> {
			DependencyListener dependencyListener = new DependencyListener();
			depender.enterRule(dependencyListener);
			dependerListeners.add(dependencyListener);
		});
		
		dependerListeners.forEach(listener -> dependers.add(listener.getDependency()));
		
		externalRelationships.setDependers(dependers);
		
		List<DependencyListener> dependeeListeners = new ArrayList<DependencyListener>();
		List<Dependency> dependees = new ArrayList<Dependency>();
		
		context.dependeePerspective().forEach(dependee -> {
			DependencyListener dependencyListener = new DependencyListener();
			dependee.enterRule(dependencyListener);
			dependeeListeners.add(dependencyListener);
		});
		
		dependeeListeners.forEach(listener -> dependees.add(listener.getDependency()));
		
		externalRelationships.setDependees(dependees);
	}
	
	public ExternalRelationships getExternalRelationships() {
		return externalRelationships;
	}
}
