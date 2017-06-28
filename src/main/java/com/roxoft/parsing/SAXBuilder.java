package main.java.com.roxoft.parsing;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXBuilder {

	private static final Logger LOG = Logger.getLogger(SAXBuilder.class);

	public void parseSAX() {
		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			SAXHandler handler = new SAXHandler();
			reader.setContentHandler(handler);
			reader.parse("src\\main\\resources\\cars.xml");
		} catch (SAXException e) {
			LOG.error(e);
		} catch (IOException e) {
			LOG.error(e);
		}
	}

}
