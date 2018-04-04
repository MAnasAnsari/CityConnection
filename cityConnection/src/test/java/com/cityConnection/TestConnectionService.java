package com.cityConnection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.cityConnection.service.ConnectionService;

public class TestConnectionService {

	ConnectionService cService = new ConnectionService();

	@Test
	public void testLoadFile() {

		ConnectionService cService = new ConnectionService();
		ClassLoader classLoader = getClass().getClassLoader();
		String filePath = classLoader.getResource("file/city.txt").getFile();
		File file = new File(filePath.replace("%20", " "));
		assertNotNull(file);
	}

	@Test
	public void testFillConnections() {
		ConnectionService cService = new ConnectionService();
		cService.loadFile();
		assertNotNull(cService.getAllCities());
		assertTrue(cService.getAllCities().size() > 0);
	}

	@Test
	public void testCheckConnections() {
		ConnectionService cService = new ConnectionService();
		cService.loadFile();
		assertEquals("Yes", cService.isConnected("Boston", "Philadelphia"));
	}
}
