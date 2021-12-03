package p2ch03;

//import com.sun.org.apache.xml.internal.security.utils.DOMNamespaceContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathNodes;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class XmlParser2 {
  protected final static Logger log;
  static {
    try(InputStream logprops = XmlParser.class.getResourceAsStream("logging.properties")) {
      LogManager.getLogManager().readConfiguration(logprops);
    } catch (IOException e) {
      Logger.getAnonymousLogger().severe("Log configuration failed.%n");
      Logger.getAnonymousLogger().severe(e.getStackTrace().toString());
    } finally {
      log = Logger.getLogger("xml", "corejava.xml");
    }
  }
  static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
  static DocumentBuilderFactory validatingFactory = DocumentBuilderFactory.newInstance();
  static DocumentBuilder builder ;
  static DocumentBuilder validatingBuilder ;
  static XPathFactory xpfactory = XPathFactory.newInstance();
  static {
    try {
      builder = factory.newDocumentBuilder();
      final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
      final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
      validatingFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
      validatingFactory.setIgnoringComments(true);
      validatingFactory.setIgnoringElementContentWhitespace(true);
      validatingFactory.setNamespaceAware(true);
      validatingFactory.setValidating(true);
      validatingBuilder = validatingFactory.newDocumentBuilder();
      validatingBuilder.setErrorHandler(new LoggingErrorHandler());
    } catch (ParserConfigurationException e) {
      log.log(Level.SEVERE, "error", e);
    }
  }
  private static class LoggingErrorHandler implements ErrorHandler {
    @Override
    public void warning(SAXParseException exception) throws SAXException {
      log.log(Level.SEVERE, "error", exception.getLocalizedMessage());
    }
    @Override
    public void error(SAXParseException exception) throws SAXException {
      log.log(Level.SEVERE, "error", exception.getLocalizedMessage());
    }
    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
      log.log(Level.SEVERE, "error", exception.getLocalizedMessage());
    }
  }
  public static Document parseXml(DocumentBuilder builder, String fname) throws URISyntaxException, IOException, SAXException {
    Path pathOfWhat = Paths.get((XmlParser.class.getResource(fname)).toURI());
    log.log(Level.INFO, "reading", pathOfWhat);
    return builder.parse(pathOfWhat.toFile());
  }
  private static Map<String, String> getAttributeMap(NamedNodeMap attrs) {
    Map<String, String> attributemap = new HashMap<>();
    if (attrs != null) {
      for (int j = 0; j < attrs.getLength(); j++) {
        Node attr = attrs.item(j);
        attributemap.put(attr.getNodeName(), attr.getNodeValue());
      }
    }
    return attributemap;
  }
  public static String getNodeString(Node nd) {
    return MessageFormat.format("Node tag: {0}; content: {1}, attributes: {2}",
        nd.getNodeName(), nd.getTextContent(), getAttributeMap(nd.getAttributes()));
  }
  public static List<String> listAllChildren(Document doc) {
    List<String> result = new ArrayList<>();
    Element root = doc.getDocumentElement();
    NodeList children = root.getChildNodes();
    for (int i = 0; i < children.getLength(); ++i) {
      Node child = children.item(i);
      result.add(getNodeString(child));
    }
    return result;
  }
  public static List<String> listChildrenXpath(Document doc) throws XPathExpressionException {
    List<String> result = new ArrayList<>();
    XPath xp = xpfactory.newXPath();
//    NamespaceContext fromDom = new DOMNamespaceContext(doc.getDocumentElement());
//    xp.setNamespaceContext(fromDom);
    XPathNodes xpnodes = xp.evaluateExpression("/rt:root/rt:child[@no]", doc, XPathNodes.class);
    for ( Node xpnode : xpnodes ) {
      result.add(getNodeString(xpnode));
    }
    return result;
  }
  public static void main(String[] args) throws URISyntaxException, IOException, SAXException, XPathExpressionException {
    //listAllChildren(parseXml(builder, "root.xml")).forEach(System.out::println);
    listAllChildren(parseXml(validatingBuilder, "root.xml")).forEach(System.out::println);
    //listChildrenXpath(parseXml(validatingBuilder, "root.xml")).forEach(System.out::println);
  }
}
