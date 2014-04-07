package co.com.cybersoft.generator.code.events;

import java.util.List;

import co.com.cybersoft.generator.code.model.Cybersoft;
import co.com.cybersoft.generator.code.model.Table;

public class EventGenerator {
	
	public void generate(Cybersoft cybersoft){
		List<Table> tables = cybersoft.getTables();
		for (Table table : tables) {
			generateCreateEvent(table);
			generateResponseCreatedEvent(table);
			generateDetailsEvent(table);
			generateResponseDetailsEvent(table);
			generateEntityEvent(table);
			generateModificationEvent(table);
			generateResponseModificationEvent(table);
		}
	}

	private void generateCreateEvent(Table table){
		
	}
	
	private void generateResponseCreatedEvent(Table table){
		
	}
	
	private void generateDetailsEvent(Table table){
		
	}
	
	private void generateResponseDetailsEvent(Table table){
		
	}
	
	private void generateEntityEvent(Table table){
		
	}
	
	private void generateModificationEvent(Table table){
		
	}
	
	private void generateResponseModificationEvent(Table table){
		
	}
}
