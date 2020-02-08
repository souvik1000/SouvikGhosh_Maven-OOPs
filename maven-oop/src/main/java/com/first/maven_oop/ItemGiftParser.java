package com.first.maven_oop;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

public class ItemGiftParser {

    private static final Logger LOG = Logger.getLogger(ItemGiftParser.class);

    private DocumentBuilder builder;

    private XPath path;

    public ItemGiftParser() throws CreateDocumentConfigurationException {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            builder = documentFactory.newDocumentBuilder();

            XPathFactory xpathFactory = XPathFactory.newInstance();
            path = xpathFactory.newXPath();
        } catch (ParserConfigurationException e) {
            throw new CreateDocumentConfigurationException("exception create new document", e);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Sweet> parse(String fileName) throws XmlParseException {

        ArrayList<Sweet> items = new ArrayList<Sweet>();
        try {
            File file = new File(fileName);
            Document doc;
            try {
                doc = builder.parse(file);
            } catch (Exception e) {
                LOG.error("xml file not found", e);
                throw new XmlNotFoundException("xml file wasn't found", e);
            }

            int itemCount = Integer.parseInt(path.evaluate("count(/gift/item)", doc));

            for (int i = 1; i <= itemCount; i++) {

                double sugar = Double.parseDouble(path.evaluate("/gift/item[" + i + "]/@sugar", doc));
                String name = path.evaluate("/gift/item[" + i + "]/name", doc);
                double weight = Double.parseDouble(path.evaluate("/gift/item[" + i
                        + "]/weight", doc));

                @SuppressWarnings("rawtypes")
                Class cl = Class.forName("com.epam.lab.model.sweets." + name);
                items.add(((Sweet) cl.getConstructor(double.class, double.class).newInstance(sugar, weight)));
            }
        } catch (Exception e) {
            LOG.error("exception with parsing xml file", e);
            throw new XmlParseException("exception with parsing xml file", e);
        }

        return items;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Sweet> parse(File file) throws XmlParseException {

        ArrayList<Sweet> items = new ArrayList<Sweet>();
        try {
            Document doc;
            try {
                doc = builder.parse(file);
            } catch (Exception ex) {
                LOG.error("xml file not found", ex);
                throw new XmlNotFoundException("xml file not found", ex);
            }

            int itemCount = Integer.parseInt(path.evaluate("count(/gift/item)", doc));

            for (int i = 1; i <= itemCount; i++) {

                double sugar;
                sugar = Double.parseDouble(path.evaluate("/gift/item[" + i + "]/@sugar", doc));
                String name = path.evaluate("/gift/item[" + i + "]/name", doc);
                double weight = Double.parseDouble(path.evaluate("/gift/item[" + i
                        + "]/weight", doc));

                // checking
                //System.out.printf("%s with %f has %f weight%n", name, sugar, weight);
                @SuppressWarnings("rawtypes")
                Class cl = Class.forName("com.epam.lab.model.sweets." + name);
                items.add(((Sweet) cl.getConstructor(double.class, double.class)
                        .newInstance(sugar, weight)));

            }
        } catch (Exception e) {
            LOG.error("exception with parsing xml file", e);
            throw new XmlParseException("exception parsing xml file", e);
        }

        return items;
    }
}
