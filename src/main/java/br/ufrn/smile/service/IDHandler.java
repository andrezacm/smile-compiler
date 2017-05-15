package br.ufrn.smile.service;

public class IDHandler {
	private static int nextId = 1;
	private int id;

	public IDHandler() {
		this.id = nextId;
		nextId++;
	}
	
	public int getID() {
		return id;
	}
}
