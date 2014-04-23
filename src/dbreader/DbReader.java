package dbreader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import raghavan.query.SochiResult;
import util.Constants;
import dbreader.mysql.MysqlDBConnect;

public class DbReader{

	Connection conn;

	public DbReader(Connection conn) {
		this.conn = conn;
	}

	public List<SochiResult> getSochiResultFromDB(String query) {
		List<SochiResult> sochiResults = new ArrayList<SochiResult>();
		ResultSet results = getResults(query, conn);
		if (results != null) {
			try {
				while (results.next()) {
					SochiResult sochiResult = new SochiResult();
					sochiResult.setCompetitionName(results.getString("competition_name"));
					sochiResult.setCompetitionType(results.getString("competition_type"));
					sochiResult.setPlayerName(results.getString("player_name"));
					sochiResult.setPlayerNationality(results.getString("player_nationality"));
					sochiResult.setGender(results.getString("player_gender"));
					sochiResult.setMedal(results.getString("medal"));

					sochiResults.add(sochiResult);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sochiResults;
	}

	public ResultSet getResults(String query, Connection conn) {
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
