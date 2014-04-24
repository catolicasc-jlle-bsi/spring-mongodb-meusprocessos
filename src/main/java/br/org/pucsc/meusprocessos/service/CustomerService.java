package br.org.pucsc.meusprocessos.service;

import java.util.List;

import br.org.pucsc.meusprocessos.model.Customer;

public interface CustomerService {
	
	public List<Customer> getAllCustomers();
	
	public Customer getCustomerById(Long id);
	
	public Customer getCustomerByName(String name);
	
	public Customer getCustomerByCnpj(String cnpj);
	
	public Customer getCustomerByEmail(String email);
	
	public void deleteCustomer(Long id);
	
	public Customer createCustomer(String name, String cnpj, String email, String phone, String observation);

	public Customer updateCustomer(Long id, String name, String cnpj, String email, String phone, String observation);
}
