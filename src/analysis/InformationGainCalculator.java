package analysis;

import java.util.ArrayList;
import java.util.List;

import models.AAnalysisObject;
import models.StockAnalysis;
import models.TreeNode;
import utilities.StockAnalysisAttribute;

public class InformationGainCalculator {

	public EntropyCalculator _entropyCalculator;

	public InformationGainCalculator() {
		_entropyCalculator = new EntropyCalculator();
	}

	public StockAnalysisAttribute getBestAttribute(final TreeNode node) {
		StockAnalysisAttribute result = StockAnalysisAttribute.DAY_GAINED;
		double maxGain = 0;

		if (!node.getAnalysisList().isEmpty() && node.getAnalysisList().get(0) != null && node.getAnalysisList().get(0) instanceof StockAnalysis) {
			for (StockAnalysisAttribute attribute : StockAnalysisAttribute.values()) {

				if (attributeNotSplitOn(node, attribute)) {
					double gain = getInformationGain(node, attribute);
					if (gain > maxGain) {
						maxGain = gain;
						result = attribute;
						//System.out.println("MaxGain: " + maxGain);
					}
				}
			}
		}
		return result;
	}
	
	private boolean attributeNotSplitOn(final TreeNode node, StockAnalysisAttribute attribute) {
		boolean result = true;
		TreeNode current = node;
		
		while (current.getParent() != null) {
			if (current.getSplitAttribute().name().equals(attribute.name())) {
				result = false;
			}
			current = current.getParent();
		}
		
		return result;
	}

	private double getInformationGain(final TreeNode node, final StockAnalysisAttribute attribute) {
		double result = 0;

		List<AAnalysisObject> positive = new ArrayList<>();
		List<AAnalysisObject> negative = new ArrayList<>();

		positive = generatePositiveList(node.getAnalysisList(), attribute);
		negative = generateNegativeList(node.getAnalysisList(), attribute);
		result = node.getEntropy() - _entropyCalculator.getNetEntropy(positive, negative);
		//System.out.println(attribute.name() + " Information Gain: " + result);

		return result;
	}

	private List<AAnalysisObject> generatePositiveList(final List<AAnalysisObject> list, final StockAnalysisAttribute attribute) {
		List<AAnalysisObject> result = new ArrayList<>();

		for (AAnalysisObject analysisObject : list) {

			if (analysisObject instanceof StockAnalysis) {
				switch(attribute) {
				case DAY_GAINED:
					if (((StockAnalysis) analysisObject).getDayGained()) {
						result.add(analysisObject);
					}
					break;
				case GAINED_MORE_THAN_1_PERCENT:
					if (((StockAnalysis) analysisObject).getGainedMoreThan1Percent()) {
						result.add(analysisObject);
					}
					break;
				case VOLUME_GREATER_THAN_AVG_VOL:
					if (((StockAnalysis) analysisObject).isVolumeGreaterThanAvgVolume()) {
						result.add(analysisObject);
					}
					break;
				default:
					break;
				}	
			}
		}
		return result;
	}

	private List<AAnalysisObject> generateNegativeList(final List<AAnalysisObject> list, final StockAnalysisAttribute attribute) {
		List<AAnalysisObject> result = new ArrayList<>();

		for (AAnalysisObject analysisObject : list) {

			if (analysisObject instanceof StockAnalysis) {
				switch(attribute) {
				case DAY_GAINED:
					if (!((StockAnalysis) analysisObject).getDayGained()) {
						result.add(analysisObject);
					}
					break;
				case GAINED_MORE_THAN_1_PERCENT:
					if (!((StockAnalysis) analysisObject).getGainedMoreThan1Percent()) {
						result.add(analysisObject);
					}
					break;
				case VOLUME_GREATER_THAN_AVG_VOL:
					if (!((StockAnalysis) analysisObject).isVolumeGreaterThanAvgVolume()) {
						result.add(analysisObject);
					}
					break;
				default:
					break;
				}	
			}
		}
		return result;
	}
}
