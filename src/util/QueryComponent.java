package util;

import raghavan.query.QueryType;

public class QueryComponent {

	private QueryType queryType;
	private String person;
	private String medal;
	private String nationality;
	private String gender;
	private String competition;
	private String competitionType;

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person.toLowerCase();
	}

	public String getMedal() {
		return medal;
	}

	public void setMedal(String medal) {
		if (medal.equals("first")) {
			this.medal = "gold";
		} else if (medal.equals("second")) {
			this.medal = "silver";
		} else if (medal.equals("third")) {
			this.medal = "bronze";
		} else {
			this.medal = medal.toLowerCase();
		}
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality.toLowerCase();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		gender = gender.toLowerCase();
		if (gender.equals("man") | gender.equals("male")) {
			this.gender = "M";
		} else {
			this.gender = "F";
		}
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition.toLowerCase();
	}

	public String getCompetitionType() {
		return competitionType;
	}

	public void setCompetitionType(String competitionType) {
		this.competitionType = competitionType;
	}

}
