package com.example.project3client.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * This class provides the interface to the rest of the system. The actual
 * functionality is distributed across specific classes for login/logout,
 * playing the game, etc.
 */
public class Connect4_Model {
	private final ServerModel serverModel;
	private final LoginModel loginModel;
	private final SetupModel setupModel;
	private final GameModel gameModel;

	public Connect4_Model() {
		serverModel = new ServerModel();
		loginModel = new LoginModel();
		setupModel = new SetupModel();
		gameModel = new GameModel();
	}

	// --- Interface to set and verify the server address

	private String serverAddress;
	private final SimpleBooleanProperty serverAddressVerifiedProperty = new SimpleBooleanProperty();

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public SimpleBooleanProperty getServerAddressVerifiedProperty() {
		return serverAddressVerifiedProperty;
	}

	// Model method to verify the server address
	public void ping() {
		boolean success = serverModel.ping(serverAddress);
		// Ensure a value change
		if (serverAddressVerifiedProperty.get() == success)
			serverAddressVerifiedProperty.set(!success);
		serverAddressVerifiedProperty.set(success);
	}

	// --- Interface for login and logout

	// Token is set by a successful login; otherwise it is null
	private final SimpleStringProperty tokenProperty = new SimpleStringProperty();

	public SimpleStringProperty getTokenProperty() {
		return tokenProperty;
	}

	// Login the given user, save the resulting token
	public void login(String user, String password) {
		String token = loginModel.login(serverAddress, user, password);
		tokenProperty.set(token);
	}
	
	// Logout the user, destroy the token
	public void logout(String user) {
		loginModel.logout(serverAddress, user);
		tokenProperty.set(null);
	}

	// --- Interface for game setup and start

	// When a game is created, it is stored here
	private final SimpleObjectProperty<Game> gameProperty = new SimpleObjectProperty<>();

	public SimpleObjectProperty<Game> getGameProperty() {
		return gameProperty;
	}

	// Model method to create a new game
	public void newGame(String options, long difficulty) {
		Game game = setupModel.newGame(serverAddress, tokenProperty.get(), options, difficulty);
		// Because the identity of a game is the token, just saving the game does not trigger
		// the ChangeListener. Hence, we first save "null", to force the listener to fire
		this.gameProperty.set(null);
		this.gameProperty.set(game);
	}

	// --- Interface to play the game

	public void move(int row, int col) {
		Game game = gameModel.move(serverAddress, tokenProperty.get(), row, col);
		// Because the identity of a game is the token, just saving the game does not trigger
		// the ChangeListener. Hence, we first save "null", to force the listener to fire
		this.gameProperty.set(null);
		this.gameProperty.set(game);
	}
}
