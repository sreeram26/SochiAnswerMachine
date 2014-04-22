package dbreader;

import java.util.List;

import raghavan.query.SochiResult;

public interface IDBReader {

	public List<SochiResult> getSochiResultFromDB(String query);
	
	public List<String> getCountriesFromDB();
}
