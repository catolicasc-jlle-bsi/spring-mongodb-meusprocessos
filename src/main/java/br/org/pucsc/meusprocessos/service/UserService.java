package br.org.pucsc.meusprocessos.service;

import br.org.pucsc.meusprocessos.model.Role;
import br.org.pucsc.meusprocessos.model.User;

public interface UserService {
	
	public User getUserById(Long userId);
	
	public User getUserByName(String name);
	
	public User getUserByEmail(String email);
	
	public User createUser(String name, String password, String email, Role role);

}
