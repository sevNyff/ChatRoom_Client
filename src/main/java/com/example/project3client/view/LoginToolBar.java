package com.example.project3client.view;

import javafx.scene.control.*;

public class LoginToolBar extends ToolBar {
	public final Label lblUser = new Label("User");
	public final TextField txtUser = new TextField();
	public final Label lblPassword = new Label("Password");
	public final PasswordField txtPassword = new PasswordField();
	public final Button btnLogin = new Button("Login");

	public LoginToolBar() {
		super();
		this.getItems().add(lblUser);
		this.getItems().add(txtUser);
		this.getItems().add(lblPassword);
		this.getItems().add(txtPassword);
		this.getItems().add(btnLogin);
		btnLogin.setDisable(true);
		this.getStyleClass().add("toolbar");
		this.setDisable(true);
	}
}
