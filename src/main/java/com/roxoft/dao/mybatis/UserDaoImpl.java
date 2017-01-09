package main.java.com.roxoft.dao.mybatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import main.java.com.roxoft.dao.IUserDao;
import main.java.com.roxoft.model.User;

public class UserDaoImpl implements IUserDao{
	
	private static final String NAMESPACE = "main.java.com.roxoft.dao.IUserDao.";

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
	public User getUserById(int id) {
		User user = new User();
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			IUserDao mapper = session.getMapper(IUserDao.class);
			user = mapper.getUserById(id);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> list;		
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		try {
			IUserDao mapper = session.getMapper(IUserDao.class);
			list = mapper.getAll();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void insert(User user) {
		SqlSession session = SessionFactory.getInstance().getSqlSessionFactory().openSession();
		String query = NAMESPACE + "insert";
		try {
			session.insert(query, user);
			session.commit();
		} finally {
			session.close();
		}		
	}

}
