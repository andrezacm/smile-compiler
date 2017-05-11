package br.ufrn.smile.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExternalRelationships {
	private List<Dependency> dependers;
	private List<Dependency> dependees;
	
	public ExternalRelationships() {
		this.dependers = new ArrayList<Dependency>();
		this.dependees = new ArrayList<Dependency>();
	}

	public List<Dependency> getDependers() {
		return dependers;
	}

	public void setDependers(List<Dependency> dependers) {
		this.dependers = dependers;
	}

	public List<Dependency> getDependees() {
		return dependees;
	}
	
	public List<Dependency> getDependees(Dependency.DependencyPerspective perspective) {
		return dependees.stream()
						.filter(dependency -> dependency.getPerspective().equals(perspective))
						.collect(Collectors.toList());		
	}

	public void setDependees(List<Dependency> dependees) {
		this.dependees = dependees;
	}
}
