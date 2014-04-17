package raghavan.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import edu.stanford.nlp.trees.Tree;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;

public class OpenNlpParser implements LexicalParser {

	public Map<String, String> parseSentence(String sentence)  {
		try
		{
		InputStream is = new FileInputStream("en-parser-chunking.bin");
	    ParserModel model = new ParserModel(is);
	    Parser parser = ParserFactory.create(model);
	    Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
	    for (Parse p : topParses)
	        p.show();
	    is.close();
		}
		catch(Exception ae)
		{
			
		}
		return null;
	}

	public void printTrees(List<String> sentences, String outputFile) {
	}

	@Override
	public Tree getTree(String sentence) {		
		return null;
	}
}
