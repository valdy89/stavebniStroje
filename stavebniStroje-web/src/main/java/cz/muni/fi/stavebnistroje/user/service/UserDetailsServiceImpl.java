package cz.muni.fi.stavebnistroje.user.service;

import cz.muni.fi.stavebnistroje.dao.CustomerDao;
import cz.muni.fi.stavebnistroje.entity.Customer;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A custom {@link UserDetailsService} where user information
 * is retrieved from a JPA repository
 */
//@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerDao customerDao;
	
	//private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Customer customer = customerDao.findByUsername(username);		
		if (customer == null)
			throw new UsernameNotFoundException("Unable to find user with specified username");
                Collection<GrantedAuthority> authority = new ArrayList<>();
                authority.add(new SimpleGrantedAuthority(customer.getRole().toString()));
		return new User(
				customer.getUsername(),
				customer.getPassword(),
				true,
				true,
				true,
				true,
				authority
		);

	}
	
	
}
