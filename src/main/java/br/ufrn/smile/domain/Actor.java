package br.ufrn.smile.domain;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Actor {
	private int id;
	private String name;
	private ActorType type;
	private List<Association> associations;
	private ExternalRelationships externalRelationships;
	
	public Actor(int id, String name, String type) {
		this.id = id;
		this.name = name;
		this.type = ActorType.valueOf(type.toUpperCase());
		this.associations = new ArrayList<Association>();
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public ActorType getType() {
		return type;
	}
	
	public List<Association> getAssociations() {
		return associations;
	}
	
	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}

	public ExternalRelationships getExternalRelationships() {
		return externalRelationships;
	}

	public void setExternalRelationships(ExternalRelationships externalRelationships) {
		this.externalRelationships = externalRelationships;
	}
	
	public int getNumberOfAssociations() {
		return associations.size();
	}
	
	public int getNumberOfDependerRelationships() {
		return externalRelationships.getDependers().size();
	}
	
	public int getNumberOfDependeeRelationships() {
		return externalRelationships.getDependees().size();
	}
	
	public Node toXML(Document doc) {
		Element actor = doc.createElement("actor");
		actor.setAttribute("id", Integer.toString(id));
		actor.setAttribute("name", name);
		actor.setAttribute("type", type.getDescription());
		
		associations.forEach(association -> {
			actor.appendChild(association.toXML(doc));
		});
		
		return actor;
	}
	
	@Override
	public boolean equals(Object actor) {
		String name = ((Actor)actor).getName();
		ActorType type = ((Actor)actor).getType();
		
		return this.name.equals(name) && this.type.equals(type);
	}

	public enum ActorType {
		ACTOR("actor"), 
		AGENT("agent"), 
		POSITION("position"), 
		ROLE("role");
		
		private final String description;
		
		ActorType(String description) {
			this.description = description;
		}
		
		public String getDescription() {
			return description;
		}
	}
}