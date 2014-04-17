package dbreader.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbreader.IDBReader;

public class MySqlReader implements IDBReader {

	@Override
	public List<String> getResulFromDB(String query) {
		List<String> resultsToReturn = new ArrayList<String>();
		ResultSet results = getResults(query, MysqlDBConnect.getConnection());
		if (results != null) {
			try {
				while (results.next()) {
					resultsToReturn.add(results.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultsToReturn;
	}

	public static ResultSet getResults(String query, Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

}
