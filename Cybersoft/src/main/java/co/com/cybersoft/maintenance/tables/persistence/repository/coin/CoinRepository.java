package co.com.cybersoft.maintenance.tables.persistence.repository.coin;

import org.springframework.data.repository.PagingAndSortingRepository;

import co.com.cybersoft.maintenance.tables.persistence.domain.Coin;
import java.util.Date;

/**
 * 
 * @author Cybersystems 2014. All rights reserved.
 *
 */
public interface CoinRepository extends PagingAndSortingRepository<Coin, Long>{
	Coin findByCompany(String value);

	Coin findByNameCoin(String value);

	Coin findByAbbreviationCurrency(String value);

	Coin findByActive(Boolean value);


}