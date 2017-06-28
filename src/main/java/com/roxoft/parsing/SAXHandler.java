package main.java.com.roxoft.parsing;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

	private static final Logger LOG = Logger.getLogger(SAXHandler.class);

	@Override
	public void startDocument() {
		LOG.info("Parsing started");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		String s = localName;
		for (int i = 0; i < attrs.getLength(); i++) {
			s += " " + attrs.getLocalName(i) + "=" + attrs.getValue(i);
		}
		LOG.info(s.trim());
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		LOG.info(new String(ch, start, length));
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
//		LOG.info(qName);
	}

	@Override
	public void endDocument() {
		LOG.info("\nParsing finished");
	}

}
