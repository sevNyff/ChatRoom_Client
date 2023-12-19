package com.example.project3client.model;

import java.util.Objects;

/**
 * This class is nearly identical to the server class, with the following exceptions:
 * - gameType is a String rather than an enumeration, because we don't know about other games.
 * - We don't use any of the Spring persistence that the server class has.
 */
public class Game {
	private String token;
	String gameType; // Will be TicTacToe from the server
	Long difficulty;
	String options;
	long[][] board;
	Boolean result; // true = won, false = lost, null = playing

	public Game() {
	}

	public Game(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Long difficulty) {
		this.difficulty = difficulty;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public long[][] getBoard() {
		return board;
	}

	public void setBoard(long[][] board) {
		this.board = board;
	}
	
	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}
	
	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Game)) return false;
		Game g = (Game) o;
		return (this.token.equals(g.token));
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.token);
	}

	@Override
	public String toString() {
		return "Game{" + "token=" + this.token + "}";
	}
}