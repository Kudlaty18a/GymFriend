package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
	
	private static Button yesButton;
	private static Button noButton;
	private static Label lMessage;
	private static boolean answer;
	private static Stage window;
	
	public static boolean display(String title, String message){
		
		window = new Stage();
		window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(400);
		window.setResizable(false);
		
		lMessage = new Label(message);
		
		yesButton = new Button("Yes");
		noButton = new Button("No");
		
		/*
		 * Setting up the answer and closing window, after clicking one of the buttons
		 */
		
		yesButton.setOnAction(e -> {
			
			answer = true;
			window.close();
			
		});
		
		yesButton.setOnKeyPressed(e -> {
			
			if(e.getCode() == KeyCode.ENTER){
				
				answer = true;
				window.close();
				
			} // End of if(e.getCode() == KeyCode.ENTER)
			
		});
		
		noButton.setOnAction(e -> {
			
			answer = false;
			window.close();
			
		});
		
		noButton.setOnKeyPressed(e -> {
			
			if(e.getCode() == KeyCode.ENTER){
				
				answer = false;
				window.close();
				
			} // End of if(e.getCode() == KeyCode.ENTER)
			
		});
		
		HBox hBox = new HBox(10);
		
		hBox.setPadding(new Insets(20, 20, 20, 20));
		hBox.getChildren().addAll(yesButton, noButton);
		hBox.setAlignment(Pos.CENTER);
		
		VBox mainLayout = new VBox(5);
		mainLayout.setPadding(new Insets(20, 0, 0, 0));
		
		mainLayout.getChildren().addAll(lMessage, hBox);
		mainLayout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(mainLayout);
		
		window.setScene(scene);
		window.showAndWait();
		
		return answer;
		
	} // End of display(String title, String message) method
	
} // End of ConfirmBox class
