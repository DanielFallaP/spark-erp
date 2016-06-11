package co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.ExchangeRate;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface ExchangeRateCustomRepo {


	List<ExchangeRate> findAllActiveByLocalCurrency(EmbeddedField ...fields) throws Exception;
	List<ExchangeRate> findByCodeName(String code) throws Exception;
	List<ExchangeRate> findAllActiveByForeignCurrency(EmbeddedField ...fields) throws Exception;

	
	Page<ExchangeRate> findAll(Pageable pageable, ExchangeRateFilterInfo filter)throws Exception;

	List<ExchangeRate> findAllNoPage(Pageable pageable, ExchangeRateFilterInfo filter)throws Exception;
	
}