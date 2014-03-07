package co.com.cybersoft.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import co.com.cybersoft.persistence.domain.Item;
import co.com.cybersoft.web.controller.items.ItemCreationController;

public class ItemSQLRepository{ 
//implements ItemRepository{

	private static final Logger LOG = LoggerFactory.getLogger(ItemCreationController.class);
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Transactional
	public Item save(Item item) {
		
		jdbcTemplate.update("insert into cybersoft.item (code, short_description, purchase_unit_of_measurement, date_of_creation, part_number, group_of_items, enabled, blocked, array) "
				+ "values (?,?,?,?,?,?,?,?,?)", 
				item.getCode(),item.getShortDescription(),item.getPurchaseUnitOfMeasurement(),item.getDateOfCreation(), item.getPartNumber(), item.getGroupOfItems(), item.getEnabled(),item.getBlocked(),item.getArray());
		
		List<Integer> result = jdbcTemplate.query("select LAST_INSERT_ID()", new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
			
		});
		
		Integer integer = result.get(0);
		item.setId(integer.toString());
		
		LOG.debug("Item created: "+integer);
		return item;
	}

}
