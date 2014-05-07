package co.com.cybersoft.man.services.currency;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.cybersoft.persistence.domain.ExchangeRate;
import co.com.cybersoft.persistence.domain.Tenancy;
import co.com.cybersoft.persistence.repository.exchangeRate.ExchangeRateRepository;
import co.com.cybersoft.persistence.repository.tenancy.TenancyRepository;

public class OpenExchangeUpdateService implements CurrencyUpdateService{

	private final String openExchangeURL="http://openexchangerates.org/historical/";
	
	private final String appId="b47cc3874ddd42928483a941d4d550f9";
	
	@Autowired
	private TenancyRepository tenancyRepo;
	
	@Autowired
	private ExchangeRateRepository exchangeRateRepo;
	
	@PostConstruct
	@Override
	public void updateTodayRates() throws Exception{
		List<Tenancy> tenancyList = tenancyRepo.findAll();
		if (!tenancyList.isEmpty()){
			//Check if the rate already exists
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			ExchangeRate exchangeRate = exchangeRateRepo.findByDate(cal.getTime());
			if (exchangeRate==null){
				cal.add(Calendar.DATE, -1);
				Tenancy tenancy = tenancyList.get(0);
				String baseCurrency=tenancy.getOtherCurrency();
				String localCurrency=tenancy.getLocalCurrency();
				
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
				int responseCode = connection.getResponseCode();
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				//Get the .json response
				String inputLine;
		 
				while ((inputLine = reader.readLine()) != null) {
					if (inputLine.contains(localCurrency)){
						String r=inputLine.replace("\""+localCurrency+"\"", "");
						r=r.replace(":", "");
						r=r.replace(",", "");
						
						Double rate=Double.parseDouble(r.trim());
						
						cal.add(Calendar.DATE, 1);
						exchangeRate = new ExchangeRate();
						exchangeRate.setActive(true);
						exchangeRate.setCode(1);
						exchangeRate.setCreatedBy("admin");
						exchangeRate.setDate(cal.getTime());
						exchangeRate.setVariation(0D);
						exchangeRate.setUserName("admin");
						exchangeRate.setRate(rate);
						exchangeRate.setLocalCurrency(localCurrency);
						exchangeRate.setForeingCurrency(baseCurrency);
						exchangeRate.setDescription("COD to USD rate");
						exchangeRate.setDateOfModification(cal.getTime());
						exchangeRate.setDate(cal.getTime());
						exchangeRate.setDateOfCreation(cal.getTime());
						
						exchangeRateRepo.save(exchangeRate);
						break;
					}
				}
				reader.close();
			}
		}
	}

	@Override
	public void updatePeriodRates(Date from, Date to) throws Exception{
		
	}

}
