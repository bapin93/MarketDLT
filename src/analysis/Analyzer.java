package analysis;

import models.TreeNode;
import utilities.StockAnalysisAttribute;

public class Analyzer implements IAnalyzer {
	
	private EntropyCalculator _entropyCalculator;
	private InformationGainCalculator _informationGainCalculator;
	
	public Analyzer() {
		_entropyCalculator = new EntropyCalculator();
		_informationGainCalculator = new InformationGainCalculator();
	}
	
	public double getEntropy(final TreeNode node) {
		double result = _entropyCalculator.calculateEntropy(node.getAnalysisList());
		node.setEntropy(result);
		if (result == 1 || result == 0) {
			node.setHomogeneous(true);
		}
		return result;
	}
	
	public StockAnalysisAttribute getBestAttribute(final TreeNode node) {
		StockAnalysisAttribute result = _informationGainCalculator.getBestAttribute(node);
		return result;
	}
	
	
}
