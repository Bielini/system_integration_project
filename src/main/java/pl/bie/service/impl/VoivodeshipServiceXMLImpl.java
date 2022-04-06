package pl.bie.service.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class VoivodeshipServiceXMLImpl implements VoivodeshipService {

    private final List<Voivodeship> voivodeshipList = new ArrayList<>();

    @Override
    public List<Voivodeship> read(List<String> paths) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            System.out.println("Files :" + paths);
            for (String path : paths) {

                Document doc = docPreparation(path, dbf);

                NodeList list = doc.getElementsByTagName("unitData");

                for (int temp = 0; temp < list.getLength(); temp++) {
                    Node node = list.item(temp);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        String name = element.getElementsByTagName("name").item(0).getTextContent();

                        voivodeshipList.add(new Voivodeship(path, name, getAmountPerYear(element)));
                    }
                }
            }

            return this.voivodeshipList;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private Document docPreparation(String path, DocumentBuilderFactory dbf) throws ParserConfigurationException, SAXException, IOException {
        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new File(path));
        doc.getDocumentElement().normalize();
        return doc;
    }

    private Map<String, Double> getAmountPerYear(Element element) {
        NodeList years = element.getElementsByTagName("year");

        Map<String, Double> amountPerYear = new HashMap<String, Double>();

        for (int i = 0; i < years.getLength(); i++) {
            String year = element.getElementsByTagName("year").item(i).getTextContent();
            String val = element.getElementsByTagName("val").item(i).getTextContent();
            amountPerYear.put(year, Double.parseDouble(val));
        }
        return amountPerYear;
    }


    @Override
    public void printData() {
        if (voivodeshipList.size() == 0) {
            System.err.println("First call read!");
        } else {
            for (Voivodeship voivodeship : voivodeshipList) {
                System.out.println(voivodeship.getSourceFile());
                System.out.println(voivodeship.getName());
                voivodeship.getValueByYears()
                        .forEach((key, value) -> System.out.println("Year: " + key + " amount: " + value));
            }
        }

    }

    @Override
    public void save() {
//TODO
    }


}
