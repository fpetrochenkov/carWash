package main.java.com.roxoft.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import main.java.com.roxoft.dao.jdbc.CarDaoImpl;
import main.java.com.roxoft.dao.jdbc.InsuranceDaoImpl;
import main.java.com.roxoft.dao.jdbc.TariffDaoImpl;
import main.java.com.roxoft.dao.jdbc.UserDaoImpl;
import main.java.com.roxoft.dbcp.DataSourceFactory;

public class Service {

	private static final Logger LOG = Logger.getLogger(Service.class);
	DataSource dataSource = DataSourceFactory.getDataSource();
	Connection connection = null;

	public void fillDataBase() {
		try {
			connection = dataSource.getConnection();
			TariffDaoImpl t = new TariffDaoImpl(connection);
			InsuranceDaoImpl i = new InsuranceDaoImpl(connection);
			UserDaoImpl u = new UserDaoImpl(connection);
			CarDaoImpl c = new CarDaoImpl(connection);
//			t.insert();
//			i.insert();
//			u.insert();
//			c.insert();
			LOG.info(c.getAll());
		} catch (SQLException e) {
			LOG.error("Can't connect to database", e);
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				LOG.error("Can't create connection to database", e);
			}
		}
	}

}
