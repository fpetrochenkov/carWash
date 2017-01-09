package main.java.com.roxoft.dao;
import java.util.List;

import main.java.com.roxoft.model.Car;

public interface ICarDao {
	
	public void update(int id);
	public void delete(int id);
	public Car getCarById(int id);
	public List<Car> getAll();
	public void insert();
	public void insert(Car car);

}
