package com.imooc.test;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.imooc.entity.Book;
import com.imooc.handler.SAXParserHandler;

public class SAXTest {

	public static void main(String[] args) {
		// get an instance of SAXParserFactory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// get an instance of SAXParser using factory
		try {
			SAXParser parser = factory.newSAXParser();
			// create a SAXParserHandler object
			SAXParserHandler handler = new SAXParserHandler();
			parser.parse("books.xml", handler);
			System.out.println("Total number of books: " + handler.getBookList().size());
			for (Book book : handler.getBookList()) {
				System.out.println(book.getId());
				System.out.println(book.getName());
				System.out.println(book.getAuthor());
				System.out.println(book.getYear());
				System.out.println(book.getPrice());
				System.out.println(book.getLanguage());
				System.out.println("---finish---");
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
