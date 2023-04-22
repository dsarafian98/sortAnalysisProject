// Import statements go here.  For example,
// import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *  Project 1: Timing Sorts<br>
 *
 *  This class will generate the random numbers needed for the sorts.
 *  It will also include methods that will reorganize the data when necessary,
 *  such as reversing it.
 *
 *  <br> <br>
 *  Created: <br>
 *    27 January 2018, Danielle Sarafian<br>
 *     With assistance from:  [people who helped (including instructor/TAs)]<br>
 *  Modifications: <br>
 *     [the date], [your name(s)], [the reason]<br>
 *
 *  @author Danielle Sarafian   [with assistance from... or working alongside ...]
 *  @version 27 January 2018
 */
public class Data<T>
{
	// State: instance variables and shared class variables go here.
	ArrayList<Integer> list;
	Debug debug = new Debug();

	// Constructors

	/**
	 * Constructs a new object of this class.
	 * 
	 *      @param   desiredLength    the desired number of items in the ArrayList
	 */
	public Data(int desiredLength)
	{
		debug.turnOff();
		list = new ArrayList<Integer>();

		// create random number generator
		Random generator = new Random();
		int randNum;

		// create list
		for (int i=0; i<desiredLength; i++)
		{
			// generate random number
			randNum = generator.nextInt(100);

			// add Integer to list
			list.add(randNum);
		}

	}

	// Methods

	/**
	 * Returns the randomized ArrayList
	 * 
	 *      @return the ArrayList
	 */
	public ArrayList<Integer> getList()
	{
		return list;
	}

	/**
	 * Reverses the elements in a list
	 * 
	 *      @param  orderedList an ArrayList in order
	 */
	public ArrayList<T> reverseList (ArrayList<T> orderedList)
	{
		ArrayList<T> reversedList = new ArrayList<T>();
		for (int index = orderedList.size()-1; index>=0; index--)
		{
			reversedList.add(orderedList.get(index));
		}
		return reversedList;
	}
	
	/**
	 * Returns the bottom element in the ArrayList.
	 * 
	 * @return	bottom element in the ArrayList
	 */
	public Integer getBottom()
	{
		return (list.get(0));
	}
	
	/**
	 * Returns the top element in the ArrayList.
	 * 
	 * @return	top element in the ArrayList
	 */
	public Integer getTop()
	{
		return (list.get(list.size()-1));
	}
}