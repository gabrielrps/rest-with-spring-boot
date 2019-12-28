package br.com.api.restwithspringboot.data.vo;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
public class PersonVO extends RepresentationModel<PersonVO> implements Serializable{
	
	private static final long serialVersionUID = 7656607858946969329L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	private String address;
	
	//@JsonIgnore
	//This annotation ignore the attribute on response
	private String gender;

}
