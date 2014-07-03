package co.com.cybersoft.man.services.currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.tables.persistence.repository.exchangeRate.ExchangeRateRepository;

public class OpenExchangeUpdateService implements CurrencyUpdateService{

	private final String openExchangeURL="http://openexchangerates.org/historical/";
	
	private final String appId="b47cc3874ddd42928483a941d4d550f9";
	
	@Autowired
	private CustomerTenancyRepository tenancyRepo;
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepo;
	
	@Override
	public void updateTodayRates() throws Exception{
		updateRatesForDay(new Date());
	}
	
	@Override
	public void updatePeriodRates(Date from, Date to) throws Exception{
		GregorianCalendar init = new GregorianCalendar();
		init.setTime(from);
		init.set(Calendar.HOUR_OF_DAY, 0);
		init.set(Calendar.MINUTE, 0);
		init.set(Calendar.SECOND, 0);
		init.set(Calendar.MILLISECOND, 0);
		
		GregorianCalendar finale = new GregorianCalendar();
		finale.setTime(to);
		finale.set(Calendar.HOUR_OF_DAY, 0);
		finale.set(Calendar.MINUTE, 0);
		finale.set(Calendar.SECOND, 0);
		finale.set(Calendar.MILLISECOND, 0);
		
		
		
		int days = Days.daysBetween(new DateTime(new Date(init.getTimeInMillis())), new DateTime(new Date(finale.getTimeInMillis()))).getDays();
		if (days>30)
			days=30;
		
		//Update the rates for the past 30 days or less
		for (int i = 0; i < days; i++) {
			init.add(Calendar.DATE, 1);
			updateRatesForDay(init.getTime());
		}
	}
	
	private void updateRatesForDay(Date date) throws Exception{
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		List<CustomerTenancy> tenancyList = tenancyRepo.findAll();
		if (!tenancyList.isEmpty()){
			//Check if the rate for today already exists
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			ExchangeRate exchangeRate = exchangeRateRepo.findByDate(cal.getTime());
			if (exchangeRate==null){
				cal.add(Calendar.DATE, -1);
				
				//Get yesterday rate if there is one
				ExchangeRate yesterdayRate = exchangeRateRepo.findByDate(cal.getTime());
				
				CustomerTenancy tenancy = tenancyList.get(0);
				String baseCurrency=tenancy.getForeignCurrencyEntity().getCode();
				String localCurrency=tenancy.getLocalCurrencyEntity().getCode();
				
				Integer year = cal.get(Calendar.YEAR);
				String month = String.format("%02d", cal.get(Calendar.MONTH)+1);
				String day = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
				
				String yesterdayLoveWasSuchAnEasyGameToPlay=year.toString()+"-"+month+"-"+day;
				String queryURL=openExchangeURL
						+yesterdayLoveWasSuchAnEasyGameToPlay
						+".json?app_id="+appId;
				
				//Send the GET request
				URL url = new URL(queryURL);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				//Get the .json response
				String inputLine;
				
				while ((inputLine = reader.readLine()) != null) {
					if (inputLine.contains(localCurrency)){
						String r=inputLine.replace("\""+localCurrency+"\"", "");
						r=r.replace(":", "");
						r=r.replace(",", "");
						
						Double rate=Double.parseDouble(r.trim());
						Double variation=0D;
						if (yesterdayRate!=null && yesterdayRate.getRate()!=null){
							if (yesterdayRate.getRate()!=0D)
								variation=Math.abs((rate-yesterdayRate.getRate())/yesterdayRate.getRate());
						}
						
						cal.add(Calendar.DATE, 1);
						exchangeRate = new ExchangeRate();
						exchangeRate.setActive(true);
						exchangeRate.setCreatedBy("admin");
						exchangeRate.setDate(cal.getTime());
						exchangeRate.setVariation(variation);
						exchangeRate.setUserName("admin");
						exchangeRate.setRate(rate);
						exchangeRate.setLocalCurrency(localCurrency);
						exchangeRate.setForeignCurrency(baseCurrency);
						exchangeRate.setDateOfModification(cal.getTime());
						exchangeRate.setDateOfCreation(cal.getTime());
						
						
						exchangeRateRepo.save(exchangeRate);

						break;
					}
				}
				reader.close();
			}
		}
	}

}
