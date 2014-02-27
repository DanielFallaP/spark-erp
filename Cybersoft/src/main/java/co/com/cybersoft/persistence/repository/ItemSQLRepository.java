package co.com.cybersoft.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import co.com.cybersoft.persistence.domain.Item;

public class ItemSQLRepository implements ItemRepository{

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Transactional
	@Override
	public Item save(Item item) {
		
		jdbcTemplate.update("insert into cybersoft.item (code, short_description, purchase_unit_of_measurement) values (?,?,?)", item.getCode(),item.getShortDescription(),item.getPurchaseUnitOfMeasurement());
		
		List<Integer> result = jdbcTemplate.query("select LAST_INSERT_ID()", new RowMapper<Integer>(){

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
			
		});
		
		Integer integer = result.get(0);
		item.setId(integer.toString());
		return item;
	}

}
