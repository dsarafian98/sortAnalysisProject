// Import statements go here.  For example,
// import java.awt.Color;
import java.util.ArrayList;
// import java.util.Random;

/**
 *  Project 1: Timing Sorts<br>
 *
 *  This class will provide methods for each sort.
 *  
 *  Invariants and sorts created based on pseudocode given in Cormen, Leiserson, Rivest, and Stein's 
 *  textbook Introduction to Algorithms, 3rd edition.
 *
 *  <br> <br>
 *  Created: <br>
 *    27 January 2018, Danielle Sarafian<br>

 *  Modifications: <br>
 *    30 January 2018, Danielle Sarafian, update merge method<br>
 *    31 January 2018, Danielle Sarafian, update merge method [with assistance from Tim Rutledge]<br>
 *    2 February 2018, Danielle Sarafian, update heap sort methods<br>
 *    4 February 2018, Danielle Sarafian, update heap sort methods<br>
 *    5 February 2018, Danielle Sarafian, update heap sort methods, write bubble sort <br>
 *    11 February 2018, Danielle Sarafian, add assert statements for insertion, merge, and heap sorts
 *    										add method to reverse a list<br>
 *    12 February 2018, Danielle Sarafian, add assert statements for bubble sort<br>
 *
 *  @author Danielle Sarafian
 *  @version 12 February 2018
 */
public class Sort<T extends Comparable<T>>
{
	// State: instance variables and shared class variables go here.
	Debug debug = new Debug();

	// Constructors

	/**
	 * Constructs a new object of this class.
	 * 
	 * 	@param	a list to sort
	 */
	public Sort()
	{
		debug.turnOn();
	}

	// Methods

	/**
	 * This method will perform a merge sort by separating the ArrayList
	 * into smaller parts and sorting, then merging those parts together 
	 * and resorting.
	 * 
	 * 		@param	aList	the ArrayList to be sorted
	 * 		@param	bottom	the bottom index in the ArrayList
	 * 		@param	top		the top index in the ArrayList
	 *      @return the sorted list
	 */
	public ArrayList<T> mergeSort(ArrayList<T> aList, int bottom, int top)
	{
		// check the whole list
		if (bottom < top)
		{
			// find the midpoint
			double midpoint = Math.floor((bottom+top)/2);
			int intMid = (int) midpoint;

			// recursively call mergeSort for first and second halves of list
			mergeSort(aList, bottom, intMid);
			mergeSort(aList, intMid+1, top);

			// merge lists
			return merge(aList, top, bottom, intMid);
		}
		return null;
	}


	/**
	 * Merges and sorts 2 ArrayLists
	 * 
	 * 		@param	aList	the ArrayList to be sorted
	 *      @param  top		index of the top of the list
	 *      @param	bottom	index of the bottom of the list
	 *      @param	mid		index of the middle of the list
	 *      @return the merged ArrayLists
	 */
	private ArrayList<T> merge(ArrayList<T> aList, int top, int bottom, int mid)
	{	

		// find the size of each half
		int lengthLeft = mid-bottom+1;
		int lengthRight = top-mid;
		int index = bottom;

		// make an arraylist to represent each half
		ArrayList<T> leftSide = new ArrayList<T>(lengthLeft);
		ArrayList<T> rightSide = new ArrayList<T>(lengthRight);

		// populate each of the new arraylists
		for (int index2 = 0; index2 < lengthLeft; index2++)
		{
			leftSide.add(index2, aList.get(bottom+index2));
		}
		for (int index2 = 0; index2 < lengthRight; index2++)
		{
			rightSide.add(index2, aList.get(mid+index2+1));
		}		

		// starting indices for each of the new arraylists
		int leftIndex = 0;
		int rightIndex = 0;

		// vacuously true
		assert(mergeAssert(aList, bottom, index, leftSide, rightSide, leftIndex, rightIndex));

		while ((leftIndex < leftSide.size() && rightIndex < rightSide.size()))
		{
			assert(mergeAssert(aList, bottom, index, leftSide, rightSide, leftIndex, rightIndex));
			if (leftSide.get(leftIndex).compareTo(rightSide.get(rightIndex))<=0)
			{		
				// set values in ArrayList
				aList.set(index, leftSide.get(leftIndex));

				// increase left index
				leftIndex++;
			}
			else
			{			
				//set values in ArrayList
				aList.set(index, rightSide.get(rightIndex));

				//increase right index
				rightIndex++;
			}
			// increase index
			index++;
		}		

		// fill ArrayList if not all the values from the right ArrayList were added
		while ((rightIndex)<rightSide.size())
		{
			// add value to ArrayList of all values
			aList.set(index, rightSide.get(rightIndex));

			// increase right index
			rightIndex++;

			// increase index counter
			index++;
		}
		// fill ArrayList if not all the values from the left ArrayList were added
		while ((leftIndex)<leftSide.size())
		{
			// add value to ArrayList of all values
			aList.set(index,  leftSide.get(leftIndex));

			// increase left index
			leftIndex++;

			// increase index counter
			index++;
		}

		// check invariant before returning
		assert(mergeAssert(aList, bottom, index, leftSide, rightSide, leftIndex, rightIndex));

		return aList;
	}

	/**
	 * This method will perform an insertion sort on the data.
	 * 
	 * 		@param	list the ArrayList to be sorted
	 * 		@return the sorted ArrayList
	 */
	public ArrayList<T> insertionSort(ArrayList<T> list)
	{
		// vacuously true because nothing has been sorted
		//assert(insertionAssert(list, 0));

		for (int index = 1; index < list.size(); index++)
		{
			// hold the current value in a variable
			T temp = list.get(index);
			int prevIndex = index-1;
			int otherIndex = index;

			// check if it is smaller than the previous value
			while (prevIndex >=0 && temp.compareTo(list.get(prevIndex)) <= 0)
			{
				// set the previous index
				list.set(otherIndex, list.get(prevIndex));
				prevIndex--;
				otherIndex--;
			}
			list.set(otherIndex, temp);
			
			//assert(insertionAssert(list, index));
		}
		// should be true because the entire list should be sorted
		//assert(insertionAssert(list, list.size()-1));

		return list;
	}

	/**
	 * This method will find the left child of a given node in the max heap
	 * 
	 * 		@param	index	the index of the element in the heap to find the left child of
	 * 		@return	the index in the ArrayList where the left child node is
	 */
	private int left(int index)
	{
		return 2*index;
	}

	/**
	 * This method will find the right child of a given node in the max heap
	 * 
	 * 		@param	index	the index of the element in the heap to find the right child of
	 * 		@return	the index in the ArrayList where the right child node is
	 */
	private int right(int index)
	{
		return ((2*index)+1);
	}

	/**
	 * This method will swap the items given as parameters
	 * 
	 * 		@param	list	the ArrayList that contains the values that should be switched
	 *		@param	index1	the index of one of the values that is being switched
	 *		@param	index2	the index of the other value that is being switched
	 *		@return	the ArrayList with the swapped values
	 */
	private ArrayList<T> swap (ArrayList<T> list, int index1, int index2)
	{
		// set item at index1 to a temp
		T temp = list.get(index1);

		// set item at index2 in index1
		list.set(index1, list.get(index2));

		// set temp at index2
		list.set(index2, temp);

		return list;
	}

	/**
	 * This method will make sure that the heap is a max heap and if it isn't,
	 * the method will rearrange the terms to make it a max heap
	 * 
	 * 		@param	list		ArrayList to sort
	 * 		@param	index		index to make max heap from
	 * 		@param	endIndex	the last index to use in the heap
	 * 		@return	an ArrayList that represents a max heap
	 */
	private ArrayList<T> maxHeapify(ArrayList<T> list, int index, int endIndex)
	{
		// get indices of left and right children
		int left = left(index);
		int right = right(index);

		// set largest to index
		int largest = index;

		// check if largest is smaller than the left child

		if (left <= endIndex && (list.get(largest).compareTo(list.get(left))==-1))
		{
			// set largest equal to the left child
			largest = left;
		}

		// check if largest is smaller than the right child
		if ((right <= endIndex) && (list.get(largest).compareTo(list.get(right))==-1))
		{
			// set largest equal to the right child
			largest = right;
		}

		// if the item at index isn't the largest
		if (largest != index)
		{
			// swap the item at index with the largest item
			list = swap(list, index, largest);

			// recursively check that the next heap is a max heap
			list = maxHeapify(list, largest, endIndex);
		}
		return list;
	}

	/**
	 * Makes the ArrayList a max heap
	 * 
	 * 		@param	list		the ArrayList to make a max heap
	 * 		@param	endIndex	the last index to use in the heap
	 * 		@return	the ArrayList as a max heap
	 */
	private ArrayList<T> buildMaxHeap(ArrayList<T> list, int endIndex)
	{
		int heapSize = list.size();
		
		// vacuously true
		//assert(heapAssert(list, heapSize));

		// make sure the ArrayList represents a max heap
		while (heapSize > 0)
		{
			//assert(heapAssert(list, heapSize));
			maxHeapify(list, heapSize-1, endIndex);
			heapSize--;
		}		
		
		// check invariant before returning list
		//assert(heapAssert(list, heapSize));
		return list;
	}

	/**
	 * Takes an unsorted list and sorts it
	 * 
	 * 		@param	list	the ArrayList to sort
	 * 		@return	the sorted ArrayList
	 */
	public ArrayList<T> heapSort(ArrayList<T> list)
	{
		// set variable for list size
		int size = list.size();

		// set variable to count index
		int i = size-1;

		// create a max heap with the list
		buildMaxHeap(list, i);

		while (i > 0)
		{
			// switch largest number (located at beginning index)
			// and smallest number (located at ending index)
			list = swap(list, 0, i);

			// remove A[n]
			i--;

			// reconfigure into another max heap
			list = maxHeapify(list, 0, i);			
		}
		return list;
	}

	/**
	 * Performs a bubble sort on an unsorted ArrayList
	 * 
	 * 		@param	list	the ArrayList to sort
	 * 		@return	the sorted ArrayList
	 */
	public ArrayList<T> bubbleSort(ArrayList<T> list)
	{
		// vacuously true
		//assert(bubbleAssert(list, 0));

		// go through every previous element in the ArrayList
		for (int i = 0; i < list.size()-1; i++)
		{
			// go through every element in the ArrayList
			for (int j = list.size()-1; j >= i+1; j--)
			{
				// check if the previous element is larger than the current element
				if (list.get(j).compareTo(list.get(i))==-1)
				{
					// swap the elements
					list = swap(list, j, (j-1));
				}
				
				// check invariant
				//assert(bubbleAssert(list, j));
			}
			
			// check invariant
			//assert(bubbleAssert(list, i));
		}
		
		// check invariant before returning sorted list
		//assert(bubbleAssert(list, list.size()-1));
		return list;
	}

	/**
	 * This method takes a list sorted in increasing order and
	 * puts it in reverse order.
	 * 
	 * 		@param list		a list sorted in increasing order
	 * 		@return	a list sorted in reverse order
	 */
	public ArrayList<T> reverseList(ArrayList<T> list)
	{
		// create a space to put the reversed list
		ArrayList<T> newList = new ArrayList<T>();


		for (int index = list.size()-1; index >= 0; index--)
		{
			// put the item from the old list in the new reversed list
			newList.add(list.get(index));
		}
		return newList;
	}

	/**
	 * The invariant for insertion sort.
	 * 
	 * The invariant is that at the start of each iteration of the for loop,
	 * the subarray list[0...index-1] consists of elements from list[0...index-1], but sorted.
	 * 
	 * 		@param	list	the list that insertion sort is being performed on
	 * 		@param	index	the current index to check the invariant for
	 * 		@return	true if the invariant passes, otherwise, false
	 */
	private boolean insertionAssert(ArrayList<T> list, int index)
	{
		int count = 0;
		T key;

		while (count < index)
		{
			// set key to item before index
			key = list.get(count);

			// check that the next item is greater than this item
			if (key.compareTo(list.get(count+1)) > 0)
			{
				return false;
			}

			// increase counter
			count ++;
		}
		return true;
	}

	/**
	 * The invariant for merge sort.
	 * 
	 * The invariant is that at the start of the while loop, the subarray
	 * list[bottom...index-1] contains the index-bottom smallest elements of 
	 * leftSide[1...leftSide.size()] and rightSide[1...rightSide.size()], in sorted order.
	 * leftSide[leftIndex] and rightSide[rightIndex] are the smallest elements in their respective
	 * arrays that have not been put into list.
	 * 
	 * 		@param	list		the ArrayList that is being sorted
	 * 		@param	bottom		the bottom index to check
	 * 		@param	index		the top index to check
	 * 		@param	leftSide	the ArrayList for the left side
	 * 		@param	rightSide	the ArrayList for the right side
	 * 		@param	leftIndex	the index to begin checking at for the left side ArrayList
	 * 		@param	rightIndex	the index to begin checking at for the right side ArrayList
	 * 		@return	true if the invariant passes, otherwise, false
	 */
	private boolean mergeAssert(ArrayList<T> list, int bottom, int index, ArrayList<T> leftSide, ArrayList<T> rightSide, int leftIndex, int rightIndex)
	{
		T key;

		for (int i=bottom; i<index-1; i++)
		{
			// get previous item
			key = list.get(i+1);

			// check if previous item is smaller than current item
			if (key.compareTo(list.get(i)) < 0)
			{				
				System.out.println("checking list");
				return false;
			}
		}

		// check that all items in the leftSide ArrayList are larger than the first item in it
		for (int j = leftIndex; j<leftSide.size(); j++)
		{
			key = leftSide.get(leftIndex);
			if (key.compareTo(leftSide.get(j))>0)
			{
				System.out.println("left side");
				return false;
			}
		}

		// check that all items in the rightSide ArrayList are larger than the first item in it
		for (int k = rightIndex; k<rightSide.size(); k++)
		{
			key = rightSide.get(rightIndex);
			if (key.compareTo(rightSide.get(k))>0)
			{
				System.out.println("right side");
				return false;
			}
		}
		return true;
	}

	/**
	 * The invariant for heap sort.
	 * 
	 * The invariant is that at the start of each iteration of the while loop, each node
	 * indicated by heapSize is the root of a max-heap.
	 * 
	 * 		@param list			the list being sorted
	 * 		@param heapSize		the index of the root of the heap
	 * 		@return true if the invariant passes, otherwise, false 
	 */
	private boolean heapAssert(ArrayList<T> list, int heapRoot)
	{
		int leftChildIndex = left(heapRoot);
		int rightChildIndex = right(heapRoot);

		if (leftChildIndex < list.size())
		{
			T leftChild = list.get(leftChildIndex);
			
			// check that left child is smaller than parent
			if (leftChild.compareTo(list.get(heapRoot))>0)
			{
				return false;
			}
		}
		
		if (rightChildIndex < list.size())
		{
			T rightChild = list.get(rightChildIndex);
			
			// check that right child is smaller than parent
			if (rightChild.compareTo(list.get(heapRoot))>0)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * The invariant for bubble sort.
	 * 
	 * The invariant for this sort is that list[j] <= list[j+1]
	 * for all items in the ArrayList until index i.
	 * 
	 * 		@param	list	the ArrayList being sorted
	 * 		@param	i		max index
	 *	 	@return	true if the invariant passes, otherwise, false
	 */
	private boolean bubbleAssert(ArrayList<T> list, int i)
	{
		for (int count = 0; count < i; count++)
		{
			if (list.get(count).compareTo(list.get(count+1)) > 0)
			{
				return false;
			}
		}
		return true;
	}
}