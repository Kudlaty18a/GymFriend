package application;

public class BackExcercises extends CollectionOfExcercises{
	
	public void addTheExcercise(ExcerciseView excerciseView){
		
		listOfExcercises.add(excerciseView);
		
	} // End of addTheExcercise(ExcerciseView excerciseView) method
	
	public ExcerciseView getTheExcercise(int excerciseNumber){
		
		excercise = listOfExcercises.get(excerciseNumber - 1);
		
		return excercise;
		
	} // End of getTheExcercise(int excerciseNumber) method
	
} // End of BackExcercises class
