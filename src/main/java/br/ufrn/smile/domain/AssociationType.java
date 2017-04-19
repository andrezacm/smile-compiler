package br.ufrn.smile.domain;

public enum AssociationType {
	COVERS("covers"),
	ISA("isA"),
	INSTANCEOF("instanceOf"),
	ISPARTOF("isPartOf"),
	OCCUPIES("occupies"),
	PLAYS("plays");
		
	private final String description;
	
	AssociationType(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}