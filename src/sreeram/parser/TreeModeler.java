package sreeram.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import raghavan.parser.StanfordParser;
import raghavan.query.IQueryResultGenerator;
import raghavan.query.QueryResultGenerator;
import raghavan.query.QueryType;
import sreeram.domain.Person;
import sreeram.domain.Result;
import sreeram.domain.SochTree;
import util.Constants;
import util.QueryComponent;
import util.Utility;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.Label;
import edu.stanford.nlp.trees.LabeledScoredTreeNode;
import edu.stanford.nlp.trees.Tree;



public class TreeModeler {
	
	public int leafCount=0;
	
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
			else if(t.nodeString().equalsIgnoreCase("did") )
			{
				t.setValue("Did---"+name);
			}
			else if(t.nodeString().equalsIgnoreCase("man") || t.nodeString().equalsIgnoreCase("woman"))
			{
				t.setValue("Gender---"+name);
			}
			else if(t.nodeString().equalsIgnoreCase("Groothuis") || t.nodeString().equalsIgnoreCase("Davis"))
			{
				t.setValue("Person---"+name);
			}
			else if(t.nodeString().equalsIgnoreCase("IceDancing") || t.nodeString().equalsIgnoreCase("SpeedSkating"))
			{
				t.setValue("Competition---"+name);
			}
			
			
		}
		
	}
	
	public void changeLeafAtPosition(Tree t , int position, String objType)
	{
		if(t.getChildrenAsList().size()!=0)
		{
			
			for(Tree child: t.getChildrenAsList())
			{
				changeLeafAtPosition(child,position,objType);
			}
			
		}
		else
		{
			leafCount++;
			
			if(leafCount== position)
			{
			String name =t.nodeString(); 
			t.setValue(objType+"---"+name);
			return;
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
					if(! ( base.get(count).label().value().equals("NNP")|| base.get(count).label().value().equals("NNPS") ) && ( other.get(count).label().value().equals("NNP")|| other.get(count).label().value().equals("NNPS") ))
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
			if( (ruleTree.label().value().contains("Country") || ruleTree.label().value().contains("Result") || ruleTree.label().value().contains("Competition")||ruleTree.label().value().contains("Person")||ruleTree.label().value().contains("Medal")||ruleTree.label().value().contains("Gender")||ruleTree.label().value().contains("Did") ||ruleTree.label().value().contains("Who") )&& parseTree.getChildrenAsList().size()==0 )
			{
				// Reached leaves of rule List 
				// if leaves reached of parseList then assign parseList with the corresponding values.
				 
				
					String orig =  parseTree.label().value();
					
					String[] arrStr= ruleTree.label().value().split("---");
					parseTree.setValue(arrStr[0] + "---" + orig);
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
	
	public QueryComponent setComponent(Tree tree)
	{
		QueryComponent objComponent = new QueryComponent();
		List<Tree> leaves=tree.getLeaves();
		for(Tree temp: leaves)
		{
			if(temp.label().value().contains("---"))
			{
				String[] arrStr= temp.label().value().split("---");
				
				if(arrStr[0].contains("Country"))
				{
					objComponent.setNationality(arrStr[1]);
				}
				else if(arrStr[0].contains("Medal"))
				{
					objComponent.setMedal(arrStr[1]);
				}
				else if(arrStr[0].contains("Gender"))
				{
					objComponent.setGender(arrStr[1]);
				}
				else if(arrStr[0].contains("Person"))
				{
					objComponent.setPerson(arrStr[1]);
				}
				else if(arrStr[0].contains("Competition"))
				{
					objComponent.setCompetition(arrStr[1]);
				}
				else if(arrStr[0].contains("Did"))
				{
					objComponent.setQueryType(QueryType.DID);
				}
				else if(arrStr[0].contains("Who"))
				{
					objComponent.setQueryType(QueryType.WHO);
				}
			}
		}
		
		return objComponent;
	}
	
	public void addCorpusInput(String[] objMapList, Tree treeObj)
	{
		for(String objMap: objMapList)
		{
			if(objMap!=null)
			{
				String[] objs = objMap.split(":");
				List<Tree> leaves=treeObj.getLeaves();
				
				if(leaves.size()>=2)
				{
				int position = Integer.parseInt(objs[0]);
				String objType = objs[1];
				
				leafCount=0;
				changeLeafAtPosition(treeObj, position, objType);
				}
				
			}
		}
		
	}
	public void parseSentence(String question)
	{
		// TODO Auto-generated method stub
		StanfordParser parser = new StanfordParser();
		
		
		//Model input for basic tree modeling
		Tree tree = parser.getTree("Did Groothuis win gold in Speedskating?");
		Tree questionTree = parser.getTree(question);
		
		//Get input of the set of questions in the corpus
		//Get the various user input feed for these set of questions.
		List<String> sentences = Utility.readFromFile(Constants.ASSIGNMENT_QUESTIONS_INPUT);
		List<String> manualTags = Utility.readFromFile(Constants.ASSIGNMENT_QUESTIONS_MANUALTAG);
		
		
		
		
		changeLeaf(tree);
		modelRuleTree(tree);
		int count =0;
		
		//Parsing the input sentences and their corresponding manual tags and inputting them into the PARSETREE
		for(String temp : sentences)
		{
			if(count>=manualTags.size()) // There is a input sentence but no available manual tagging -> Omit parsing 
				break;
			
			Tree treeTemp = parser.getTree(temp);
			String[] objMapList= manualTags.get(count).split(",");
			addCorpusInput(objMapList, treeTemp); // Adding a sentence and its corpus tag into the tree
			addSentenceToModel( treeTemp, tree);
			count++;
		}
		
		getDBMap(questionTree, tree); // parsing the question to identify its tags.
		
		QueryComponent objComponent = setComponent(questionTree); //Converting from output Tree to Query Component
		
		IQueryResultGenerator objGenerator = new QueryResultGenerator();
		util.Result resultFromUtil= objGenerator.getResultForQuery(objComponent);
		System.out.println(resultFromUtil.getResults());
		
		
		
	}

}
