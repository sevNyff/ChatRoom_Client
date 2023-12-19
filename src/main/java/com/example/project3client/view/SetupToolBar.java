package com.example.project3client.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;

public class SetupToolBar extends ToolBar {
	public final Label lblDifficulty = new Label("Difficulty");
	public final Slider sliderDifficulty = new Slider(1, 2, 2);
	public final Button btnStart = new Button("Start");
	
	public SetupToolBar() {
		super();
		this.getItems().add(lblDifficulty);
		this.getItems().add(sliderDifficulty);
		sliderDifficulty.setShowTickMarks(true);
		sliderDifficulty.setMajorTickUnit(1);
		sliderDifficulty.setMinorTickCount(0);
		sliderDifficulty.setShowTickLabels(true);
		sliderDifficulty.setSnapToTicks(true);
		this.getItems().add(btnStart);
		this.setDisable(true);
	}
}
