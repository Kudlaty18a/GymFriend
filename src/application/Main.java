package application;
	
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Stage window;
	private static int width, height;
	private static Scene loginScene, applicationScene;
	private static Button logInButton;
	private static Label lUsername, lPassword;
	private static TextField tfUsername;
	private static PasswordField tfPassword;
	private static final String USERNAME = "damian", PASSWORD = "12345";
	
	private static BorderPane mainLayout;
	
	private static TreeView<String> excercises;
	
	private static ExcerciseView excerciseView;
	
	/*
	 * Class to hold objects of ExcerciseView
	 */
	
	private static CollectionOfExcercises shoulderExcercises;
	private static CollectionOfExcercises chestExcercises;
	private static CollectionOfExcercises backExcercises;
	private static CollectionOfExcercises armsExcercises;
	private static CollectionOfExcercises legsExcercises;
	
	public static void main(String[] args) {
		launch(args);
	} // End of main(String[] args) method
	
	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		window.setTitle("Fitness Friend");
		
		window.setOnCloseRequest(e ->{
			
			e.consume();
			closeProgram();
			
		});
		
		window.setScene(loginScene());
		window.setResizable(false);
		window.show();
		
	} // End of start(Stage primaryStage) method
	
	/*
	 * Setting up the loginScene layout
	 */
	
	public static Scene loginScene(){
		
		BorderPane mainLayout = new BorderPane();
		mainLayout.setPadding(new Insets(20, 20, 20, 20));
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 0, 42));
		grid.setHgap(10);
		grid.setVgap(10);
		
		lUsername = new Label("Username: ");
		tfUsername = new TextField();
		tfUsername.setPromptText("Username");
		tfUsername.setOnKeyPressed(e -> {
			
			if(e.getCode() == KeyCode.ENTER) {
				
				accessConditions();
				
			} // end of if(event.getCode() == KeyCode.ENTER)
			
		}); // End of tfUsername.setOnKeyPressed()
		
		lPassword = new Label("Password: ");
		tfPassword = new PasswordField();
		tfPassword.setPromptText("Password");
		tfPassword.setOnKeyPressed(e -> {
				
			if(e.getCode() == KeyCode.ENTER) {
				
				accessConditions();
				
			} // end of if(event.getSource() == KeyCode.ENTER) 
			
		}); // End of tfPassword.setOnKeyPressed()
		
		logInButton = new Button("Log In");
		logInButton.setOnAction(e -> logInButtonClicked());
		
		grid.add(lUsername, 0, 0);
		grid.add(tfUsername, 1, 0);
		grid.add(lPassword, 2, 0);
		grid.add(tfPassword, 3, 0);
		grid.add(logInButton, 4, 0);
		
		mainLayout.setTop(grid);
		Image theRock = new Image("/resources/TheRock.jpg");
		ImageView viewTheRock = new ImageView();
		viewTheRock.setImage(theRock);
		
		StackPane imagePane = new StackPane();
		imagePane.setPadding(new Insets(20, 20, 20, 20));
		imagePane.getChildren().add(viewTheRock);
		
		mainLayout.setCenter(imagePane);
		
		width = 850;
		height = 850;
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		window.setX((screenBounds.getWidth() - width) / 2);
		window.setY((screenBounds.getHeight() - height) / 2);
		
		loginScene = new Scene(mainLayout, width, height);
		
		return loginScene;
		
	} // End of loginScene method
	
	/*
	 * Setting up the applicationScene
	 */
	
	public static Scene applicationScene(){
		
		mainLayout = new BorderPane();
		
		mainLayout.setTop(topLayout());
		mainLayout.setLeft(excercisesPane());
		mainLayout.setBottom(legendPane());
		mainLayout.setCenter(firstCenterPane());
		
		width = 1262;
		height = 862;
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		window.setX((screenBounds.getWidth() - width) / 2);
		window.setY((screenBounds.getHeight() - height) / 2);
		
		applicationScene = new Scene(mainLayout, width, height);
		
		return applicationScene;
		
	} // End of applicationScene method
	
	/*
	 * logInButton action
	 */
	
	public static void logInButtonClicked(){
		
		accessConditions();
		
	} // End of logInButtonClicked() method
	
	private static void closeProgram(){
		
		boolean answer = ConfirmBox.display("Exit", "Are you sure you want to exit?");
		
		if(answer){
			
			window.close();
			
		} // End of if(answer)
		
	} // End of closeProgram() method
	
	public static void accessConditions(){
		
		String getUsername = tfUsername.getText();
		String getPassword = tfPassword.getText();
		
		if(getUsername.equalsIgnoreCase(USERNAME) && getPassword.equals(PASSWORD)){
			
			window.setScene(applicationScene());
			
		}
		else{
			
			AlertBox.display("Error", "Wrong Username or Password");
			
		} // End of if(getUsername.equalsIgnoreCase(USERNAME) && getPassword.equals(PASSWORD))
		
	} // End of public static void accessConditions() method
	
	/*
	 * Creates MenuBar
	 */
	
	public static MenuBar menuBar(){
		
		MenuBar menuBar = new MenuBar();
		
		Menu fileMenu = new Menu("File");
		MenuItem closeProgram = new MenuItem("Close program");
		closeProgram.setOnAction(e -> {
			
			e.consume();
			closeProgram();
			
		}); // End of closeProgram.setOnAction()
		
		closeProgram.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.CONTROL_DOWN));
		
		SeparatorMenuItem separator = new SeparatorMenuItem();
		
		MenuItem logout = new MenuItem("Log Out");
		logout.setOnAction(e -> logOut());
		logout.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN));
		
		fileMenu.getItems().addAll(logout, separator, closeProgram);
		
		menuBar.getMenus().add(fileMenu);
		
		return menuBar;
		
	} // End of menuBar() method
	
	/*
	 * Creates information and logout panel
	 */
	
	public static HBox logoutPane(){
		
		String name = tfUsername.getText();
		char firstChar = name.charAt(0);
		char correctFirstChar = Character.toUpperCase(firstChar);
		
		String correctName = correctFirstChar + name.substring(1, name.length());
		
		Label welcomeMessage = new Label("Hi " + correctName + ", nice to see you again!");
		
		Button logoutButton = new Button("Log Out");
		logoutButton.setOnAction(e -> logOut());
		
		HBox hBox = new HBox(400);
		hBox.setPadding(new Insets(10, 10, 5, 10));
		hBox.setAlignment(Pos.BASELINE_RIGHT);
		hBox.getChildren().addAll(welcomeMessage, logoutButton);
		hBox.setStyle("-fx-border-color: black; -fx-background-color: #DCDCDC;");
		
		return hBox;
		
	} // End of logoutPane() method
	
	public static VBox topLayout(){
		
		VBox topLayout = new VBox();
		
		topLayout.getChildren().addAll(menuBar(), logoutPane());
		
		return topLayout;
		
	} // End of topLayout() method
	
	/*
	 * Action for logoutButton
	 */
	
	public static void logOut(){
		
		boolean logout = ConfirmBox.display("Logout", "Are you sure you want to exit?");
		
		if(logout){
			
			window.setScene(loginScene());
			
		} // End if(logout)
		
	} // End of logOut() method
	
	/*
	 * Creates a TreeView with excercises
	 */
	
	public static StackPane excercisesPane(){
		
		/*
		 * Parents
		 */
		
		TreeItem<String> root, shoulders, chest, back, arms, legs;
		
		root = new TreeItem<>();
		
		shoulders = makeBranch("Shoulders", root);
		shoulders.setExpanded(false);
		
		/*
		 * Childrens of shoulders
		 */
		
		TreeItem<String> shouldersFirstExcercise = makeBranch("Standing military press", shoulders);
		TreeItem<String> shouldersSecondExcercise = makeBranch("Military press from behind the head", shoulders);
		TreeItem<String> shouldersThirdExcercise = makeBranch("Dumbbell press", shoulders);
		TreeItem<String> shouldersFourthExcercise = makeBranch("Arnold press", shoulders);
		TreeItem<String> shouldersFifthExcercise = makeBranch("Lateral raise", shoulders);
		TreeItem<String> shouldersSixthExcercise = makeBranch("Lateral raise in the torso fall", shoulders);
		TreeItem<String> shouldersSeventhExcercise = makeBranch("Upright barbell row", shoulders);
		TreeItem<String> shouldersEighthExcercise = makeBranch("Upright dumbbell row", shoulders);
		TreeItem<String> shouldersNinthExcercise = makeBranch("Front barbell raisei", shoulders);
		TreeItem<String> shouldersTenthExcercise = makeBranch("Front dumbbell raisei", shoulders);
		TreeItem<String> shouldersEleventhExcercise = makeBranch("Lateral raise during lying down", shoulders);
		TreeItem<String> shouldersTwelfthExcercise = makeBranch("Front lift rope raisei", shoulders);
		TreeItem<String> shouldersThirteenthExcercise = makeBranch("Lateral raise with lift rope", shoulders);
		TreeItem<String> shouldersFourteenthExcercise = makeBranch("Lateral raise in the torso fall with lift rope", shoulders);
		TreeItem<String> shouldersFifteenthExcercise = makeBranch("Opposite incline flyes", shoulders);
		
		chest = makeBranch("Chest", root);
		chest.setExpanded(false);
		
		/*
		 * Childrens of chest
		 */
		
		TreeItem<String> chestFirstExcercise = makeBranch("Bench press", chest);
		TreeItem<String> chestSecondExcercise = makeBranch("Bench press with dumbbells", chest);
		TreeItem<String> chestThirdExcercise = makeBranch("Incline barbell press", chest);
		TreeItem<String> chestFourthExcercise = makeBranch("Incline dumbbell press", chest);
		TreeItem<String> chestFifthExcercise = makeBranch("Decline barbell press", chest);
		TreeItem<String> chestSixthExcercise = makeBranch("Decline dumbbell press", chest);
		TreeItem<String> chestSeventhExcercise = makeBranch("Incline flyes", chest);
		TreeItem<String> chestEighthExcercise = makeBranch("Bench press - narrow hold", chest);
		TreeItem<String> chestNinthExcercise = makeBranch("Pull over", chest);
		TreeItem<String> chestTenthExcercise = makeBranch("Pumps on the handrails", chest);
		TreeItem<String> chestEleventhExcercise = makeBranch("Incline flyes - sitting on the machine", chest);
		TreeItem<String> chestTwelfthExcercise = makeBranch("Crossing the ropes during standing up", chest);
		TreeItem<String> chestThirteenthExcercise = makeBranch("Bench press - sitting on the machine", chest);
		
		back = makeBranch("Back", root);
		back.setExpanded(false);
		
		/*
		 * Childrens of back
		 */
		
		TreeItem<String> backFirstExcercise = makeBranch("Wide-grip pull-up", back);
		TreeItem<String> backSecondExcercise = makeBranch("Close-grip pull-up", back);
		TreeItem<String> backThirdExcercise = makeBranch("Reverse-grip pull-up", back);
		TreeItem<String> backFourthExcercise = makeBranch("Bent-over barbell row", back);
		TreeItem<String> backFifthExcercise = makeBranch("Incline-bench dumbbell row", back);
		TreeItem<String> backSixthExcercise = makeBranch("Bent-over end barbell row", back);
		TreeItem<String> backSeventhExcercise = makeBranch("Seated cable row", back);
		TreeItem<String> backEighthExcercise = makeBranch("Close-grip pull-down", back);
		TreeItem<String> backNinthExcercise = makeBranch("Wide-grip pull-down", back);
		TreeItem<String> backTenthExcercise = makeBranch("Reverse-grip pull-down", back);
		TreeItem<String> backEleventhExcercise = makeBranch("Moving the barbell during lying down", back);
		TreeItem<String> backTwelfthExcercise = makeBranch("Rowing durinng lying down", back);
		TreeItem<String> backThirteenthExcercise = makeBranch("Slopes with the barbell on the neck", back);
		TreeItem<String> backFourteenthExcercise = makeBranch("Back extension", back);
		TreeItem<String> backFifteenthExcercise = makeBranch("Deadlift", back);
		TreeItem<String> backSixteenthExcercise = makeBranch("Deadlift on straight legs", back);
		TreeItem<String> backSeventeenthExcercise = makeBranch("Dumbbell shrug", back);
		
		arms = makeBranch("Arms", root);
		arms.setExpanded(false);
		
		/*
		 * Childrens of arms
		 */
		
		TreeItem<String> armsFirstExcercise = makeBranch("Barbell curl", arms);
		TreeItem<String> armsSecondExcercise = makeBranch("Standing dumbbell curl", arms);
		TreeItem<String> armsThirdExcercise = makeBranch("Ez-bar preacher curl", arms);
		TreeItem<String> armsFourthExcercise = makeBranch("Dumbbell preacher curl", arms);
		TreeItem<String> armsFifthExcercise = makeBranch("Seated incline curl", arms);
		TreeItem<String> armsSixthExcercise = makeBranch("Concentration curl", arms);
		TreeItem<String> armsSeventhExcercise = makeBranch("Standing dumbbell curl", arms);
		TreeItem<String> armsEighthExcercise = makeBranch("Barbell curl - straight barbell", arms);
		TreeItem<String> armsNinthExcercise = makeBranch("Barbell preacher curl", arms);
		TreeItem<String> armsTenthExcercise = makeBranch("Barbell wrist curl", arms);
		TreeItem<String> armsEleventhExcercise = makeBranch("Barbell reverse curl", arms);
		TreeItem<String> armsTwelfthExcercise = makeBranch("Triceps pressdown", arms);
		TreeItem<String> armsThirteenthExcercise = makeBranch("Seated ez-bar french press", arms);
		TreeItem<String> armsFourteenthExcercise = makeBranch("One hand seated ez-bar french press", arms);
		TreeItem<String> armsFifteenthExcercise = makeBranch("Lying ez-bar french press", arms);
		TreeItem<String> armsSixteenthExcercise = makeBranch("Lying dumbbell french press", arms);
		TreeItem<String> armsSeventeenthExcercise = makeBranch("Dumbbell kickback", arms);
		TreeItem<String> armsEighteenthExcercise = makeBranch("Triceps press straight", arms);
		TreeItem<String> armsNineteenthExcercise = makeBranch("Triceps press straight with support", arms);
		TreeItem<String> armsTwentiethExcercise = makeBranch("Handrail pumps", arms);
		TreeItem<String> armsTwentyFirstExcercise = makeBranch("Back supported pumps", arms);
		TreeItem<String> armsTwentySecondExcercise = makeBranch("Triceps reverse pressdown", arms);
		TreeItem<String> armsTwentyThirdExcercise = makeBranch("Barbell press with narrow handle", arms);
		
		legs = makeBranch("Legs", root);
		legs.setExpanded(false);
		
		/*
		 * Childrens of legs
		 */
		
		TreeItem<String> legsFirstExcercise = makeBranch("Squat - back barbell", legs);
		TreeItem<String> legsSecondExcercise = makeBranch("Squat - front barbell", legs);
		TreeItem<String> legsThirdExcercise = makeBranch("Hack squat", legs);
		TreeItem<String> legsFourthExcercise = makeBranch("Leg press", legs);
		TreeItem<String> legsFifthExcercise = makeBranch("Sisyphus", legs);
		TreeItem<String> legsSixthExcercise = makeBranch("Leg extension", legs);
		TreeItem<String> legsSeventhExcercise = makeBranch("45 degree leg press", legs);
		TreeItem<String> legsEighthExcercise = makeBranch("Leg curl", legs);
		TreeItem<String> legsNinthExcercise = makeBranch("Lunge", legs);
		TreeItem<String> legsTenthExcercise = makeBranch("Scissors", legs);
		TreeItem<String> legsEleventhExcercise = makeBranch("High step", legs);
		TreeItem<String> legsTwelfthExcercise = makeBranch("Drain back leg", legs);
		TreeItem<String> legsThirteenthExcercise = makeBranch("Pulling knees in place", legs);
		TreeItem<String> legsFourteenthExcercise = makeBranch("Bringing legs inward", legs);
		TreeItem<String> legsFifteenthExcercise = makeBranch("Leg drain on the outside", legs);
		TreeItem<String> legsSixteenthExcercise = makeBranch("Stiff leg deadlift on bench", legs);
		TreeItem<String> legsSeventeenthExcercise = makeBranch("Standing barbell calf raise", legs);
		TreeItem<String> legsEighteenthExcercise = makeBranch("Seated calf raise", legs);
		TreeItem<String> legsNineteenthExcercise = makeBranch("Donkey calf raises", legs);
		TreeItem<String> legsTwentiethExcercise = makeBranch("Hack calf raise", legs);
		TreeItem<String> legsTwentyFirstExcercise = makeBranch("45 degree calf press", legs);
		TreeItem<String> legsTwentySecondExcercise = makeBranch("Reverse calf raises", legs);
		
		excercises = new TreeView<>(root);
		excercises.setShowRoot(false);
		
		excercises.getSelectionModel().selectedItemProperty()
			.addListener((v, oldValue, newValue) -> {
			
			excercises.setOnMouseClicked(e -> {
				
				if(e.getClickCount() == 2){
					
					if(newValue == shouldersFirstExcercise)
						mainLayout.setCenter(centerPane(1, shoulderExcercises));
					
					else if(newValue == shouldersSecondExcercise)
						mainLayout.setCenter(centerPane(2, shoulderExcercises));
					
					else if(newValue == shouldersThirdExcercise)
						mainLayout.setCenter(centerPane(3, shoulderExcercises));
					
					else if(newValue == shouldersFourthExcercise)
						mainLayout.setCenter(centerPane(4, shoulderExcercises));
					
					else if(newValue == shouldersFifthExcercise)
						mainLayout.setCenter(centerPane(5, shoulderExcercises));
					
					else if(newValue == shouldersSixthExcercise)
						mainLayout.setCenter(centerPane(6, shoulderExcercises));
					
					else if(newValue == shouldersSeventhExcercise)
						mainLayout.setCenter(centerPane(7, shoulderExcercises));
					
					else if(newValue == shouldersEighthExcercise)
						mainLayout.setCenter(centerPane(8, shoulderExcercises));
					
					else if(newValue == shouldersNinthExcercise)
						mainLayout.setCenter(centerPane(9, shoulderExcercises));
					
					else if(newValue == shouldersTenthExcercise)
						mainLayout.setCenter(centerPane(10, shoulderExcercises));
					
					else if(newValue == shouldersEleventhExcercise)
						mainLayout.setCenter(centerPane(11, shoulderExcercises));
					
					else if(newValue == shouldersTwelfthExcercise)
						mainLayout.setCenter(centerPane(12, shoulderExcercises));
					
					else if(newValue == shouldersThirteenthExcercise)
						mainLayout.setCenter(centerPane(13, shoulderExcercises));
					
					else if(newValue == shouldersFourteenthExcercise)
						mainLayout.setCenter(centerPane(14, shoulderExcercises));
					
					else if(newValue == shouldersFifteenthExcercise)
						mainLayout.setCenter(centerPane(15, shoulderExcercises));
					
					else if(newValue == chestFirstExcercise)
						mainLayout.setCenter(centerPane(1, chestExcercises));
					
					else if(newValue == chestSecondExcercise)
						mainLayout.setCenter(centerPane(2, chestExcercises));
					
					else if(newValue == chestThirdExcercise)
						mainLayout.setCenter(centerPane(3, chestExcercises));
					
					else if(newValue == chestFourthExcercise)
						mainLayout.setCenter(centerPane(4, chestExcercises));
					
					else if(newValue == chestFifthExcercise)
						mainLayout.setCenter(centerPane(5, chestExcercises));
					
					else if(newValue == chestSixthExcercise)
						mainLayout.setCenter(centerPane(6, chestExcercises));
					
					else if(newValue == chestSeventhExcercise)
						mainLayout.setCenter(centerPane(7, chestExcercises));
					
					else if(newValue == chestEighthExcercise)
						mainLayout.setCenter(centerPane(8, chestExcercises));
					
					else if(newValue == chestNinthExcercise)
						mainLayout.setCenter(centerPane(9, chestExcercises));
					
					else if(newValue == chestTenthExcercise)
						mainLayout.setCenter(centerPane(10, chestExcercises));
					
					else if(newValue == chestEleventhExcercise)
						mainLayout.setCenter(centerPane(11, chestExcercises));
					
					else if(newValue == chestTwelfthExcercise)
						mainLayout.setCenter(centerPane(12, chestExcercises));
					
					else if(newValue == chestThirteenthExcercise)
						mainLayout.setCenter(centerPane(13, chestExcercises));
					
					else if(newValue == backFirstExcercise)
						mainLayout.setCenter(centerPane(1, backExcercises));
					
					else if(newValue == backSecondExcercise)
						mainLayout.setCenter(centerPane(2, backExcercises));
					
					else if(newValue == backThirdExcercise)
						mainLayout.setCenter(centerPane(3, backExcercises));
					
					else if(newValue == backFourthExcercise)
						mainLayout.setCenter(centerPane(4, backExcercises));
					
					else if(newValue == backFifthExcercise)
						mainLayout.setCenter(centerPane(5, backExcercises));
					
					else if(newValue == backSixthExcercise)
						mainLayout.setCenter(centerPane(6, backExcercises));
					
					else if(newValue == backSeventhExcercise)
						mainLayout.setCenter(centerPane(7, backExcercises));
					
					else if(newValue == backEighthExcercise)
						mainLayout.setCenter(centerPane(8, backExcercises));
					
					else if(newValue == backNinthExcercise)
						mainLayout.setCenter(centerPane(9, backExcercises));
					
					else if(newValue == backTenthExcercise)
						mainLayout.setCenter(centerPane(10, backExcercises));
					
					else if(newValue == backEleventhExcercise)
						mainLayout.setCenter(centerPane(11, backExcercises));
					
					else if(newValue == backTwelfthExcercise)
						mainLayout.setCenter(centerPane(12, backExcercises));
					
					else if(newValue == backThirteenthExcercise)
						mainLayout.setCenter(centerPane(13, backExcercises));
					
					else if(newValue == backFourteenthExcercise)
						mainLayout.setCenter(centerPane(14, backExcercises));
					
					else if(newValue == backFifteenthExcercise)
						mainLayout.setCenter(centerPane(15, backExcercises));
					
					else if(newValue == backSixteenthExcercise)
						mainLayout.setCenter(centerPane(16, backExcercises));

					else if(newValue == backSeventeenthExcercise)
						mainLayout.setCenter(centerPane(17, backExcercises));
					
					else if(newValue == armsFirstExcercise)
						mainLayout.setCenter(centerPane(1, armsExcercises));
					
					else if(newValue == armsSecondExcercise)
						mainLayout.setCenter(centerPane(2, armsExcercises));
					
					else if(newValue == armsThirdExcercise)
						mainLayout.setCenter(centerPane(3, armsExcercises));
					
					else if(newValue == armsFourthExcercise)
						mainLayout.setCenter(centerPane(4, armsExcercises));
					
					else if(newValue == armsFifthExcercise)
						mainLayout.setCenter(centerPane(5, armsExcercises));
					
					else if(newValue == armsSixthExcercise)
						mainLayout.setCenter(centerPane(6, armsExcercises));
					
					else if(newValue == armsSeventhExcercise)
						mainLayout.setCenter(centerPane(7, armsExcercises));
					
					else if(newValue == armsEighthExcercise)
						mainLayout.setCenter(centerPane(8, armsExcercises));
					
					else if(newValue == armsNinthExcercise)
						mainLayout.setCenter(centerPane(9, armsExcercises));
					
					else if(newValue == armsTenthExcercise)
						mainLayout.setCenter(centerPane(10, armsExcercises));
					
					else if(newValue == armsEleventhExcercise)
						mainLayout.setCenter(centerPane(11, armsExcercises));
					
					else if(newValue == armsTwelfthExcercise)
						mainLayout.setCenter(centerPane(12, armsExcercises));
					
					else if(newValue == armsThirteenthExcercise)
						mainLayout.setCenter(centerPane(13, armsExcercises));
					
					else if(newValue == armsFourteenthExcercise)
						mainLayout.setCenter(centerPane(14, armsExcercises));

					else if(newValue == armsFifteenthExcercise)
						mainLayout.setCenter(centerPane(15, armsExcercises));
					
					else if(newValue == armsSixteenthExcercise)
						mainLayout.setCenter(centerPane(16, armsExcercises));
					
					else if(newValue == armsSeventeenthExcercise)
						mainLayout.setCenter(centerPane(17, armsExcercises));
					
					else if(newValue == armsEighteenthExcercise)
						mainLayout.setCenter(centerPane(18, armsExcercises));
					
					else if(newValue == armsNineteenthExcercise)
						mainLayout.setCenter(centerPane(19, armsExcercises));
					
					else if(newValue == armsTwentiethExcercise)
						mainLayout.setCenter(centerPane(20, armsExcercises));
					
					else if(newValue == armsTwentyFirstExcercise)
						mainLayout.setCenter(centerPane(21, armsExcercises));
					
					else if(newValue == armsTwentySecondExcercise)
						mainLayout.setCenter(centerPane(22, armsExcercises));
					
					else if(newValue == armsTwentyThirdExcercise)
						mainLayout.setCenter(centerPane(23, armsExcercises));
					
					else if(newValue == legsFirstExcercise)
						mainLayout.setCenter(centerPane(1, legsExcercises));
					
					else if(newValue == legsSecondExcercise)
						mainLayout.setCenter(centerPane(2, legsExcercises));
					
					else if(newValue == legsThirdExcercise)
						mainLayout.setCenter(centerPane(3, legsExcercises));
					
					else if(newValue == legsFourthExcercise)
						mainLayout.setCenter(centerPane(4, legsExcercises));
					
					else if(newValue == legsFifthExcercise)
						mainLayout.setCenter(centerPane(5, legsExcercises));
					
					else if(newValue == legsSixthExcercise)
						mainLayout.setCenter(centerPane(6, legsExcercises));
					
					else if(newValue == legsSeventhExcercise)
						mainLayout.setCenter(centerPane(7, legsExcercises));
					
					else if(newValue == legsEighthExcercise)
						mainLayout.setCenter(centerPane(8, legsExcercises));
					
					else if(newValue == legsNinthExcercise)
						mainLayout.setCenter(centerPane(9, legsExcercises));
					
					else if(newValue == legsTenthExcercise)
						mainLayout.setCenter(centerPane(10, legsExcercises));
					
					else if(newValue == legsEleventhExcercise)
						mainLayout.setCenter(centerPane(11, legsExcercises));
					
					else if(newValue == legsTwelfthExcercise)
						mainLayout.setCenter(centerPane(12, legsExcercises));
					
					else if(newValue == legsThirteenthExcercise)
						mainLayout.setCenter(centerPane(13, legsExcercises));
					
					else if(newValue == legsFourteenthExcercise)
						mainLayout.setCenter(centerPane(14, legsExcercises));
					
					else if(newValue == legsFifteenthExcercise)
						mainLayout.setCenter(centerPane(15, legsExcercises));
					
					else if(newValue == legsSixteenthExcercise)
						mainLayout.setCenter(centerPane(16, legsExcercises));
					
					else if(newValue == legsSeventeenthExcercise)
						mainLayout.setCenter(centerPane(17, legsExcercises));
					
					else if(newValue == legsEighteenthExcercise)
						mainLayout.setCenter(centerPane(18, legsExcercises));
					
					else if(newValue == legsNineteenthExcercise)
						mainLayout.setCenter(centerPane(19, legsExcercises));
					
					else if(newValue == legsTwentiethExcercise)
						mainLayout.setCenter(centerPane(20, legsExcercises));
					
					else if(newValue == legsTwentyFirstExcercise)
						mainLayout.setCenter(centerPane(21, legsExcercises));
					
					else if(newValue == legsTwentySecondExcercise)
						mainLayout.setCenter(centerPane(22, legsExcercises));
					
				} // End of if(e.getClickCount() == 2)
				
			}); // End of excercises.setOnMouseClicked()
			
		}); // End of excercises.getSelectionModel().selectedItemProperty().addListener()
		
		StackPane layout = new StackPane();
		layout.setMinWidth(340);
		layout.getChildren().add(excercises);
		
		return layout;
		
	} // End of excercisesPane()
	
	/*
	 * Creates branches to TreeView
	 */
	
	public static TreeItem<String> makeBranch(String title, TreeItem<String> parent){
		
		TreeItem<String> item = new TreeItem<>(title);
		item.setExpanded(true);
		parent.getChildren().add(item);
		return item;
		
	} // End of makeBranches(String title, TreeView<String> parent) method
	
	/*
	 * Creates a legendPane on the bottom of an app
	 */
	
	public static HBox legendPane(){
		
		HBox hBox = new HBox(10);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setAlignment(Pos.CENTER);
		
		Image star = new Image("/resources/star.png");
		ImageView[] viewTheStar = new ImageView[6];
		
		for(int i = 0; i < 6; i++){
			
			viewTheStar[i] = new ImageView();
			viewTheStar[i].setImage(star);
			
		} // End of for
		
		hBox.setStyle("-fx-border-color: black; -fx-background-color: #DCDCDC;");
		
		Label novice = new Label(" - Novice        ");
		Label intermediate = new Label(" - Intermediate        ");
		Label advanced = new Label(" - Advanced        ");
		
		hBox.getChildren().addAll(viewTheStar[0], novice, viewTheStar[1], viewTheStar[2],  intermediate, viewTheStar[3], viewTheStar[4], viewTheStar[5], advanced);
		
		return hBox;
		
	} // End of legendPane() method
	
	/*
	 * Creates a centerPane
	 */
	
	public static VBox centerPane(int number, CollectionOfExcercises collection){
		
		VBox centerPane = new VBox(10);
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setPadding(new Insets(20, 20, 20, 20));
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20, 20, 20, 20));
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(40);
		gridPane.setVgap(40);
		
		excerciseView = collection.getTheExcercise(number);
		
		ImageView[] view = excerciseView.getGifs();
		
		Label nameOfExcercise = new Label(excerciseView.getNameOfExcercise());
		nameOfExcercise.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;");
		
		centerPane.getChildren().add(nameOfExcercise);
		
		HBox hBox = new HBox(20);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		hBox.setAlignment(Pos.CENTER);
		
		Image star = new Image("/resources/star.png");
		ImageView[] viewTheStar = new ImageView[6];
		
		/*
		 * Add stars to hBox
		 */
		
		for(int i = 0; i < excerciseView.getDifficultyLevel(); i++){
			
			viewTheStar[i] = new ImageView();
			viewTheStar[i].setImage(star);
			hBox.getChildren().add(viewTheStar[i]);
			
		} // End of for
		
		centerPane.getChildren().add(hBox);
		
		/*
		 * Add gifs to centerPane
		 */
		
		int x = 0, y = 0;
		
		for(int i = 0; i < view.length; i++){
			
			gridPane.add(view[i], x, y);
			
			x++;
			
			if(x == 3){
				x = 0;
				y++;
				
			} // End of if(x == 3)
			
		} // End of for
		
		centerPane.getChildren().add(gridPane);
		
		return centerPane;
		
	} // End of centerPane() method
	
	public static void createExcercise(String name, int difficulty, CollectionOfExcercises parent, Image...images){
		
		ExcerciseView excerciseType = new ExcerciseView();
		excerciseType.setNameOfExcercise(name);
		excerciseType.setDifficultyLevel(difficulty);
		excerciseType.setGifs(images);
		
		parent.addTheExcercise(excerciseType);
		
	} // End of createExcercise(String name, int difficulty, CollectionOfExcercises parent, Image...images) method
	
	public static Image[] image(String...paths){
		
		List<Image> imageList = new ArrayList<>();
		
		Image[] image;
		
		for(String path : paths){
			
			imageList.add(new Image(path));
			
		} // End of for
		
		image = new Image[imageList.size()];
		
		for(int i = 0; i < imageList.size(); i++){
			
			image[i] = imageList.get(i);
			
		}
		
		return image;
		
	} // End of image(String...path) method
	
	public Main(){
		
		/*
		 * Class to hold objects of ExcerciseView
		 */
		
		shoulderExcercises = new ShoulderExcercises();
		chestExcercises = new ChestExcercises();
		backExcercises = new BackExcercises();
		armsExcercises = new ArmExcercises();
		legsExcercises = new LegExcercises();
		
		/*
		 * Defining all excercises
		 */
		
		// Shoulder excercises

		// shouldersFirstExcercise
		
		Image[] shouldersFirstExcerciseImages = image("/resources/shoulders-image004.gif",
				"/resources/shoulders-image005.gif", "/resources/shoulders-image006.gif");

		
		createExcercise("Standing military press", 2, shoulderExcercises, shouldersFirstExcerciseImages);
		
		// shouldersSecondExcercise
		
		Image[] shouldersSecondExcerciseImages = image("/resources/shoulders-image007.gif", 
				"/resources/shoulders-image008.gif");
		
		createExcercise("Military press from behind the head", 2, shoulderExcercises, shouldersSecondExcerciseImages);
		
		// shouldersThirdExcercise
		
		Image[] shouldersThirdExcerciseImages = image("/resources/shoulders-image009.gif");
		
		createExcercise("Dumbbell press", 3, shoulderExcercises, shouldersThirdExcerciseImages);
		
		// shouldersFourthExcercise
		
		Image[] shouldersFourthExcerciseImages = image("/resources/shoulders-image010.gif");
				
		createExcercise("Arnold press", 3, shoulderExcercises, shouldersFourthExcerciseImages);
		
		// shouldersFifthExcercise
		
		Image[] shouldersFifthExcerciseImages = image("/resources/shoulders-image011.gif");
						
		createExcercise("Lateral raise", 3, shoulderExcercises, shouldersFifthExcerciseImages);
		
		// shouldersSixthExcercise
		
		Image[] shouldersSixthExcerciseImages = image("/resources/shoulders-image012.gif",
				"/resources/shoulders-image013.gif");
								
		createExcercise("Lateral raise in the torso fall", 3, shoulderExcercises, shouldersSixthExcerciseImages);
		
		// shouldersSeventhExcercise
		
		Image[] shouldersSeventhExcerciseImages = image("/resources/shoulders-image014.gif");
										
		createExcercise("Upright barbell row", 3, shoulderExcercises, shouldersSeventhExcerciseImages);
		
		// shouldersEighthExcercise
		
		Image[] shouldersEighthExcerciseImages = image("/resources/shoulders-image015.gif");
												
		createExcercise("Upright dumbbell row", 3, shoulderExcercises, shouldersEighthExcerciseImages);
		
		// shouldersNinthExcercise
		
		Image[] shouldersNinthExcerciseImages = image("/resources/shoulders-image016.gif");
														
		createExcercise("Front barbell raisei", 3, shoulderExcercises, shouldersNinthExcerciseImages);
		
		// shouldersTenthExcercise
		
		Image[] shouldersTenthExcerciseImages = image("/resources/shoulders-image017.gif");
																
		createExcercise("Front dumbbell raisei", 3, shoulderExcercises, shouldersTenthExcerciseImages);
		
		// shouldersEleventhExcercise
		
		Image[] shouldersEleventhExcerciseImages = image("/resources/shoulders-image018.gif");
																		
		createExcercise("Lateral raise during lying down", 3, shoulderExcercises, shouldersEleventhExcerciseImages);
		
		// shouldersTwelfthExcercise
		
		Image[] shouldersTwelfthExcerciseImages = image("/resources/shoulders-image019.gif");
																				
		createExcercise("Front lift rope raisei", 3, shoulderExcercises, shouldersTwelfthExcerciseImages);
		
		// shouldersThirteenthExcercise
		
		Image[] shouldersThirteenthExcerciseImages = image("/resources/shoulders-image020.gif");
																						
		createExcercise("Lateral raise with lift rope", 3, shoulderExcercises, shouldersThirteenthExcerciseImages);
		
		// shouldersFourteenthExcercise
		
		Image[] shouldersFourteenthExcerciseImages = image("/resources/shoulders-image021.gif");
																								
		createExcercise("Lateral raise in the torso fall with lift rope", 3, shoulderExcercises, shouldersFourteenthExcerciseImages);
		
		// shouldersFifteenthExcercise
		
		Image[] shouldersFifteenthExcerciseImages = image("/resources/shoulders-image022.gif");
																										
		createExcercise("Opposite incline flyes", 3, shoulderExcercises, shouldersFifteenthExcerciseImages);
		
		// Chest excercises
		
		// chestFirstExcercise
		
		Image[] chestFirstExcerciseImages = image("/resources/chest-image008.gif",
				"/resources/chest-image009.gif", "/resources/chest-image010.gif");
		
		createExcercise("Bench press", 0, chestExcercises, chestFirstExcerciseImages);
		
		// chestSecondExcercise
		
		Image[] chestSecondExcerciseImages = image("/resources/chest-image011.gif");
				
		createExcercise("Bench press with dumbbells", 0, chestExcercises, chestSecondExcerciseImages);
		
		// chestThirdExcercise
		
		Image[] chestThirdExcerciseImages = image("/resources/chest-image012.gif",
				"/resources/chest-image013.gif", "/resources/chest-image014.gif");
						
		createExcercise("Incline barbell press", 0, chestExcercises, chestThirdExcerciseImages);
		
		// chestFourthExcercise
		
		Image[] chestFourthExcerciseImages = image("/resources/chest-image015.gif");
								
		createExcercise("Incline dumbbell press", 0, chestExcercises, chestFourthExcerciseImages);
		
		// chestFifthExcercise
		
		Image[] chestFifthExcerciseImages = image("/resources/chest-image016.gif",
				"/resources/chest-image017.gif", "/resources/chest-image018.gif");
										
		createExcercise("Decline barbell press", 0, chestExcercises, chestFifthExcerciseImages);
		
		// chestSixthExcercise
		
		Image[] chestSixthExcerciseImages = image("/resources/chest-image019.gif");
												
		createExcercise("Decline dumbbell press", 0, chestExcercises, chestSixthExcerciseImages);
		
		// chestSeventhExcercise
		
		Image[] chestSeventhExcerciseImages = image("/resources/chest-image020.gif",
				"/resources/chest-image021.gif", "/resources/chest-image022.gif");
														
		createExcercise("Incline flyes", 0, chestExcercises, chestSeventhExcerciseImages);
		
		// chestEighthExcercise
		
		Image[] chestEighthExcerciseImages = image("/resources/chest-image023.gif",
				"/resources/chest-image024.gif");
																
		createExcercise("Bench press - narrow hold", 0, chestExcercises, chestEighthExcerciseImages);
		
		// chestNinthExcercise
		
		Image[] chestNinthExcerciseImages = image("/resources/chest-image025.gif");
																		
		createExcercise("Pull over", 0, chestExcercises, chestNinthExcerciseImages);
		
		// chestTenthExcercise
		
		Image[] chestTenthExcerciseImages = image("/resources/chest-image026.gif");
																				
		createExcercise("Pumps on the handrails", 0, chestExcercises, chestTenthExcerciseImages);
		
		// chestEleventhExcercise
		
		Image[] chestEleventhExcerciseImages = image("/resources/chest-image027.gif",
				"/resources/chest-image028.gif");
																						
		createExcercise("Incline flyes - sitting on the machine", 0, chestExcercises, chestEleventhExcerciseImages);
		
		// chestTwelfthExcercise
		
		Image[] chestTwelfthExcerciseImages = image("/resources/chest-image029.gif");
																								
		createExcercise("Crossing the ropes during standing up", 0, chestExcercises, chestTwelfthExcerciseImages);
		
		// chestThirteenthExcercise
		
		Image[] chestThirteenthExcerciseImages = image("/resources/chest-image030.gif",
				"/resources/chest-image030.gif");
																										
		createExcercise("Bench press - sitting on the machine", 0, chestExcercises, chestThirteenthExcerciseImages);
		
		// Back excercises
		
		// backFirstExcercise
		
		Image[] backFirstExcerciseImages = image("/resources/back-image021.gif",
				"/resources/back-image022.gif");
																										
		createExcercise("Wide-grip pull-up", 0, backExcercises, backFirstExcerciseImages);
		
		// backSecondExcercise
		
		Image[] backSecondExcerciseImages = image("/resources/back-image023.gif");
																										
		createExcercise("Close-grip pull-up", 0, backExcercises, backSecondExcerciseImages);
		
		// backThirdExcercise
		
		Image[] backThirdExcerciseImages = image("/resources/back-image024.gif");
																										
		createExcercise("Reverse-grip pull-up", 0, backExcercises, backThirdExcerciseImages);
		
		// backFourthExcercise
		
		Image[] backFourthExcerciseImages = image("/resources/back-image025.gif",
				"/resources/back-image026.gif", "/resources/back-image027.gif");
																										
		createExcercise("Bent-over barbell row", 0, backExcercises, backFourthExcerciseImages);
		
		// backFifthExcercise
		
		Image[] backFifthExcerciseImages = image("/resources/back-image028.gif",
				"/resources/back-image029.gif");
																										
		createExcercise("Incline-bench dumbbell row", 0, backExcercises, backFifthExcerciseImages);
		
		// backSixthExcercise
		
		Image[] backSixthExcerciseImages = image("/resources/back-image030.gif",
				"/resources/back-image031.gif", "/resources/back-image032.gif");
																										
		createExcercise("Bent-over end barbell row", 0, backExcercises, backSixthExcerciseImages);
		
		// backSeventhExcercise
		
		Image[] backSeventhExcerciseImages = image("/resources/back-image033.gif",
				"/resources/back-image034.gif", "/resources/back-image035.gif");
																										
		createExcercise("Seated cable row", 0, backExcercises, backSeventhExcerciseImages);
		
		// backEighthExcercise
		
		Image[] backEighthExcerciseImages = image("/resources/back-image036.gif",
				"/resources/back-image037.gif", "/resources/back-image038.gif",
				"/resources/back-image042.gif", "/resources/back-image043.gif");
																										
		createExcercise("Close-grip pull-down", 0, backExcercises, backEighthExcerciseImages);
		
		// backNinthExcercise
		
		Image[] backNinthExcerciseImages = image("/resources/back-image039.gif",
				"/resources/back-image040.gif");
																										
		createExcercise("Wide-grip pull-down", 0, backExcercises, backNinthExcerciseImages);
		
		// backTenthExcercise
		
		Image[] backTenthExcerciseImages = image("/resources/back-image041.gif");
																										
		createExcercise("Reverse-grip pull-down", 0, backExcercises, backTenthExcerciseImages);
		
		// backEleventhExcercise
		
		Image[] backEleventhExcerciseImages = image("/resources/back-image044.gif",
				"/resources/back-image045.gif", "/resources/back-image046.gif");
																										
		createExcercise("Moving the barbell during lying down", 0, backExcercises, backEleventhExcerciseImages);
		
		// backTwelfthExcercise
		
		Image[] backTwelfthExcerciseImages = image("/resources/back-image047.gif",
				"/resources/back-image048.gif");
																										
		createExcercise("Rowing durinng lying down", 0, backExcercises, backTwelfthExcerciseImages);
		
		// backThirteenthExcercise
		
		Image[] backThirteenthExcerciseImages = image("/resources/back-image049.gif",
				"/resources/back-image050.gif");
																										
		createExcercise("Slopes with the barbell on the neck", 0, backExcercises, backThirteenthExcerciseImages);
		
		// backFourteenthExcercise
		
		Image[] backFourteenthExcerciseImages = image("/resources/back-image051.gif",
				"/resources/back-image052.gif");
																										
		createExcercise("Back extension", 0, backExcercises, backFourteenthExcerciseImages);
		
		// backFifteenthExcercise
		
		Image[] backFifteenthExcerciseImages = image("/resources/back-image053.gif",
				"/resources/back-image054.gif", "/resources/back-image055.gif");
																										
		createExcercise("Deadlift", 0, backExcercises, backFifteenthExcerciseImages);
		
		// backSixteenthExcercise
		
		Image[] backSixteenthExcerciseImages = image("/resources/back-image056.gif",
				"/resources/back-image057.gif", "/resources/back-image058.gif");
																										
		createExcercise("Deadlift on straight legs", 0, backExcercises, backSixteenthExcerciseImages);
		
		// backSeventeenthExcercise
		
		Image[] backSeventeenthExcerciseImages = image("/resources/back-image059.gif",
				"/resources/back-image060.gif", "/resources/back-image061.gif",
				"/resources/back-image062.gif", "/resources/back-image063.gif",
				"/resources/back-image064.gif");
																										
		createExcercise("Dumbbell shrug", 0, backExcercises, backSeventeenthExcerciseImages);
		
		// Arm excercises
		
		// armsFirstExcercise
		
		Image[] armsFirstExcerciseImages = image("/resources/arms-image006.gif");
		
		createExcercise("Barbell curl", 0, armsExcercises, armsFirstExcerciseImages);
		
		// armsSecondExcercise
		
		Image[] armsSecondExcerciseImages = image("/resources/arms-image007.gif");
		
		createExcercise("Standing dumbbell curl", 0, armsExcercises, armsSecondExcerciseImages);
		
		// armsThirdExcercise
		
		Image[] armsThirdExcerciseImages = image("/resources/arms-image009.gif",
				"/resources/arms-image010.gif", "/resources/arms-image011.gif");
		
		createExcercise("Ez-bar preacher curl", 0, armsExcercises, armsThirdExcerciseImages);
		
		// armsFourthExcercise
		
		Image[] armsFourthExcerciseImages = image("/resources/arms-image012.gif");
		
		createExcercise("Dumbbell preacher curl", 0, armsExcercises, armsFourthExcerciseImages);
		
		// armsFifthExcercise
		
		Image[] armsFifthExcerciseImages = image("/resources/arms-image013.gif");
		
		createExcercise("Seated incline curl", 0, armsExcercises, armsFifthExcerciseImages);
		
		// armsSixthExcercise
		
		Image[] armsSixthExcerciseImages = image("/resources/arms-image014.gif");
		
		createExcercise("Concentration curl", 0, armsExcercises, armsSixthExcerciseImages);
		
		// armsSeventhExcercise
		
		Image[] armsSeventhExcerciseImages = image("/resources/arms-image015.gif",
				"/resources/arms-image016.gif");
		
		createExcercise("Standing dumbbell curl", 0, armsExcercises, armsSeventhExcerciseImages);
		
		// armsEighthExcercise
		
		Image[] armsEighthExcerciseImages = image("/resources/arms-image017.gif",
				"/resources/arms-image018.gif");
		
		createExcercise("Barbell curl - straight barbell", 0, armsExcercises, armsEighthExcerciseImages);
		
		// armsNinthExcercise
		
		Image[] armsNinthExcerciseImages = image("/resources/arms-image019.gif",
				"/resources/arms-image020.gif");
		
		createExcercise("Barbell preacher curl", 0, armsExcercises, armsNinthExcerciseImages);
		
		// armsTenthExcercise
		
		Image[] armsTenthExcerciseImages = image("/resources/arms-image021.gif",
				"/resources/arms-image022.gif", "/resources/arms-image023.gif");
		
		createExcercise("Barbell wrist curl", 0, armsExcercises, armsTenthExcerciseImages);
		
		// armsEleventhExcercise
		
		Image[] armsEleventhExcerciseImages = image("/resources/arms-image024.gif",
				"/resources/arms-image025.gif", "/resources/arms-image026.gif");
		
		createExcercise("Barbell reverse curl", 0, armsExcercises, armsEleventhExcerciseImages);
		
		// armsTwelfthExcercise
		
		Image[] armsTwelfthExcerciseImages = image("/resources/arms-image028.gif");
		
		createExcercise("Triceps pressdown", 0, armsExcercises, armsTwelfthExcerciseImages);
		
		// armsThirteenthExcercise
		
		Image[] armsThirteenthExcerciseImages = image("/resources/arms-image029.gif",
				"/resources/arms-image030.gif", "/resources/arms-image031.gif");
		
		createExcercise("Seated ez-bar french press", 0, armsExcercises, armsThirteenthExcerciseImages);
		
		// armsFourteenthExcercise
		
		Image[] armsFourteenthExcerciseImages = image("/resources/arms-image032.gif",
				"/resources/arms-image033.gif");
		
		createExcercise("One hand seated ez-bar french press", 0, armsExcercises, armsFourteenthExcerciseImages);
		
		// armsFifteenthExcercise
		
		Image[] armsFifteenthExcerciseImages = image("/resources/arms-image034.gif",
				"/resources/arms-image035.gif");
		
		createExcercise("Lying ez-bar french press", 0, armsExcercises, armsFifteenthExcerciseImages);
		
		// armsSixteenthExcercise
		
		Image[] armsSixteenthExcerciseImages = image("/resources/arms-image036.gif",
				"/resources/arms-image037.gif");
		
		createExcercise("Lying dumbbell french press", 0, armsExcercises, armsSixteenthExcerciseImages);
		
		// armsSeventeenthExcercise
		
		Image[] armsSeventeenthExcerciseImages = image("/resources/arms-image038.gif");
		
		createExcercise("Dumbbell kickback", 0, armsExcercises, armsSeventeenthExcerciseImages);
		
		// armsEighteenthExcercise
		
		Image[] armsEighteenthExcerciseImages = image("/resources/arms-image039.gif");
		
		createExcercise("Triceps press straight", 0, armsExcercises, armsEighteenthExcerciseImages);
		
		// armsNineteenthExcercise
		
		Image[] armsNineteenthExcerciseImages = image("/resources/arms-image040.gif");
		
		createExcercise("Triceps press straight with support", 0, armsExcercises, armsNineteenthExcerciseImages);
		
		// armsTwentiethExcercise
		
		Image[] armsTwentiethExcerciseImages = image("/resources/arms-image041.gif");
		
		createExcercise("Handrail pumps", 0, armsExcercises, armsTwentiethExcerciseImages);
		
		// armsTwentyFirstExcercise
		
		Image[] armsTwentyFirstExcerciseImages = image("/resources/arms-image042.gif");
		
		createExcercise("Back supported pumps", 0, armsExcercises, armsTwentyFirstExcerciseImages);
		
		// armsTwentySecondExcercise
		
		Image[] armsTwentySecondExcerciseImages = image("/resources/arms-image043.gif");
		
		createExcercise("Triceps reverse pressdown", 0, armsExcercises, armsTwentySecondExcerciseImages);
		
		// armsTwentyThirdExcercise
		
		Image[] armsTwentyThirdExcerciseImages = image("/resources/arms-image044.gif",
				"/resources/arms-image045.gif");
		
		createExcercise("Barbell press with narrow handle", 0, armsExcercises, armsTwentyThirdExcerciseImages);
		
		// Leg excercises
		
		// legsFirstExcercise
		
		Image[] legsFirstExcerciseImages = image("/resources/legs-image023.gif",
				"/resources/legs-image024.gif", "/resources/legs-image025.gif",
				"/resources/legs-image026.gif", "/resources/legs-image027.gif",
				"/resources/legs-image028.gif");
		
		createExcercise("Squat - back barbell", 0, legsExcercises, legsFirstExcerciseImages);
		
		// legsSecondExcercise
		
		Image[] legsSecondExcerciseImages = image("/resources/legs-image029.gif",
				"/resources/legs-image030.gif");
		
		createExcercise("Squat - front barbell", 0, legsExcercises, legsSecondExcerciseImages);
		
		// legsThirdExcercise
		
		Image[] legsThirdExcerciseImages = image("/resources/legs-image031.gif",
				"/resources/legs-image032.gif");
		
		createExcercise("Hack squat", 0, legsExcercises, legsThirdExcerciseImages);
		
		// legsFourthExcercise
		
		Image[] legsFourthExcerciseImages = image("/resources/legs-image033.gif",
				"/resources/legs-image034.gif");
		
		createExcercise("Leg press", 0, legsExcercises, legsFourthExcerciseImages);
		
		// legsFifthExcercise
		
		Image[] legsFifthExcerciseImages = image("/resources/legs-image035.gif");
		
		createExcercise("Sisyphus", 0, legsExcercises, legsFifthExcerciseImages);
		
		// legsSixthExcercise
		
		Image[] legsSixthExcerciseImages = image("/resources/legs-image036.gif");
		
		createExcercise("Leg extension", 0, legsExcercises, legsSixthExcerciseImages);
		
		// legsSeventhExcercise
		
		Image[] legsSeventhExcerciseImages = image("/resources/legs-image037.gif",
				"/resources/legs-image038.gif", "/resources/legs-image039.gif",
				"/resources/legs-image040.gif");
		
		createExcercise("45 degree leg press", 0, legsExcercises, legsSeventhExcerciseImages);
		
		// legsEighthExcercise
		
		Image[] legsEighthExcerciseImages = image("/resources/legs-image041.gif",
				"/resources/legs-image042.gif", "/resources/legs-image043.gif",
				"/resources/legs-image044.gif");
		
		createExcercise("Leg curl", 0, legsExcercises, legsEighthExcerciseImages);
		
		// legsNinthExcercise
		
		Image[] legsNinthExcerciseImages = image("/resources/legs-image045.gif",
				"/resources/legs-image046.gif");
		
		createExcercise("Lunge", 0, legsExcercises, legsNinthExcerciseImages);
		
		// legsTenthExcercise
		
		Image[] legsTenthExcerciseImages = image("/resources/legs-image047.gif",
				"/resources/legs-image048.gif", "/resources/legs-image049.gif");
		
		createExcercise("Scissors", 0, legsExcercises, legsTenthExcerciseImages);
		
		// legsEleventhExcercise
		
		Image[] legsEleventhExcerciseImages = image("/resources/legs-image050.gif",
				"/resources/legs-image051.gif");
		
		createExcercise("High step", 0, legsExcercises, legsEleventhExcerciseImages);
		
		// legsTwelfthExcercise
		
		Image[] legsTwelfthExcerciseImages = image("/resources/legs-image052.gif");
		
		createExcercise("Drain back leg", 0, legsExcercises, legsTwelfthExcerciseImages);
		
		// legsThirteenthExcercise
		
		Image[] legsThirteenthExcerciseImages = image("/resources/legs-image053.gif");
		
		createExcercise("Pulling knees in place", 0, legsExcercises, legsThirteenthExcerciseImages);
		
		// legsFourteenthExcercise
		
		Image[] legsFourteenthExcerciseImages = image("/resources/legs-image054.gif");
		
		createExcercise("Bringing legs inward", 0, legsExcercises, legsFourteenthExcerciseImages);
		
		// legsFifteenthExcercise
		
		Image[] legsFifteenthExcerciseImages = image("/resources/legs-image055.gif");
		
		createExcercise("Leg drain on the outside", 0, legsExcercises, legsFifteenthExcerciseImages);
		
		// legsSixteenthExcercise
		
		Image[] legsSixteenthExcerciseImages = image("/resources/legs-image056.gif",
				"/resources/legs-image057.gif", "/resources/legs-image058.gif");
		
		createExcercise("Stiff leg deadlift on bench", 0, legsExcercises, legsSixteenthExcerciseImages);
		
		// legsSeventeenthExcercise
		
		Image[] legsSeventeenthExcerciseImages = image("/resources/legs-image064.gif",
				"/resources/legs-image065.gif", "/resources/legs-image066.gif",
				"/resources/legs-image067.gif");
		
		createExcercise("Standing barbell calf raise", 0, legsExcercises, legsSeventeenthExcerciseImages);
		
		// legsEighteenthExcercise
		
		Image[] legsEighteenthExcerciseImages = image("/resources/legs-image068.gif",
				"/resources/legs-image069.gif");
		
		createExcercise("Seated calf raise", 0, legsExcercises, legsEighteenthExcerciseImages);
		
		// legsNineteenthExcercise
		
		Image[] legsNineteenthExcerciseImages = image("/resources/legs-image070.gif",
				"/resources/legs-image071.gif");
		
		createExcercise("Donkey calf raises", 0, legsExcercises, legsNineteenthExcerciseImages);
		
		// legsTwentiethExcercise
		
		Image[] legsTwentiethExcerciseImages = image("/resources/legs-image072.gif");
		
		createExcercise("Hack calf raise", 0, legsExcercises, legsTwentiethExcerciseImages);
		
		// legsTwentyFirstExcercise
		
		Image[] legsTwentyFirstExcerciseImages = image("/resources/legs-image073.gif");
		
		createExcercise("45 degree calf press", 0, legsExcercises, legsTwentyFirstExcerciseImages);
		
		// legsTwentySecondExcercise
		
		Image[] legsTwentySecondExcerciseImages = image("/resources/legs-image074.gif",
				"/resources/legs-image075.gif", "/resources/legs-image076.gif");
		
		createExcercise("Reverse calf raises", 0, legsExcercises, legsTwentySecondExcerciseImages);
		
	} // End of Main() constructor
	
	/*
	 * First center pane
	 */
	
	public static StackPane firstCenterPane(){
		
		Image theRock2 = new Image("resources/TheRock2.jpg");
		ImageView viewTheRock2 = new ImageView();
		viewTheRock2.setImage(theRock2);
		
		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER);
		stackPane.setPadding(new Insets(20, 20, 20, 20));
		stackPane.getChildren().add(viewTheRock2);
		
		return stackPane;
		
	} // End of firstCenterPane() method
	
} // End of Main class




























