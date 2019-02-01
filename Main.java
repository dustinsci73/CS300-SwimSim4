//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Swim Simulation Project 4
// Files:           Main.java, SwimSimulation.java, Fish.java, Food.java, Hook.java
// Course:          (CS300, Fall, 2017)
//
// Author:          (Dustin Li)
// Email:           (dli284@wisc.edu)
// Lecturer's Name: (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (Brennan Fife)
// Partner Email:   (bfife@wisc.edu)
// Lecturer's Name: (Gary Dahl)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _x_ Write-up states that pair programming is allowed for this assignment.
//   _x_ We have both read and understand the course Pair Programming Policy.
//   _x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         (NONE)
// Online Sources:  (NONE)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * @author Dustin Li
 * @version 1.0
 * Creates and runs the whole program
 */
public class Main {
	private static SwimSimulation sim;
	/**
	 * Calls the Utility object to open the GUI and start the simulation.
	 */
	public static void main(String[] args) {
	// TODO Auto-generated method stub
			//Data data = new Data();
			//data.tank = new char[32][8];
			//setup(data);
			//while(true) {
			//    update(data);
			//}
		Utility.startSimulation();		
	}
	/**
	 * Sets up dimensions of the tank and generates random positions of objects 
	 * to be placed in the tank.
	 * 
	 * @param data the data object that contains all of the arrays that are utilized
	 * in the method
	 */
	public static void setup(Data data)	{
		sim = new SwimSimulation(data.processing);
	}
	/**
	 * Updates the GUI by repeatedly calling other methods. 
	 * 
	 * @param data The data object that contains all of the arrays that are utilized
	 * in the method
	 */
	public static void update(Data data) {
		
		
		sim.update();
	}
	/**
	 * Fills the tank with the water.
	 * 
	 * @param tank The array to be filled with chars
	 * @param water The char that fills the array
	 */
	public static void fillTank(char[][] tank, char water) {
		for (int i = 0; i < tank.length; i++) {
		    for (int j = 0; j < tank[i].length; j++) {
			   	   tank[i][j] = water;
			   } 
		}
	}
	/**
	 * Draws out the tank in the console.	 
	 * 
	 * @param tank Array of chars to be drawn.
	 */
	public static void renderTank(char[][] tank) {
		for (int i = 0; i < tank.length; i++) {
			for (int j = 0; j < tank[i].length; j++) {
				System.out.print(tank[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * Records the position of the user's cursor when a click is detected.
	 * 
	 * @param data Data object which contains the method for detecting the user's click 
	 * @param mouseX The x position of the user's cursor
	 * @param mouseY The y position of the user's cursor
	 */
	public static void onClick(Data data, int mouseX, int mouseY) {
		sim.handleClick(mouseX, mouseY);
		//data.hookPositions[0][0] = mouseX;
		//data.hookPositions[0][1] = data.processing.height - 1;
	}
}