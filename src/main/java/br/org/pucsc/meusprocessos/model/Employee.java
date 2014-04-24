package br.org.pucsc.meusprocessos.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Employee")
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;
	
	private String cnpj;	

	private String oabNumber;
	
	private String email;
	
	private String phone;
	
	private String observation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOabNumber() {
		return oabNumber;
	}

	public void setOabNumber(String oabNumber) {
		this.oabNumber = oabNumber;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Employee() {
	}
	
	public Employee(String name, String cnpj, String email) {
		this.name = name;
		this.cnpj = cnpj;
		this.email = email;
	}
	
}
