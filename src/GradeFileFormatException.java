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
 * This class implements a GradeFileFormatException, if
 * createGradeEstimatorFromFile() loads some grade_info that doesn't meet the
 * requirements, then it will throw this type of exception.
 *
 * <p>
 * Bugs: None
 *
 * @author Team 57 import teamName
 */
public class GradeFileFormatException extends Exception {
	/**
	 * CONSTRUCTOR HEADER construct an exception of GradeFileFormatException
	 *
	 */
	public GradeFileFormatException() {
		super();
	}

	/**
	 * CONSTRUCTOR HEADER construct an exception of GradeFileFormatException
	 * 
	 * @param message:
	 *            any String message when throw this exception
	 */
	public GradeFileFormatException(String message) {
		super(message);
	}
}
