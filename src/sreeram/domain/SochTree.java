package sreeram.domain;

import edu.stanford.nlp.trees.LabeledScoredTreeNode;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeFactory;

public class SochTree extends LabeledScoredTreeNode {

	private int countOcc;
	private WordObject wObj;
	public int getCountOcc() {
		return countOcc;
	}
	public void setCountOcc(int countOcc) {
		this.countOcc = countOcc;
	}
	public WordObject getwObj() {
		return wObj;
	}
	public void setwObj(WordObject wObj) {
		this.wObj = wObj;
	}
	
	public SochTree(WordObject wObj)
	{
		this.wObj = wObj;
	}

}
