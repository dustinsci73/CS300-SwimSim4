	/**
	 * The Food class places the food objects into the display. Each time the update method is run, 
	 * the position of the food is updated down and to the left of the screen. Additionally, the food class 
	 * will determine the distance between fish and food, using the formula to calculate distances between 
	 * two values. Finally, the getEaten when called, will position the food back at the top of the screen.
	 * 
	 * @author Dustin Li
	 * @version 1.0
	 */
public class Food {
	private int positionX;
	private int positionY;
	private PApplet processing;
	private PImage simImage;
	
	/**
	 * Generates random positions for food objects to be placed within the limits of the tank size. This is 
	 * the default constructor.
	 * 
	 * @param reference variable of type PApplet that will be initializing the private PApplet variable of 
	 * 				    the Food class
	 */
	public Food (PApplet reference) {
		processing = reference;
		simImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
		this.positionX = Utility.randomInt(processing.width);
		this.positionY = Utility.randomInt(processing.height);
	}
	
	/**
	 * Generates random positions for objects to be placed within the limits of the tank size. This is the 
	 * overloaded constructor.
	 * @param reference variable of type PApplet that will be initializing the private PApplet variable of 
	 * 				    the Food class
	 * @param x         the x-position for food
	 * @param y 		    the y-position for food
	 */
	public Food (PApplet reference, int x, int y) {
		processing = reference;
		simImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
		this.positionX = x;
		this.positionY = y;
	}
	
	/**
	 * Updates the position of the food object, moving the image on the display down one unit and to the 
	 * left one unit each time the update method is called.
	 */
	public void update() {  
		processing.image(simImage, positionX, positionY);
		if (positionX > 0) {
			positionX = positionX - 1;
		}
		else {
			positionX = processing.width;
		}	
		if (positionY < processing.height) {
			positionY = positionY + 1;
		}
		else { 
			positionY = 0;
		}
	}
	
	/**
	 * Determines the distance between the two coordinates based on the distance value formula
	 *
	 * @param x x position of the fish object
	 * @param y y position of the fish object
	 * @return the value that will be used to determine if the objects are within 40 units of each other.
	 */
	public float distanceTo(int x, int y) {
		//distance equation
		float distance = (float) Math.sqrt(Math.pow(x - positionX, 2) + Math.pow(y - positionY, 2));
		return distance; // sqrt((x2-x1)^2 + (y2-y1)^2)
		}		
	
	/**
	 * Resets y position of food to 0, placing the food object back to the top of the screen.
	 */
	public void getEaten() {
		positionY = 0;
	} 
}