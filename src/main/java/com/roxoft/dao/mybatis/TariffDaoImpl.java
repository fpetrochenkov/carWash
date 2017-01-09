package main.java.com.roxoft.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import main.java.com.roxoft.dao.ITariffDao;
import main.java.com.roxoft.model.Tariff;

public class TariffDaoImpl implements ITariffDao{
	
	private static final String NAMESPACE = "main.java.com.roxoft.dao.ITariffDao.";

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
	public void insert(Tariff tariff) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		String query = NAMESPACE + "insert";
		try {
			session.insert(query, tariff);
			session.commit();
		} finally {
			session.close();
		}		
	}

	@Override
	public Tariff getTariffById(int id) {
		Tariff tariff = new Tariff();
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			ITariffDao mapper = session.getMapper(ITariffDao.class);
			tariff = mapper.getTariffById(id);
		} finally {
			session.close();
		}
		return tariff;
	}

	@Override
	public List<Tariff> getAll() {
		List<Tariff> list;		
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			ITariffDao mapper = session.getMapper(ITariffDao.class);
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

}
