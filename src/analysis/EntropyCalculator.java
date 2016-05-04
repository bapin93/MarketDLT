package analysis;

import java.util.List;

import models.AAnalysisObject;

public class EntropyCalculator {
	
	public EntropyCalculator() {

	}
	
	public double calculateEntropy(final List<AAnalysisObject> analysisList) {
		double result = 0;
		int positives = 0;
		int negatives = 0;
		
		for (AAnalysisObject object : analysisList) {
			if (object.getOutcome() == true) {
				positives++;
			} else {
				negatives++;
			}
		}
		
		double positiveRatio = (double)positives/analysisList.size();
		double negativeRatio = (double)negatives/analysisList.size();
		result = (positiveRatio * log(positiveRatio, 2) * -1) + (negativeRatio * log(negativeRatio, 2) * -1);
		//System.out.println("Positives: " + positives + " Negatives: " + negatives);
		return result;
	}
	
	public double getNetEntropy(final List<AAnalysisObject> analysisList1, final List<AAnalysisObject> analysisList2) {
		double result = 0;
		double entropy1 = calculateEntropy(analysisList1);
		double entropy2 = calculateEntropy(analysisList2);
		//System.out.println(entropy1 + " " + entropy2);
		double totalSubjects = analysisList1.size() + analysisList2.size();
		
		result = analysisList1.size()/totalSubjects * entropy1 + analysisList2.size()/totalSubjects * entropy2;

		return result;
	}
	
	public double log(final double x, final int base) {
		return (Math.log(x) / Math.log(base));
	}
}
