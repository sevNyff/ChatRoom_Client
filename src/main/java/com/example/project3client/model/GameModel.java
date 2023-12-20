package com.example.project3client.model;

import com.fasterxml.jackson.databind.ObjectMapper;

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

public class GameModel {

	public Game move(String serverAddress, String token, int row, int col) {
		Game game = null;
		try {
			// Self-built JSON is simplest, in this case
			String sreq = "{ \"token\":\"" + token + "\", \"row\":\"" + row + "\", \"col\":\"" + col + "\" }";
			BodyPublisher json_req = BodyPublishers.ofString(sreq);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://" + serverAddress + "/game/move"))
					.setHeader("Content-type", "application/json")
					.timeout(Duration.of(3, ChronoUnit.SECONDS))
					.POST(json_req)
					.build();
			
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());
			System.out.println("HTTP response: " + response);
			if (response.statusCode() == 200) {
				String body = response.body();
				ObjectMapper objectMapper = new ObjectMapper();
				game = objectMapper.readValue(body, Game.class);
			}
		} catch (IOException | URISyntaxException | InterruptedException  e) {
			e.printStackTrace();
		}
		return game;
	}
	
}
