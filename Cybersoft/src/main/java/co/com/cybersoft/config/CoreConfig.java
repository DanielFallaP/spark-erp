package co.com.cybersoft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.com.cybersoft.core.services.items.ItemService;
import co.com.cybersoft.core.services.items.ItemServiceImpl;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitService;
import co.com.cybersoft.core.services.measurementUnit.MeasurementUnitServiceImpl;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.core.services.articulo.ArticuloService;
import co.com.cybersoft.core.services.articulo.ArticuloServiceImpl;
import co.com.cybersoft.persistence.services.articulo.ArticuloPersistenceService;

import co.com.cybersoft.core.services.grupo.GrupoService;
import co.com.cybersoft.core.services.grupo.GrupoServiceImpl;
import co.com.cybersoft.persistence.services.grupo.GrupoPersistenceService;

import co.com.cybersoft.core.services.unidadMedida.UnidadMedidaService;
import co.com.cybersoft.core.services.unidadMedida.UnidadMedidaServiceImpl;
import co.com.cybersoft.persistence.services.unidadMedida.UnidadMedidaPersistenceService;



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
	public ArticuloService articuloService(ArticuloPersistenceService articuloPersistenceService){
		return new ArticuloServiceImpl(articuloPersistenceService);
	}

	@Bean
	public GrupoService grupoService(GrupoPersistenceService grupoPersistenceService){
		return new GrupoServiceImpl(grupoPersistenceService);
	}

	@Bean
	public UnidadMedidaService unidadMedidaService(UnidadMedidaPersistenceService unidadMedidaPersistenceService){
		return new UnidadMedidaServiceImpl(unidadMedidaPersistenceService);
	}


}