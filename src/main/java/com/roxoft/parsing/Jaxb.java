package main.java.com.roxoft.parsing;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import main.java.com.roxoft.model.Cars;

public class Jaxb {
	
	private static final Logger LOG = Logger.getLogger(Jaxb.class);

	public void jaxb() {
		try {
			JAXBContext contex = JAXBContext.newInstance(Cars.class);
			Unmarshaller u = contex.createUnmarshaller();
			FileReader reader = new FileReader("src\\main\\resources\\cars.xml");
			Cars cars = (Cars) u.unmarshal(reader);
			LOG.info(cars);
		} catch (JAXBException e) {
			LOG.error("JAXBException:", e);
		} catch (FileNotFoundException e) {
			LOG.error("Can't find a file:", e);
		}
	}

}
