package raghavan.query;

import java.util.List;

import util.QueryComponent;
import util.Result;
import dbreader.IDBReader;
import dbreader.sqlite.SqliteReader;

public class SqlLiteQueryResultGenerator implements IQueryResultGenerator {

	IDBReader dbReader = new SqliteReader();

	@Override
	public Result getResultForQuery(QueryComponent queryComponent) {
		Result result = new Result();
		QueryCreator queryCreator = new QueryCreator();
		String query = queryCreator.getSqlQueryFromQueryComponent(queryComponent);
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
		return result;
	}
}
