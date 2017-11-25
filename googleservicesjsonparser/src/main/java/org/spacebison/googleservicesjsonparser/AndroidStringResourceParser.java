package org.spacebison.googleservicesjsonparser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public final class AndroidStringResourceParser {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;

    private AndroidStringResourceParser() {
    }

    public static Map<String, String> parseStringResXml(String xml) throws IOException, SAXException {
        Document xmlDocument = getXmlDocument(xml);

        NodeList nodeList = xmlDocument.getElementsByTagName("resources");
        return getNodeStream(nodeList)
                .flatMap(resourcesNode -> getNodeStream(resourcesNode.getChildNodes()))
                .filter(AndroidStringResourceParser::isValidStringResourceNode)
                .collect(Collectors.toMap(
                        node -> node.getAttributes().getNamedItem("name").getTextContent(),
                        node -> node.getFirstChild().getTextContent()));
    }

    private static boolean isValidStringResourceNode(Node node) {
        if (!node.hasAttributes() || !"string".equals(node.getNodeName())) {
            return false;
        }

        Node name = node.getAttributes().getNamedItem("name");
        return name != null && name.getTextContent() != null;
    }

    static Stream<Node> getNodeStream(NodeList nodeList) {
        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item);
    }

    private static Document getXmlDocument(String xml) throws SAXException, IOException {
        DocumentBuilder documentBuilder = getDocumentBuilder();
        return documentBuilder.parse(new ByteArrayInputStream(xml.getBytes(UTF_8)));
    }

    private static DocumentBuilder getDocumentBuilder() {
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        return documentBuilder;
    }
}
