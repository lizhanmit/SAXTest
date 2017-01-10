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
	// 用来保存遍历出的book对象
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public ArrayList<Book> getBookList() {
		return bookList;
	}

	/**
	 * 用来标识解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("SAX Parsering Starts");
	}
	
	/**
	 * 用来标识解析结束
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("SAX Parsering Ends");
	}
	
	/**
	 * 用来遍历xml文件的开始标签，解析xml元素
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// 调用DefaultHandler类的startElement方法
		super.startElement(uri, localName, qName, attributes);
		
		if (qName.equals("book")) {
			// create a book object
			book = new Book();
			bookIndex++;
			// 开始解析book元素的属性
			System.out.println("=================下面开始遍历第" + bookIndex +"本书的内容=================");
//			// 已知book元素下属性的名称，根据属性名称获取属性值
//			String value = attributes.getValue("id");
//			System.out.println("book的属性值是： " + value);
			
			// 不知道book元素下属性的名称以及个数，如何获取属性名称及属性值
			int num = attributes.getLength();
			for (int i = 0; i < num; i++) {
				System.out.print("book元素的第" + (i + 1) + "个属性名是： " + attributes.getQName(i));
				System.out.println("---属性值是： " + attributes.getValue(i));
				if (attributes.getQName(i).equals("id")) {
					book.setId(attributes.getValue(i));
				}
			}
		}
		else if (!qName.equals("book") && !qName.equals("bookstore")) {
			System.out.print("节点名是： " + qName);
		}
	}
	
	/**
	 * 用来遍历xml文件的结束标签
	 */
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// 调用DefaultHandler类的endElement方法
		super.endElement(uri, localName, qName);
		// 判断是否针对一本书已经遍历结束
		if (qName.equals("book")) {
			// 保存遍历出的book对象
			bookList.add(book);
			// 完成遍历，对book对象清空，然后准备存储下一个book对象
			book = null;
			System.out.println("======================结束遍历第" + bookIndex +"本书的内容=================");
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
	 * 用来获取节点值
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException { // 每次调用这个方法时，参数ch都是整个xml文档的内容
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		value = new String(ch, start, length);
		if (!value.trim().equals("")) {
			System.out.println("---节点值是： " + value);
		}
	}
}
