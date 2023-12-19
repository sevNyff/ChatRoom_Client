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

public class SetupModel {
	
	public Game newGame(String serverAddress, String token, String options, long difficulty) {
		Game game = null;
		
		Game gameQuery = new Game();
		gameQuery.setToken(token);
		gameQuery.setOptions(options);
		gameQuery.setDifficulty(difficulty);
		gameQuery.setGameType("Connect4");
		gameQuery.setOptions("");
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String sreq = objectMapper.writeValueAsString(gameQuery);
			BodyPublisher json_req = BodyPublishers.ofString(sreq);
			HttpRequest request = HttpRequest.newBuilder()
					.uri(new URI("http://" + serverAddress + "/game/new"))
					.setHeader("Content-type", "application/json")
					.timeout(Duration.of(3, ChronoUnit.SECONDS))
					.POST(json_req)
					.build();
			
			HttpResponse<String> response = HttpClient.newBuilder().build().send(request, BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				String body = response.body();
				ObjectMapper objectMapper2 = new ObjectMapper();
				game = objectMapper2.readValue(body, Game.class);
			}
		} catch (IOException | URISyntaxException | InterruptedException  e) {
			// Do nothing - fall through
		}
		return game;
	}

}
