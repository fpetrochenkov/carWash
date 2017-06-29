package main.java.com.roxoft.main;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import main.java.com.roxoft.dao.mybatis.CarDaoImpl;
import main.java.com.roxoft.parsing.DOM4JParser;
import main.java.com.roxoft.parsing.DOMBuilder;
import main.java.com.roxoft.parsing.JDOM;
import main.java.com.roxoft.parsing.Jackson;
import main.java.com.roxoft.parsing.Jaxb;
import main.java.com.roxoft.parsing.SAXBuilder;
import main.java.com.roxoft.parsing.Stax;
import main.java.com.roxoft.parsing.XPathParser;
import main.java.com.roxoft.service.Service;

public class Runner {

	private static final Logger LOG = Logger.getLogger(Runner.class);

	public static void main(String[] args) throws XMLStreamException, IOException {

//		LOG.info("===========================================================================");
//		Stax stax = new Stax();
//		stax.buildSetCars("src\\main\\resources\\cars.xml");
//		LOG.info(stax.getCars());
//
//		LOG.info("===========================================================================");
//		Jaxb j = new Jaxb();
//		j.jaxb();
//		LOG.info("===========================================================================");
//		try {
//			Jackson jack = new Jackson();
//			jack.jackson();
//		} catch (JsonParseException e) {
//			LOG.error("JsonParseException: ", e);
//		} catch (JsonMappingException e) {
//			LOG.error("JsonMappingException: ", e);
//		}
//		LOG.info("===========================================================================");
//		Service service = new Service();
//		service.fillDataBase();
//		LOG.info("===========================================================================");
//		CarDaoImpl c = new CarDaoImpl();
//		LOG.info(c.getAll());
//
//		LOG.info("===========================================================================");
//		DOMBuilder domBuilder = new DOMBuilder();
//		domBuilder.buildSetCars("src\\main\\resources\\cars.xml");
//		LOG.info(domBuilder.getCars());
//
//		LOG.info("===========================================================================");
//		SAXBuilder saxBuilder = new SAXBuilder();
//		saxBuilder.parseSAX();
		
//		LOG.info("===========================================================================");
//		JDOM jdom = new JDOM();
//		jdom.parseJDOM();
		
//		LOG.info("===========================================================================");
//		XPathParser xpath = new XPathParser();
//		xpath.parseXPath();
		
//		LOG.info("===========================================================================");
//		DOM4JParser parser = new DOM4JParser();
//		parser.parseDOM4J();
		
		

	}
}
