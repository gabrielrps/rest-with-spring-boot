package br.com.api.restwithspringboot.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable{
	
	private static final long serialVersionUID = 6298993022656542395L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "first_name", nullable = false, length = 30)
	private String firstName;
	
	@Column(name = "last_name", nullable = false, length = 30)
	private String lastName;
	
	@Column(name = "address", nullable = false, length = 50)
	private String address;
	
	@Column(name = "gender", nullable = false, length = 6)
	private String gender;
	
	@Column(name = "enabled", nullable = false)
	private Boolean enabled;

}
