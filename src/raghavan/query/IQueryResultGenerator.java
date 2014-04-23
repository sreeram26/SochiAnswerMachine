package raghavan.query;

import java.util.List;

import util.QueryComponent;
import util.Result;

public interface IQueryResultGenerator {

	public Result getResultForQuery(QueryComponent queryComponent);

	public List<String> getAllCountriesFromDB();
}
