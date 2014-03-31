package co.com.cybersoft.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Item;

public interface ItemRepository extends MongoRepository<Item, String>{
	Item findByCode(String code);
	List<Item> findAllOrderById();
}
