package main;
import java.io.IOException;

import analysis.Analyzer;
import models.AAnalysisObject;
import models.TreeNode;
import tree.TreeGenerator;
import utilities.ListGenerator;
import yahoofinance.YahooFinance;

public class Main {

	public static void main( String [] args) throws IOException {

		ListGenerator listGenerator = new ListGenerator();
		TreeNode root = new TreeNode(listGenerator.generateStockAnalysisList(YahooFinance.get("AAPL")));

		for (AAnalysisObject ao : root.getAnalysisList()) {
			System.out.println(ao.toString());
		}
		
		Analyzer analyzer = new Analyzer();
		
		System.out.println(analyzer.getEntropy(root));
		System.out.println(analyzer.getBestAttribute(root).name());
		
		TreeGenerator generator = new TreeGenerator(root);
		for (String rule : generator.getRuleList()) {
			System.out.println(rule);
		}
	}
}
