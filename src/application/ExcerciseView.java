package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExcerciseView {

	private String nameOfExcercise;
	private int difficultyLevel;
	private int howManyGifs;
	private ImageView[] viewTheGifs;
	private List<Image> theGifs;
	
	public void setNameOfExcercise(String nameOfExcercise){
		
		this.nameOfExcercise = nameOfExcercise;
		
	} // End of setNameOfExcercise(String nameOfExcercise) method
	
	public void setDifficultyLevel(int difficultyLevel){
		
		this.difficultyLevel = difficultyLevel;
		
	} // End of setDifficultyLevel(int difficyltyLevel) method
	
	public void setGifs(Image... gifs){
		
		theGifs = new ArrayList<>(); // List to hold objects (Images)
		
		/*
		 * Checks how many arguments the method takes
		 */
		
		int lengthOfMethodArguments = 0;
		
		for(Image img : gifs){
			
			theGifs.add(img);
			lengthOfMethodArguments++;
			
		} // End of for
		
		howManyGifs = lengthOfMethodArguments;
		
	} // End of setGifs(Image... gifs) method
	
	public String getNameOfExcercise(){
		
		return nameOfExcercise;
		
	} // End of getNameOfExcercise() method
	
	public int getDifficultyLevel(){
		
		return difficultyLevel;
		
	} // End of getDifficultyLevel() method
	
	public int getHowManyGifs(){
		
		return howManyGifs;
		
	} // End of getHowManyGifs() method
	
	public ImageView[] getGifs(){
		
		viewTheGifs = new ImageView[howManyGifs];
		
		for(int i = 0; i < howManyGifs; i++){
			
			viewTheGifs[i] = new ImageView();
			viewTheGifs[i].setImage(theGifs.get(i));
			
		} // End of for
		
		return viewTheGifs;
		
	} // End of getGifs() method
	
} // End of ExcerciseView class
