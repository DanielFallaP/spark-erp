package co.com.cybersoft.maintenance.tables.persistence.repository.coin;

import java.util.List;

import co.com.cybersoft.maintenance.tables.persistence.domain.Coin;
import co.com.cybersoft.util.EmbeddedField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import co.com.cybersoft.maintenance.tables.web.domain.coin.CoinFilterInfo;


/**
 * 
 * @author Cybersystems 2016. All rights reserved.
 *
 */
public interface CoinCustomRepo {

	Long getTotalCount() throws Exception;


	List<Coin> findAllActiveByCompany(EmbeddedField ...fields) throws Exception;
	List<Coin> findAllActiveByNameCoin(EmbeddedField ...fields) throws Exception;
	List<Coin> findAllActiveByAbbreviationCurrency(EmbeddedField ...fields) throws Exception;

	
	Page<Coin> findAll(Pageable pageable, CoinFilterInfo filter)throws Exception;

	List<Coin> findAllNoPage(Pageable pageable, CoinFilterInfo filter)throws Exception;
	
}