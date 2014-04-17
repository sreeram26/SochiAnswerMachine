package raghavan.parser;

import java.util.LinkedList;
import java.util.Queue;

import edu.stanford.nlp.trees.Tree;

public class SochingAnswerEngine {

	public static void main(String[] args) {
		LexicalParser parser = new StanfordParser();
		String question1 = "Did Groothuis win gold in speedskating?";
		String question2 = "Did a Russian man win gold in speedskating?";
		String question3 = "Who won gold in speedskating?";

		Tree tree = parser.getTree(question1);
		printTree(tree);

	}

	public static void printTree(Tree tree) {
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
