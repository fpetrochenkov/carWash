package main.java.com.roxoft.dao.jdbc;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public abstract class AbstractDao {
	
	private static final Logger LOG = Logger.getLogger(AbstractDao.class);
	
	public void closePreparedStatement(PreparedStatement st) {
		try {
			if(st != null) {
				st.close();
			}
		} catch(SQLException e) {
			LOG.error("SQLException: ", e);
		}
	}

}
