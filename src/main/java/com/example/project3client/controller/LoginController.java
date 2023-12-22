package com.example.project3client.controller;

import com.example.project3client.model.Connect4_Model;
import com.example.project3client.model.Game;
import com.example.project3client.model.LoginModel;
import com.example.project3client.view.Connect4_View;
import com.example.project3client.view.LoginToolBar;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.net.URISyntaxException;

public class LoginController {
	private final Connect4_View view;
	private final Connect4_Model model;
	private final LoginModel loginModel;
	private final LoginToolBar loginToolBar;
	private final Game game;

	
	private boolean goodUser = false;
	private boolean goodPassword = false;

	public LoginController(Connect4_Model model, Connect4_View view) {
		this.view = view;
		this.model = model;
        loginToolBar = new LoginToolBar();
        loginModel = new LoginModel();
		game = new Game();

		// Events concerning Login
		view.toolsLogin.txtUser.textProperty().addListener((observable, oldValue, newValue) -> {
			validateUser(newValue);
		});
		validateUser(view.toolsLogin.txtUser.getText()); // Initialize

		view.toolsLogin.txtPassword.textProperty().addListener((observable, oldValue, newValue) -> {
			validatePassword(newValue);
		});
		validatePassword(view.toolsLogin.txtPassword.getText()); // Initialize
		
		enableLoginButton();

		view.toolsLogin.btnLogin.setOnAction(this::login);

		
		// On login, disable login controls - On logout, enable login controls
		model.getTokenProperty().addListener((observable, oldValue, newValue) -> {
			boolean enable = model.getTokenProperty().get() == null;
			Platform.runLater(() -> {				
				view.toolsLogin.txtUser.setDisable(!enable);
				view.toolsLogin.txtPassword.setDisable(!enable);
				view.toolsSetup.setDisable(enable);
				
				//view.toolsLogin.btnLogin.setText( enable ? "Login" : "Logout");
				
				// Regardless of why we're here, ensure the playing field is disabled
				view.gameBoard.setDisable(true);
			});
		});
	}

	private void login(ActionEvent e) {
		if (model.getTokenProperty().get() == null) {
			model.login(view.toolsLogin.txtUser.getText(), view.toolsLogin.txtPassword.getText());
			view.toolsLogin.btnLogin.setText("Logout");
		} else {
			model.logout(view.toolsLogin.txtUser.getText());
			view.toolsLogin.btnLogin.setText("Login");
			showAlertMessage("You are logged out!");
		}
	}

	private void showAlertMessage(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
		alert.showAndWait();
	}

	public boolean isLoggedIn() {
		System.out.println(model.getTokenProperty());
		return model.getTokenProperty() != null;
	}
	private void validateUser(String newValue) {
		this.goodUser = newValue.length() >= 3;
		Utility.setValidInvalidStyle(view.toolsLogin.txtUser, goodUser);
		enableLoginButton();
	}

	private void validatePassword(String newValue) {
		this.goodPassword = newValue.length() >= 3;
		Utility.setValidInvalidStyle(view.toolsLogin.txtPassword, goodPassword);
		enableLoginButton();
	}

	

	void enableLoginButton() {
		boolean valid = goodUser && goodPassword;
		view.toolsLogin.btnLogin.setDisable(!valid);
	}
	
}
