package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import models.AAnalysisObject;
import models.StockAnalysis;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class ListGenerator {
	
	private Calendar _calendar;
	
	public ListGenerator() {
		_calendar = new GregorianCalendar(2016, 1, 1);
	}
	
	public List<AAnalysisObject> generateStockAnalysisList(final Stock stock) throws IOException {
		List<AAnalysisObject> result = new ArrayList<AAnalysisObject>();
		List<HistoricalQuote> historicalQuotes = stock.getHistory(_calendar, Interval.DAILY);
		for (HistoricalQuote quote : historicalQuotes) {
			
		}
		
		return result;
	}
	
	public Calendar getCalendar() {
		return _calendar;
	}
	
}
