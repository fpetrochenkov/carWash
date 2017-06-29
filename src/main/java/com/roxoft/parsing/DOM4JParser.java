package main.java.com.roxoft.parsing;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import main.java.com.roxoft.model.Car;
import main.java.com.roxoft.model.Insurance;
import main.java.com.roxoft.model.Tariff;
import main.java.com.roxoft.model.User;

public class DOM4JParser {

	private static final Logger LOG = Logger.getLogger(DOM4JParser.class);

	public void parseDOM4J() {
		try {
			File inputFile = new File("src\\main\\resources\\cars.xml");
			SAXReader reader = new SAXReader();
			Document doc = reader.read(inputFile);
			Element root = doc.getRootElement();
			Iterator itr = root.elementIterator();
			List<Car> cars = new ArrayList<>();
			Car car;
			while (itr.hasNext()) {
				car = new Car();
				Node node = (Node) itr.next();
				car.setModel(node.selectSingleNode("model").getText());
				User user = new User();
				user.setName(node.selectSingleNode("user").selectSingleNode("name").getText());
				Tariff tariff = new Tariff();
				tariff.setDescription(node.selectSingleNode("user").selectSingleNode("tariff")
						.selectSingleNode("description").getText());
				user.setTariff(tariff);
				car.setUser(user);
				Insurance insurance = new Insurance();
				insurance.setCost(
						Integer.parseInt(node.selectSingleNode("insurance").selectSingleNode("cost").getText()));
				insurance.setExp_date(node.selectSingleNode("insurance").selectSingleNode("exp_date").getText());
				car.setInsurance(insurance);
				cars.add(car);
			}
			LOG.info(cars);
		} catch (DocumentException e) {
			LOG.error(e);
		}

	}

}
