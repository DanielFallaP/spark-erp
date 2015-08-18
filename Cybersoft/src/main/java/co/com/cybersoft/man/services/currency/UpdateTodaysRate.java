package co.com.cybersoft.man.services.currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.CustomerTenancy;
import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.purchase.tables.persistence.repository.customerTenancy.CustomerTenancyRepository;
import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateRepository;

public class UpdateTodaysRate implements Runnable{

	private Date date;
	
	private CustomerTenancyRepository tenancyRepo;
	
	private ExchangeRateRepository exchangeRateRepo;
	
	private final String openExchangeURL="http://openexchangerates.org/historical/";
	
	private final String appId="b47cc3874ddd42928483a941d4d550f9";
	
	
	public UpdateTodaysRate(Date date, CustomerTenancyRepository tenancyRepo, ExchangeRateRepository exchangeRateRepo){
		this.date=date;
		this.tenancyRepo=tenancyRepo;
		this.exchangeRateRepo=exchangeRateRepo;
	}
	
	@Override
	public void run() {
		try {
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			Iterable<CustomerTenancy> tenancyList = tenancyRepo.findAll();
			if (tenancyList.iterator().hasNext()){
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
					
					CustomerTenancy tenancy = tenancyList.iterator().next();
					String baseCurrency=tenancy.getForeignCurrency().getCode().getCurrency();
					String localCurrency=tenancy.getLocalCurrency().getCode().getCurrency();
					
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
							if (yesterdayRate!=null && yesterdayRate.getExchangeRate()!=null){
								if (yesterdayRate.getExchangeRate()!=0D)
									variation=Math.abs((rate-yesterdayRate.getExchangeRate())/yesterdayRate.getExchangeRate());
							}
							
							cal.add(Calendar.DATE, 1);
							exchangeRate = new ExchangeRate();
							exchangeRate.setActive(true);
							exchangeRate.setCreatedBy("admin");
							exchangeRate.setDate(cal.getTime());
							exchangeRate.setVariation(variation);
							exchangeRate.setUserName("admin");
							exchangeRate.setExchangeRate(rate);
							exchangeRate.setLocalCurrency(tenancy.getLocalCurrency());
							exchangeRate.setForeignCurrency(tenancy.getForeignCurrency());
							exchangeRate.setDateOfModification(cal.getTime());
							exchangeRate.setDateOfCreation(cal.getTime());
							
							
							exchangeRateRepo.save(exchangeRate);
							
							break;
						}
					}
					reader.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
