package main.java.com.roxoft.parsing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import main.java.com.roxoft.model.Car;
import main.java.com.roxoft.model.Insurance;
import main.java.com.roxoft.model.Tariff;
import main.java.com.roxoft.model.User;

import org.apache.log4j.Logger;

public class JDOM {

	private static final Logger LOG = Logger.getLogger(JDOM.class);

	public void parseJDOM() {

		final String fileName = "src\\main\\resources\\cars.xml";
		org.jdom2.Document jdomDoc;
		try {
			jdomDoc = useDOMParser(fileName);
			Element root = jdomDoc.getRootElement();
			List<Element> carElements = root.getChildren("car");
			List<Car> cars = new ArrayList<>();
			for (Element carElement : carElements) {
				Car car = new Car();
				car.setModel(carElement.getChildText("model"));
				User user = new User();
				Element userElement = carElement.getChild("user");
				user.setName(userElement.getChildText("name"));
				Tariff tariff = new Tariff();
				Element tariffElement = userElement.getChild("tariff");
				tariff.setDescription(tariffElement.getChildText("description"));
				user.setTariff(tariff);
				car.setUser(user);
				Insurance insurance = new Insurance();
				Element insuranceElement = carElement.getChild("insurance");
				insurance.setCost(Integer.parseInt(insuranceElement.getChildText("cost")));
				insurance.setExp_date(insuranceElement.getChildText("exp_date"));
				car.setInsurance(insurance);
				cars.add(car);
			}
			for (Car c : cars) {
				LOG.info(c);
			}
		} catch (Exception e) {
			LOG.error(e);
		}

	}

	private org.jdom2.Document useDOMParser(String fileName)
			throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(fileName));
		DOMBuilder domBuilder = new DOMBuilder();
		return domBuilder.build(doc);

	}

}
