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

import co.com.cybersoft.persistence.repository.items.ItemRepository;
import co.com.cybersoft.persistence.services.items.ItemPersistenceService;
import co.com.cybersoft.persistence.services.items.ItemPersistenceServiceImpl;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceService;
import co.com.cybersoft.persistence.services.measurementUnit.MeasurementUnitPersistenceServiceImpl;
import co.com.cybersoft.persistence.repository.MeasurementUnitRepository;
import co.com.cybersoft.persistence.repository.active.ActiveRepository;
import co.com.cybersoft.persistence.repository.active.ActiveCustomRepo;
import co.com.cybersoft.persistence.repository.active.ActiveCustomRepoImpl;
import co.com.cybersoft.persistence.services.active.ActivePersistenceService;
import co.com.cybersoft.persistence.services.active.ActivePersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.partner.PartnerRepository;
import co.com.cybersoft.persistence.repository.partner.PartnerCustomRepo;
import co.com.cybersoft.persistence.repository.partner.PartnerCustomRepoImpl;
import co.com.cybersoft.persistence.services.partner.PartnerPersistenceService;
import co.com.cybersoft.persistence.services.partner.PartnerPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.corporation.CorporationRepository;
import co.com.cybersoft.persistence.repository.corporation.CorporationCustomRepo;
import co.com.cybersoft.persistence.repository.corporation.CorporationCustomRepoImpl;
import co.com.cybersoft.persistence.services.corporation.CorporationPersistenceService;
import co.com.cybersoft.persistence.services.corporation.CorporationPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.company.CompanyRepository;
import co.com.cybersoft.persistence.repository.company.CompanyCustomRepo;
import co.com.cybersoft.persistence.repository.company.CompanyCustomRepoImpl;
import co.com.cybersoft.persistence.services.company.CompanyPersistenceService;
import co.com.cybersoft.persistence.services.company.CompanyPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.branch.BranchRepository;
import co.com.cybersoft.persistence.repository.branch.BranchCustomRepo;
import co.com.cybersoft.persistence.repository.branch.BranchCustomRepoImpl;
import co.com.cybersoft.persistence.services.branch.BranchPersistenceService;
import co.com.cybersoft.persistence.services.branch.BranchPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.measurementUnits.MeasurementUnitsRepository;
import co.com.cybersoft.persistence.repository.measurementUnits.MeasurementUnitsCustomRepo;
import co.com.cybersoft.persistence.repository.measurementUnits.MeasurementUnitsCustomRepoImpl;
import co.com.cybersoft.persistence.services.measurementUnits.MeasurementUnitsPersistenceService;
import co.com.cybersoft.persistence.services.measurementUnits.MeasurementUnitsPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.items.ItemsRepository;
import co.com.cybersoft.persistence.repository.items.ItemsCustomRepo;
import co.com.cybersoft.persistence.repository.items.ItemsCustomRepoImpl;
import co.com.cybersoft.persistence.services.items.ItemsPersistenceService;
import co.com.cybersoft.persistence.services.items.ItemsPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.bill.BillRepository;
import co.com.cybersoft.persistence.repository.bill.BillCustomRepo;
import co.com.cybersoft.persistence.repository.bill.BillCustomRepoImpl;
import co.com.cybersoft.persistence.services.bill.BillPersistenceService;
import co.com.cybersoft.persistence.services.bill.BillPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.jointVenture.JointVentureRepository;
import co.com.cybersoft.persistence.repository.jointVenture.JointVentureCustomRepo;
import co.com.cybersoft.persistence.repository.jointVenture.JointVentureCustomRepoImpl;
import co.com.cybersoft.persistence.services.jointVenture.JointVenturePersistenceService;
import co.com.cybersoft.persistence.services.jointVenture.JointVenturePersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.contract.ContractRepository;
import co.com.cybersoft.persistence.repository.contract.ContractCustomRepo;
import co.com.cybersoft.persistence.repository.contract.ContractCustomRepoImpl;
import co.com.cybersoft.persistence.services.contract.ContractPersistenceService;
import co.com.cybersoft.persistence.services.contract.ContractPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.afeType.AfeTypeRepository;
import co.com.cybersoft.persistence.repository.afeType.AfeTypeCustomRepo;
import co.com.cybersoft.persistence.repository.afeType.AfeTypeCustomRepoImpl;
import co.com.cybersoft.persistence.services.afeType.AfeTypePersistenceService;
import co.com.cybersoft.persistence.services.afeType.AfeTypePersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.afe.AfeRepository;
import co.com.cybersoft.persistence.repository.afe.AfeCustomRepo;
import co.com.cybersoft.persistence.repository.afe.AfeCustomRepoImpl;
import co.com.cybersoft.persistence.services.afe.AfePersistenceService;
import co.com.cybersoft.persistence.services.afe.AfePersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.costCenter.CostCenterRepository;
import co.com.cybersoft.persistence.repository.costCenter.CostCenterCustomRepo;
import co.com.cybersoft.persistence.repository.costCenter.CostCenterCustomRepoImpl;
import co.com.cybersoft.persistence.services.costCenter.CostCenterPersistenceService;
import co.com.cybersoft.persistence.services.costCenter.CostCenterPersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.calculusType.CalculusTypeRepository;
import co.com.cybersoft.persistence.repository.calculusType.CalculusTypeCustomRepo;
import co.com.cybersoft.persistence.repository.calculusType.CalculusTypeCustomRepoImpl;
import co.com.cybersoft.persistence.services.calculusType.CalculusTypePersistenceService;
import co.com.cybersoft.persistence.services.calculusType.CalculusTypePersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.operationType.OperationTypeRepository;
import co.com.cybersoft.persistence.repository.operationType.OperationTypeCustomRepo;
import co.com.cybersoft.persistence.repository.operationType.OperationTypeCustomRepoImpl;
import co.com.cybersoft.persistence.services.operationType.OperationTypePersistenceService;
import co.com.cybersoft.persistence.services.operationType.OperationTypePersistenceServiceImpl;

import co.com.cybersoft.persistence.repository.wareHouse.WareHouseRepository;
import co.com.cybersoft.persistence.repository.wareHouse.WareHouseCustomRepo;
import co.com.cybersoft.persistence.repository.wareHouse.WareHouseCustomRepoImpl;
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

	@Bean
	public ActiveCustomRepo activeCustomRepo(){
		return new ActiveCustomRepoImpl();
	}

	@Autowired
	private PartnerRepository partnerRepository;

	@Bean
	public PartnerCustomRepo partnerCustomRepo(){
		return new PartnerCustomRepoImpl();
	}

	@Autowired
	private CorporationRepository corporationRepository;

	@Bean
	public CorporationCustomRepo corporationCustomRepo(){
		return new CorporationCustomRepoImpl();
	}

	@Autowired
	private CompanyRepository companyRepository;

	@Bean
	public CompanyCustomRepo companyCustomRepo(){
		return new CompanyCustomRepoImpl();
	}

	@Autowired
	private BranchRepository branchRepository;

	@Bean
	public BranchCustomRepo branchCustomRepo(){
		return new BranchCustomRepoImpl();
	}

	@Autowired
	private MeasurementUnitsRepository measurementUnitsRepository;

	@Bean
	public MeasurementUnitsCustomRepo measurementUnitsCustomRepo(){
		return new MeasurementUnitsCustomRepoImpl();
	}

	@Autowired
	private ItemsRepository itemsRepository;

	@Bean
	public ItemsCustomRepo itemsCustomRepo(){
		return new ItemsCustomRepoImpl();
	}

	@Autowired
	private BillRepository billRepository;

	@Bean
	public BillCustomRepo billCustomRepo(){
		return new BillCustomRepoImpl();
	}

	@Autowired
	private JointVentureRepository jointVentureRepository;

	@Bean
	public JointVentureCustomRepo jointVentureCustomRepo(){
		return new JointVentureCustomRepoImpl();
	}

	@Autowired
	private ContractRepository contractRepository;

	@Bean
	public ContractCustomRepo contractCustomRepo(){
		return new ContractCustomRepoImpl();
	}

	@Autowired
	private AfeTypeRepository afeTypeRepository;

	@Bean
	public AfeTypeCustomRepo afeTypeCustomRepo(){
		return new AfeTypeCustomRepoImpl();
	}

	@Autowired
	private AfeRepository afeRepository;

	@Bean
	public AfeCustomRepo afeCustomRepo(){
		return new AfeCustomRepoImpl();
	}

	@Autowired
	private CostCenterRepository costCenterRepository;

	@Bean
	public CostCenterCustomRepo costCenterCustomRepo(){
		return new CostCenterCustomRepoImpl();
	}

	@Autowired
	private CalculusTypeRepository calculusTypeRepository;

	@Bean
	public CalculusTypeCustomRepo calculusTypeCustomRepo(){
		return new CalculusTypeCustomRepoImpl();
	}

	@Autowired
	private OperationTypeRepository operationTypeRepository;

	@Bean
	public OperationTypeCustomRepo operationTypeCustomRepo(){
		return new OperationTypeCustomRepoImpl();
	}

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
		return new ActivePersistenceServiceImpl(activeRepository, activeCustomRepo());
	}

	@Bean 
	public PartnerPersistenceService partnerPersistenceService(){
		return new PartnerPersistenceServiceImpl(partnerRepository, partnerCustomRepo());
	}

	@Bean 
	public CorporationPersistenceService corporationPersistenceService(){
		return new CorporationPersistenceServiceImpl(corporationRepository, corporationCustomRepo());
	}

	@Bean 
	public CompanyPersistenceService companyPersistenceService(){
		return new CompanyPersistenceServiceImpl(companyRepository, companyCustomRepo());
	}

	@Bean 
	public BranchPersistenceService branchPersistenceService(){
		return new BranchPersistenceServiceImpl(branchRepository, branchCustomRepo());
	}

	@Bean 
	public MeasurementUnitsPersistenceService measurementUnitsPersistenceService(){
		return new MeasurementUnitsPersistenceServiceImpl(measurementUnitsRepository, measurementUnitsCustomRepo());
	}

	@Bean 
	public ItemsPersistenceService itemsPersistenceService(){
		return new ItemsPersistenceServiceImpl(itemsRepository, itemsCustomRepo());
	}

	@Bean 
	public BillPersistenceService billPersistenceService(){
		return new BillPersistenceServiceImpl(billRepository, billCustomRepo());
	}

	@Bean 
	public JointVenturePersistenceService jointVenturePersistenceService(){
		return new JointVenturePersistenceServiceImpl(jointVentureRepository, jointVentureCustomRepo());
	}

	@Bean 
	public ContractPersistenceService contractPersistenceService(){
		return new ContractPersistenceServiceImpl(contractRepository, contractCustomRepo());
	}

	@Bean 
	public AfeTypePersistenceService afeTypePersistenceService(){
		return new AfeTypePersistenceServiceImpl(afeTypeRepository, afeTypeCustomRepo());
	}

	@Bean 
	public AfePersistenceService afePersistenceService(){
		return new AfePersistenceServiceImpl(afeRepository, afeCustomRepo());
	}

	@Bean 
	public CostCenterPersistenceService costCenterPersistenceService(){
		return new CostCenterPersistenceServiceImpl(costCenterRepository, costCenterCustomRepo());
	}

	@Bean 
	public CalculusTypePersistenceService calculusTypePersistenceService(){
		return new CalculusTypePersistenceServiceImpl(calculusTypeRepository, calculusTypeCustomRepo());
	}

	@Bean 
	public OperationTypePersistenceService operationTypePersistenceService(){
		return new OperationTypePersistenceServiceImpl(operationTypeRepository, operationTypeCustomRepo());
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