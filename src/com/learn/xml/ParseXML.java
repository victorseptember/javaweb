package com.learn.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;

public class ParseXML {
    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read("src/com/learn/xml/XML_demo1.xml");   //通过DOM方式解析XML文档

        Element root = document.getRootElement();
        System.out.println(root.getNamespacePrefix());
        System.out.println(root.getName());

        Iterator<Element> it = root.elementIterator();  //得到元素迭代器

        /*将所有子元素输出*/
        while(it.hasNext()) {
            Element element = it.next();
            System.out.println(element.getName());

            if (element.getName().equals("good")) {
                Element name = element.element("name");
                System.out.println(name.getText());
            }

            Iterator<Attribute> attribute= element.attributeIterator();
            /*将所有子元素的所有属性输出*/
            while (attribute.hasNext()){
                Attribute attr = attribute.next();
                System.out.println(attr.getName()+"; "+attr.getValue());
            }
        }
    }

}
