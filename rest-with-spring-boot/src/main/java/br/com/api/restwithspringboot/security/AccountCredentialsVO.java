package br.com.api.restwithspringboot.security;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentialsVO implements Serializable{

	private static final long serialVersionUID = -2562597875829278795L;
	
	private String username;
	private String password;

}
