package br.com.api.restwithspringboot.data.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVO implements Serializable{
	
	private static final long serialVersionUID = 7656607858946969329L;

	private Long id;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;

}
