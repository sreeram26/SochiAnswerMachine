package util;

import java.util.ArrayList;
import java.util.List;

public class Result {

	private List<String> results = new ArrayList<String>();

	public List<String> getResults() {
		return this.results;
	}

	public void addResult(String result) {
		this.results.add(result);
	}

	public void addAllResults(List<String> resultsFromDB) {
		this.results.addAll(resultsFromDB);		
	}

}
