package co.com.cybersoft.purchase.tables.persistence.repository.currency;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.Currency;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.currency.CurrencyFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CurrencyCustomRepo {

	Long getTotalCount() throws Exception;

	List<Currency> findByContainingCurrency(String substring);


	List<Currency> findAllActiveByCode(EmbeddedField ...fields) throws Exception;
	List<Currency> findAllActiveByCurrency(EmbeddedField ...fields) throws Exception;

	
	Page<Currency> findAll(Pageable pageable, CurrencyFilterInfo filter)throws Exception;

	List<Currency> findAllNoPage(Pageable pageable, CurrencyFilterInfo filter)throws Exception;
	
}