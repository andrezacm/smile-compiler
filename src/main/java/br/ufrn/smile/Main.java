package br.ufrn.smile;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		SmileCompiler compiler = new SmileCompiler("path");
		
		compiler.setConfiguration(3, 2, 2);
		
		compiler.compile();
		
		System.out.println("total of actors " + compiler.getNumberOfActors());
		System.out.println("total of actors associations " + compiler.getNumberOfAssociations());
		
		compiler.getActors().forEach(actor -> {
			System.out.println(actor.getName()
					+ " assoc: " + actor.getNumberOfAssociations()
					+ " depender: " + actor.getNumberOfDependerRelationships()
					+ " dependee: " + actor.getNumberOfDependeeRelationships());
		});
		
		System.out.println("----------------------------------------------");
		
		compiler.toXML();
		
		System.out.println("----------------------------------------------");
		
		compiler.print();
	}
}