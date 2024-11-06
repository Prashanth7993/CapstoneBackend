package com.capstone.route_service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class StopPojo {
	private long id;

	private String name;
	private double latitude;
	private double longitude;

	private RoutePojo route;

}
