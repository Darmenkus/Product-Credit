package util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.net.URL;

public class ExchangeRatesUtils {
    private static final String NB_RATES_URL = "http://www.nationalbank.kz/rss/get_rates.cfm?fdate=";

    public static String getRate(String date, String currency) {
        String rate = "";
        String url = NB_RATES_URL + date;

        Document document = loadXMLDocument(url);
        NodeList nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String title = element.getElementsByTagName("title").item(0).getTextContent();
                if (title.equals(currency))
                    rate = element.getElementsByTagName("description").item(0).getTextContent();
                break;
            }
        }
        return rate;
    }

    public static String getRatesXML(String date) {
        return toString(loadXMLDocument(NB_RATES_URL + date));
    }

    private static Document loadXMLDocument(String url) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            return factory.newDocumentBuilder().parse(new URL(url).openStream());
        } catch (Exception e) {
            throw new RuntimeException("Error converting to String", e);
        }
    }

    private static String toString(Document doc) {
        try {
            StringWriter writer = new StringWriter();
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error converting to String", e);
        }
    }
}
