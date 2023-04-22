// Import statements go here.  For example,
// import java.awt.Color;
import java.util.ArrayList;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
// import java.util.Random;

/**
 *  Project 1: Timing Sorts<br>
 *
 *  The <code>Simulation</code> class provides a main method
 *  for a program that creates a list of random elements, then
 *  sorts the elements using different sorting methods and times them.
 *
 *  <br> <br>
 *  Created: <br>
 *     27 January 2018, Danielle Sarafian<br>
 *     With assistance from:  [people who helped (including instructor/TAs)]<br>
 *  Modifications: <br>
 *    	30 January 2018, Danielle Sarafian, call mergeSort method<br>
 *    	2 February 2018, Danielle Sarafian, call heapSort method<br>
 *    	5 February 2018, Danielle Sarafian, call bubbleSort method, implement timing for methods, write output to a file<br>
 *    	6 February 2018, Danielle Sarafian, update output written to file<br>
 *    	12 February 2018, Danielle Sarafian, update output written to file<br>
 *
 *  @author Danielle Sarafian
 *  @version 12 February 2018
 */
public class Simulation 
{
	/**
	 *  The main function initiates execution of this program.
	 *    @param    String[] args not used in this program
	 **/
	public static void main(String[] args) throws IOException
	{
		System.out.println ("Welcome to Project 1.");
		System.out.println("Danielle Sarafian");

		Sort sort = new Sort();
		Data data;
		
		// create list of different lengths to test
		ArrayList<Integer> dataLengths = new ArrayList<Integer>();
		dataLengths.add(100000);
		dataLengths.add(200000);
		dataLengths.add(300000);
		dataLengths.add(400000);
		dataLengths.add(500000);
		dataLengths.add(600000);
		dataLengths.add(700000);
		dataLengths.add(800000);
		dataLengths.add(900000);
		dataLengths.add(1000000);



		PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\owner\\Documents\\AaSchool\\CS215\\projects\\Project1DanielleSarafian\\heapSortSortedRedo2.txt"));

		// create list for the times it takes to sort different sizes
		ArrayList<Long> insertionTimes = new ArrayList<Long>();
		ArrayList<Long> mergeTimes = new ArrayList<Long>();
		ArrayList<Long> heapTimes = new ArrayList<Long>();
		ArrayList<Long> bubbleTimes = new ArrayList<Long>();

		
		for (Integer i : dataLengths)
		{
			data = new Data(i);

			ArrayList<Integer> list = data.getList();
			
			// sort list and reverse list
			ArrayList<Integer> sortedList = sort.heapSort(list);
			//ArrayList<Integer> reversed = sort.reverseList(sortedList);

			/*// time for insertion sort
			long insertionStartTime = System.nanoTime();
			sort.insertionSort(sortedList);
			long insertionEndTime = System.nanoTime();
			long insertionDuration = insertionEndTime - insertionStartTime;
			insertionTimes.add(insertionDuration);
			System.out.println("insertion sort time: " + insertionDuration);

			// time for merge sort
			long mergeStartTime = System.nanoTime();
			sort.mergeSort(sortedList, 0, sortedList.size()-1);
			long mergeEndTime = System.nanoTime();
			long mergeDuration = mergeEndTime-mergeStartTime;
			mergeTimes.add(mergeDuration);
			System.out.println("merge sort time: " + mergeDuration);*/

			// time for heap sort
			long heapStartTime = System.nanoTime();
			sort.heapSort(sortedList);
			long heapEndTime = System.nanoTime();
			long heapDuration = heapEndTime-heapStartTime;
			heapTimes.add(heapDuration);
			System.out.println("heap sort time: " + heapDuration);

			// time for bubble sort
			/*long bubbleStartTime = System.nanoTime();
			sort.bubbleSort(sortedList);
			long bubbleEndTime = System.nanoTime();
			long bubbleDuration = bubbleEndTime-bubbleStartTime;
			bubbleTimes.add(bubbleDuration);
			System.out.println("bubble sort time: " + bubbleDuration);*/			
		}

		// print results into a txt file with tab deliminators
		out.println("SORTS \t NUM ELEMENTS \t TIME");
		/*for (int i = 0; i < insertionTimes.size(); i++)
		{
			out.println("INSERTION \t" + dataLengths.get(i) + "\t" + insertionTimes.get(i));
		}
		for (int i = 0; i < mergeTimes.size(); i++)
		{
			out.println("MERGE \t" + dataLengths.get(i) + "\t" + mergeTimes.get(i));
		}*/
		for (int i = 0; i < heapTimes.size(); i++)
		{
			out.println("HEAP \t" + dataLengths.get(i) + "\t" + heapTimes.get(i));
		}
		/*for (int i = 0; i < bubbleTimes.size(); i++)
		{
			out.println("BUBBLE \t" + dataLengths.get(i) + "\t" + bubbleTimes.get(i));
		}*/
		out.close();


		System.out.println ("Program done.");

	}//end main

}//end class