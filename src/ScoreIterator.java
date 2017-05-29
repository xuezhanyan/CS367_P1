
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

import java.util.NoSuchElementException;

/*
 * Indirect access, only  iterate through a given ScoreList considering only the
 *  items in the ScoreList that match a given category.
 *
 * @author Team 57 import teamName
 */
public class ScoreIterator implements ScoreIteratorADT {
	private ScoreList myList;
	private int curpos;
	private int start_index; // start index of this category
	private int end_index; // start index of this category

	public ScoreIterator(ScoreList myList, String category) {
		this.myList = myList;
		int num_category = 0;
		for (int i = 0; i < myList.size(); i++) {
			// if (myList.get(i).getName().charAt(0) == category.charAt(0)) {
			if (myList.get(i).getCategory().charAt(0) == category.charAt(0)) {
				num_category++;
				end_index = i;
			}
		}
		start_index = end_index - num_category + 1;
		curpos = start_index;
	}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true if the iteration has more elements
	 */
	@Override
	public boolean hasNext() {
		return curpos <= end_index;
	}

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element in the iteration
	 * @throws NoSuchElementException
	 *             if the iteration has no more elements
	 */
	@Override
	public Score next() {
		if (!hasNext())
			throw new NoSuchElementException();
		Score result = myList.get(curpos);
		curpos++;
		return result;
	}

}
