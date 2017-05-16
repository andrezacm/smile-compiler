package br.ufrn.smile.service;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.ufrn.smile.domain.Actor;

public class BuildExternalRelationshipsXML {
	public static List<Node> call(Document doc, List<Actor> actors) {
		List<Node> nodes = new ArrayList<Node>();
		
		actors.forEach(actor -> {
			actor.getExternalRelationships().getDependers().forEach(depender -> {
				Element ielement = doc.createElement("ielement");
				ielement.setAttribute("name", depender.getName());
				ielement.setAttribute("type", depender.getType().getDescription());
				
				Element dependency = doc.createElement("dependency");
				
				Element edepender = doc.createElement("depender");
				edepender.setAttribute("aref", Integer.toString(actor.getId()));
				
				Element edependee = doc.createElement("dependee");
				edependee.setAttribute("aref", Integer.toString(depender.getActor().getId()));
				
				dependency.appendChild(edepender);
				dependency.appendChild(edependee);
				
				ielement.appendChild(dependency);
				
				nodes.add(ielement);
			});
		});
		
		return nodes;
	}
}
