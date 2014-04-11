package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.core.services.items.ItemServiceImpl;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitServiceImpl;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;

import co.com.cybersoft.core.services.afe.AfeService;
import co.com.cybersoft.core.services.afe.AfeServiceImpl;
import co.com.cybersoft.persistence.services.afe.AfePersistenceService;

import co.com.cybersoft.core.services.paymentType.PaymentTypeService;
import co.com.cybersoft.core.services.paymentType.PaymentTypeServiceImpl;
import co.com.cybersoft.persistence.services.paymentType.PaymentTypePersistenceService;



@Configuration
public class CoreConfig {

	@Bean 
	public ItemService itemService(ItemPersistenceService itemPersistenceService){
		return new ItemServiceImpl(itemPersistenceService);
	}
	
	@Bean
	public MeasurementUnitService measurementUnitService(MeasurementUnitPersistenceService measurementUnitPersistenceService){
		return new MeasurementUnitServiceImpl(measurementUnitPersistenceService);
	}

	@Bean
	public AfeService afeService(AfePersistenceService afePersistenceService){
		return new AfeServiceImpl(afePersistenceService);
	}

	@Bean
	public PaymentTypeService paymentTypeService(PaymentTypePersistenceService paymentTypePersistenceService){
		return new PaymentTypeServiceImpl(paymentTypePersistenceService);
	}


}