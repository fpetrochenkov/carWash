package main.java.com.roxoft.model;

public enum CarEnum {

	cars("cars"), car("car"), model("model"), user("user"), name("name"), tariff("tariff"), description(
			"description"), insurance("insurance"), cost("cost"), exp_date("exp_date");

	private String value;

	private CarEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
