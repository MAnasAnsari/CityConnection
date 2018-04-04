/**
 * 
 */
package com.cityConnection.model;

import java.util.ArrayList;

/**
 * @author muhaa
 *
 */
public class City {

	private String name;
	private ArrayList<String> connections;

	public City() {
		connections = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getConnections() {
		return connections;
	}

	public void setConnections(ArrayList<String> connections) {
		this.connections = connections;
	}

}
