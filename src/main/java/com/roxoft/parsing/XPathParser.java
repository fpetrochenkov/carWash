package main.java.com.roxoft.parsing;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import main.java.com.roxoft.model.Car;
import main.java.com.roxoft.model.Insurance;
import main.java.com.roxoft.model.Tariff;
import main.java.com.roxoft.model.User;

public class XPathParser {

	private static final Logger LOG = Logger.getLogger(XPathParser.class);

	public void parseXPath() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder;
		Document doc;
		try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse("src\\main\\resources\\cars.xml");
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			Car car = getCarByModel(doc, xpath, "Bentley");
			LOG.info(car);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			LOG.error(e);
		}
	}

	private Car getCarByModel(Document doc, XPath xpath, String model) {

		Car car = new Car();
		User user = new User();
		Tariff tariff = new Tariff();
		Insurance insurance = new Insurance();
		try {
			XPathExpression carExpr = xpath.compile("//car/model[contains(text(),'" + model + "')]");
			car.setModel((String) carExpr.evaluate(doc, XPathConstants.STRING));
			XPathExpression userExpr = xpath.compile("//car[model='" + model + "']/user/name/text()");
			user.setName((String) userExpr.evaluate(doc, XPathConstants.STRING));
			XPathExpression tariffExpr = xpath.compile("//car[model='" + model + "']/user/tariff/description/text()");
			tariff.setDescription((String) tariffExpr.evaluate(doc, XPathConstants.STRING));
			user.setTariff(tariff);
			car.setUser(user);
			XPathExpression insuranceExpr = xpath.compile("//car[model='" + model + "']/insurance/cost/text()");
			insurance.setCost(Integer.parseInt((String) insuranceExpr.evaluate(doc, XPathConstants.STRING)));
			insuranceExpr = xpath.compile("//car[model='" + model + "']/insurance/exp_date/text()");
			insurance.setExp_date((String) insuranceExpr.evaluate(doc, XPathConstants.STRING));
			car.setInsurance(insurance);
		} catch (XPathException e) {
			LOG.error(e);
		}
		return car;

	}

}
