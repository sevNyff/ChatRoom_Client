package com.example.project3client.view;

import javafx.scene.control.*;

public class ServerToolBar extends ToolBar {
	public final Label lblServer = new Label("Server:");
	public final TextField txtServer = new TextField("127.0.0.1:8080");
	public final Button btnPing = new Button("Ping");
	public final Label lblStatus = new Label();
	
	public ServerToolBar() {
		super();
		this.getItems().add(lblServer);
		this.getItems().add(txtServer);
		txtServer.setPromptText("127.0.0.1:8080");
		txtServer.setTooltip(new Tooltip("Server:Port"));
		this.getItems().add(btnPing);
		btnPing.setDisable(true);
		this.getItems().add(lblStatus);
		this.getStyleClass().add("toolbar");
	}
}
