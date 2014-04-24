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

import br.org.pucsc.meusprocessos.model.Role;
import br.org.pucsc.meusprocessos.model.User;
import br.org.pucsc.meusprocessos.service.UserService;

@Service
public class UserRepository implements UserService {
	

	@Autowired
	MongoTemplate mongoTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String USER_COLLECTION = "User";

	public User getUserById(Long id){
		return mongoTemplate.findById(id, User.class);
	}

	public User getUserByName(String userName){
		List<User> roleList = mongoTemplate.find(new Query(Criteria.where("name").is(userName)), User.class,USER_COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}
	
	public User getUserByEmail(String email){
		List<User> roleList = mongoTemplate.find(new Query(Criteria.where("email").is(email)), User.class,USER_COLLECTION);
		return roleList!=null && roleList.size()>0?roleList.get(0):null;
	}

	public User createUser(String name,String password,String email,Role role){
		User user = new User(name,password,email);
		user.setRole(role);
		user.setEnabled(true);
		List<User> usersList = new ArrayList<User>();
		long val = 1;
		usersList = getAllUsers();
		val = usersList.size();
		logger.warn("size of values "+val);
		
		user.setId(val+1);
		mongoTemplate.save(user,USER_COLLECTION);
		return user;
	}
	
	public List<User> getAllUsers(){
		return mongoTemplate.findAll(User.class, USER_COLLECTION);
	}
}
