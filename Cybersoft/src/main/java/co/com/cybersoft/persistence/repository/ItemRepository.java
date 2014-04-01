package co.com.cybersoft.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import co.com.cybersoft.persistence.domain.Item;

public interface ItemRepository extends MongoRepository<Item, String>{
}
