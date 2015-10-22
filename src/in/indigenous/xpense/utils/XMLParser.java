package in.indigenous.xpense.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser<T> {

	public List<T> parse(File file, Class<T> clazz) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		List<T> list = new ArrayList<T>();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root Element :: "
					+ doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getDocumentElement().getChildNodes();
			for (int i = 0; i < nList.getLength(); i++) {
				Node node = nList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					NodeList subNodeList = element.getChildNodes();
					T obj = clazz.newInstance();
					for (int j = 0; j < subNodeList.getLength(); j++) {
						Node subNode = subNodeList.item(j);
						String nodeName = subNode.getNodeName();
						String methodName = "set"
								+ nodeName.substring(0, 1).toUpperCase()
								+ nodeName.substring(1);
						Method[] ms = clazz.getMethods();
						for (Method m : ms) {
							if (m.getName().equals(methodName)) {
								System.out.println("Method Name :: " + methodName);
								System.out.println("Node Value :: " + subNode.getTextContent());
								System.out.println("Method param type :: " + m.getParameterTypes()[0].getSimpleName());
								if ("int".equals(m.getParameterTypes()[0]
										.getSimpleName())) {
									m.invoke(obj, Integer.parseInt(subNode
											.getTextContent()));
								} else if("double".equals(m.getParameterTypes()[0]
										.getSimpleName())){
									m.invoke(obj, Double.parseDouble(subNode.getTextContent()));
								} else if("Date".equals(m.getParameterTypes()[0]
										.getSimpleName())){
									SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");

									m.invoke(obj, dateFormatter.parse(subNode.getTextContent()));
								} else {
									m.invoke(obj, subNode.getTextContent());
								}
								break;
							}
						}
					}
					list.add(obj);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}
}
