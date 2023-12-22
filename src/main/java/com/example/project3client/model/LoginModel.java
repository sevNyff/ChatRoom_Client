package com.example.project3client.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class LoginModel {

	public String login(String serverAddress, String user, String password) {
		String token = null;
		User userQuery = new User();
		userQuery.setUserName(user);
		userQuery.setPassword(password);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String sreq = objectMapper.writeValueAsString(userQuery);
			BodyPublisher json_req = BodyPublishers.ofString(sreq);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://" + serverAddress + "/users/login"))
					.setHeader("Content-type", "application/json")
					.timeout(Duration.of(3, ChronoUnit.SECONDS))
					.POST(json_req)
					.build();
			
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				String body = response.body();
				ObjectMapper objectMapper2 = new ObjectMapper();
				objectMapper2.registerModule(new JavaTimeModule()); // Needed, in order to use classes from java.time
				User u = objectMapper2.readValue(body, User.class);
				
				token = u.getToken(); // Save the token - that's all we care about
				System.out.println(token);
			}
		} catch (URISyntaxException | IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return token;
	}

	public void logout(String serverAddress, String user) {
		try {
			String sreq = "{ \"userName\":\"" + user + "\" }";
			BodyPublisher json_req = BodyPublishers.ofString(sreq);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://" + serverAddress + "/users/logout"))
					.setHeader("Content-type", "application/json")
					.timeout(Duration.of(3, ChronoUnit.SECONDS))
					.POST(json_req)
					.build();

			// We don't care about the response, so we ignore it
			HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());
		} catch (URISyntaxException | IOException | InterruptedException e) {
			// Do nothing - fall through
		}
		System.out.println("User is logged out");
	}

}
