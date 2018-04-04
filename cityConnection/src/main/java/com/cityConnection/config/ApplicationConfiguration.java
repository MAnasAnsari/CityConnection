package com.cityConnection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cityConnection.service.ConnectionService;

@Configuration
public class ApplicationConfiguration {

	/**
	 * This configuration is used for caching the file content
	 * @return
	 */
	@Bean
	public ConnectionService connectionService() {
		ConnectionService connectionService = new ConnectionService();
		connectionService.loadFile();

		return connectionService;
	}

}
