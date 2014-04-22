package util;


public interface Constants {

	//Raghavan settings
	String ENGLISH_PCFG_DATASET = "edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
	String TREE_OUTPUT_FILE = "data/tree.txt";
	String DATA_INPUT_FILE = "data/input-data.txt";
	String ASSIGNMENT_INPUT_FILE = "data/assignment-3a";
	String ASSIGNMENT_OUTPUT_FILE = "data/assignment-3a-output";
	String ASSIGNMENT_QUESTIONS_INPUT = "data/inputQuestions.txt";
	String ASSIGNMENT_QUESTIONS_MANUALTAG = "data/ManualTagging.txt";
	
	//Mysql Db settings
	final String dbDriver = "com.mysql.jdbc.Driver";
	
	/*final String mydata_url = "jdbc:mysql://proto.rrc.uic.edu:33333/RRC_USERS";
	String mydata_username = "jdbcconnector";
	String mydata_password = "password12345";*/
	
	final String mydata_url = "jdbc:mysql://localhost:3306/sochi";
	String mydata_username = "root";
	String mydata_password = "encrypted";

}
