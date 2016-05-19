package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import models.AAnalysisObject;
import models.StockAnalysis;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

public class ListGenerator {

	private Calendar _calendar;

	public ListGenerator() {
		_calendar = new GregorianCalendar(2015, 1, 1);
	}

	public List<AAnalysisObject> generateStockAnalysisList(final Stock stock) throws IOException {
		List<AAnalysisObject> result = new ArrayList<AAnalysisObject>();
		List<HistoricalQuote> historicalQuotes = stock.getHistory(_calendar, Interval.DAILY);
		StockQuote quote = stock.getQuote();
		Iterator<HistoricalQuote> it = historicalQuotes.iterator();
		HistoricalQuote nextDay = null;
		while (it.hasNext()) {
			Object o = it.next();
			
			if (o instanceof HistoricalQuote) {
				HistoricalQuote current = (HistoricalQuote) o;				
				if(nextDay != null) {
					result.add(new StockAnalysis(current, nextDay, quote));
				}
				nextDay = current;
			}
		}

		return result;
	}
	public Calendar getCalendar() {
		return _calendar;
	}

}
