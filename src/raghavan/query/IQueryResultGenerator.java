package raghavan.query;

import util.QueryComponent;
import util.Result;

public interface IQueryResultGenerator {

	public Result getResultForQuery(QueryComponent queryComponent);

}
