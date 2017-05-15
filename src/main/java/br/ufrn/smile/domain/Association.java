package br.ufrn.smile.domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Association {
	private Actor actor;
	private AssociationType type;
	
	public Association(String associationType, Actor actor) {
		this.type = AssociationType.valueOf(associationType.toUpperCase());
		this.actor = actor;
	}

	public AssociationType getType() {
		return type;
	}

	public void setType(AssociationType type) {
		this.type = type;
	}
	
	public Actor getActor() {
		return actor;
	}

	public Boolean isValid(Actor mainActor) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		return AssociationFactory.getValidator(type).isValid(mainActor, this);
	}
	
	public Node toXML(Document doc) {
		Element association = doc.createElement("actorLink");
		association.setAttribute("type", type.getDescription());
		association.setAttribute("aref", Integer.toString(actor.getId()));
		
		return association;
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