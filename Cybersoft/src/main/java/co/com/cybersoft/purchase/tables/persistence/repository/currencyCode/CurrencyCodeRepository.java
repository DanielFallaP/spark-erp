package co.com.cybersoft.purchase.tables.persistence.repository.currencyCode;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CurrencyCodeRepository extends PagingAndSortingRepository<CurrencyCode, Long>{
	CurrencyCode findByCurrencyName(String value);

	CurrencyCode findByCountry(String value);

	CurrencyCode findByCurrency(String value);

	CurrencyCode findBySymbol(String value);

	CurrencyCode findByDec1(Integer value);

	CurrencyCode findByDec2(Integer value);

	CurrencyCode findByDec3(Integer value);

	CurrencyCode findByHex1(String value);

	CurrencyCode findByHex2(String value);

	CurrencyCode findByHex3(String value);

	CurrencyCode findByActive(Boolean value);


}