package com.capstone.route_service.models;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class RoutePojo {
	
    private long id;

    private String name;
    private String origin;
    private String destination;

    private List<StopPojo> stops = new ArrayList<>();

    private List<RailwayStationPojo> stations=new ArrayList<>();
}
