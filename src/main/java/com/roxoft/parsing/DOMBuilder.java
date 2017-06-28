package main.java.com.roxoft.parsing;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import main.java.com.roxoft.model.Car;
import main.java.com.roxoft.model.Insurance;
import main.java.com.roxoft.model.Tariff;
import main.java.com.roxoft.model.User;

public class DOMBuilder {
	
	private static final Logger LOG = Logger.getLogger(DOMBuilder.class);

	private Set<Car> cars;
	DocumentBuilder docBuilder;

	public DOMBuilder() {
		this.cars = new HashSet<Car>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOG.error(e);
		}
	}

	public Set<Car> getCars() {
		return cars;
	}

	public void buildSetCars(String fileName) {
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList carsList = root.getElementsByTagName("car");
			for (int i = 0; i < carsList.getLength(); i++) {
				Element carElement = (Element) carsList.item(i);
				Car car = buildCar(carElement);
				cars.add(car);
			}
		} catch (IOException e) {
			LOG.error(e);
		} catch (SAXException e) {
			LOG.error(e);
		}
	}

	private Car buildCar(Element carElement) {
		Car car = new Car();
		car.setModel(getElementTextContent(carElement, "model"));
		User user = new User();
		Element userElement = (Element) carElement.getElementsByTagName("user").item(0);
		user.setName(getElementTextContent(userElement, "name"));			
		Tariff tariff = new Tariff();
		Element tariffElement = (Element) userElement.getElementsByTagName("tariff").item(0);
		tariff.setDescription(getElementTextContent(tariffElement, "description"));
		user.setTariff(tariff);
		car.setUser(user);
		Insurance insurance = new Insurance();
		Element insuranceElement = (Element) carElement.getElementsByTagName("insurance").item(0);
		insurance.setExp_date(getElementTextContent(insuranceElement, "exp_date"));
		Integer cost = Integer.parseInt(getElementTextContent(insuranceElement, "cost"));
		insurance.setCost(cost);
		car.setInsurance(insurance);
		return car;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}

}
