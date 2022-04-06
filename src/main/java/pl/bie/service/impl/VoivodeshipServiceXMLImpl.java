package pl.bie.service.impl;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import pl.bie.model.Voivodeship;
import pl.bie.service.VoivodeshipService;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

                Document doc = docPreparation("xmlfiles/"+path, dbf);

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
    public void save(String fileName) {
        if (voivodeshipList.size() == 0) {
            System.err.println("First call read!");
        } else {
            try {

                Document document = configureAndCreateDocument();

                Element root = createTitle(document);


                for (Voivodeship voivodeship : this.voivodeshipList) {


                    Element category = document.createElement("category");
                    root.appendChild(category);

                    Attr attr = document.createAttribute("sourceFile");
                    attr.setValue(voivodeship.getSourceFile());
                    category.setAttributeNode(attr);

                    Element voivodeshipName = document.createElement("voivodeship");
                    category.appendChild(voivodeshipName);

                    Attr attr2 = document.createAttribute("name");
                    attr2.setValue(voivodeship.getName());
                    voivodeshipName.setAttributeNode(attr2);

                    for (Map.Entry<String, Double> stringDoubleEntry : voivodeship.getValueByYears().entrySet()) {
                        Element yearValue = document.createElement("yearValue");
                        voivodeshipName.appendChild(yearValue);


                        Element year = document.createElement("year");
                        year.appendChild(document.createTextNode(stringDoubleEntry.getKey()));
                        yearValue.appendChild(year);

                       
                        Element value = document.createElement("value");
                        value.appendChild(document.createTextNode(String.valueOf(stringDoubleEntry.getValue())));
                        yearValue.appendChild(value);
                    }
                    
                    
                    createAndTransformToXML(fileName, document);
                    
                }
                System.out.println("Done creating XML File");
            } catch (ParserConfigurationException | TransformerException e) {
                e.printStackTrace();
            }
        }
    }



    private Document configureAndCreateDocument() throws ParserConfigurationException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        return documentBuilder.newDocument();
    }

    private Element createTitle(Document document) {
        Element root = document.createElement("statistics");
        document.appendChild(root);
        return root;
    }

    private void createAndTransformToXML(String fileName, Document document) throws TransformerException {
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(fileName));
        transformDocument(domSource, streamResult);
    }

    private void transformDocument(DOMSource domSource, StreamResult streamResult) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT,"yes");
        transformer.transform(domSource, streamResult);
    }

}
