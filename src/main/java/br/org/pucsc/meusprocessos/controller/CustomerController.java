
package br.org.pucsc.meusprocessos.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.org.pucsc.meusprocessos.model.Customer;
import br.org.pucsc.meusprocessos.service.CustomerService;
import br.org.pucsc.meusprocessos.service.UserService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {
	

	@Autowired
	CustomerService customerService;
	
	@Autowired 
	UserService userService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/" ,method = RequestMethod.GET)
	public String getIndex(Model model){
		
		List<Customer> customers = getCustomerService().getAllCustomers();
		model.addAttribute("customers", customers);
	
		return "customer/list";
	}
	
	@RequestMapping(value="/read" ,method = RequestMethod.GET)
	public String getRead(String id, Model model){
		
		Customer customer = getCustomerService().getCustomerById(new Long(id));
		model.addAttribute("customer", customer);
	
		return "customer/edit";
	}
	
	@RequestMapping(value="/new" ,method = RequestMethod.GET)
	public String getNew(Model model){
				
		model.addAttribute("customer", new Customer());
	
		return "customer/edit";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String getDelete(HttpServletRequest request){
		String id = request.getParameter("id");
		System.out.println("******** customer delete: "+id);
				
		getCustomerService().deleteCustomer(new Long(id));
		
		return "customer/edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String postSave(HttpServletRequest request){
		
		boolean isCreate = true;
				
		if (request.getParameter("id") != null && !request.getParameter("id").equals("")) {
			isCreate = false;
		}
		
		String name = request.getParameter("name") != null ? request.getParameter("name") : "";
		String cnpj = request.getParameter("cnpj") != null ? request.getParameter("cnpj") : "";;
		String email = request.getParameter("email") != null ? request.getParameter("email") : "";;
		String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";;
		String observation = request.getParameter("observation") != null ? request.getParameter("observation") : "";;
		
		Customer customer = null;
		
		if (isCreate) {
			customer = getCustomerService().createCustomer(name, cnpj, email, phone, observation);
		} else {
			Long id = new Long(request.getParameter("id"));
			customer = getCustomerService().updateCustomer(id, name, cnpj, email, phone, observation);
		}
		
		request.setAttribute("customer",customer);
		
		return "redirect:/customer/";
	}
			
}
