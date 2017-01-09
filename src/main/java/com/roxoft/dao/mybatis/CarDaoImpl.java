package main.java.com.roxoft.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import main.java.com.roxoft.dao.ICarDao;
import main.java.com.roxoft.model.Car;

public class CarDaoImpl implements ICarDao{
	
	private static final String NAMESPACE = "main.java.com.roxoft.dao.ICarDao.";

	@Override
	public void update(int id) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		String query = NAMESPACE + "update";
		try {			
			session.update(query, id);
			session.commit();			
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(int id) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		String query = NAMESPACE + "delete";
		try {
			session.delete(query, id);
			session.commit();
		} finally {
			session.close();
		}
	}

	@Override
	public Car getCarById(int id) {
		Car car = new Car();
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			ICarDao mapper = session.getMapper(ICarDao.class);
			car = mapper.getCarById(id);
		} finally {
			session.close();
		}
		return car;
	}

	@Override
	public List<Car> getAll() {
		List<Car> list;		
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			ICarDao mapper = session.getMapper(ICarDao.class);
			list = mapper.getAll();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Car car) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		String query = NAMESPACE + "insert";
		try {
			session.insert(query, car);
			session.commit();
		} finally {
			session.close();
		}		
	}

}
