package dbreader.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Constants;

public class MysqlDBConnect {
	private static MysqlDBConnect instance = null;
	private static Connection conn;

	private MysqlDBConnect() {

		try {
			if (conn == null) {
				Class.forName(Constants.dbDriver);
				conn = DriverManager.getConnection(Constants.mydata_url,
						Constants.mydata_username, Constants.mydata_password);
			}
		} catch (ClassNotFoundException cnfErr) {
			cnfErr.printStackTrace();
		} catch (SQLException err) {
			err.printStackTrace();
		}
	}

	public static MysqlDBConnect getInstance() {
		if (instance == null){
			instance = new MysqlDBConnect();
			return instance;
		}
		else
			return instance;
	}

	public static Connection getConnection() {
		return getInstance().conn;
	}
}
