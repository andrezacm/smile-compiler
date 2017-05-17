package br.ufrn.smile.service;

public class ConfigurationHandler {
	private static int maxNumberOfAssociations = Integer.MAX_VALUE;
	private static int maxNumberOfDependerRelationships = Integer.MAX_VALUE;
	private static int maxNumberOfDependeeRelationships = Integer.MAX_VALUE;
	
	private static ConfigurationHandler configHandler;
	
	private ConfigurationHandler(int maxNumberOfAssociations, int maxNumberOfDependerRelationships, int maxNumberOfDependeeRelationships) {
		ConfigurationHandler.maxNumberOfAssociations = maxNumberOfAssociations;
		ConfigurationHandler.maxNumberOfDependerRelationships = maxNumberOfDependerRelationships;
		ConfigurationHandler.maxNumberOfDependeeRelationships = maxNumberOfDependeeRelationships;
	}
	
	public static ConfigurationHandler getConfigurationHandler(int maxNumberOfAssociations, int maxNumberOfDependerRelationships, int maxNumberOfDependeeRelationships) {
		if(configHandler == null) {
			configHandler = new ConfigurationHandler(maxNumberOfAssociations, maxNumberOfDependerRelationships, maxNumberOfDependeeRelationships);
		}
		
		return configHandler;
	}

	public static int getMaxNumberOfAssociations() {
		return maxNumberOfAssociations;
	}

	public static int getMaxNumberOfDependerRelationships() {
		return maxNumberOfDependerRelationships;
	}

	public static int getMaxNumberOfDependeeRelationships() {
		return maxNumberOfDependeeRelationships;
	}
}
