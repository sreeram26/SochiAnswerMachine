package sreeram.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.trees.LabeledScoredTreeNode;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.SimpleTree;
import raghavan.parser.LexicalParser;
import raghavan.parser.StanfordParser;
import sreeram.domain.Person;
import sreeram.domain.Result;
import sreeram.domain.SochTree;
import sreeram.domain.WordObject;
import util.Constants;
import util.Utility;



public class TreeModeler {
	
	
	public void changeLeaf(Tree t)
	{
		if(t.getChildrenAsList().size()!=0)
		{
			
			for(Tree child: t.getChildrenAsList())
			{
				changeLeaf(child);
			}
			
		}
		else
		{
			String name =t.nodeString(); 
			if (t.nodeString().equalsIgnoreCase("sweden") || t.nodeString().equalsIgnoreCase("russian") || t.nodeString().equalsIgnoreCase("canadian"))
			{
//				String name =t.nodeString(); 
				Tree newTree = new SochTree(new Person(null,null, "sweden"));
				newTree.setChildren(t.getChildrenAsList());
				newTree.setLabel(t.label());
				t= newTree;
				t.setValue("Country---" + name);
				System.out.println("aadsasd"); 
				
			}
			else if (t.nodeString().equalsIgnoreCase("strikes") || t.nodeString().equalsIgnoreCase("win") || t.nodeString().equalsIgnoreCase("arrive") )
			{
				Tree newTree = new SochTree(new Result(-1, "strikes", -1, -1));
				newTree.setChildren(t.getChildrenAsList());
				newTree.setLabel(t.label());
				t= newTree;
				t.setValue("Result---" + name);
			}
			else if(t.nodeString().equalsIgnoreCase("gold") || t.nodeString().equalsIgnoreCase("second "))
			{
				Tree newTree = new SochTree(new Result(-1, null, -1, 1));
				newTree.setChildren(t.getChildrenAsList());
				newTree.setLabel(t.label());
				t= newTree;
				t.setValue("Medal---"+name);
			}
			else if(t.nodeString().equalsIgnoreCase("man") || t.nodeString().equalsIgnoreCase("woman"))
			{
				t.setValue("Gender---"+name);
			}
			else if(t.nodeString().equalsIgnoreCase("Groothis") || t.nodeString().equalsIgnoreCase("Davis"))
			{
				t.setValue("Person---"+name);
			}
			else if(t.nodeString().equalsIgnoreCase("IceDancing") || t.nodeString().equalsIgnoreCase("SpeedSkating"))
			{
				t.setValue("Competition---"+name);
			}
			
			
		}
		
	}
	
	public void modelRuleTree(Tree t)
	{
		
		if(t.getChildrenAsList().size()==0)
		{
			return;
		}
		else
		{
			for ( Tree tr : t.getChildrenAsList())
			{
				modelRuleTree(tr);
			}
			
			Label lbl= new CoreLabel();
			lbl.setValue("0");
			List<Tree> newList = new ArrayList<Tree>();
			Tree temp= new LabeledScoredTreeNode(lbl, t.getChildrenAsList());
			newList.add(temp);
			t.setChildren(newList);
		}
			

		
	}
	
	
	public boolean isSameArray(List<Tree> base , List<Tree> other)
	{
		if(base.size()!= other.size())
			return false;
		else if (base== null || other==null )
			return false;
		else if (base.size()==0)
			return false;
		else
		{
			
			for( int count =0 ; count <base.size() ; count++)
			{
				
				if(! base.get(count).label().value().equals(other.get(count).label().value()))
					return false;
				
			}
			
			
			return true;
		}
					
		
	}

	public void getDBMap(Tree parseTree , Tree ruleTree)
	{
		if (ruleTree.getChildrenAsList().size() !=0 && parseTree.getChildrenAsList().size()!=0)
		{
			
			List<Tree> ruleList = ruleTree.getChildrenAsList();
			List<Tree> parseList = parseTree.getChildrenAsList();
			boolean shouldAssign = true;
			 
			
			 for( Tree temp : parseList)
			 {
				 if(temp.getChildrenAsList()!=null)
				 {
					 if(temp.getChildrenAsList().size() ==0 )
					 {
						 shouldAssign = false;
					 }
						 
				 }
				 else
					 shouldAssign=false;
			 }

			 for(Tree check : ruleList)
			 {
				 if(shouldAssign)
				 {
						if(isSameArray(check.getChildrenAsList(), parseList))
						{
							for( int count =0 ; count <parseList.size() ; count++)
							{
								 getDBMap(parseList.get(count),check.getChildrenAsList().get(count));
							}
							return;
						}
				 }
				 else
				 {
					 getDBMap(parseList.get(0),check.getChildrenAsList().get(0));
					 return;
				 }

			}
			
		}
		else
		{
			if( (ruleTree.label().value().contains("Country") || ruleTree.label().value().contains("Result") || ruleTree.label().value().contains("Game")||ruleTree.label().value().contains("Person") )&& parseTree.getChildrenAsList().size()==0 )
			{
				// Reached leaves of rule List 
				// if leaves reached of parseList then assign parseList with the corresponding values.
				 
				
					String orig =  parseTree.label().value();
					parseTree.setValue(orig+ruleTree.label().value());
					return;// To end this loop
				
			}
		}
		
		
		
		
		
	}
	
	public void addSentenceToModel(Tree parseTree , Tree ruleTree)
	{
		List<Tree> ruleList = ruleTree.getChildrenAsList();
		List<Tree> parseList = parseTree.getChildrenAsList();
		
		if(ruleList.size()== 0 || parseList.size()==0 )
			return;
		
		for(Tree check : ruleList)
		 {
		 		if(isSameArray(check.getChildrenAsList(), parseList))
				{
		 			if(parseList.size() == 1)
		 			{
		 				if(parseList.get(0).getChildrenAsList().size()==0)
		 				{
		 					//The rule is not there .
		 					return;
		 				}
		 				
		 			}
		 			for( int count =0 ; count <parseList.size() ; count++)
		 			{
		 				addSentenceToModel(parseList.get(count),check.getChildrenAsList().get(count));
		 			}

					return;
				}
		}
		
		//Code for adding the parseList to the ruleTree.
		addParseList(parseTree, ruleTree);
		
	}
	public void addParseList(Tree parseTree, Tree ruleTree)
	{
		
		modelRuleTree(parseTree);
		
		parseTree=parseTree.getChildrenAsList().get(0);
		Label lbl= parseTree.label();
		lbl.setValue(String.valueOf(ruleTree.getChildrenAsList().size()));
		parseTree.setLabel(lbl);
//		ruleTree.getChildrenAsList().add(parseTree);
		ruleTree.addChild(parseTree);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StanfordParser parser = new StanfordParser();
		
		Tree tree = parser.getTree("Did Groothuis win gold in Speedskating?");
//		Tree tree1 = parser.getTree("Sweden strikes gold.");
		
		
		List<String> sentences = Utility.readFromFile(Constants.ASSIGNMENT_QUESTIONS_INPUT);
//		parser.printTrees(sentences, Constants.ASSIGNMENT_OUTPUT_FILE);
		
		
		
		
		
		System.out.println(tree.getChildrenAsList().getClass().toString());
		TreeModeler objTreeModel = new TreeModeler();
		objTreeModel.changeLeaf(tree);
		objTreeModel.modelRuleTree(tree);
//		objTreeModel.getDBMap(tree1, tree);

		for(String temp : sentences)
		{
			objTreeModel.changeLeaf(parser.getTree(temp));
			objTreeModel.addSentenceToModel( parser.getTree(temp) , tree);
		}
		
		
		
		
		
		
		
//		List<String> sentences = Utility.readFromFile(Constants.ASSIGNMENT_INPUT_FILE);
		
		
		
		System.out.println(tree);
		
		
		
	}

}
