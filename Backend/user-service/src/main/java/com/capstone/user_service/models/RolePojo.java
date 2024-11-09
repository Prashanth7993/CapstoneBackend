package com.capstone.user_service.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data


public class RolePojo {
	private long roleId;
	private String name;

	private UserCredentialPojo usersCrentials;

}
