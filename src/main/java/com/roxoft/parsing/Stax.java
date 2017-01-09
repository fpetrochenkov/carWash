package main.java.com.roxoft.parsing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import main.java.com.roxoft.model.Car;
import main.java.com.roxoft.model.CarEnum;
import main.java.com.roxoft.model.Insurance;
import main.java.com.roxoft.model.Tariff;
import main.java.com.roxoft.model.User;

public class Stax {
	
	private static final Logger LOG = Logger.getLogger(Stax.class);
	private Set<Car> cars = new HashSet<>();
	private XMLInputFactory inputFactory;

	public Stax() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public void buildSetCars(String file) {
		FileInputStream input = null;
		XMLStreamReader reader = null;
		String name;
		try {
			input = new FileInputStream(new File(file));
			reader = inputFactory.createXMLStreamReader(input);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					if (CarEnum.valueOf(name) == CarEnum.car) {
						Car car = buildCar(reader);
						cars.add(car);
					}
				}
			}
		} catch (XMLStreamException e) {
			LOG.error("XMLStreamException: ", e);
		} catch (FileNotFoundException e) {
			LOG.error("FileNotFoundException: ", e);
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				LOG.error("IOException: ", e);
			}
		}
	}

	private Car buildCar(XMLStreamReader reader) throws XMLStreamException {
		Car car = new Car();
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch(CarEnum.valueOf(name)) {
				case model:
					car.setModel(getXMLText(reader));
					break;
				case user:
					car.setUser(buildUser(reader));
					break;
				case insurance:
					car.setInsurance(buildInsurance(reader));
					break;
				} 
			break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if(CarEnum.valueOf(name) == CarEnum.car) {
					return car;
				}
				break;
			}		
		} 
		throw new XMLStreamException("Unknown element in tag Car");
	}	

	private User buildUser(XMLStreamReader reader) throws XMLStreamException {
		User user = new User();
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch(CarEnum.valueOf(name)) {
				case name:
					user.setName(getXMLText(reader));
					break;
				case tariff:
					user.setTariff(buildTariff(reader));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if(CarEnum.valueOf(name) == CarEnum.user) {
					return user;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag User");
	}

	private Tariff buildTariff(XMLStreamReader reader) throws XMLStreamException {
		Tariff tariff = new Tariff();
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch(CarEnum.valueOf(name)) {
				case description:
					tariff.setDescription(getXMLText(reader));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if(CarEnum.valueOf(name) == CarEnum.tariff) {
					return tariff;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Tariff");
	}

	private Insurance buildInsurance(XMLStreamReader reader) throws NumberFormatException, XMLStreamException {
		Insurance insurance = new Insurance();
		String name;
		while(reader.hasNext()) {
			int type = reader.next();
			switch(type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				switch(CarEnum.valueOf(name)) {
				case cost:
					insurance.setCost(Integer.parseInt(getXMLText(reader)));
					break;
				case exp_date:
					insurance.setExp_date(getXMLText(reader));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if(CarEnum.valueOf(name) == CarEnum.insurance) {
					return insurance;
				}
			}
		}
		throw new XMLStreamException("Unknown element in tag Insurance");
	}

	public Set<Car> getCars() {
		return cars;
	}
	
	private String getXMLText(XMLStreamReader reader) {
		String text = null;
		try {
		if(reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		} catch(XMLStreamException e) {
			LOG.error("XMLStreamException: ", e);
		}
		return text;
	}

}
