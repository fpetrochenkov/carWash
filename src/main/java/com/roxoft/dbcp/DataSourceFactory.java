package main.java.com.roxoft.dbcp;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class DataSourceFactory {
	
	private static final Logger LOG = Logger.getLogger(DataSourceFactory.class);
	private static Connection connection = null;
	private static BasicDataSource dataSource = null;

	public static DataSource getDataSource() {
		try {
			Properties prop = new Properties();
			FileInputStream input = null;
			input = new FileInputStream("src\\main\\resources\\database.properties");
			prop.load(input);
			if (dataSource == null) {
				dataSource = new BasicDataSource();
				dataSource.setUsername(prop.getProperty("username"));
				dataSource.setPassword(prop.getProperty("password"));
				dataSource.setUrl(prop.getProperty("url"));
				dataSource.setDriverClassName(prop.getProperty("driver"));
				dataSource.setInitialSize(1);// number of connections on start
				dataSource.setMaxTotal(10);// max number of connections
				dataSource.setMaxConnLifetimeMillis(6000);// max time for each
															// connection
			}

			try {
				connection = dataSource.getConnection();
			} catch (SQLException e) {
				LOG.error("SQLException: ", e);
			}
		} catch (IOException e) {
			LOG.error("IOException: ", e);
		}

		return dataSource;
	}
}
