package main.java.com.roxoft.parsing;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.java.com.roxoft.model.Cars;

public class Jackson {
	
	private static final Logger LOG = Logger.getLogger(Jackson.class);
	
	public void jackson() throws JsonParseException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	Cars cars = mapper.readValue(new File("src\\main\\resources\\cars.json"), Cars.class);
	LOG.info(cars);
	}

}
