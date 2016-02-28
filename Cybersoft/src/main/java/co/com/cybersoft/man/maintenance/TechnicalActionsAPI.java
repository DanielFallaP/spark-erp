package co.com.cybersoft.man.maintenance;

import co.com.cybersoft.purchase.tables.persistence.repository.exchangeRate.ExchangeRateRepository;
import co.com.cybersoft.purchase.tables.web.domain.exchangeRate.ExchangeRateFilterInfo;

public interface TechnicalActionsAPI {
	void transactionA(ExchangeRateFilterInfo filter, ExchangeRateRepository exchangeRateRepository);
}
