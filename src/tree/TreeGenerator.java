package tree;

import java.util.ArrayList;
import java.util.List;

import analysis.Analyzer;
import analysis.InformationGainCalculator;
import models.AAnalysisObject;
import models.StockAnalysis;
import models.TreeNode;
import utilities.StockAnalysisAttribute;

public class TreeGenerator {

	private Analyzer _analyzer;
	private List<String> _ruleList;

	public TreeGenerator(final TreeNode root) {
		_analyzer = new Analyzer();
		_ruleList = new ArrayList<>();
		generateTree(root);
	}
	
	public List<String> getRuleList() {
		return _ruleList;
	}

	private void generateTree(final TreeNode node) {
		if (node.isHomogeneous()) {
			generateRule(node);
			System.out.println("HOMOGENEOUS");
		} else if (splitOnAllAttributes(node)) {
			generateRule(node);
			System.out.println("SPLIT ON ALL");
		} else {
			System.out.println("BRANCHING");
			branch(node);
		}
	}

	private void branch(final TreeNode node) {
		StockAnalysisAttribute bestAttribute = _analyzer.getBestAttribute(node);
		List<AAnalysisObject> positive = new ArrayList<AAnalysisObject>();
		List<AAnalysisObject> negative = new ArrayList<AAnalysisObject>();

		if (bestAttribute == null) {
			System.out.println("NULLL");
		} else if (!node.getAnalysisList().isEmpty() && node.getAnalysisList().get(0) != null && node.getAnalysisList().get(0) instanceof StockAnalysis) {
			System.out.println(bestAttribute.name());

			for (AAnalysisObject analysis : node.getAnalysisList()) {

				switch (bestAttribute) {
				case DAY_GAINED:
					if (((StockAnalysis) analysis).getDayGained()) {
						positive.add(analysis);
					} else {
						negative.add(analysis);
					}
					break;
				case GAINED_MORE_THAN_1_PERCENT:
					if (((StockAnalysis) analysis).getGainedMoreThan1Percent()) {
						positive.add(analysis);
					} else {
						negative.add(analysis);
					}
					break;
				case VOLUME_GREATER_THAN_AVG_VOL:
					if (((StockAnalysis) analysis).isVolumeGreaterThanAvgVolume()) {
						positive.add(analysis);
					} else {
						negative.add(analysis);
					}
					break;
				default:
					break;
				}	
			}
		}
		TreeNode node1 = new TreeNode(node, positive);
		TreeNode node2 = new TreeNode(node, negative);
		node1.setSplitAttribute(bestAttribute);
		node2.setSplitAttribute(bestAttribute);
		generateTree(node1);
		generateTree(node2);
	}

	private void generateRule(final TreeNode node) {
		String rule = "";

		TreeNode current = node;
		System.out.println(current.getSplitAttribute().name());
		while (current.getParent() != null) {
			rule = " AND " + current.getSplitAttribute().name() + rule;
			current = current.getParent();
		}	
		_ruleList.add(rule);
	}
	
	private boolean splitOnAllAttributes(final TreeNode node) {
		boolean result = false;
		TreeNode current = node;
		
		int numAttributes = StockAnalysisAttribute.values().length;
		int count = 0;
		
		while (current.getParent() != null) {
			count++;
			current = current.getParent();
		}
		
		if (count > numAttributes) {
			result = true;
		}
		
		return result;
	}

}
