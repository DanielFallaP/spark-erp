package co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface ExchangeRateRepository extends PagingAndSortingRepository<ExchangeRate, Long>{
	ExchangeRate findByLocalCurrency(String value);

	ExchangeRate findByForeignCurrency(String value);

	ExchangeRate findByDate(Date value);

	ExchangeRate findByExchangeRate(Double value);

	ExchangeRate findByVariation(Double value);

	ExchangeRate findByActive(Boolean value);


}