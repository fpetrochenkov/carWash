package main.java.com.roxoft.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import main.java.com.roxoft.dao.ITariffDao;
import main.java.com.roxoft.data.TariffDescriptions;
import main.java.com.roxoft.model.Tariff;

public class TariffDaoImpl extends AbstractDao implements ITariffDao {

	private static final Logger LOG = Logger.getLogger(TariffDaoImpl.class);
	private Connection cn;

	public TariffDaoImpl(Connection cn) {
		this.cn = cn;
	}

	private static final String SQL_INSERT = "INSERT INTO tariffs (description) VALUES(?)";
	private static final String SQL_UPDATE = "UPDATE tariffs SET description = '180$ tariff' WHERE id = ?";
	private static final String SQL_GET_ALL = "SELECT * FROM tariffs";
	private static final String SQL_GET_BY_ID = "SELECT description FROM tariffs WHERE id = ?";
	private static final String SQL_DELETE = "DELETE  FROM tariffs WHERE id = ?";

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
		ArrayList<String> descriptions = new ArrayList<>(Arrays.asList(new TariffDescriptions().getDescriptions()));
		PreparedStatement st = null;
		try {
			st = cn.prepareStatement(SQL_INSERT);
			for (String str : descriptions) {
				st.setString(1, str);
				st.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error("SQLException: ", e);
		} finally {
			closePreparedStatement(st);
		}
	}

	@Override
	public Tariff getTariffById(int id) {
		Tariff tariff = new Tariff();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				tariff.setDescription(rs.getString("description"));
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
		return tariff;
	}

	@Override
	public List<Tariff> getAll() {
		ArrayList<Tariff> tariffs = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = cn.prepareStatement(SQL_GET_ALL);
			rs = st.executeQuery();
			while (rs.next()) {
				Tariff tariff = new Tariff();
				tariff.setDescription(rs.getString("description"));
				tariffs.add(tariff);
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
		return tariffs;
	}

	@Override
	public void insert(Tariff tariff) {
		// TODO Auto-generated method stub
		
	}
}
