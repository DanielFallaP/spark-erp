package co.com.cybersoft.man.maintenance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;

import co.com.cybersoft.maintenance.tables.core.services.workOrders.WorkOrdersService;
import co.com.cybersoft.maintenance.tables.events.workOrders.RequestWorkOrdersPageEvent;
import co.com.cybersoft.maintenance.tables.events.workOrders.WorkOrdersPageEvent;
import co.com.cybersoft.maintenance.tables.persistence.domain.MaintenanceHistory;
import co.com.cybersoft.maintenance.tables.persistence.domain.WorkOrders;
import co.com.cybersoft.maintenance.tables.persistence.repository.maintenanceHistory.MaintenanceHistoryRepository;
import co.com.cybersoft.maintenance.tables.persistence.repository.workOrders.WorkOrdersRepository;
import co.com.cybersoft.maintenance.tables.web.domain.workOrders.WorkOrdersFilterInfo;

public class WorkOrdersAPI {
	@Autowired
	private WorkOrdersRepository workOrdersRepo;
	
	@Autowired
	private MaintenanceHistoryRepository historyRepo;
	
	@Autowired
	private WorkOrdersService workOrdersService;
	
	public void sendToHistory(WorkOrdersFilterInfo filter){
		PageRequest pageRequest = new PageRequest(0, 1000, Direction.DESC, "id");
		RequestWorkOrdersPageEvent pageEvent = new RequestWorkOrdersPageEvent(pageRequest, filter);
		WorkOrdersPageEvent requestWorkOrdersFilter;
		
		try {
			requestWorkOrdersFilter=workOrdersService.requestWorkOrdersFilter(pageEvent);
			List<WorkOrders> allList = requestWorkOrdersFilter.getAllList();
			List<WorkOrders> toRemove = new ArrayList<WorkOrders>();
			
			ArrayList<MaintenanceHistory> histories = new ArrayList<MaintenanceHistory>();
			
			for (WorkOrders workOrder : allList) {
				if (workOrder.getState()!=null && workOrder.getState().equals("Finalizado")){
					MaintenanceHistory history = new MaintenanceHistory();
					history.setActive(Boolean.TRUE);
					history.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
					history.setDateOfCreation(new Date());
					history.setDateOfModification(new Date());
					history.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
					
					history.setYearHistoricalOrder(workOrder.getYearOrder());
					
					histories.add(history);
					toRemove.add(workOrder);
				}
			}
			
			workOrdersRepo.delete(toRemove);
			historyRepo.save(histories);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
