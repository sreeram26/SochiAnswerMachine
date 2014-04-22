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
		if (query != null) {
			List<SochiResult> sochiResults = dbReader.getSochiResultFromDB(query);

			if (queryComponent.getQueryType() == QueryType.DID) {
				if (sochiResults != null && sochiResults.size() > 1) {
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
				}else{
					result.addResult("No result found");
				}
			}

		}
		return result;
	}
}
