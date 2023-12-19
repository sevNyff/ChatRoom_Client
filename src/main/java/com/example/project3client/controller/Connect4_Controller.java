package com.example.project3client.controller;


import com.example.project3client.model.Connect4_Model;
import com.example.project3client.view.Connect4_View;

public class Connect4_Controller {

	public Connect4_Controller(Connect4_Model model, Connect4_View view) {
		new ServerController(model, view);
		new LoginController(model, view);
		new SetupController(model, view);
		new GameController(model, view);
	}
}
