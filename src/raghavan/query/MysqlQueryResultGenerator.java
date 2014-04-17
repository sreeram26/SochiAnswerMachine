package raghavan.query;

import java.util.List;

import dbreader.IDBReader;
import dbreader.mysql.MySqlReader;
import util.QueryComponent;
import util.Result;

public class MysqlQueryResultGenerator implements IQueryResultGenerator {

	IDBReader dbReader = new MySqlReader();

	@Override
	public Result getResultForQuery(QueryComponent queryComponent) {
		Result result = new Result();
		QueryCreator queryCreator = new QueryCreator();
		String query = queryCreator.getSqlQueryFromQueryComponent(queryComponent);
		System.out.println(query);
		if (query != null) {
			List<String> resultsFromDB = dbReader.getResulFromDB(query);
			if (!resultsFromDB.isEmpty()) {
				if (queryComponent.getQueryType() == QueryType.DID) {
					int count = Integer.parseInt(resultsFromDB.get(0));
					if (count > 0) {
						result.addResult("Yes");
					} else {
						result.addResult("No");
					}
				} else {
					result.addAllResults(resultsFromDB);
				}
			}
		}
		return result;
	}

}
