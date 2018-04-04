/**
 * 
 */
package com.cityConnection.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.cityConnection.model.City;

/**
 * @author muhaa
 *
 */
@Service
public class ConnectionService {

	HashMap<String, City> connections = new HashMap<>();

	/**
	 * This method is used for loading file components
	 */
	public void loadFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		String filePath = classLoader.getResource("file/city.txt").getFile();
		File file = new File(filePath.replace("%20", " "));

		try (Scanner scanner = new Scanner(file)) {
			ArrayList<String> lines = new ArrayList<>();

			while (scanner.hasNextLine()) {
				lines.add(scanner.nextLine());
			}

			for (int i = 0; i <= lines.size() - 1; i++) {
				fillUpConnections(i, lines);
			}

			for (int i = lines.size() - 1; i >= 0; i--) {
				fillUpConnections(i, lines);
			}

			filterConnections();

			scanner.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to make connection between the cities with respect to origin and multiple destinations
	 * @param index
	 * @param lines
	 */
	public void fillUpConnections(int index, ArrayList<String> lines) {

		String[] cities = lines.get(index).split(",");

		for (String origin : cities) {
			origin = origin.trim();
			City city = connections.containsKey(origin) ? connections.get(origin) : new City();
			city.setName(origin);
			for (String destination : cities) {
				destination = destination.trim();
				if (!origin.equalsIgnoreCase(destination)) {
					city.getConnections().add(destination);

				}
				addDestinationsOfDestinationsToOrigin(destination, city);
			}

			connections.put(origin, city);
		}

	}

	/**
	 * This method is used to filter connections like filter duplication or origin is also present in the destination list
	 */
	public void filterConnections() {
		for (City city : connections.values()) {
			HashMap<String, String> cities = new HashMap<>();
			for (String cityNames : city.getConnections()) {
				if (!cityNames.equalsIgnoreCase(city.getName())) {
					cities.put(cityNames, cityNames);
				}
			}
			city.setConnections(new ArrayList<String>(cities.values()));
		}
	}
	
	/**
	 * This method is used to asign destinations of destination to origins e.g if a is connected to b and b is coonected to c then c should
	 * be connected to a
	 * @param destination
	 * @param city
	 */
	public void addDestinationsOfDestinationsToOrigin(String destination, City city) {
		City destinationCity = connections.get(destination);
		if (destinationCity != null) {
			city.getConnections().addAll(destinationCity.getConnections());
		}

	}

	/**
	 * check the two cities are connected or not
	 * @param origin
	 * @param destination
	 * @return
	 */
	public String isConnected(String origin, String destination) {
		boolean isOriginAvailable = !origin.isEmpty() && connections.containsKey(origin);
		boolean isDestinationAvailable = !destination.isEmpty() && connections.containsKey(destination);

		if (isOriginAvailable && isDestinationAvailable) {
			City originCity = connections.get(origin);

			for (String city : originCity.getConnections()) {
				if (city.equalsIgnoreCase(destination)) {
					return "Yes";
				}
			}
		}
		return "No";
	}

	public ArrayList<City> getAllCities() {
		return new ArrayList<City>(connections.values());
	}

}
