package main.java.com.roxoft.dao;
import java.util.List;

import main.java.com.roxoft.model.Tariff;

public interface ITariffDao {
	
	public void update(int id);
	public void delete(int id);
	public void insert();
	public Tariff getTariffById(int id);
	public List<Tariff> getAll();
	void insert(Tariff tariff);

}
