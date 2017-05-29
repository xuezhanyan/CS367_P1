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
 * This class contains student name, how many points did he (or she) get and
 * what is the maximum score. Moreover, it contains public methods to access and
 * change to this object.
 *
 * @author Team 57 import teamName
 */
public class Score {
	String name;
	double points; // points got from the test
	double possible; // maximum score of the test

	/**
	 * This constructor initializes a new score object and check whether all
	 * input arguments are legal.
	 * 
	 * @param name
	 *            a non-null string to store in score object.
	 * @param points
	 *            a non-negative integer to record score got.
	 * @param possible
	 *            a non-negative integer to record score got, which need to not
	 *            smaller than points.
	 * @throws IllegalArgumentException
	 */
	public Score(String name, double points, double possible)
			throws IllegalArgumentException {
		if (name == null)
			throw new IllegalArgumentException();
		if (points < 0 || possible < 0 || points > possible)
			throw new IllegalArgumentException();
		this.name = name;
		this.points = points;
		this.possible = possible;
	}

	/**
	 * Returns the string name in score object
	 * 
	 * @return the string name in score object
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns the double points in score object
	 * 
	 * @return the double points in score object
	 */
	public double getPoints() {
		return this.points;
	}

	/**
	 * Returns the double points maximum possible in score object
	 * 
	 * @return the double points maximum possible in score object
	 */
	public double getMaxPossible() {
		return this.possible;
	}

	/**
	 * Returns the string contains the first character in string name
	 * 
	 * @return the string contains the first character in string name
	 */
	public String getCategory() {
		return Character.toString(this.name.charAt(0));
	}

	/**
	 * Returns the double representing percentage of score got
	 * 
	 * @return the double representing percentage of score got
	 */
	public double getPercent() {
		return this.points / this.possible * 100;
	}

}
