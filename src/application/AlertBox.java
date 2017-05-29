package application;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;

public class AlertBox {
	
	private static Stage window;
	private static Label label;
	private static Button okButton;

	public static void display(String title, String message){
		
		window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL); 
		window.setTitle(title);
		window.setMinWidth(400);
		
		label = new Label();
		label.setText(message);
		
		okButton = new Button("Ok");
		okButton.setOnAction(e -> window.close());
		okButton.setOnKeyPressed(e -> {

			if(e.getCode() == KeyCode.ENTER) {
				
				window.close();
				
			} // End of if(event.getCode() == KeyCode.ENTER)
			
		});
		
		VBox layout = new VBox(20);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.getChildren().addAll(label, okButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		
	} // End of public static void display(String title, String message) method
	
} // End of AlertBox class















