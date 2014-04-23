package util;

public interface Constants {

	// Raghavan settings
	String ENGLISH_PCFG_DATASET = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
	String TREE_OUTPUT_FILE = "data/tree.txt";
	String DATA_INPUT_FILE = "data/input-data.txt";
	String ASSIGNMENT_INPUT_FILE = "data/assignment-3a";
	String ASSIGNMENT_OUTPUT_FILE = "data/assignment-3a-output";
	String ASSIGNMENT_QUESTIONS_INPUT = "data/inputQuestions.txt";

	//Sreeram
	String ASSIGNMENT_QUESTIONS_MANUALTAG = "data/ManualTagging.txt";

	// Mysql Db settings
	final String dbDriver = "com.mysql.jdbc.Driver";
	final String mydata_url = "jdbc:mysql://localhost:3306/sochi";
	String mydata_username = "root";
	String mydata_password = "encrypted";

	// sqlite Db settings
	final String sqlite_dbDriver = "org.sqlite.JDBC";
	final String sqlite_url = "jdbc:sqlite:/Users/raghavankl/Dropbox/code/webservices/SpringDataSource/sochi.db";
	String sqlite_username = "root";
	String sqlite_password = "encrypted";

	// Query
	String GET_ALL_COUNTRIES = "select distinct nationality from athletes;";
	String GET_SOCHI_RESULT = "select  a.name as player_name,a.nationality as player_nationality,a.gender as player_gender,"
			+ " c.name competition_name,c.type as competition_type,r.medal as medal from athletes a, competitions c,results r"
			+ " where c.comp_id = r.comp_id  and a.name = r.winner ";

}
