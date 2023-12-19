package com.example.project3client.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

public class ServerModel {

	public boolean ping(String serverAddress) {
		boolean success = false;
		try {
			HttpRequest request = HttpRequest.newBuilder().uri(new URI("http://" + serverAddress + "/ping"))
					.timeout(Duration.of(3, ChronoUnit.SECONDS)).GET().build();
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				String body = response.body();
				Map<String, String> myMap = new HashMap<String, String>();
				ObjectMapper objectMapper = new ObjectMapper();
				// Transform incoming JSON into a simple map
				myMap = objectMapper.readValue(body, HashMap.class);
				success = myMap.get("ping").equals("success");
			}
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// Do nothing - fall through
		}
		return success;
	}
}
