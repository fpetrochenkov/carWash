package main.java.com.roxoft.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlType(name = "user", propOrder = {"name","tariff"})
public class User {
	@JsonProperty("name")
	private String name;
	@JsonProperty("tariff")
	private Tariff tariff;
	
	public User() {}
	
	public User(String name, Tariff tariff) {
		this.name = name;
		this.tariff =tariff; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Tariff getTariff() {
		return tariff;
	}
	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}
	
	@Override
	public String toString() {
		return name + "\nTariff: " + tariff;
	}
	

}
