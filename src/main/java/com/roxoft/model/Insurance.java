package main.java.com.roxoft.model;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlType(name = "insurance", propOrder = {"cost","exp_date"})
public class Insurance {
	@JsonProperty("cost")
	private int cost;
	@JsonProperty("exp_date")
	private String exp_date;
	
	public Insurance() {}
	
	public Insurance(int cost, String exp_date) {
		this.cost = cost;
		this.exp_date = exp_date;
	}
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String exp_date) {
		this.exp_date = exp_date;
	}
	
	@Override
	public String toString() {
		return "Cost: " + cost + " Exp-Date: " + exp_date;
	}

}
