package br.ufrn.smile;

import java.io.IOException;
import java.io.InputStream;

public class Main {
	public static void main(String[] args) throws IOException {
		InputStream input = Main.class.getResourceAsStream("/actor_one.smile");
		InputStream input2 = Main.class.getResourceAsStream("/actor_two.smile");
		
		SmileCompiler compiler = new SmileCompiler();
		
		compiler.buildActor(input);
		compiler.buildActor(input2);
		
		compiler.verifyErrors();
		
		compiler.print();
		
		System.out.println(compiler.toXML());
	}
}