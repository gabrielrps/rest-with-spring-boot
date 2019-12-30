package br.com.api.restwithspringboot.services;

import br.com.api.restwithspringboot.data.model.User;

public interface UserService {
	
	public User loadUserByUsername(String username);

}
