package br.ufrn.smile.domain;

import java.util.ArrayList;
import java.util.List;

public class ExternalRelationships {
	private List<Dependency> dependers;
	
	public ExternalRelationships() {
		this.dependers = new ArrayList<Dependency>();
	}

	public List<Dependency> getDependers() {
		return dependers;
	}

	public void setDependers(List<Dependency> dependers) {
		this.dependers = dependers;
	}
}
