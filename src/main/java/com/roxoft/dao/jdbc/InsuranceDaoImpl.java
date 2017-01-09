package main.java.com.roxoft.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.roxoft.dao.IInsuranceDao;
import main.java.com.roxoft.data.InsuranceCosts;
import main.java.com.roxoft.data.InsuranceExpDates;
import main.java.com.roxoft.model.Insurance;

public class InsuranceDaoImpl extends AbstractDao implements IInsuranceDao {

	private static final Logger LOG = Logger.getLogger(InsuranceDaoImpl.class);
	private Connection cn;

	public InsuranceDaoImpl(Connection cn) {
		this.cn = cn;
	}

	private static final String SQL_INSERT = "INSERT INTO insurance (cost, exp_date) VALUES(?,?)";
	private static final String SQL_UPDATE = "UPDATE insurance SET cost = '180', exp_date = '05-2017' WHERE id = ?";
	private static final String SQL_GET_ALL = "SELECT * FROM insurance";
	private static final String SQL_GET_BY_ID = "SELECT cost, exp_date FROM insurance WHERE id = ?";
	private static final String SQL_DELETE = "DELETE FROM insurance WHERE id = ?";

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
		ArrayList<Integer> costs = new ArrayList<>(Arrays.asList(new InsuranceCosts().getCosts()));
		ArrayList<String> dates = new ArrayList<>(Arrays.asList(new InsuranceExpDates().getDates()));
		PreparedStatement st = null;
		try {
			st = cn.prepareStatement(SQL_INSERT);
			for (int i = 0; i < dates.size(); i++) {
				st.setInt(1, costs.get(i));
				st.setString(2, dates.get(i));
				st.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			closePreparedStatement(st);
		}
	}

	@Override
	public Insurance getInsuranceById(int id) {
		Insurance insurance = new Insurance();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				insurance.setCost(rs.getInt("cost"));
				insurance.setExp_date(rs.getString("exp_date"));
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
		return insurance;
	}

	@Override
	public List<Insurance> getAll() {
		ArrayList<Insurance> list = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_ALL);
			rs = st.executeQuery();
			while (rs.next()) {
				Insurance insurance = new Insurance();
				insurance.setCost(rs.getInt("cost"));
				insurance.setExp_date(rs.getString("exp_date"));
				list.add(insurance);
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
		return list;
	}

	@Override
	public void insert(Insurance insurance) {
		// TODO Auto-generated method stub
		
	}

}
