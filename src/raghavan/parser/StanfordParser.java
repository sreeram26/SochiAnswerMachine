package raghavan.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import util.Constants;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreePrint;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.trees.TypedDependency;

public class StanfordParser implements LexicalParser {

	LexicalizedParser lp = null;
	TokenizerFactory<CoreLabel> tokenizerFactory = null;
	
	public StanfordParser() {
		lp = LexicalizedParser.loadModel(Constants.ENGLISH_PCFG_DATASET);
		// This option shows loading and using an explicit tokenizer
		tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
	}

	public Map<String, String> parseSentence(String sentence) {		
		lp.setOptionFlags(new String[] { "-outputFormat", "penn,typedDependenciesCollapsed", "-retainTmpSubcategories" });
		
		List<CoreLabel> rawWords = tokenizerFactory.getTokenizer(new StringReader(sentence)).tokenize();
		Tree bestParse = lp.parseTree(rawWords);

		Tree parseApplied = lp.apply(rawWords);
		System.out.println("Parse Score = " + parseApplied.score());

		Queue<Tree> queue = new LinkedList<Tree>();

		queue.add(bestParse);
		while (!queue.isEmpty()) {
			Tree currentNode = queue.poll();
			currentNode.printLocalTree();
			System.out.println(currentNode.nodeString());
			queue.addAll(currentNode.getChildrenAsList());
		}
		return null;
	}

	public void printTrees(List<String> sentences,String outputFile) {
		File f = new File(outputFile);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (String sentence : sentences) {
			printTree(sentence, pw);
		}
	}

	public void printTree(String sentence) {
		printTree(sentence, null);
	}

	public void printTree(String sentence, PrintWriter pw) {

		Tokenizer<CoreLabel> tok = tokenizerFactory.getTokenizer(new StringReader(sentence));
		List<CoreLabel> rawWords2 = tok.tokenize();
		Tree parse = lp.apply(rawWords2);

		TreebankLanguagePack tlp = new PennTreebankLanguagePack();
		GrammaticalStructureFactory gsf = tlp.grammaticalStructureFactory();
		GrammaticalStructure gs = gsf.newGrammaticalStructure(parse);
		List<TypedDependency> tdl = gs.typedDependenciesCCprocessed();
		// You can also use a TreePrint object to print trees and
		// dependencies
		TreePrint tp = new TreePrint("penn,typedDependenciesCollapsed");
		
		if (pw != null) {
			pw.append("Sentence:"+sentence+"\n");
			tp.printTree(parse, pw);
		} else {
			tp.printTree(parse);
		}
	}
	
	//Sreeram add
	public Tree getTree(String sentence)
	{
		
		Tokenizer<CoreLabel> tok = tokenizerFactory.getTokenizer(new StringReader(sentence));
		List<CoreLabel> rawWords2 = tok.tokenize();
		return lp.apply(rawWords2);
	}

}
