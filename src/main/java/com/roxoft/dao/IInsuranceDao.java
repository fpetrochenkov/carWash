package main.java.com.roxoft.dao;
import java.util.List;

import main.java.com.roxoft.model.Insurance;

public interface IInsuranceDao {
	
	public void update(int id);
	public void delete(int id);
	public void insert();
	public Insurance getInsuranceById(int id);
	public List<Insurance> getAll();
	void insert(Insurance insurance);

}
