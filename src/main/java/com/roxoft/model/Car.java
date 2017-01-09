package main.java.com.roxoft.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.fasterxml.jackson.annotation.JsonProperty;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "car", propOrder = {"model","user", "insurance"})
public class Car {
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(required = true)	
	@JsonProperty("model")
	private String model;
	@XmlElement(required = true)
	@JsonProperty("user")
	private User user;
	@XmlElement(required = true)
	@JsonProperty("insurance")
	private Insurance insurance;
	
	public Car() {}
	
	public Car(String model, User user, Insurance insurance) {
		this.model = model;
		this.user = user;
		this.insurance = insurance;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Insurance getInsurance() {
		return insurance;
	}
	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}
	
	@Override
	public String toString() {
		return "\nModel: " + model + "\nUser: " + user + "\nInsurance: " + insurance;
	}

}
