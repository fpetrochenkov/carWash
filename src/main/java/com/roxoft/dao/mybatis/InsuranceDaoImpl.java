package main.java.com.roxoft.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import main.java.com.roxoft.dao.IInsuranceDao;
import main.java.com.roxoft.model.Insurance;

public class InsuranceDaoImpl implements IInsuranceDao {
	
	private static final String NAMESPACE = "main.java.com.roxoft.dao.IInsuranceDao.";

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
	public void insert() {
		// TODO Auto-generated method stub		
	}

	@Override
	public Insurance getInsuranceById(int id) {
		Insurance insurance = new Insurance();
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			IInsuranceDao mapper = session.getMapper(IInsuranceDao.class);
			insurance = mapper.getInsuranceById(id);
		} finally {
			session.close();
		}
		return insurance;
	}

	@Override
	public List<Insurance> getAll() {
		List<Insurance> list;		
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			IInsuranceDao mapper = session.getMapper(IInsuranceDao.class);
			list = mapper.getAll();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void insert(Insurance insurance) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		String query = NAMESPACE + "insert";
		try {
			session.insert(query, insurance);
			session.commit();
		} finally {
			session.close();
		}		
	}

}
