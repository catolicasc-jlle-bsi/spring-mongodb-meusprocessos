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

import br.org.pucsc.meusprocessos.model.Employee;
import br.org.pucsc.meusprocessos.service.EmployeeService;

@Service
public class EmployeeRepository implements EmployeeService {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String COLLECTION = "Employee";

	public Employee getEmployeeById(Long id){
		List<Employee> roleList = mongoTemplate.find(new Query(Criteria.where("id").is(id)), Employee.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

	public Employee getEmployeeByName(String name){
		List<Employee> roleList = mongoTemplate.find(new Query(Criteria.where("name").is(name)), Employee.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}
	
	public Employee getEmployeeByEmail(String email){
		List<Employee> roleList = mongoTemplate.find(new Query(Criteria.where("email").is(email)), Employee.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}
	public List<Employee> getAllEmployees(){
		return mongoTemplate.findAll(Employee.class, COLLECTION);
	}

	public Employee getEmployeeByCnpj(String cnpj) {
		List<Employee> roleList = mongoTemplate.find(new Query(Criteria.where("name").is(cnpj)), Employee.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

	public void deleteEmployee(Long id) {
		Employee employee = getEmployeeById(id);
		mongoTemplate.remove(employee);
	}

	public Employee createEmployee(String name,String cnpj, String oabNumber, String email,String fone,String observation){
		Employee employee = new Employee(name,cnpj,email);
		employee.setPhone(fone);
		employee.setOabNumber(oabNumber);
		employee.setObservation(observation);
		List<Employee> usersList = new ArrayList<Employee>();
		long val = 1;
		usersList = getAllEmployees();
		val = usersList.size();
		logger.warn("size of values "+val);
		
		employee.setId(val+1);
		mongoTemplate.save(employee,COLLECTION);
		return employee;
	}
	
	public Employee updateEmployee(Long id, String name, String cnpj, String oabNumber, String email, String phone, String observation) {
		Employee employee = getEmployeeById(id);
		
		employee.setName(name);
		employee.setCnpj(cnpj);;
		employee.setEmail(email);
		employee.setOabNumber(oabNumber);
		employee.setPhone(phone);
		employee.setObservation(observation);

		mongoTemplate.save(employee);
		
		return employee;
	}

}
