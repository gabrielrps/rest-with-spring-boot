package br.com.api.restwithspringboot.services.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.restwithspringboot.data.model.User;
import br.com.api.restwithspringboot.repository.UserRepository;
import br.com.api.restwithspringboot.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user != null)
			return user;
		else					
			throw new UsernameNotFoundException("Username " + username + "not found");
	}

	

}
