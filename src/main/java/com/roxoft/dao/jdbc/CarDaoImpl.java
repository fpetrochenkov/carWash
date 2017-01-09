package main.java.com.roxoft.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.roxoft.dao.ICarDao;
import main.java.com.roxoft.data.Cars;
import main.java.com.roxoft.model.Car;
import main.java.com.roxoft.model.Insurance;
import main.java.com.roxoft.model.Tariff;
import main.java.com.roxoft.model.User;

public class CarDaoImpl extends AbstractDao implements ICarDao {

	private static final Logger LOG = Logger.getLogger(CarDaoImpl.class);
	private Connection cn;

	public CarDaoImpl(Connection cn) {
		this.cn = cn;
	}

	private static final String SQL_INSERT = "INSERT INTO cars (model, user, insurance) VALUES(?,?,?)";
	private static final String SQL_UPDATE = "UPDATE cars SET model = 'Ferrari', user = '2', insurance = '3' WHERE id = ?";
	private static final String SQL_GET_ALL = "SELECT model, name, description, cost, exp_date FROM cars INNER JOIN users ON users.id = user INNER JOIN tariffs ON tariffs.id = tariff INNER JOIN insurance ON insurance.id = insurance";
	private static final String SQL_GET_BY_ID = "SELECT model, name, description, cost, exp_date FROM cars INNER JOIN users ON users.id = user INNER JOIN tariffs ON tariffs.id = tariff INNER JOIN insurance ON insurance.id = insurance WHERE cars.id = ?";
	private static final String SQL_DELETE = "DELETE FROM cars WHERE id = ?";

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
	public Car getCarById(int id) {
		Car car = new Car();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				car.setModel(rs.getString("model"));
				User user = new User();
				user.setName(rs.getString("name"));
				car.setUser(user);
				Tariff tariff = new Tariff();
				tariff.setDescription(rs.getString("description"));
				user.setTariff(tariff);
				Insurance insurance = new Insurance();
				insurance.setCost(rs.getInt("cost"));
				insurance.setExp_date(rs.getString("exp_date"));
				car.setInsurance(insurance);
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
		return car;
	}

	@Override
	public List<Car> getAll() {
		List<Car> cars = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_ALL);
			rs = st.executeQuery();
			while (rs.next()) {
				Car car = new Car();
				car.setModel(rs.getString("model"));
				cars.add(car);
				User user = new User();
				user.setName(rs.getString("name"));
				car.setUser(user);
				Tariff tariff = new Tariff();
				tariff.setDescription(rs.getString("description"));
				user.setTariff(tariff);
				Insurance insurance = new Insurance();
				insurance.setCost(rs.getInt("cost"));
				insurance.setExp_date(rs.getString("exp_date"));
				car.setInsurance(insurance);
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
		return cars;
	}

	@Override
	public void insert() {
		ArrayList<String> cars = new ArrayList<>(Arrays.asList(new Cars().getCars()));
		PreparedStatement st = null;
		try {
			st = cn.prepareStatement(SQL_INSERT);
			for (int i = 0; i < cars.size(); i++) {
				st.setString(1, cars.get(i));
				st.setInt(2, i + 1);
				st.setInt(3, i + 1);
				st.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			closePreparedStatement(st);
		}
	}

	@Override
	public void insert(Car car) {
		// TODO Auto-generated method stub
		
	}

}
