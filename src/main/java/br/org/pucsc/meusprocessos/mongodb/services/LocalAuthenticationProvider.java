package br.org.pucsc.meusprocessos.mongodb.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import br.org.pucsc.meusprocessos.service.UserService;

@Component
public class LocalAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
	}
	
	@Override
	public UserDetails retrieveUser(String email, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		String password = (String) authentication.getCredentials();
		if (!StringUtils.hasText(password)) {
			logger.warn("E-mail {}: no password provided", email);
			throw new BadCredentialsException("Please enter password");
		}
		
		// UserAccount user = userService.getByUsernameAndPassword(username,
		// encoder.encodePassword(password, null));
		br.org.pucsc.meusprocessos.model.User user = userService.getUserByEmail(email);
		if (user == null) {
			logger.warn("E-mail {}, password {}: email and password not found", email, password);
			throw new BadCredentialsException("Invalid Email/Password");
		}
		
		final List<GrantedAuthority> auths;
		if (null != user.getRole()) {
			auths = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getRoleName());
		} else {
			auths = AuthorityUtils.NO_AUTHORITIES;
		}
		
		return new User(email, password, user.isEnabled(), // enabled
				true, // account not expired
				true, // credentials not expired
				true, // account not locked
				auths);
	}
	
}
