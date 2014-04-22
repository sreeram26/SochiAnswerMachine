package test;

import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeModel;

import org.junit.Assert;
import org.junit.Test;

import dbreader.IDBReader;
import dbreader.mysql.MySqlReader;
import raghavan.query.IQueryResultGenerator;
import raghavan.query.MysqlQueryResultGenerator;
import raghavan.query.QueryType;
import sreeram.parser.TreeModeler;
import util.QueryComponent;

public class SochiAnswerEngineTest {

	IQueryResultGenerator queryResultGenerator = new MysqlQueryResultGenerator();
	
	IDBReader dbReader = new MySqlReader();

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
	public void testNationalityFromDB(){
		Assert.assertEquals(14, dbReader.getCountriesFromDB().size());
	}
	
	@Test
	public void testQuestionToAnswer(){
		TreeModeler treeModeler = new TreeModeler();
		treeModeler.parseSentence("Who won gold in speedskating?");
	}

}
