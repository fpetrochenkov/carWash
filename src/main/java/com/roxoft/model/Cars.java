package main.java.com.roxoft.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
@XmlRootElement
public class Cars {
	
	@XmlElement(name = "car")
	@JsonProperty("cars")
	private ArrayList<Car> cars = new ArrayList<>();
	public Cars() {
		super();
	}
	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}
	
	@Override
	public String toString() {
		return "cars: " + cars;
	}

}
