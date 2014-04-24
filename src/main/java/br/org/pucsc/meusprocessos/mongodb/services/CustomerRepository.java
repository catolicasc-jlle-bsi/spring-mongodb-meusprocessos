package br.org.pucsc.meusprocessos.mongodb.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import br.org.pucsc.meusprocessos.model.Customer;
import br.org.pucsc.meusprocessos.service.CustomerService;

@Service
public class CustomerRepository implements CustomerService {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String COLLECTION = "Customer";

	public Customer getCustomerById(Long id){
		List<Customer> roleList = mongoTemplate.find(new Query(Criteria.where("id").is(id)), Customer.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

	public Customer getCustomerByName(String name){
		List<Customer> roleList = mongoTemplate.find(new Query(Criteria.where("name").is(name)), Customer.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}
	
	public Customer getCustomerByEmail(String email){
		List<Customer> roleList = mongoTemplate.find(new Query(Criteria.where("email").is(email)), Customer.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}
	public List<Customer> getAllCustomers(){
		return mongoTemplate.findAll(Customer.class, COLLECTION);
	}

	public Customer getCustomerByCnpj(String cnpj) {
		List<Customer> roleList = mongoTemplate.find(new Query(Criteria.where("name").is(cnpj)), Customer.class,COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

	public void deleteCustomer(Long id) {
		Customer customer = getCustomerById(id);
		mongoTemplate.remove(customer);
	}

	public Customer createCustomer(String name,String password,String email,String fone,String observation){
		Customer customer = new Customer(name,password,email);
		customer.setPhone(fone);
		customer.setObservation(observation);
		List<Customer> usersList = new ArrayList<Customer>();
		long val = 1;
		usersList = getAllCustomers();
		val = usersList.size();
		logger.warn("size of values "+val);
		
		customer.setId(val+1);
		mongoTemplate.save(customer,COLLECTION);
		return customer;
	}
	
	public Customer updateCustomer(Long id, String name, String cnpj, String email, String phone, String observation) {
		Customer customer = getCustomerById(id);
		
		customer.setName(name);
		customer.setCnpj(cnpj);;
		customer.setEmail(email);
		customer.setPhone(phone);
		customer.setObservation(observation);

		mongoTemplate.save(customer);
		
		return customer;
	}

}
