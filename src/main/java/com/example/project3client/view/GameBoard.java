package com.example.project3client.view;


import com.example.project3client.model.Game;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameBoard extends GridPane {
	public final Button[][] buttons = new Button[6][7];

	public GameBoard() {
		super();
		this.setHgap(15); // Set horizontal gap
		this.setVgap(15);

		// Create the playing board: buttons in 7 columns & 6 rows
		for (int col = 0; col < 7; col++) {
			for (int row = 0; row < 6; row++) {
				// Create
				Button btn = new Button();
				btn.getStyleClass().add("gameButton");

				// Add to layout
				this.add(btn, col, row);

				// Add to array of buttons, for easy reference
				buttons[row][col] = btn;

				// Format
				btn.setPrefSize(30, 30);
			}
		}
		this.setDisable(true);
		this.getStyleClass().add("gameBoard");
	}

	public void updateGame(Game game) {
		long[][] board = game.getBoard();

		for (int col = 0; col < 7; col++) {
			for (int row = 0; row < 6; row++) {
				Button button = buttons[row][col];
				if (board[row][col] > 0) {
					button.getStyleClass().removeAll("blueButton", "gameButton");
					button.getStyleClass().add("redButton"); // Add redButton style class
				} else if (board[row][col] < 0) {
					button.getStyleClass().removeAll("redButton", "gameButton");
					button.getStyleClass().add("blueButton"); // Add blueButton style class
				} else {
					button.getStyleClass().removeAll("redButton", "blueButton");
					button.getStyleClass().add("gameButton"); // Reset to default style
				}

				button.setDisable(board[row][col] != 0 || game.getResult() == true);
			}
		}
	}



	public Button[][] getButtons() {
		return buttons;
	}
}
