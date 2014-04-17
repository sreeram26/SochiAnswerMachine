package test;

import org.junit.Test;

import raghavan.parser.StanfordParser;

public class SochingParserTest {

	@Test
	public void testParser(){
		String sentence = "A Norwegian woman won the gold in Ski-Cross.";
		StanfordParser parser = new StanfordParser();
		//parser.parseSentence(sentence);
		parser.printTree(sentence);
	}
	
	@Test
	public void testParsingScore(){
		String sentence = "Did you watch it ?";
		StanfordParser parser = new StanfordParser();
		parser.parseSentence(sentence);
	}

}
