package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import raghavan.query.IQueryResultGenerator;
import raghavan.query.QueryResultGenerator;
import raghavan.query.QueryType;
import sreeram.parser.TreeModeler;
import util.QueryComponent;
import util.Result;

public class SochiAnswerEngineTest {

	IQueryResultGenerator queryResultGenerator = new QueryResultGenerator();
	TreeModeler treeModeler = new TreeModeler();

	@Test
	public void testDidWithoutNationalityYes() {
		// Did Groothuis win gold in speedskating?
		QueryComponent queryComponent = new QueryComponent();
		queryComponent.setPerson("Groothuis");
		queryComponent.setMedal("gold");
		queryComponent.setCompetition("speedskating");
		queryComponent.setCompetitionType("1000");
		queryComponent.setQueryType(QueryType.DID);

		Assert.assertEquals("Yes", queryResultGenerator.getResultForQuery(queryComponent).getResults().get(0));
	}

	@Test
	public void testDidWithoutNationalityNo() {
		// Did Groothuis win silver in speedskating?
		QueryComponent queryComponent1 = new QueryComponent();
		queryComponent1.setPerson("Groothuis");
		queryComponent1.setMedal("silver");
		queryComponent1.setCompetition("speedskating");
		queryComponent1.setQueryType(QueryType.DID);

		Assert.assertEquals("No", queryResultGenerator.getResultForQuery(queryComponent1).getResults().get(0));

	}

	@Test
	public void testDidWithNationalityYes() {
		// Did a Netherlands man win gold in speedskating?
		QueryComponent queryComponent = new QueryComponent();
		queryComponent.setNationality("Netherlands");
		queryComponent.setMedal("gold");
		queryComponent.setCompetition("speedskating");
		queryComponent.setGender("Man");
		queryComponent.setQueryType(QueryType.DID);
		Assert.assertEquals("Yes", queryResultGenerator.getResultForQuery(queryComponent).getResults().get(0));

	}

	@Test
	public void testDidWithNationalityNo() {
		// Did a Russian man win gold in speedskating?
		QueryComponent queryComponent1 = new QueryComponent();
		queryComponent1.setNationality("Russian");
		queryComponent1.setMedal("gold");
		queryComponent1.setCompetition("speedskating");
		queryComponent1.setGender("Man");
		queryComponent1.setQueryType(QueryType.DID);
		Assert.assertEquals("No", queryResultGenerator.getResultForQuery(queryComponent1).getResults().get(0));
	}

	@Test
	public void testWho() {
		// Who won gold in speedskating?
		QueryComponent queryComponent = new QueryComponent();
		queryComponent.setCompetition("speedskating");
		queryComponent.setQueryType(QueryType.WHO);
		queryComponent.setMedal("gold");
		List<String> results = new ArrayList<String>();
		results.add("groothuis");
		results.add("mulder");
		Assert.assertEquals(results, queryResultGenerator.getResultForQuery(queryComponent).getResults());

	}
	

	@Test
	public void testDidWithCompetitionType() {
		// Who won gold in 500 speedskating?
		QueryComponent queryComponent = new QueryComponent();
		queryComponent.setCompetition("speedskating");
		queryComponent.setQueryType(QueryType.WHO);
		queryComponent.setCompetitionType("500");
		queryComponent.setMedal("gold");
		List<String> results = new ArrayList<String>();
		results.add("mulder");
		Assert.assertEquals(results, queryResultGenerator.getResultForQuery(queryComponent).getResults());
	}
	
	@Test
	public void testNationalityFromDB(){
		Assert.assertEquals(14, queryResultGenerator.getAllCountriesFromDB().size());
	}
	
	@Test
	public void testQuestionToAnswer(){
		TreeModeler treeModeler = new TreeModeler();
		treeModeler.parseSentence("Who won gold in speedskating?");
	}
	
	@Test
	public void testQuestionToAnswerWithCompetitionType(){
		// Who won gold in 500 speedskating?		
		Result results = treeModeler.parseSentence("Who won gold in 500 speedskating?");
		checkTheResults(results,"mulder");
	}

	
	@Test
	public void testAllGivenQuestionsInDocument(){

		checkTheResults(treeModeler.parseSentence("Did Groothuis win gold in speedskating?"),"Yes");
		
		checkTheResults(treeModeler.parseSentence("Did Davis arrive second in icedancing?"),"No");
		
		checkTheResults(treeModeler.parseSentence("Did a Russian man win gold in speedskating?"),"No");
		
		checkTheResults(treeModeler.parseSentence("Did a Canadian woman arrive second in speedskating?"),"No");
		
		checkTheResults(treeModeler.parseSentence("Who won gold in speedskating?"),"groothuis","mulder");
		
		checkTheResults(treeModeler.parseSentence("Who arrived second in speedskating?"),"morrison", "smeekens");
		
		checkTheResults(treeModeler.parseSentence("Who won gold in 500 speedskating?"),"mulder");
	
	}
	
	@Test
	public void testCompetitionQuestions(){
		checkTheResults(treeModeler.parseSentence("Did Davis arrive first in icedancing?"),"Yes");
		
		checkTheResults(treeModeler.parseSentence("Did an American woman arrive second in icedancing?"),"No");
		
		checkTheResults(treeModeler.parseSentence("Did an Russian woman arrive second in icedancing?"),"Yes");
		
		checkTheResults(treeModeler.parseSentence("Who won silver in speedskating?"),"morrison", "smeekens");
		
		checkTheResults(treeModeler.parseSentence("Did Hoefl-Riesch win gold  in super-combined?"),"Yes");
						
	}


	private void checkTheResults(Result actualResults,String... expectedResultsString) {
		
		List<String> expectedResultsList = new ArrayList<String>();
		for(String str : expectedResultsString){
			expectedResultsList.add(str);
		}
		Assert.assertEquals(expectedResultsList,actualResults.getResults());
	}

}
