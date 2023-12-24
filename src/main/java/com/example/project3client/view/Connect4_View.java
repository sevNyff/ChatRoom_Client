package com.example.project3client.view;

import com.example.project3client.model.Connect4_Model;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Connect4_View {
	private final Connect4_Model model;
	
	public final Stage stage;
	// Create the top toolbars
	public final ServerToolBar toolsServer = new ServerToolBar();
	public final LoginToolBar toolsLogin = new LoginToolBar();
	public final SetupToolBar toolsSetup = new SetupToolBar();	
	public final GameBoard gameBoard = new GameBoard();

	
	public Connect4_View(Stage stage, Connect4_Model model) {
		this.model = model;
		this.stage = stage;

		// Create the root layout
		VBox root  = new VBox(toolsServer, toolsLogin, toolsSetup, gameBoard);
		root.getStyleClass().add("vbox");
		
		// Create the scene using our layout; then display it
		Scene scene = new Scene(root);
		String css = getClass().getResource("/Connect4.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setTitle("Connect 4");
		stage.setScene(scene);	
	}
	
	
	public void show() {
		stage.show();
	}
}

