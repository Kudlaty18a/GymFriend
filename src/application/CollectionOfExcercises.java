package application;

import java.util.ArrayList;
import java.util.List;

public class CollectionOfExcercises {
		
	protected List<ExcerciseView> listOfExcercises;
	protected ExcerciseView excercise;
	
	protected CollectionOfExcercises(){
		
		listOfExcercises = new ArrayList<>();
		
	} // End of CollectionOfExcercises() constructor
	
	/*
	 * Creates method which add excercises to the list
	 */
	
	protected void addTheExcercise(ExcerciseView excerciseView){
		
		listOfExcercises.add(excerciseView);
		
	} // End of addTheExcercise(ExcerciseView excerciseView) method
	
	/*
	 * Creates method which return excercise
	 */
	
	protected ExcerciseView getTheExcercise(int excerciseNumber){
		
		excercise = listOfExcercises.get(excerciseNumber - 1);
		
		return excercise;
		
	} // End of getTheExcercise(int excerciseNumber) method
	
} // End of CollectionOfExcercises class
