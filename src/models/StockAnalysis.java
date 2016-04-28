package models;

import yahoofinance.Stock;

public class StockAnalysis extends AAnalysisObject {

	private boolean _outcome;
	private boolean _previousDayGained;
	private boolean _volumeGreaterThanAvgVolume;
	
	public StockAnalysis() {
		super();
	}
	
	public StockAnalysis(final Stock stock) {
		super();
	}
	
	public boolean getOutcome() {
		return _outcome;
	}
	
	public void setOutcome(final boolean outcome) {
		_outcome = outcome;
	}
}
