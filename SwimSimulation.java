
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;

/**
 * Creates and runs the simulation in the tank
 * @author Dustin Li
 *
 */
public class SwimSimulation 
{
	private PApplet processing;
	private Fish [] fishPositions;
	private Food [] foodPositions;
	private Hook [] hookPositions;
	String[] strings;
	Fish fish1;
	Fish fish2;
	Fish fish3;
	Fish fish4;
	Food food1;
	Food food2;
	Food food3;
	Food food4;
	Food food5;
	Food food6;
	Hook hook1;
	
	/**
	 * Creates the fish, food, and hook objects to be placed in the tank and fills the fishPostions,
	 * foodPositions, and hookPositions arrays with these objects
	 * 
	 * @param process variable of type PApplet that will be initializing the private PApplet variable of 
	 * 				   the SwimSimulation class
	 */
	public SwimSimulation(PApplet process) 
	{
		
		this.processing = process;
		try 
		{
			getData();
		}
		catch (RuntimeException e)
		{
			fish1 = new Fish(processing);
			fish2 = new Fish(processing);
			fish3 = new Fish(processing);
			fish4 = new Fish(processing);
			food1 = new Food(processing);
			food2 = new Food(processing);
			food3 = new Food(processing);
			food4 = new Food(processing);
			food5 = new Food(processing);
			food6 = new Food(processing);
			hook1 = new Hook(processing);
			this.fishPositions = new Fish[] {fish1, fish2, fish3, fish4};
			this.foodPositions = new Food[] {food1, food2, food3, food4, food5, food6};
			this.hookPositions = new Hook[] {hook1};
		}
		
	}
	/**
	 * Updates the screen by moving all of the objects in the tank in their specified directions. Also calls
	 * the tryToEat and tryToCatch methods to see if the objects are within 40 units of each other.
	 */
	public void update()
	{
		processing.background(0,255,255);
		for (int i = 0; i < fishPositions.length; i++) 
		{
			for (int j = 0; j < foodPositions.length; j++)
			{
				((Fish) fishPositions[i]).tryToEat(foodPositions[j]);
			}
		}
		for (int i = 0; i < hookPositions.length; i++) 
		{
			for (int j = 0; j < fishPositions.length; j++)
			{
				((Hook) hookPositions[i]).tryToCatch(fishPositions[j]);
			}
		}
		for (int i = 0; i < fishPositions.length; i++) 
		{
			((Fish) fishPositions[i]).update();
		}
		for (int i = 0; i < foodPositions.length; i++) 
		{
			((Food) foodPositions[i]).update();
		}
		for (int i = 0; i < hookPositions.length; i++) 
		{
			((Hook) hookPositions[i]).update();
		}
	}
	/**
	 * Calls the handle click method of the Hook class
	 * 
	 * @param mouseX x position of the location of the mouse click
	 * @param mouseY y position of the location of the mouse click
	 */
	public void handleClick(int mouseX, int mouseY) 
	{
		for (int i = 0; i < hookPositions.length; i++) 
		{
			((Hook) hookPositions[i]).handleClick(mouseX, mouseY);
		}	
	} 
	/**
	 * Reads the lines of the .ssf files passed to it, then randomly chooses one of the .ssd files contained 
	 * within the .ssf file, then reads the .ssd file and implements the positions of each object into the
	 * program by creating new fish, food, and hook objects with the positions in the .ssd file, and adds
	 * the to the foodPositions, hookPositions, and fishPositions arrays
	 * 
	 * Had issues with dealing with the whitespace between lines of the .ssd files
	 * Program was throwing a NullPointerException error
	 */
	private void getData()
	{
		
		try 
		{
		  	try 
			{
				Files.readAllBytes(Paths.get("FileOptions.ssf"));
			}
			catch (FileNotFoundException e) //checks to see if the FileOptions.ssf file exists
			{
				System.out.print("WARNING: Could not find or open the FileOptions.ssf file.");
				throw new RuntimeException(); //throws a runtime exception which will be caught in the 
										     //SwimSimulaton Constructor
			}
			String line = new String(Files.readAllBytes(Paths.get("FileOptions.ssf"))); 
			String[] data = line.split(";");
			int x = Utility.randomInt(data.length); 
			String xFile = data[x]; //randomly chooses an .ssd file name
			String xFileTwo = xFile.trim();
			try
			{
				Files.readAllLines(Paths.get(xFileTwo));
			}
			catch (FileNotFoundException e) //checks to see if the .ssd file chosen exists
			{
				System.out.println("WARNING: Could not find or open the " + xFileTwo + "file.");
				throw new RuntimeException(); //throws a runtime exception which will be caught in the 
			                                  //SwimSimulaton Constructor
			}
			List<String> lines = Files.readAllLines(Paths.get(xFileTwo));
			int counter = 0;
			String objTwo = "";
			for (int i = 0; i < lines.size(); i++)
			{
				String[] positions;
				String trim = lines.get(i).trim();
				if (trim.contains(":"))  //differentiates between a line with an object or position
				{
					counter = 0;
					String[] obj = lines.get(i).split(":");
					objTwo = obj[0].trim().toUpperCase();
					String objOne = obj[1].trim();
					if (objTwo.equals("HOOK")) //differentiates between different objects
					{
						hookPositions = new Hook[Integer.parseInt(objOne)];
					}
					else if (objTwo.equals("FISH"))
					{
						fishPositions = new Fish[Integer.parseInt(objOne)];
					}
					else if (objTwo.equals("FOOD")) 
					{
						foodPositions = new Food[Integer.parseInt(objOne)];	
					}
				}
				else if (trim.contains(",")) 
				{
					if (objTwo.equals("HOOK"))
					{
						positions = trim.split(","); //splits the x and y values of the position
						int xPos = Integer.parseInt(positions[0].trim());
						int yPos = Integer.parseInt(positions[1].trim());
						hookPositions[counter] = new Hook(processing, xPos, yPos);	//places new hook object 
					                                                            	//into the array
					}
					if (objTwo.equals("FISH"))
					{
						positions = trim.split(",");
						int xPos = Integer.parseInt(positions[0].trim());
						int yPos = Integer.parseInt(positions[1].trim());
						fishPositions[counter] = new Fish(processing, xPos, yPos);
					}
					if (objTwo.equals("FOOD"))
					{
						positions = trim.split(",");
						int xPos = Integer.parseInt(positions[0].trim());
						int yPos = Integer.parseInt(positions[1].trim());
						foodPositions[counter] = new Food(processing, xPos, yPos);
					}	
					counter++;
				}
				else  //checks to see if the current line is the whitespace
			    {
					continue; //skips the whitespace				
				}
			}	
		}
		catch (IOException e) { }
//		catch ()
	}	
}