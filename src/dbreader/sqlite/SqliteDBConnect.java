package dbreader.sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.Constants;

public class SqliteDBConnect {
	private static SqliteDBConnect instance = null;
	private static Connection conn;

	private SqliteDBConnect() {

		try {
			if (conn == null) {
				Class.forName(Constants.sqlite_dbDriver);
				conn = DriverManager.getConnection(Constants.sqlite_url);
			}
		} catch (ClassNotFoundException cnfErr) {
			cnfErr.printStackTrace();
		} catch (SQLException err) {
			err.printStackTrace();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static SqliteDBConnect getInstance() {
		if (instance == null){
			instance = new SqliteDBConnect();
			return instance;
		}
		else
			return instance;
	}

	public static Connection getConnection() {
		return getInstance().conn;
	}
}
