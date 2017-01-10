package com.imooc.handler;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.imooc.entity.Book;

public class SAXParserHandler extends DefaultHandler {
	int bookIndex = 0;
	String value = null;
	Book book = null;
	// ���������������book����
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public ArrayList<Book> getBookList() {
		return bookList;
	}

	/**
	 * ������ʶ������ʼ
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("SAX Parsering Starts");
	}
	
	/**
	 * ������ʶ��������
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("SAX Parsering Ends");
	}
	
	/**
	 * ��������xml�ļ��Ŀ�ʼ��ǩ������xmlԪ��
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// ����DefaultHandler���startElement����
		super.startElement(uri, localName, qName, attributes);
		
		if (qName.equals("book")) {
			// create a book object
			book = new Book();
			bookIndex++;
			// ��ʼ����bookԪ�ص�����
			System.out.println("=================���濪ʼ������" + bookIndex +"���������=================");
//			// ��֪bookԪ�������Ե����ƣ������������ƻ�ȡ����ֵ
//			String value = attributes.getValue("id");
//			System.out.println("book������ֵ�ǣ� " + value);
			
			// ��֪��bookԪ�������Ե������Լ���������λ�ȡ�������Ƽ�����ֵ
			int num = attributes.getLength();
			for (int i = 0; i < num; i++) {
				System.out.print("bookԪ�صĵ�" + (i + 1) + "���������ǣ� " + attributes.getQName(i));
				System.out.println("---����ֵ�ǣ� " + attributes.getValue(i));
				if (attributes.getQName(i).equals("id")) {
					book.setId(attributes.getValue(i));
				}
			}
		}
		else if (!qName.equals("book") && !qName.equals("bookstore")) {
			System.out.print("�ڵ����ǣ� " + qName);
		}
	}
	
	/**
	 * ��������xml�ļ��Ľ�����ǩ
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// ����DefaultHandler���endElement����
		super.endElement(uri, localName, qName);
		// �ж��Ƿ����һ�����Ѿ���������
		if (qName.equals("book")) {
			// �����������book����
			bookList.add(book);
			// ��ɱ�������book������գ�Ȼ��׼���洢��һ��book����
			book = null;
			System.out.println("======================����������" + bookIndex +"���������=================");
		}
		else if (qName.equals("name")) { // when qName.equals("name"), set the name of the book object
			book.setName(value);
		}
		else if (qName.equals("author")) { // when qName.equals("name"), set the name of the book object
			book.setAuthor(value);
		}
		else if (qName.equals("year")) { // when qName.equals("name"), set the name of the book object
			book.setYear(value);
		}
		else if (qName.equals("price")) { // when qName.equals("name"), set the name of the book object
			book.setPrice(value);
		}
		else if (qName.equals("language")) { // when qName.equals("name"), set the name of the book object
			book.setLanguage(value);
		}
	}
	
	/**
	 * ������ȡ�ڵ�ֵ
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException { // ÿ�ε����������ʱ������ch��������xml�ĵ�������
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		value = new String(ch, start, length);
		if (!value.trim().equals("")) {
			System.out.println("---�ڵ�ֵ�ǣ� " + value);
		}
	}
}
