package com.hui.dom4j;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class DOM4JTest {

	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("XMLTest.xml"));
		Element rootElement = document.getRootElement(); 	 //��ȡ�����
		System.out.println(rootElement.getName());
		
		/*for(Iterator  i=rootElement.elementIterator();i.hasNext();) {
			Element element = (Element)i.next();  	//��ȡ�ӽ��
			System.out.println(element.getName());
			
			for(Iterator j=element.attributeIterator();j.hasNext();) {
				Attribute attribute = (Attribute)j.next();  	//��ȡ���ԣ�����element.attributeValue(str)ֱ�ӻ�ȡԪ��ĳָ�����Ե�ֵ
				System.out.println(attribute.getName() + " " + attribute.getValue());
			}
		}		
		*/
		
		
		//XPATH
		List<Node> list = document.selectNodes("//hibernate-mapping/class/property");     //ʹ��XPath��Ҫ����jaxen��jar����
		/*for(Node n : list) {
			System.out.println(n.getName());
			System.out.println(n.valueOf("@name"));
		}		
		Node node = document.selectSingleNode("//hibernate-mapping/class/property"); //ֻȡ��һ�����
		String name = node.valueOf(@name); 
		*/
		
		
		Element element = (Element) rootElement.selectSingleNode("disk");  //��ȡ������µ�һ��Ϊ��disk����element	
		//Element element = rootElement.element("disk"); //��ȡ������µ�disk�ڵ�
		//Element  element = document.elementById("disk");  //ֱ��ͨ��ID��ȡdisk�ڵ㣬xml�е�IDҪ��д
 		List<Element> elements = element.elements();               //��ȡdisk�����е��ӽڵ㡣
		for(Element e : elements) {
			System.out.println(e.getName()+":" + e.getText());
		}
		 
		
	}
	}
	
}
 