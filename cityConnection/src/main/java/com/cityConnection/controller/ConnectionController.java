/**
 * 
 */
package com.cityConnection.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityConnection.model.City;
import com.cityConnection.service.ConnectionService;

/**
 * @author muhaa
 *
 */
@RestController
public class ConnectionController {

	ConnectionService connectionService;

	@Autowired
	public ConnectionController(ConnectionService connectionService) {
		this.connectionService = connectionService;
	}

	/**
	 * This service is used to get all connections of particular cities
	 * @return arralist of cities
	 */
	@RequestMapping("/all")
	public ArrayList<City> getAll() {
		connectionService.loadFile();
		return connectionService.getAllCities();
	}

	/**
	 * This service is used to find available connection between origin and destination
	 * @param origin
	 * @param destination
	 * @return
	 */
	@GetMapping("/connected")
	public String checkCityConnection(@RequestParam("origin") String origin, @RequestParam("destination") String destination) {
		return connectionService.isConnected(origin.trim(), destination.trim());
	}

}
