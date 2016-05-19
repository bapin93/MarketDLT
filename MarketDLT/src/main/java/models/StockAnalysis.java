package models;

import java.io.IOException;
import java.math.BigDecimal;

import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.quotes.stock.StockQuote;

public class StockAnalysis extends AAnalysisObject {

	private boolean _dayGained;
	private boolean _volumeGreaterThanAvgVolume;
	private boolean _gainedMoreThan1Precent;

	public StockAnalysis() {
		super();
	}

	public StockAnalysis(final HistoricalQuote day, final HistoricalQuote nextDay, final StockQuote quote) throws IOException {
		super();
		setOutcome(nextDay.getOpen().compareTo(nextDay.getClose()) == -1);
		setDayGained(day.getOpen().compareTo(day.getClose()) == -1);
		setGainedMoreThan1Percent(day.getClose().compareTo(day.getOpen().multiply(BigDecimal.valueOf(1.01))) == 1);
		setVolumeGreaterThanAvgVolume(day.getVolume() > quote.getAvgVolume());
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

	public boolean getGainedMoreThan1Percent() {
		return _gainedMoreThan1Precent;
	}

	public void setGainedMoreThan1Percent(final boolean gainedMoreThan1Percent) {
		_gainedMoreThan1Precent = gainedMoreThan1Percent;
	}

	public String toString() {
		return "DAY GAINED: " + getDayGained() +
				"\tVOL > AVG VOL: " + isVolumeGreaterThanAvgVolume() + 
				"\tGAINED MORE THAN 2%: " + getGainedMoreThan1Percent() + 
				"\tOUTCOME: " + getOutcome();
	}
}
