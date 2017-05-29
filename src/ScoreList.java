/////////////////////////////////////////////////////////////////////////////
// Semester:         CS367 Spring 2017
// PROJECT:          Program 1
// FILE:             Config.java, GradeEstimator.java, GradeFileFormatException.java
//					 Score.java, ScoreIterator.java, ScoreIteratorADT.java, 
//					 ScoreList.java, ScoreListADT.java
//
// TEAM:    Team 57 import teamName
// Authors: Matthew Schmude, Xuezhan Yan, Kellie Stein, Shuo Sean Li, Da Chen, Andy Kempken
// Author1: Matthew Schmude, schmude@wisc.edu, 9074395576, Lec 002
// Author2: Xuezhan Yan, xyan56@wisc.edu, 9074973794, Lec 002
// Author3: Kellie Stein, kstein5@wisc.edu, 9075112731, Lec 002
// Author4: Shuo Sean Li, sli486@wisc.edu, 9074080509, Lec 002
// Author5: Da Chen, dchen95@wisc.edu, 9070275327, Lec 002
// Author6: Andy Kempken, akempken@wisc.edu, 9072543862, Lec 002
//
// ---------------- OTHER ASSISTANCE CREDITS 
// Persons: Identify persons by name, relationship to you, and email. 
// Describe in detail the the ideas and help they provided. 
// 
// Online sources: avoid web searches to solve your problems, but if you do 
// search, be sure to include Web URLs and description of 
// of any information you find. 
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * This is the container to collect all score objects in an array and use
 * numItems to track number of items in the array.This class also implement
 * ScoreListADT interface to provide necessary methods to edit the container.
 *
 * @author Team 57 import teamName
 */
public class ScoreList implements ScoreListADT {
	private Score[] items;
	private int numItems;

	/**
	 * This constructor initializes a new scorelist object with initializes a
	 * new Score type array with size of 10 and initializes numItems in
	 * container to be 0.
	 * 
	 */
	public ScoreList() {
		items = new Score[10];
		numItems = 0;
	}

	/** 
	 * Returns the number of Scores in the list or zero
	 * @return the number of scores in this list
	 */
	@Override
	public int size() {
		return numItems;
	}

	/** 
	 * Adds the score to the end of this list.
	 * 
	 * @param s a non-null Score to place as the last item in the list.
	 * @throws IllegalArgumentException
	 */
	@Override
	public void add(Score s) throws IllegalArgumentException {
		if (s == null)
			throw new IllegalArgumentException();
		// if full, expand
		if (items.length == numItems) {
			Score[] expitems = new Score[items.length * 2];
			for (int i = 0; i < items.length; i++) {
				expitems[i] = items[i];
			}
			items = expitems;
		}
		items[numItems] = s;
		numItems++;
	}

	/**
	 * Removes and returns the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public Score remove(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > items.length - 1)
			throw new IndexOutOfBoundsException();
		/*
		 * Score returnScore = items[i]; // handle only one items in array if
		 * (items.length == 1) { Score[] nullArray = new Score[10]; items =
		 * nullArray; numItems=0; return returnScore; } // shift others and
		 * override for (int j = i + 1; j < numItems; j++) { items[j - 1] =
		 * items[j]; }
		 */
		Score returnScore = items[i];
		Score[] newArray = new Score[numItems - 1];
		for (int j = 0; j < numItems; j++) {
			if (j < i) {
				newArray[j] = items[j];
			} else if (j > i) {
				newArray[j - 1] = items[j];
			} else {

			}

		}
		items = newArray;
		numItems--;
		return returnScore;
	}

	/**
	 * Returns (without removing) the item at index position i.
	 * If i is less than zero or greater than size()-1,
	 * will throw an IndexOutOfBoundsException.
	 * @param i must be greater than or equal to zero and less than size()
	 * @return the item at index i
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public Score get(int i) throws IndexOutOfBoundsException {
		if (i < 0 || i > items.length - 1)
			throw new IndexOutOfBoundsException();
		return items[i];
	}

}
