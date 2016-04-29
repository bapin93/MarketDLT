package models;

import java.io.IOException;

import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.stock.StockQuote;

public class StockAnalysis extends AAnalysisObject {

	private boolean _outcome;
	private boolean _dayGained;
	private boolean _volumeGreaterThanAvgVolume;

	public StockAnalysis() {
		super();
	}

	public StockAnalysis(final HistoricalQuote day, final HistoricalQuote nextDay, final StockQuote quote) throws IOException {
		super();
		setOutcome(nextDay.getOpen().compareTo(nextDay.getClose()) == -1);
		setDayGained(day.getOpen().compareTo(day.getClose()) == -1);
		setVolumeGreaterThanAvgVolume(day.getVolume() > quote.getAvgVolume());
	}

	public boolean getOutcome() {
		return _outcome;
	}

	public void setOutcome(final boolean outcome) {
		_outcome = outcome;
	}

	public boolean getDayGained() {
		return _dayGained;
	}

	public void setDayGained(final boolean dayGained) {
		_dayGained = dayGained;
	}

	public boolean isVolumeGreaterThanAvgVolume() {
		return _volumeGreaterThanAvgVolume;
	}

	public void setVolumeGreaterThanAvgVolume(final boolean isVolumeGreaterThanAvgVolume) {
		_volumeGreaterThanAvgVolume = isVolumeGreaterThanAvgVolume;
	}
	
	public String toString() {
		return "DAY GAINED: " + getDayGained() + " VOL > AVG VOL: " + isVolumeGreaterThanAvgVolume() + " OUTCOME: " + getOutcome();
	}
}
