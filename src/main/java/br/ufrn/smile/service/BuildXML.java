package br.ufrn.smile.service;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufrn.smile.domain.Actor;

public class BuildXML {
	public static void call(List<Actor> actors) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
			Element root = doc.createElementNS("", "SMILE");
			
			doc.appendChild(root);
			
			actors.forEach(actor -> root.appendChild(actor.toXML(doc)));
			
			//output
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(doc);
			StreamResult console = new StreamResult(System.out);
			
			transformer.transform(source, console);			
			
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
