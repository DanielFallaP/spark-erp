package co.com.cybersoft.config;


import java.net.UnknownHostException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import co.com.cybersoft.persistence.repository.ActiveRepository;
import co.com.cybersoft.persistence.repository.AfeRepository;
import co.com.cybersoft.persistence.repository.AfeTypeRepository;
import co.com.cybersoft.persistence.repository.BillRepository;
import co.com.cybersoft.persistence.repository.BranchRepository;
import co.com.cybersoft.persistence.repository.CalculusTypeRepository;
import co.com.cybersoft.persistence.repository.CompanyRepository;
import co.com.cybersoft.persistence.repository.ContractRepository;
import co.com.cybersoft.persistence.repository.CorporationRepository;
import co.com.cybersoft.persistence.repository.CostCenterRepository;
import co.com.cybersoft.persistence.repository.ItemsRepository;
import co.com.cybersoft.persistence.repository.JointVentureRepository;
import co.com.cybersoft.persistence.repository.MeasurementUnitRepository;
import co.com.cybersoft.persistence.repository.MeasurementUnitsRepository;
import co.com.cybersoft.persistence.repository.OperationTypeRepository;
import co.com.cybersoft.persistence.repository.PartnerRepository;
import co.com.cybersoft.persistence.repository.WareHouseCustomRepo;
import co.com.cybersoft.persistence.repository.WareHouseCustomRepoImpl;
import co.com.cybersoft.persistence.repository.WareHouseRepository;
import co.com.cybersoft.persistence.repository.items.ItemRepository;
import co.com.cybersoft.persistence.services.active.ActivePersistenceService;
import co.com.cybersoft.persistence.services.active.ActivePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.afe.AfePersistenceService;
import co.com.cybersoft.persistence.services.afe.AfePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.afeType.AfeTypePersistenceService;
import co.com.cybersoft.persistence.services.afeType.AfeTypePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.bill.BillPersistenceService;
import co.com.cybersoft.persistence.services.bill.BillPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.branch.BranchPersistenceService;
import co.com.cybersoft.persistence.services.branch.BranchPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.calculusType.CalculusTypePersistenceService;
import co.com.cybersoft.persistence.services.calculusType.CalculusTypePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.company.CompanyPersistenceService;
import co.com.cybersoft.persistence.services.company.CompanyPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.contract.ContractPersistenceService;
import co.com.cybersoft.persistence.services.contract.ContractPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.corporation.CorporationPersistenceService;
import co.com.cybersoft.persistence.services.corporation.CorporationPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.costCenter.CostCenterPersistenceService;
import co.com.cybersoft.persistence.services.costCenter.CostCenterPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
import co.com.cybersoft.persistence.services.items.ItemPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.items.ItemsPersistenceService;
import co.com.cybersoft.persistence.services.items.ItemsPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.jointVenture.JointVenturePersistenceService;
import co.com.cybersoft.persistence.services.jointVenture.JointVenturePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.measurementUnits.MeasurementUnitsPersistenceService;
import co.com.cybersoft.persistence.services.measurementUnits.MeasurementUnitsPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.operationType.OperationTypePersistenceService;
import co.com.cybersoft.persistence.services.operationType.OperationTypePersistenceServiceImpl;
import co.com.cybersoft.persistence.services.partner.PartnerPersistenceService;
import co.com.cybersoft.persistence.services.partner.PartnerPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.wareHouse.WareHousePersistenceService;
import co.com.cybersoft.persistence.services.wareHouse.WareHousePersistenceServiceImpl;

import com.mongodb.Mongo;

@Configuration
@EnableMongoRepositories(basePackages="co.com.cybersoft.persistence.repository",
includeFilters=@ComponentScan.Filter(value={ItemRepository.class, MeasurementUnitRepository.class,ActiveRepository.class,PartnerRepository.class,CorporationRepository.class,CompanyRepository.class,BranchRepository.class,MeasurementUnitsRepository.class,ItemsRepository.class,BillRepository.class,JointVentureRepository.class,ContractRepository.class,AfeTypeRepository.class,AfeRepository.class,CostCenterRepository.class,CalculusTypeRepository.class,OperationTypeRepository.class,WareHouseRepository.class},type=FilterType.ASSIGNABLE_TYPE))
public class PersistenceConfig {
	
	@Bean
	public	MongoTemplate mongoTemplate(Mongo mongo) throws UnknownHostException{
		return new MongoTemplate(mongo,"cybersoft");
	}
	
	@Bean
	public  Mongo mongo() throws UnknownHostException{
		return new Mongo("localhost");
	}
	
	/**
	 * Item repository
	 */
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private MeasurementUnitRepository measurementUnitRepository;
	
	@Autowired
	private ActiveRepository activeRepository;

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private CorporationRepository corporationRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private MeasurementUnitsRepository measurementUnitsRepository;

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private JointVentureRepository jointVentureRepository;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private AfeTypeRepository afeTypeRepository;

	@Autowired
	private AfeRepository afeRepository;

	@Autowired
	private CostCenterRepository costCenterRepository;

	@Autowired
	private CalculusTypeRepository calculusTypeRepository;

	@Autowired
	private OperationTypeRepository operationTypeRepository;

	@Autowired
	private WareHouseRepository wareHouseRepository;

	@Bean
	public WareHouseCustomRepo wareHouseCustomRepo(){
		return new WareHouseCustomRepoImpl();
	}
		
	@Bean
    public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("daniel");
		dataSource.setPassword("daniel");
		dataSource.setUrl("jdbc:mysql://localhost/cybersoft");
		return dataSource;
    }

	@Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }
	
	@Bean
	public ItemPersistenceService itemPersistenceService(){
		return new ItemPersistenceServiceImpl(itemRepository);
	}
	
	@Bean 
	public MeasurementUnitPersistenceService measurementUnitPersistenceService(){
		return new MeasurementUnitPersistenceServiceImpl(measurementUnitRepository);
	}
	
	@Bean 
	public ActivePersistenceService activePersistenceService(){
		return new ActivePersistenceServiceImpl(activeRepository);
	}

	@Bean 
	public PartnerPersistenceService partnerPersistenceService(){
		return new PartnerPersistenceServiceImpl(partnerRepository);
	}

	@Bean 
	public CorporationPersistenceService corporationPersistenceService(){
		return new CorporationPersistenceServiceImpl(corporationRepository);
	}

	@Bean 
	public CompanyPersistenceService companyPersistenceService(){
		return new CompanyPersistenceServiceImpl(companyRepository);
	}

	@Bean 
	public BranchPersistenceService branchPersistenceService(){
		return new BranchPersistenceServiceImpl(branchRepository);
	}

	@Bean 
	public MeasurementUnitsPersistenceService measurementUnitsPersistenceService(){
		return new MeasurementUnitsPersistenceServiceImpl(measurementUnitsRepository);
	}

	@Bean 
	public ItemsPersistenceService itemsPersistenceService(){
		return new ItemsPersistenceServiceImpl(itemsRepository);
	}

	@Bean 
	public BillPersistenceService billPersistenceService(){
		return new BillPersistenceServiceImpl(billRepository);
	}

	@Bean 
	public JointVenturePersistenceService jointVenturePersistenceService(){
		return new JointVenturePersistenceServiceImpl(jointVentureRepository);
	}

	@Bean 
	public ContractPersistenceService contractPersistenceService(){
		return new ContractPersistenceServiceImpl(contractRepository);
	}

	@Bean 
	public AfeTypePersistenceService afeTypePersistenceService(){
		return new AfeTypePersistenceServiceImpl(afeTypeRepository);
	}

	@Bean 
	public AfePersistenceService afePersistenceService(){
		return new AfePersistenceServiceImpl(afeRepository);
	}

	@Bean 
	public CostCenterPersistenceService costCenterPersistenceService(){
		return new CostCenterPersistenceServiceImpl(costCenterRepository);
	}

	@Bean 
	public CalculusTypePersistenceService calculusTypePersistenceService(){
		return new CalculusTypePersistenceServiceImpl(calculusTypeRepository);
	}

	@Bean 
	public OperationTypePersistenceService operationTypePersistenceService(){
		return new OperationTypePersistenceServiceImpl(operationTypeRepository);
	}

	@Bean 
	public WareHousePersistenceService wareHousePersistenceService(){
		return new WareHousePersistenceServiceImpl(wareHouseRepository, wareHouseCustomRepo());
	}


	
	 @Bean
     public PlatformTransactionManager txManager() {
         return new DataSourceTransactionManager(dataSource());
     }
}