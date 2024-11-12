package com.capstone.user_service.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class UsersPojo {

	private long id;

	private String name;
	private String email;
	private String phone;
	private String role;
	private String password;
	
	List<TicketPojo> tickets;
}
