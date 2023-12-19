package com.example.project3client.controller;

import com.example.project3client.model.Connect4_Model;
import com.example.project3client.view.Connect4_View;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class SetupController {
	private final Connect4_View view;
	private final Connect4_Model model;

	public SetupController(Connect4_Model model, Connect4_View view) {
		this.view = view;
		this.model = model;

		view.toolsSetup.btnStart.setOnAction(this::start);
	}

	private void start(ActionEvent e) {
		Platform.runLater(() -> {
			view.toolsSetup.setDisable(true);
			view.gameBoard.setDisable(false);
		});
		String options = ""; // Currently no options available
		long difficulty = (long) view.toolsSetup.sliderDifficulty.getValue();
		model.newGame(options, difficulty);
	}
}
