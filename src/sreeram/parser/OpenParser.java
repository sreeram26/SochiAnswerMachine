package sreeram.parser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.util.InvalidFormatException;
import raghavan.parser.LexicalParser;
import raghavan.parser.OpenNlpParser;
import raghavan.parser.StanfordParser;
import util.Constants;
import util.Utility;



public class OpenParser {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		// TODO Auto-generated method stub
		
//		LexicalParser parser = new OpenNlpParser();
////		List<String> sentences = Utility.readFromFile(Constants.ASSIGNMENT_INPUT_FILE);
//		
//		for(String str : sentences)
//		{
//			parser.parseSentence(str);
//		}
		
		StanfordParser parse = new StanfordParser();
		parse.parseSentence("Did Groothuis win gold in speedskating? ");
//		parser.pa(sentences,Constants.ASSIGNMENT_OUTPUT_FILE);
		
	}

}
