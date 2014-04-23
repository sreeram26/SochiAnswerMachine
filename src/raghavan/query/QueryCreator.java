package raghavan.query;

import util.Constants;
import util.QueryComponent;

public class QueryCreator {

	public String getSqlQueryFromQueryComponent(QueryComponent queryComponent) {

		StringBuffer query = new StringBuffer(Constants.GET_SOCHI_RESULT);

		ifPresentAddArgument(query, "a.nationality", queryComponent.getNationality(), true);
		ifPresentAddArgument(query, "a.gender", queryComponent.getGender());
		ifPresentAddArgument(query, "a.name", queryComponent.getPerson());
		ifPresentAddArgument(query, "r.medal", queryComponent.getMedal());
		ifPresentAddArgument(query, "c.type", queryComponent.getCompetitionType());
		ifPresentAddArgument(query, "c.name", queryComponent.getCompetition());

		return query.toString();
	}

	private void ifPresentAddArgument(StringBuffer query, String parameter, String argument) {
		ifPresentAddArgument(query, parameter, argument, false);
	}

	private void ifPresentAddArgument(StringBuffer query, String parameter, String argument, boolean isLikeCondition) {
		if (isPresent(argument)) {
			query.append(appendQueryCondition(parameter, argument, isLikeCondition));
		}
	}

	private String appendQueryCondition(String parameter, String argument, boolean isLikeCondition) {
		StringBuffer queryArgument = new StringBuffer(" and ");
		if (isLikeCondition) {
			queryArgument.append(parameter + " like \"" + argument + "%\"");
		} else {
			queryArgument.append(parameter + " = \"" + argument + "\"");
		}
		return queryArgument.toString();
	}

	private boolean isPresent(String value) {
		if (value != null && !value.isEmpty()) {
			return true;
		}
		return false;
	}


}
