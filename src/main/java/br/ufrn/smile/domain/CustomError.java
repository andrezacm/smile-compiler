package br.ufrn.smile.domain;

import java.util.StringJoiner;

public class CustomError {
	private String message;
	
	public CustomError(String message) {
		this.message = message;
	}
	
	public CustomError(Actor mainActor, Association association) {
		StringBuilder msg = new StringBuilder("error on " + mainActor.getName() + 
					 						  " association: " + association.getType().getDescription() + " (");
		
		StringJoiner sj = new StringJoiner(", ");
		association.getActors().forEach(actor -> { 
			sj.add(actor.getType().getDescription() + " " + actor.getName());
		});
		
		msg.append(sj);
		msg.append(")");
		
		this.message = msg.toString();
	}

	public String getMessage() {
		return message;
	}
}
