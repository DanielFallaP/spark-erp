package co.com.cybersoft.purchase.tables.persistence.repository.currency;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long>{
	Currency findByCode(String value);

	Currency findByCurrency(String value);

	Currency findByActive(Boolean value);


}