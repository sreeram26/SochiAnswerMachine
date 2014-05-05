package raghavan.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.Constants;
import util.QueryComponent;
import util.Result;
import dbreader.DbReader;
import dbreader.mysql.MysqlDBConnect;
import dbreader.sqlite.SqliteDBConnect;

public class QueryResultGenerator implements IQueryResultGenerator {

	Connection conn = SqliteDBConnect.getConnection();
	
	DbReader dbReader = new DbReader(conn);

	@Override
	public Result getResultForQuery(QueryComponent queryComponent) {
		Result result = new Result();
		QueryCreator queryCreator = new QueryCreator();
		String query = queryCreator.getSqlQueryFromQueryComponent(queryComponent);
		System.out.println(query);
		if (query != null) {
			List<SochiResult> sochiResults = dbReader.getSochiResultFromDB(query);

			if (queryComponent.getQueryType() == QueryType.DID) {
				if (sochiResults != null && sochiResults.size() >= 1) {
					result.addResult("Yes");
				} else {
					result.addResult("No");
				}

			} else {
				if (sochiResults != null) {
					for (SochiResult sochiResult : sochiResults) {
						if (queryComponent.getQueryType() == QueryType.WHO) {
							result.addResult(sochiResult.getPlayerName());
						}
					}
				} else {
					result.addResult("No result found");
				}
			}

		}
		System.out.println();
		System.out.println(result);
		return result;
	}

	public List<String> getAllCountriesFromDB() {
		List<String> countries = new ArrayList<String>();
		ResultSet results = dbReader.getResults(Constants.GET_ALL_COUNTRIES, MysqlDBConnect.getConnection());
		if (results != null) {
			try {
				while (results.next()) {
					countries.add(results.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return countries;
	}
}
