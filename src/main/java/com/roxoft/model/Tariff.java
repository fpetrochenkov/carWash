package main.java.com.roxoft.model;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlType(name = "tariff", propOrder = {"description"})
public class Tariff {
	@JsonProperty("description")
	private String description;
	
	public Tariff() {}
	
	public Tariff(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
