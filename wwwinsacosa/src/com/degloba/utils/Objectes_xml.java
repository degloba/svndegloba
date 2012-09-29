package com.degloba.utils;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;

import com.degloba.HBM.Person;

import com.google.common.collect.Lists;
import com.google.common.io.Closeables;

import com.degloba.dataModels_JPA.PersistenceService;

public class Objectes_xml {

	/* 
     * Funcio que converteix un fitxer xml d'objectes a objectes (classes)
     * data.xml --> llista objectes
     */
    private List<Person> parseTestData() throws Exception {
        InputStream dataStream = null;
        try {
            dataStream = PersistenceService.class.getResourceAsStream("data.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Node node = documentBuilder.parse(dataStream).getDocumentElement();
            
            List<Person> persons = Lists.newArrayList();

            for (Node personNode = node.getFirstChild(); personNode != null; personNode = personNode.getNextSibling()) {
                if (personNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                
                Person person = new Person();
                persons.add(person);
                
                for (Node personDataNode = personNode.getFirstChild(); personDataNode != null; personDataNode = personDataNode.getNextSibling()) {
                    if (personDataNode.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }
                    
                    String nodeName = personDataNode.getNodeName();
                    String text = personDataNode.getTextContent();
                    if ("name".equals(nodeName)) {
                        person.setName(text);
                    } else if ("surname".equals(nodeName)) {
                        person.setSurname(text);
                    } else if ("email".equals(nodeName)) {
                        person.setEmail(text);
                    }
                }
            }
            
            return persons;
        } finally {
            Closeables.closeQuietly(dataStream);
        }
    }
	
}
