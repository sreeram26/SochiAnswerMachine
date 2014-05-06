package raghavan.query;

import util.Constants;
import util.QueryComponent;

public class QueryCreator {

	public String getSqlQueryFromQueryComponent(QueryComponent queryComponent) {

		StringBuffer query = new StringBuffer(Constants.GET_SOCHI_RESULT);

		int numberOfParameters=0;
		numberOfParameters += ifPresentAddArgument(query, "a.nationality", queryComponent.getNationality(), true);
		numberOfParameters += ifPresentAddArgument(query, "a.gender", queryComponent.getGender());
		numberOfParameters += ifPresentAddArgument(query, "a.name", queryComponent.getPerson());
		numberOfParameters += ifPresentAddArgument(query, "r.medal", queryComponent.getMedal());
		numberOfParameters += ifPresentAddArgument(query, "c.type", queryComponent.getCompetitionType());
		numberOfParameters += ifPresentAddArgument(query, "c.name", queryComponent.getCompetition());

		if(numberOfParameters > 1)
			return query.toString();
		
		return null;
	}

	private int ifPresentAddArgument(StringBuffer query, String parameter, String argument) {
		return ifPresentAddArgument(query, parameter, argument, false);
	}

	private int ifPresentAddArgument(StringBuffer query, String parameter, String argument, boolean isLikeCondition) {
		if (isPresent(argument)) {
			query.append(appendQueryCondition(parameter, argument, isLikeCondition));
			return 1;
		}
		return 0;
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
