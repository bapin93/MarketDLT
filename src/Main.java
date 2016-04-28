import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;

public class Main {

	public static void main( String [] args) throws IOException {

		Calendar cal = new GregorianCalendar(2015, 1, 1);

		Stock stock = YahooFinance.get("AAPL");
		StockQuote quote = stock.getQuote();
		stock.print();

		System.out.println(quote.getSymbol());
		System.out.println(quote.toString());
		System.out.println(stock.getQuote(true).getLastTradeTime().get(Calendar.HOUR_OF_DAY) + ":" + stock.getQuote().getLastTradeTime().get(Calendar.MINUTE) + ":" + stock.getQuote().getLastTradeTime().get(Calendar.SECOND));
		ArrayList<HistoricalQuote> history = (ArrayList<HistoricalQuote>) stock.getHistory(cal, Interval.DAILY);

		int count = 0;
		double sum = 0;
		for (HistoricalQuote historicalQuote : history) {
			sum += historicalQuote.getClose().doubleValue();
			count ++;
			if (count == 30) {
				//System.out.println(stock.getStockExchange());
				//System.out.println("AVGRAGE: " + sum/count);
				count = 0;
				sum = 0;
			}
			System.out.println(historicalQuote.getDate().get(Calendar.MONTH) + "/" + historicalQuote.getDate().get(Calendar.DAY_OF_MONTH) + "/" + historicalQuote.getDate().get(Calendar.YEAR) + ":\t" + historicalQuote.getClose());
		}
	}
}
