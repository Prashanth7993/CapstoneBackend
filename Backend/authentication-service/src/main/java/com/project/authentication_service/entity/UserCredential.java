package com.project.authentication_service.entity;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class UserCredential {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String username;
	private String password;
	
	@OneToMany(mappedBy="usersCrential",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Role> roles;
	
	
	@Override
    public String toString() {
        return "UserCredential{id=" + id + ", username='" + username + "', roles=" + 
               (roles != null ? roles.stream().map(role -> role.getName()).collect(Collectors.joining(", ")) : "No Roles") + "}";
    }

}
