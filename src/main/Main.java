package main;
import java.io.IOException;

import models.AAnalysisObject;
import models.TreeNode;
import utilities.ListGenerator;
import yahoofinance.YahooFinance;

public class Main {

	public static void main( String [] args) throws IOException {

		ListGenerator listGenerator = new ListGenerator();
		TreeNode root = new TreeNode(listGenerator.generateStockAnalysisList(YahooFinance.get("AAPL")));

		for (AAnalysisObject ao : root.getAnalysisList()) {
			System.out.println(ao.toString());
		}
	}
}
