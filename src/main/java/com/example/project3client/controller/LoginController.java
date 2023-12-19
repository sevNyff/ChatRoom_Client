package com.example.project3client.controller;

import com.example.project3client.model.Connect4_Model;
import com.example.project3client.view.Connect4_View;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class LoginController {
	private final Connect4_View view;
	private final Connect4_Model model;
	
	private boolean goodUser = false;
	private boolean goodPassword = false;

	public LoginController(Connect4_Model model, Connect4_View view) {
		this.view = view;
		this.model = model;

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

		view.toolsLogin.btnLogin.setOnAction(this::login_logout);
		
		// On login, disable login controls - On logout, enable login controls
		model.getTokenProperty().addListener((observable, oldValue, newValue) -> {
			boolean enable = model.getTokenProperty().get() == null;
			Platform.runLater(() -> {				
				view.toolsLogin.txtUser.setDisable(!enable);
				view.toolsLogin.txtPassword.setDisable(!enable);
				view.toolsSetup.setDisable(enable);
				
				view.toolsLogin.btnLogin.setText( enable ? "Login" : "Logout");
				
				// Regardless of why we're here, ensure the playing field is disabled
				view.gameBoard.setDisable(true);
			});
		});
	}
	
	private void login_logout(ActionEvent e) {
		if (model.getTokenProperty().get() == null)
			model.login(view.toolsLogin.txtUser.getText(), view.toolsLogin.txtPassword.getText());
		else
			model.logout(view.toolsLogin.txtUser.getText());
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
