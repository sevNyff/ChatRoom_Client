package com.example.project3client.controller;

import javafx.scene.control.Control;

public class Utility {
	public static void setValidInvalidStyle(Control control, boolean valid) {
		control.getStyleClass().remove("valid");
		control.getStyleClass().remove("invalid");
		if (valid) {
			control.getStyleClass().add("valid");
		} else {
			control.getStyleClass().add("invalid");
		}
	}
}
