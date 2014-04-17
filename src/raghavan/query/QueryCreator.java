package raghavan.query;

import util.QueryComponent;

public class QueryCreator {

	public String getSqlQueryFromQueryComponent(QueryComponent queryComponent) {
		String query = null;
		
		if (queryComponent.getQueryType() == QueryType.DID) {
			if(queryComponent.getNationality() != null){
				query = "select count(*) from athletes a, competitions c,results r where a.nationality like \""+queryComponent.getNationality()+"%\" and a.gender = \""+queryComponent.getGender()+"\" and c.name = \""+queryComponent.getCompetition()+"\" and r.medal = \""+queryComponent.getMedal()+"\"  and c.comp_id = r.comp_id  and a.name = r.winner";
			}else{
				query = "select count(*) from athletes a,competitions c,results r where a.name = \""+queryComponent.getPerson()+"\" and c.name = \""+queryComponent.getCompetition()+"\" and r.medal = \""+queryComponent.getMedal()+"\" and r.comp_id = c.comp_id  and  r.winner = a.name";
			}
		} else if (queryComponent.getQueryType() == QueryType.WHO) {
			query = "select a.name from athletes a, competitions c,results r where  c.name = \""+queryComponent.getCompetition()+"\" and r.medal = \""+queryComponent.getMedal()+"\"  and c.comp_id = r.comp_id  and a.name = r.winner";
		}
		
		if(queryComponent.getCompetitionType() != null && !queryComponent.getCompetitionType().isEmpty()){
			query += " and c.type =\""+queryComponent.getCompetitionType()+"\"";
		}
		query += ";";
		
		return query;
	}
	
	/*
	 -- Did Groothuis win gold in speedskating?
	select count(*) from athletes a,competitions c,results r where a.name = "groothuis" and c.name = "speedskating" and r.medal = "gold" 
	and r.comp_id = c.comp_id  and  r.winner = a.name;

	--Did a Russian man win gold in speedskating?
	select * from athletes a, competitions c,results r where a.nationality = "netherlands" and a.gender = "M" and c.name = "speedskating" and r.medal = "gold"  and c.comp_id = r.comp_id  and a.name = r.winner;

	--Who won gold in speedskating?
	select a.name from athletes a, competitions c,results r where  c.name = "speedskating" and r.medal = "gold"  and c.comp_id = r.comp_id  and a.name = r.winner;

	 */

}
