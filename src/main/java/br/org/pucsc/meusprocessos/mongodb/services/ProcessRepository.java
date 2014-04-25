package br.org.pucsc.meusprocessos.mongodb.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import br.org.pucsc.meusprocessos.model.Customer;
import br.org.pucsc.meusprocessos.model.Employee;
import br.org.pucsc.meusprocessos.model.Process;
import br.org.pucsc.meusprocessos.service.ProcessService;

@Service
public class ProcessRepository implements ProcessService {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String COLLECTION = "Process";

	public Process getProcessById(Long id){
		List<Process> roleList = mongoTemplate.find(new Query(Criteria.where("id").is(id)), Process.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

	public Process getProcessByName(String name){
		List<Process> roleList = mongoTemplate.find(new Query(Criteria.where("name").is(name)), Process.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}
	
	public List<Process> getAllProcesss(){
		return mongoTemplate.findAll(Process.class, COLLECTION);
	}

	public void deleteProcess(Long id) {
		Process process = getProcessById(id);
		mongoTemplate.remove(process);
	}

	public Process createProcess(String name, String observation, Long idCustomer, Long idEmployee) {
		Process process = new Process(name);
		process.setIdCustomer(idCustomer);
		process.setIdEmployee(idEmployee);
		process.setObservation(observation);

		List<Process> usersList = new ArrayList<Process>();
		long val = 1;
		usersList = getAllProcesss();
		val = usersList.size();
		logger.warn("size of values "+val);
		
		process.setId(val+1);
		mongoTemplate.save(process,COLLECTION);
		return process;
	}
	
	public Process updateProcess(Long id, String name, String observation, Long idCustomer, Long idEmployee) {
		Process process = getProcessById(id);
		
		process.setName(name);
		process.setIdCustomer(idCustomer);
		process.setIdEmployee(idEmployee);
		process.setObservation(observation);

		mongoTemplate.save(process);
		
		return process;
	}

}
