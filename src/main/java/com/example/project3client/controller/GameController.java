package com.example.project3client.controller;

import com.example.project3client.model.Connect4_Model;
import com.example.project3client.model.Game;
import com.example.project3client.view.Connect4_View;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class GameController {
	private final Connect4_View view;
	private final Connect4_Model model;

	public GameController(Connect4_Model model, Connect4_View view) {
		this.view = view;
		this.model = model;

		// When the game-object changes, it is our turn
		model.getGameProperty().addListener((obs, old, newValue) -> {
			Platform.runLater(() -> {
				if (newValue != null) {
					Game game = (Game) newValue;
					view.gameBoard.updateGame(game);
					if (game.getResult() == true) {
						model.showAlert("Game Over!");
						view.toolsSetup.setDisable(false);
						view.gameBoard.setDisable(true);
					}
				}
			});
		});

		// Attach EventHandlers to the game buttons
		for (int col = 0; col < 7; col++) {
			for (int row = 0; row < 6; row++) {
				view.gameBoard.getButtons()[row][col].setOnAction(this::userMove);
			}
		}
	}

	private void userMove(ActionEvent e) {
		Button btn = (Button) e.getSource();
		int col = 0;
		int row = 0;
		boolean found = false;
		while (col < 7 && !found) {
			row = 0;
			while (row < 6 && !found) {
				found = (btn == view.gameBoard.getButtons()[row][col]);
				if (!found)
					row++;
			}
			if (!found)
				col++;
		}
		model.move(row, col);
	}
}
