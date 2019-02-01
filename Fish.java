	/**
	 * The Fish class places the fish objects into the display. Each time the update method is run, 
	 * the position of the fish is updated by moving the screen to the right. Additionally, the fish class 
	 * will determine the distance of the x and y, using the formula to calculate distances between the two
	 * values. Finally, the getEaten when called, will position the food back at the top of the screen.
	 * 
	 * @author Dustin Li
	 * @version 1.0
	 */
public class Fish {
	private int positionX;
	private int positionY;
	private PApplet processing;
	private PImage simImage;
	/**
	 * Generates random positions for objects to be placed within the limits of the tank size. This is the
	 * default constructor.
	 * @param reference variable of type PApplet that will be initializing the private PApplet variable of 
	 * 				    the Fish class
	 */
	public Fish (PApplet reference) {
		processing = reference;
		simImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		this.positionX = Utility.randomInt(processing.width);
		this.positionY = Utility.randomInt(processing.height);
	}
	/**
	 * Generates random positions for objects to be placed within the limits of the tank size. This is the 
	 * overloaded constructor.
	 *
	 * @param reference variable of type PApplet that will be initializing the private PApplet variable of 
	 * 				    the Fish class
	 * @param x         the x-position of the fish object
	 * @param y 		    the y-position of the fish object
	 */
	public Fish (PApplet reference, int x, int y) {
		processing = reference;
		simImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		this.positionX = x; 
		this.positionY = y; 
	}
	
	/**
	 * Updates the position of the fish each time the method is called by moving the fish object to the 
	 * right one unit.
	 */
	public void update() { 
		processing.image(simImage, positionX, positionY);
		if (positionX < processing.width) {
			positionX = positionX + 1;
		}
		else {
			positionX = 0;
		}
	}		
	
	/**
	 * Determines if fish can eat the food object if the distance between them is less than 40 units
	 * 
	 * @param food the food object that will be used to measure the distance
	 */
	public void tryToEat(Food food) {
		float distance = food.distanceTo(positionX, positionY);
		if (distance <= 40) {
			food.getEaten();
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
		float distance = (float) Math.sqrt(Math.pow(x - positionX, 2) + Math.pow(y - positionY, 2));
		return distance; // sqrt((x2-x1)^2 + (y2-y1)^2)
	}		
	
	/**
	 * Resets the x position of the fish to 0, placing the object on left of the screen.
	 */
	public void getCaught() {
		positionX = 0;
	} 
}
