package demo;

/**
 * NLP Project Part 2
 * Use this as the main class for your project. 
 * Implement the logic to generate the SQL query and the query answer.
 * Create additional methods, variables, and classes as needed.
 *
 */

import java.util.Scanner;

import raghavan.query.IQueryResultGenerator;
import raghavan.query.QueryResultGenerator;
import sreeram.parser.TreeModeler;
public class CS421_Project2 {
	
	public static String currentQuery = new String();

	public static void main(String[] args) {
		System.out.println("Welcome to the Olympics QA System.");
		System.out.println("Please ask a question. Type 'q' when finished.");
		System.out.println();
		TreeModeler treeModeler = new TreeModeler();
		String input;
		Scanner keyboard = new Scanner(System.in);
		do{		
			input =keyboard.nextLine().trim();
			
			if(!input.equalsIgnoreCase("q")){
				currentQuery = input;
				System.out.println("Query: "+currentQuery);
				treeModeler.parseSentence(currentQuery);
				//TODO perform any query processing				
				//printSQL(); //TODO implement method below
				//printAnswer(); //TODO implement method below
				System.out.println();
			}
		}while(!input.equalsIgnoreCase("q"));
		
		keyboard.close();
		System.out.println("Goodbye.");

	}
	
	public static void printSQL(){
		//TODO update this to get and print appropriate SQL
		System.out.println("<SQL>");
	}
	public static void printAnswer(){
		//TODO update this to get and print appropriate answer
		System.out.println("<ANSWER>");
	}

}
