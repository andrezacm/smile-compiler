package br.ufrn.smile.domain;

import java.util.ArrayList;
import java.util.List;

public class Association {
	private AssociationType type;
	private List<Actor> actors;
	
	public Association(String associationType) {
		this.type = AssociationType.valueOf(associationType.toUpperCase());
		this.actors = new ArrayList<Actor>();
	}

	public AssociationType getType() {
		return type;
	}

	public void setType(AssociationType type) {
		this.type = type;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Boolean isValid(Actor mainActor) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return AssociationFactory.getValidator(type).isValid(mainActor, this);
	}
	
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
}