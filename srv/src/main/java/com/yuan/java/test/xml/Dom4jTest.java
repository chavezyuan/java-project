package com.yuan.java.test.xml;

import com.google.common.collect.Lists;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Desc :
 * Author : chavezyuan
 * Date : 2016-08-29
 */
public class Dom4jTest {

    static String table = "t_rs_limit_rule";

    static List<String> excludeField = Lists.newArrayList("created_at", "last_modified");

    public static void main(String[] args) throws IOException, DocumentException {
        demo();
    }

    public static void demo() throws DocumentException, IOException {

        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("/Users/chavezyuan/Desktop/t_rs_limit_rule.xml"));

        Document result = DocumentHelper.createDocument();
        Element root = result.addElement("dataset");

        System.out.println("<dataset>");

        for(Object ele : document.getRootElement().elements()) {
            Element element = (Element) ele;
            Element record = root.addElement(table);

            for(Object f : element.elements()) {

                Element field = (Element) f;
                if(excludeField.contains(field.getName())) {
                    continue;
                }
                record.addAttribute(field.getName(), field.getStringValue());
            }
            System.out.println(record.asXML());
        }
        System.out.println("</dataset>");

        System.out.println("------------------------------");


        System.out.println(result.asXML());
    }
}
