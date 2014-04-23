package raghavan.parser;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import raghavan.query.IQueryResultGenerator;
import raghavan.query.QueryResultGenerator;
import util.QueryComponent;
import util.Result;
import edu.stanford.nlp.trees.Tree;

public class SochingAnswerEngine {

	IQueryResultGenerator queryResultGenerator = new QueryResultGenerator();

//	public static void main(String[] args) {
//
//		String question1 = "Did Groothuis win gold in speedskating?";
//		String question2 = "Did a Russian man win gold in speedskating?";
//		String question3 = "Who won gold in speedskating?";
//
//		SochingAnswerEngine sochingAnswerEngine = new SochingAnswerEngine();
//		
//		Result result = sochingAnswerEngine.getResultForQuestion(question2);
//		
//	}

	public Result getResultForQuestion(String question) {
		question = cleanseQuestion(question);
		Tree tree = getParseTreeForQuestion(question);
		QueryComponent queryComponent = new QueryComponent();
		populateQueryComponentFromTree(queryComponent, tree);

		Result result = queryResultGenerator.getResultForQuery(queryComponent);
		return result;
	}

	private void populateQueryComponentFromTree(QueryComponent queryComponent, Tree tree){
		List<Tree> leaves = tree.getLeaves();
		
		for(Tree leaf : leaves){
			Tree parent = leaf.parent();
		}
	}

	private Tree getParseTreeForQuestion(String question) {
		LexicalParser parser = new StanfordParser();
		Tree tree = parser.getTree(question);
		System.out.println(tree);
		printTree(tree);
		return tree;
	}

	private String cleanseQuestion(String question) {
		question = question.replace("?", "");
		return question;
	}

	private void printTree(Tree tree) {
		Queue<Tree> queue = new LinkedList<Tree>();
		queue.add(tree);
		queue.add(null);
		int nullHitCounter = 0;
		while (nullHitCounter <= 1 && !queue.isEmpty()) {

			Tree currentNode = queue.poll();
			if (currentNode == null) {
				queue.add(null);
				System.out.println();
				nullHitCounter += 1;
			} else {
				nullHitCounter = 0;
				System.out.print(currentNode.value() + " ");
				queue.addAll(currentNode.getChildrenAsList());
			}
		}
	}

}
