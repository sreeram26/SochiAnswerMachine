package raghavan.parser;

import java.util.List;
import java.util.Map;

import edu.stanford.nlp.trees.Tree;

public interface LexicalParser {

	public Map<String, String> parseSentence(String sentence);

	public void printTrees(List<String> sentences,String outputFile);
	
	public Tree getTree(String sentence);

}
