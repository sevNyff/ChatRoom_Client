package com.example.project3client.controller;

import com.example.project3client.model.Connect4_Model;
import com.example.project3client.view.Connect4_View;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class ServerController {
	private final Connect4_View view;
	private final Connect4_Model model;
	
	public ServerController(Connect4_Model model, Connect4_View view) {
		this.view = view;
		this.model = model;
		
		view.toolsServer.txtServer.textProperty().addListener((observable, oldValue, newValue) -> {
			validateWebAddress(newValue);
		});
		validateWebAddress(view.toolsServer.txtServer.getText()); // Initialize

		model.getServerAddressVerifiedProperty().addListener((observable, oldValue, newValue) -> {
			Platform.runLater(() -> {
				view.toolsServer.lblStatus.setText(newValue ? "Success" : "Failure");
				view.toolsServer.txtServer.setDisable(newValue);
				view.toolsServer.btnPing.setDisable(newValue);
				view.toolsLogin.setDisable(!newValue);
			});
		});

		view.toolsServer.btnPing.setOnAction(this::ping);
	}

	private void ping(ActionEvent e) {
		model.setServerAddress(view.toolsServer.txtServer.getText());
		model.ping();
	}

	/**
	 * Two options: numeric (IPv4) or symbolic (e.g., www.fhnw.ch). In either case,
	 * we split on the periods.
	 */
	private void validateWebAddress(String newValue) {
		boolean valid = false;

		String[] parts = newValue.split(":");
		if (parts.length == 2) {
			valid = isValidWebAddress(parts[0]) && isValidPort(parts[1]);
		}

		Utility.setValidInvalidStyle(view.toolsServer.txtServer, valid);
		view.toolsServer.btnPing.setDisable(!valid);
	}

	protected boolean isValidWebAddress(String newValue) {
		boolean valid = false;
		String[] parts = newValue.split("\\.", -1);

		// check for a numeric address first: 4 parts, each an integer 0 to 255
		if (parts.length == 4) {
			valid = true;
			for (String part : parts) {
				try {
					int value = Integer.parseInt(part);
					if (value < 0 || value > 255) valid = false;
				} catch (NumberFormatException e) {
					// wasn't an integer
					valid = false;
				}
			}
		}

		// If not valid, try for a symbolic address: at least two parts, each
		// part at least two characters. We don't bother checking what kinds of
		// characters they are.
		if (!valid) {
			if (parts.length >= 2) {
				valid = true;
				for (String part : parts) {
					if (part.length() < 2) valid = false;
				}
			}
		}

		return valid;
	}

	protected boolean isValidPort(String newValue) {
		boolean valid = true;

		try {
			int value = Integer.parseInt(newValue);
			if (value < 1 || value > 65535) valid = false;
		} catch (NumberFormatException e) {
			// wasn't an integer
			valid = false;
		}

		return valid;
	}
}
