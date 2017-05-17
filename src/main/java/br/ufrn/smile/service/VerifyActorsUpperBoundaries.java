package br.ufrn.smile.service;

import br.ufrn.smile.domain.Actor;
import br.ufrn.smile.domain.CustomWarning;

public class VerifyActorsUpperBoundaries {
	public static void call(Actor actor) {
		int maxAssociations = ConfigurationHandler.getMaxNumberOfAssociations();
		
		if(actor.getNumberOfAssociations() > maxAssociations) {
			String message = "number of associations on " + actor.getName() + 
							 " exceeded the limit of " + maxAssociations;
			
			generateWarning(message);
		}
		
		int maxDepender = ConfigurationHandler.getMaxNumberOfDependerRelationships();
		
		if(actor.getNumberOfDependerRelationships() > maxDepender) {
			String message = "number of depender relationships on " + actor.getName() + 
							 " exceeded the limit of " + maxDepender;
			generateWarning(message);
		}
		
		int maxDependee = ConfigurationHandler.getMaxNumberOfDependeeRelationships();
		
		if(actor.getNumberOfDependeeRelationships() > maxDependee) {
			String message = "number of dependee relationships on " + actor.getName() + 
					 		 " exceeded the limit of " + maxDependee;
			generateWarning(message);
		}
	}
	
	private static void generateWarning(String message) {
		CustomWarning warning = new CustomWarning(message);
		ErrorHandler.getErrorHandler().addWarning(warning);
	}
}
