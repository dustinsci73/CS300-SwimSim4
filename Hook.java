	/**
	 * The Hook class places the hook object into the display. Each time the update method is run, 
	 * it moves the position of the hook up the screen, moving faster at the rate of the given formula.
	 * Additionally, the hook class will determine the position of the hook object based on where the user
	 * clicks on the screen, placing the hook at x-value of the click and the bottom of the screen. 
	 * Finally, it will try to catch a fish object, based on the tryToCatch method, which will determine if 
	 * the fish object is within 40 units of the hook object.
	 * 
	 * @author Dustin Li
	 * @version 1.0
	 */
public class Hook {
	private int positionX;
	private int positionY;
	private PApplet processing;
	private PImage simImage;
	
	/**
	 * Generates random positions for objects to be placed with the limits of the tank size. This is the
	 * default constructor.
	 * 
	 * @param reference variable of type PApplet that will be initializing the private PApplet variable of 
	 * 				    the Hook class
	 */
	public Hook (PApplet reference) {
		processing = reference;
		simImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
		this.positionX = Utility.randomInt(processing.width);
		this.positionY = Utility.randomInt(processing.height);
	}
	
	/**
	 * Determines the position of our hook object based off of the values passed to it. This is the 
	 * overloaded constructor.
	 * 
	 * @param reference variable of type PApplet that will be initializing the private PApplet variable of 
	 * 				    the Hook class
	 * @param x         the x-position for the hook
	 * @param y         the y-position for the hook
	 */
	public Hook (PApplet reference, int x, int y) {
		processing = reference;
		simImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
		this.positionX = x; 
		this.positionY = y; 
	}
	
	/**
	 * Updates the position of the hook, progressing at a faster rate with each update
	 */
	public void update() { 
		processing.image(simImage, positionX, positionY);
		processing.line(positionX + 5, positionY, positionX + 5, 0);
		if (positionY > 0) {
			positionY = positionY -(processing.height + 50 - positionY)/50; //formula for progressing rate
		}
		else {
			positionY = processing.height;
		}
	}	
	
	/**
	 * Positions the hook based on where a mouse click is detected
	 * 
	 * @param mouseX x position of the detected mouse click
	 * @param mouseY y position of the detected mouse click
	 */
	public void handleClick(int mouseX, int mouseY) {
		positionX = mouseX;
		positionY = 	processing.height;	
	}
	
	/**
	 * Determines if the position of the fish object is with 40 pixels of the hook. If so,
	 * the method getCaught will be called, resetting the fish to the left side of the screen 
	 * 
	 * @param fish object of type fish that the hook object is trying to catch
	 */
	public void tryToCatch(Fish fish) {
		float distance = fish.distanceTo(positionX, positionY);
		if (distance <= 40) {
			fish.getCaught();
		}
	}
}
