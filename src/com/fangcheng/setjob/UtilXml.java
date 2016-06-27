package com.fangcheng.setjob;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentFragment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class UtilXml
{
  private static Log logger = LogFactory.getLog(UtilXml.class);
  public static final String module = UtilXml.class.getName();

  public static Document readXmlDocument(String content) throws SAXException, ParserConfigurationException, IOException
  {
    return readXmlDocument(content, true);
  }

  public static Document readXmlDocument(String content, boolean validate) throws SAXException, ParserConfigurationException, IOException
  {
    if (content == null) {
      logger.warn("[UtilXml.readXmlDocument] content was null, doing nothing");
      return null;
    }
    ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes("UTF-8"));
    return readXmlDocument(bis, validate, "Internal Content");
  }

  public static Document readXmlDocument(URL url) throws SAXException, ParserConfigurationException, IOException
  {
    return readXmlDocument(url, true);
  }

  public static Document readXmlDocument(URL url, boolean validate) throws SAXException, ParserConfigurationException, IOException
  {
    if (url == null) {
      logger.warn("[UtilXml.readXmlDocument] URL was null, doing nothing");
      return null;
    }
    return readXmlDocument(url.openStream(), validate, url.toString());
  }

  /** @deprecated */
  public static Document readXmlDocument(InputStream is)
    throws SAXException, ParserConfigurationException, IOException
  {
    return readXmlDocument(is, true, null);
  }

  public static Document readXmlDocument(InputStream is, String docDescription) throws SAXException, ParserConfigurationException, IOException
  {
    return readXmlDocument(is, true, docDescription);
  }

  public static Document readXmlDocument(InputStream is, boolean validate, String docDescription) throws SAXException, ParserConfigurationException, IOException
  {
    if (is == null) {
      logger.warn("[UtilXml.readXmlDocument] InputStream was null, doing nothing");
      return null;
    }

    long startTime = System.currentTimeMillis();

    Document document = null;

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(validate);
    factory.setNamespaceAware(true);

    factory.setAttribute("http://xml.org/sax/features/validation", Boolean.valueOf(validate));
    factory.setAttribute("http://apache.org/xml/features/validation/schema", Boolean.valueOf(validate));

    DocumentBuilder builder = factory.newDocumentBuilder();

    document = builder.parse(is);

    double totalSeconds = (System.currentTimeMillis() - startTime) / 1000.0D;
    if (logger.isInfoEnabled()) logger.info("XML Read " + totalSeconds + "s: " + docDescription);
    return document;
  }

  public static Document makeEmptyXmlDocument() {
    return makeEmptyXmlDocument(null);
  }

  public static Document makeEmptyXmlDocument(String rootElementName) {
    Document document = null;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    factory.setValidating(true);
    try
    {
      DocumentBuilder builder = factory.newDocumentBuilder();

      document = builder.newDocument();
    } catch (Exception e) {
      logger.error(e);
    }

    if (document == null) return null;

    if (rootElementName != null) {
      Element rootElement = document.createElement(rootElementName);
      document.appendChild(rootElement);
    }

    return document;
  }

  public static Element addChildElement(Element element, String childElementName, Document document)
  {
    Element newElement = document.createElement(childElementName);

    element.appendChild(newElement);
    return newElement;
  }

  public static Element addChildElementValue(Element element, String childElementName, String childElementValue, Document document)
  {
    Element newElement = addChildElement(element, childElementName, document);

    newElement.appendChild(document.createTextNode(childElementValue));
    return newElement;
  }

  public static Element addChildElementCDATAValue(Element element, String childElementName, String childElementValue, Document document)
  {
    Element newElement = addChildElement(element, childElementName, document);

    newElement.appendChild(document.createCDATASection(childElementValue));
    return newElement;
  }

  public static List<? extends Element> childElementList(Element element)
  {
    if (element == null) return null;
    List<Element> elements = new LinkedList<Element>();
    Node node = element.getFirstChild();

    if (node != null) {
      do
        if (node.getNodeType() == 1) {
          Element childElement = (Element)node;
          elements.add(childElement);
        }
      while ((node = node.getNextSibling()) != null);
    }
    return elements;
  }

  public static List<? extends Element> childElementList(Element element, String childElementName)
  {
    if (element == null) return null;

    List<Element> elements = new LinkedList<Element>();
    Node node = element.getFirstChild();

    if (node != null) {
      do {
        if ((node.getNodeType() != 1) || ((childElementName != null) && (!childElementName.equals(node.getNodeName()))))
          continue;
        Element childElement = (Element)node;

        elements.add(childElement);
      }
      while ((node = node.getNextSibling()) != null);
    }
    return elements;
  }

  public static List<? extends Element> childElementList(Element element, Set<String> childElementNames)
  {
    if (element == null) return null;

    List<Element> elements = new LinkedList<Element>();
    if (childElementNames == null) return elements;
    Node node = element.getFirstChild();

    if (node != null) {
      do
        if ((node.getNodeType() == 1) && (childElementNames.contains(node.getNodeName()))) {
          Element childElement = (Element)node;
          elements.add(childElement);
        }
      while ((node = node.getNextSibling()) != null);
    }
    return elements;
  }



  public static List<? extends Element> childElementList(DocumentFragment fragment)
  {
    if (fragment == null) return null;
    List<Element> elements = new LinkedList<Element>();
    Node node = fragment.getFirstChild();
    if (node != null) {
      do
        if (node.getNodeType() == 1) {
          Element childElement = (Element)node;
          elements.add(childElement);
        }
      while ((node = node.getNextSibling()) != null);
    }
    return elements;
  }

  public static Element firstChildElement(Element element, Set<String> childElementNames)
  {
    if (element == null) return null;

    Node node = element.getFirstChild();

    if (node != null) {
      do
        if ((node.getNodeType() == 1) && (childElementNames.contains(node.getNodeName()))) {
          Element childElement = (Element)node;

          return childElement;
        }
      while ((node = node.getNextSibling()) != null);
    }
    return null;
  }



  public static Element firstChildElement(Element element)
  {
    if (element == null) return null;

    Node node = element.getFirstChild();

    if (node != null) {
      do
        if (node.getNodeType() == 1) {
          Element childElement = (Element)node;

          return childElement;
        }
      while ((node = node.getNextSibling()) != null);
    }
    return null;
  }

  public static Element firstChildElement(Element element, String childElementName)
  {
    if (element == null) return null;
    if (childElementName==null || childElementName.length()==0) return null;

    Node node = element.getFirstChild();

    if (node != null) {
      do {
        if ((node.getNodeType() != 1) || ((childElementName != null) && (!childElementName.equals(node.getNodeName()))))
          continue;
        Element childElement = (Element)node;
        return childElement;
      }
      while ((node = node.getNextSibling()) != null);
    }
    return null;
  }

  public static Element firstChildElement(Element element, String childElementName, String attrName, String attrValue)
  {
    if (element == null) return null;

    Node node = element.getFirstChild();

    if (node != null) {
      do {
        if ((node.getNodeType() != 1) || ((childElementName != null) && (!childElementName.equals(node.getNodeName()))))
          continue;
        Element childElement = (Element)node;

        String value = childElement.getAttribute(attrName);

        if ((value != null) && (value.equals(attrValue))) {
          return childElement;
        }
      }
      while ((node = node.getNextSibling()) != null);
    }
    return null;
  }

  public static String childElementValue(Element element, String childElementName)
  {
    if (element == null) return null;

    Element childElement = firstChildElement(element, childElementName);

    return elementValue(childElement);
  }

  public static String childElementValue(Element element, String childElementName, String defaultValue)
  {
    if (element == null) return defaultValue;

    Element childElement = firstChildElement(element, childElementName);
    String elementValue = elementValue(childElement);

    if ((elementValue == null) || (elementValue.length() == 0)) {
      return defaultValue;
    }
    return elementValue;
  }

  public static String elementValue(Element element)
  {
    if (element == null) return null;

    element.normalize();
    Node textNode = element.getFirstChild();

    if (textNode == null) return null;

    StringBuilder valueBuffer = new StringBuilder();
    do {
      if ((textNode.getNodeType() == 4) || (textNode.getNodeType() == 3))
        valueBuffer.append(textNode.getNodeValue());
    }
    while ((textNode = textNode.getNextSibling()) != null);
    return valueBuffer.toString();
  }

  public static String checkEmpty(String string) {
    if ((string != null) && (string.length() > 0)) {
      return string;
    }
    return "";
  }

  public static String checkEmpty(String string1, String string2) {
    if ((string1 != null) && (string1.length() > 0))
      return string1;
    if ((string2 != null) && (string2.length() > 0)) {
      return string2;
    }
    return "";
  }

  public static String checkEmpty(String string1, String string2, String string3) {
    if ((string1 != null) && (string1.length() > 0))
      return string1;
    if ((string2 != null) && (string2.length() > 0))
      return string2;
    if ((string3 != null) && (string3.length() > 0)) {
      return string3;
    }
    return "";
  }

  public static boolean checkBoolean(String str) {
    return checkBoolean(str, false);
  }

  public static boolean checkBoolean(String str, boolean defaultValue) {
    if (defaultValue)
    {
      return !"false".equals(str);
    }

    return "true".equals(str);
  }
}