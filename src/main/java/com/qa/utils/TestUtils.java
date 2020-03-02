package com.qa.utils;

import java.io.InputStream;
import java.util.HashMap;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestUtils {
	
	public static final long WAIT=10;
	
	public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
		HashMap<String, String> stringMap=new HashMap<String, String>();
		
		//get documents builder
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		
		//build document
		Document document=builder.parse(file);
		
		//normalize the xml structure. It's important!
		document.getDocumentElement().normalize();
		
		//root node
		Element root=document.getDocumentElement();
		
		//sysout(root.getNodeName());
		
		//get all elements
		NodeList nList=document.getElementsByTagName("string");
		
		//sysout("============");
		
		for(int temp=0;temp<nList.getLength();temp++) {
			Node node=nList.item(temp);
			//sysout("");
			
			if(node.getNodeType()==Node.ELEMENT_NODE) {
				Element eElement=(Element) node;
				stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
			}
		}
		return stringMap;
	}

}
