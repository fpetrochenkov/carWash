package main.java.com.roxoft.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.roxoft.dao.IUserDao;
import main.java.com.roxoft.data.Users;
import main.java.com.roxoft.model.Tariff;
import main.java.com.roxoft.model.User;

public class UserDaoImpl extends AbstractDao implements IUserDao {

	private static final Logger LOG = Logger.getLogger(UserDaoImpl.class);
	private Connection cn;

	public UserDaoImpl(Connection cn) {
		this.cn = cn;
	}

	private static final String SQL_INSERT = "INSERT INTO users (name, tariff) VALUES(?,?)";
	private static final String SQL_UPDATE = "UPDATE users SET name = 'Alex', tariff = '2' WHERE id = ?";
	private static final String SQL_GET_ALL = "SELECT name, tariff, description FROM users INNER JOIN tariffs ON tariffs.id = tariff";
	private static final String SQL_GET_BY_ID = "SELECT name, tariff, description FROM users INNER JOIN tariffs ON tariffs.id = tariff WHERE users.id = ?";
	private static final String SQL_DELETE = "DELETE FROM users WHERE id = ?";

	@Override
	public void update(int id) {
		PreparedStatement st = null;
		try {
			st = cn.prepareStatement(SQL_UPDATE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			closePreparedStatement(st);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement st = null;
		try {
			st = cn.prepareStatement(SQL_DELETE);
			st.setInt(1, id);
			st.executeUpdate();
		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			closePreparedStatement(st);
		}
	}

	@Override
	public void insert() {
		ArrayList<String> names = new ArrayList<>(Arrays.asList(new Users().getUsers()));
		PreparedStatement st = null;
		try {
			st = cn.prepareStatement(SQL_INSERT);
			for (int i = 0; i < names.size(); i++) {
				st.setString(1, names.get(i));
				st.setInt(2, i + 1);
				st.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			closePreparedStatement(st);
		}
	}

	@Override
	public User getUserById(int id) {
		User user = new User();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				user.setName(rs.getString("name"));
				Tariff tariff = new Tariff();
				tariff.setDescription(rs.getString("description"));
				user.setTariff(tariff);
			}

		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOG.error("SQLException: ", e);
			}
			closePreparedStatement(st);
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		ArrayList<User> users = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_ALL);
			rs = st.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setName(rs.getString("name"));
				users.add(user);
				Tariff tariff = new Tariff();
				tariff.setDescription(rs.getString("description"));
				user.setTariff(tariff);
			}
		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOG.error("SQLException: ", e);
			}
			closePreparedStatement(st);
		}
		return users;
	}

	@Override
	public void insert(User user) {
		// TODO Auto-generated method stub
		
	}

}
