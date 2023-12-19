package com.example.project3client;


import com.example.project3client.controller.Connect4_Controller;
import com.example.project3client.model.Connect4_Model;
import com.example.project3client.view.Connect4_View;
import javafx.application.Application;
import javafx.stage.Stage;

public class Connect4 extends Application {
	private Connect4_Model model;
	private Connect4_View view;
	private Connect4_Controller controller;
	
	public static void main(String[] args) {
		launch();
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		model = new Connect4_Model();
		view = new Connect4_View(stage, model);
		controller = new Connect4_Controller(model, view);
		view.show();
	}

}
