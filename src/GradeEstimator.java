
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This class estimates letter grades for scores based on the percentage given.
 * A properly formatted file containing scores and thresholds can be scanned to
 * estimate grades from.
 *
 * <p>
 * Bugs: None
 * </p>
 *
 * @author Team 57 import teamName
 */
public class GradeEstimator {
	// list of scores
	private static ScoreList mylist;
	// array of letter grades
	private static String[] letter_grades;
	// array of minimum thresholds
	private static double[] minimum_thresholds;
	// array of category names
	private static String[] category_names;
	// array of category weights
	private static double[] category_weights;

	/**
	 * The main method checks if there is one and only one command line
	 * argument. If this is true, then it calls the
	 * createGradeEstimatorFromFile() method with the given command line
	 * argument. The main method also handles the exceptions thrown by other
	 * methods.
	 * 
	 * @param args
	 *            the command line arguments. args[0] must be a file which is
	 *            loaded
	 */
	public static void main(String[] args) {
		try {
			GradeEstimator thisEstimator;
			if ((args != null) && (args.length == 1))
				thisEstimator = createGradeEstimatorFromFile(args[0]);
			else {
				System.out.println(Config.USAGE_MESSAGE);
				// use default input from Config.java
				thisEstimator = new GradeEstimator();
				letter_grades = Config.GRADE_LETTER;
				minimum_thresholds = Config.GRADE_THRESHOLD;
				category_names = Config.CATEGORY_KEY;
				category_weights = Config.CATEGORY_WEIGHT;
			}
			System.out.println(thisEstimator.getEstimateReport());
		} catch (FileNotFoundException ex) {
			System.out.println("java.io.FileNotFoundException: " + args[0]
					+ " (The system cannot find the file specified)");
			return; // gracefully exit, return to method calling main()
		} catch (GradeFileFormatException ex) {
			System.out.println("GradeFileFormatException");
			return; // gracefully exit, return to method calling main()
		}
	}

	/**
	 * This method creates a new GradeEstimator instance, storing the scores
	 * from the given file.
	 *
	 * @param gradeInfo
	 *            The file to take scores from
	 * @return A new GradeEstimator instance
	 * @throws FileNotFoundException
	 *             -- If the file cannot be found
	 * @throws GradeFileFormatException
	 *             -- If the file is formatted incorrectly
	 */
	public static GradeEstimator createGradeEstimatorFromFile(String gradeInfo)
			throws FileNotFoundException, GradeFileFormatException {
		// you do not need to check whether there is legal, if not
		// FileNotFoundException will be automatically thrown
		File file = new File(gradeInfo);
		// this can throw FileNotFoundException
		Scanner scan = new Scanner(file);
		mylist = new ScoreList();

		try {
			// get data of front four lines
			letter_grades = getArrayBeforePound_String(scan.nextLine());
			minimum_thresholds = getArrayBeforePound_double(scan.nextLine());
			category_names = getArrayBeforePound_String(scan.nextLine());
			category_weights = getArrayBeforePound_double(scan.nextLine());

			// get data of followings (text scores)
			while (scan.hasNextLine()) {
				String wholeLine = scan.nextLine();
				if (wholeLine.contains("#")) {
					String[] splitted = wholeLine.split("#");
					wholeLine = splitted[0];
				}
				Scanner sc = new Scanner(wholeLine);
				mylist.add(
						new Score(sc.next(), sc.nextDouble(), sc.nextDouble()));
			}
		} catch (Exception e) {
			// no mater what exception is found, always throw
			// GradeFileFormatException
			throw new GradeFileFormatException("GradeFileFormatException");
		}
		return new GradeEstimator(mylist);
	}

	/**
	 * This method creates a string containing weighted percentages and grade
	 * estimates from a file.
	 *
	 * @return The grade estimates and how they were calculated
	 */
	public String getEstimateReport() {
		String returnMess = "";
		// print list scores
		for (int j = 0; j < mylist.size(); j++) {
			returnMess += mylist.get(j).getName()
					+ String.format(" %7.2f\n", mylist.get(j).getPercent());
		}
		returnMess += "Grade estimate is based on " + mylist.size()
				+ " scores\n";
		double totalPercent = 0.0;
		// print repeat for each category
		for (int i = 0; i < category_names.length; i++) {
			double thisPercent = 0.0;
			int numItem = 0;
			// if score list contain this category
			if (isScoreHasThisCategory(mylist, category_names[i])) {
				// for every category
				ScoreIterator itr = new ScoreIterator(this.mylist,
						category_names[i]);
				// loop for all score in this category
				while (itr.hasNext()) {
					Score thisScore = itr.next();
					thisPercent += thisScore.getPercent();
					numItem++;
				}
				thisPercent = thisPercent / numItem / 100;
			} else {
				// if score list contain this category, get full credit
				thisPercent = 1;
			}
			// use string.format(); similar to printf()
			returnMess += String.format("%7.2f%% = %5.2f%% of %2.0f%% for %s\n",
					thisPercent * category_weights[i], 100 * thisPercent,
					category_weights[i], category_names[i]);
			totalPercent += thisPercent * category_weights[i];

		}

		returnMess += "--------------------------------\n"
				+ String.format("%7.2f%% weighted percent\n", totalPercent);
		// find out what letter grade it is
		int letterIndex;
		for (letterIndex = 0; letterIndex < minimum_thresholds.length; letterIndex++) {
			if (totalPercent >= minimum_thresholds[letterIndex])
				break;
		}
		if (letterIndex == minimum_thresholds.length)
			return returnMess += "Letter Grade Estimate: unable to estimate letter grade for "
					+ totalPercent;
		else
			return returnMess += "Letter Grade Estimate: "
					+ letter_grades[letterIndex];
	}

	/**
	 * Helper function input ScoreList and category, output true if ScoreList
	 * contains this category
	 * 
	 * @param mylist
	 *            The list of scores from the file
	 * @param category
	 *            The category of score from the file
	 * @return boolean Whether the Score is in the given category
	 */

	private boolean isScoreHasThisCategory(ScoreList mylist, String category) {
		for (int ii = 0; ii < mylist.size(); ii++)
			if (mylist.get(ii).name.charAt(0) == category.charAt(0))
				return true;
		return false;
	}

	/**
	 * This is private constructor method that create a new GradeEstimator
	 * servicing for GradeEstimator used by "No Grade File specified" to create
	 * a blank and new ScoreList
	 * 
	 */
	private GradeEstimator() {
		this.mylist = new ScoreList();
	}

	/**
	 * This is private constructor method that create a new GradeEstimator
	 * servicing for GradeEstimator used by normal input file to copy parameter
	 * to this ScoreList
	 * 
	 * @param mylist
	 *            The list of scores from the file
	 */
	private GradeEstimator(ScoreList mylist) {
		this.mylist = mylist;
	}

	/**
	 * This private helper method reads the data before the # sign for a given
	 * line of the file. It takes all the values as String and adds them to an
	 * array to be returned.
	 * 
	 * @param scanLine
	 *            a line from the file to be read
	 * @return String [] the array of values read from the file
	 */
	private static String[] getArrayBeforePound_String(String scanLine) {
		if (scanLine.contains("#")) {
			String[] splitted = scanLine.split("#");
			scanLine = splitted[0];
		}
		Scanner sc = new Scanner(scanLine);
		ArrayList<String> returnList = new ArrayList<>();
		while (sc.hasNext())
			returnList.add(sc.next());
		// convert arraylist to array
		String[] returnArray = new String[returnList.size()];
		for (int i = 0; i < returnList.size(); i++)
			returnArray[i] = returnList.get(i);
		return returnArray;
	}

	/**
	 * This private helper method reads the data before the # sign for a given
	 * line of the file. It takes all the values as doubles and adds them to an
	 * array to be returned.
	 * 
	 * @param scanLine
	 *            a line from the file to be read
	 * @return double [] the array of values read from the file
	 */
	private static double[] getArrayBeforePound_double(String scanLine) {
		if (scanLine.contains("#")) {
			String[] splitted = scanLine.split("#");
			scanLine = splitted[0];
		}
		Scanner sc = new Scanner(scanLine);
		ArrayList<Double> returnList = new ArrayList<>();
		while (sc.hasNext())
			returnList.add(sc.nextDouble());
		// convert arraylist to array
		double[] returnArray = new double[returnList.size()];
		for (int i = 0; i < returnList.size(); i++)
			returnArray[i] = returnList.get(i);
		return returnArray;
	}
}
