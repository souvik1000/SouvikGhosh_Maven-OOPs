package com.first.maven_oop;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class ItemGiftBuilder {

    private DocumentBuilder builder;

    private Document doc;

    public ItemGiftBuilder() throws CreateDocumentConfigurationException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new CreateDocumentConfigurationException("exception create new document", e);
        }
    }

    public Document build(ArrayList<Sweet> items) {
        doc = builder.newDocument();
        doc.appendChild(createItems(items));
        return doc;
    }

    private Element createItems(ArrayList<Sweet> items) {
        Element e = doc.createElement("gift");

        for (Sweet anItem : items)
            e.appendChild(createItem(anItem));

        return e;
    }

    private Element createItem(Sweet anItem) {
        Element e = doc.createElement("item");

        e.appendChild(createTextElement("name", "" + anItem.getClass().getSimpleName()));
        e.setAttribute("sugar", anItem.get_SugarLevel() + "");
        e.appendChild(createTextElement("weight", "" + anItem.get_Weight()));
        return e;
    }

    private Element createTextElement(String name, String text) {
        Text t = doc.createTextNode(text);
        Element e = doc.createElement(name);
        e.appendChild(t);
        return e;
    }

}
