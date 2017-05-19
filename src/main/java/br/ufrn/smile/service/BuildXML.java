package br.ufrn.smile.service;

import java.io.StringWriter;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import br.ufrn.smile.domain.Actor;

public class BuildXML {
	public static String call(List<Actor> actors) {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		StringBuilder xml = new StringBuilder();
		
		try {
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
			Element root = doc.createElementNS("", "SMILE");
			
			doc.appendChild(root);
			
			actors.forEach(actor -> root.appendChild(actor.toXML(doc)));
			
			BuildExternalRelationshipsXML.call(doc, actors).forEach(node -> root.appendChild(node));
			
			//output
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new StringWriter());
			
			transformer.transform(source, result);		
			
			xml.append(result.getWriter().toString());
			
		} catch (ParserConfigurationException | TransformerFactoryConfigurationError | TransformerException e) {
			e.printStackTrace();
		}
		
		return xml.toString();
	}
}
