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

		/*
		 * if(queryComponent.getNationality() != null){ query =
		 * "select count(*) from athletes a, competitions c,results r where a.nationality like \""
		 * +
		 * queryComponent.getNationality()+"%\" and a.gender = \""+queryComponent
		 * .getGender()+"\" " +
		 * "and c.name = \""+queryComponent.getCompetition()
		 * +"\" and r.medal = \""+queryComponent.getMedal()+"\"  " +
		 * "and c.comp_id = r.comp_id  and a.name = r.winner"; }else{ query =
		 * "select count(*) from athletes a,competitions c,results r where a.name = \""
		 * +queryComponent.getPerson()+"\" " +
		 * "and c.name = \""+queryComponent.getCompetition
		 * ()+"\" and r.medal = \""+queryComponent.getMedal()+"\" " +
		 * "and r.comp_id = c.comp_id  and  r.winner = a.name"; } } else if
		 * (queryComponent.getQueryType() == QueryType.WHO) { query =
		 * "select a.name from athletes a, competitions c,results r where  c.name = \""
		 * +
		 * queryComponent.getCompetition()+"\" and r.medal = \""+queryComponent.
		 * getMedal()+"\"  and c.comp_id = r.comp_id  and a.name = r.winner"; }
		 * 
		 * if(queryComponent.getCompetitionType() != null &&
		 * !queryComponent.getCompetitionType().isEmpty()){ query +=
		 * " and c.type =\""+queryComponent.getCompetitionType()+"\""; } query
		 * += ";";
		 */

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

	private String appendQueryCondition(String parameter, String argument) {
		return appendQueryCondition(parameter, argument, false);
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

	/*
	 * -- Did Groothuis win gold in speedskating? select count(*) from athletes
	 * a,competitions c,results r where a.name = "groothuis" and c.name =
	 * "speedskating" and r.medal = "gold" and r.comp_id = c.comp_id and
	 * r.winner = a.name;
	 * 
	 * --Did a Russian man win gold in speedskating? select * from athletes a,
	 * competitions c,results r where a.nationality = "netherlands" and a.gender
	 * = "M" and c.name = "speedskating" and r.medal = "gold" and c.comp_id =
	 * r.comp_id and a.name = r.winner;
	 * 
	 * --Who won gold in speedskating? select a.name from athletes a,
	 * competitions c,results r where c.name = "speedskating" and r.medal =
	 * "gold" and c.comp_id = r.comp_id and a.name = r.winner;
	 */

}
