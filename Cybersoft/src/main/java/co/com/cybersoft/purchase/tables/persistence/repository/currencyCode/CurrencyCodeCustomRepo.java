package co.com.cybersoft.purchase.tables.persistence.repository.currencyCode;

import java.util.List;

import co.com.cybersoft.purchase.tables.persistence.domain.CurrencyCode;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.purchase.tables.web.domain.currencyCode.CurrencyCodeFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CurrencyCodeCustomRepo {

	Long getTotalCount() throws Exception;

	List<CurrencyCode> findByContainingCurrencyName(String substring);


	List<CurrencyCode> findAllActiveByCurrencyName(EmbeddedField ...fields) throws Exception;
	List<CurrencyCode> findAllActiveByCountry(EmbeddedField ...fields) throws Exception;
	List<CurrencyCode> findAllActiveByCurrency(EmbeddedField ...fields) throws Exception;
	List<CurrencyCode> findAllActiveBySymbol(EmbeddedField ...fields) throws Exception;
	List<CurrencyCode> findAllActiveByHex1(EmbeddedField ...fields) throws Exception;
	List<CurrencyCode> findAllActiveByHex2(EmbeddedField ...fields) throws Exception;
	List<CurrencyCode> findAllActiveByHex3(EmbeddedField ...fields) throws Exception;

	
	Page<CurrencyCode> findAll(Pageable pageable, CurrencyCodeFilterInfo filter)throws Exception;

	List<CurrencyCode> findAllNoPage(Pageable pageable, CurrencyCodeFilterInfo filter)throws Exception;
	
}