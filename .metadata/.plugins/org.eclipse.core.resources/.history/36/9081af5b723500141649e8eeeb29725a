package project2_myMethod1;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 
 * Advanced Database Technology and Applications (COMP6521) Project 2 - Mining
 * Frequent Itemsets from Secondary Memory
 * 
 * @author Lin Xu
 * @author Lin Zhu
 * @author Xin Shao
 * 
 *         This is an implementation of the Apriori algorithm to compute
 *         frequent itemsets.
 * 
 *         The application reads a dataset file as an input (space delimited
 *         text file, with the items encoded as integers. integers are from 0 to
 *         1999) and a percentage, given as an integer (e.g. input 2 represents
 *         2%).
 * 
 *         The application will output each itemsets (Pairs, Triples,
 *         Quadruples, etc.) that appears together in the same transaction (a
 *         transaction is represented by one line in the file) in more than 2%
 *         of all transactions (where 2% is the percentage given as an input).
 * 
 */
public class COMP6521_Project2 extends Observable {

	public static void main(String[] args) throws Exception {

		showMenu();
		Scanner keyboard = new Scanner(System.in);
		String fileName = null;
		double minSup = 0.0;
		int userChoice = 0;

		while (true) {
			Boolean valid = false;

			while (!valid) {
				try {
					userChoice = keyboard.nextInt();
					valid = true;
				} catch (Exception e) {
					System.out
							.println(">> Invalid Input, please enter an Integer");
					valid = false;
					keyboard.nextLine();
				}
			}

			switch (userChoice) {
			case 1:
				fileName = "small.dat";
				break;
			case 2:
				fileName = "medium.dat";
				break;
			case 3:
				fileName = "large.dat";
				break;
			case 4:
				keyboard.nextLine();
				System.out
						.println("Please input the name of file to be sorted:");
				String userName = keyboard.nextLine();
				fileName = userName;
				break;
			case 5:
				System.out.println("Thanks for testing!");
				keyboard.close();
				System.exit(0);
			default:
				System.out.println("Invalid Input, please try again.");
			}

			break;
		}

		showMenu2();
		Boolean valid = false;

		while (!valid) {
			try {
				minSup = keyboard.nextDouble();
				valid = true;
			} catch (Exception e) {
				System.out.println(">> Invalid Input, please enter an Integer");
				valid = false;
				keyboard.nextLine();
			}
		}

		String[] argu = { fileName, Double.toString(minSup) };
		@SuppressWarnings("unused")
		COMP6521_Project2 ap = new COMP6521_Project2(argu);
	}

	public static void showMenu() {
		System.out.println("***********************************************");
		System.out.println("*******Welcome to Mining Frequent Itemsets*****\n"
				+ "*****from Secondary Memory Testing Program*****");
		System.out.println("***********************************************");
		System.out.println(">> Please select an test case (1-4)");
		System.out.println(">> 1. small.dat");
		System.out.println(">> 2. medium.dat");
		System.out.println(">> 3. large.dat");
		System.out.println(">> 4. others");
		System.out.println(">> 5. Exit");
	}

	public static void showMenu2() {
		System.out.println("***********************************************");
		System.out.println("***********************************************");

		System.out.println(">> Please input a percentage, "
				+ "\n   Given as an integer (e.g. input 2 represents 2%):");
	}

	/* the list of current itemsets */
	private ArrayList<int[]> itemsets;
	/* the name of the transcation file */
	private String transaFileName;
	/* number of different items in the dataset */
	private int itemsNum;
	/* total number of transactions in transaFileName */
	private int transaNum;
	/* minimum support for a frequent itemset in percentage, e.g. 2 means 2% */
	private double minSup;

	private int hashBucketsNum =500000;
	private int minValueToUseHash=2000;
	private int[] bitList = new int[hashBucketsNum];
	private int[] bitVector = new int[hashBucketsNum];

	/**
	 * Generates the apriori itemsets from a file
	 * 
	 * @param args
	 *            configuration parameters: args[0] is a filename, args[1] the
	 *            min support (e.g. 2 for 2%)
	 */
	public COMP6521_Project2(String[] args) throws Exception {
		infoConfirm(args);
		algoApriori();
	}

	/** computes itemsNum, transaNum, and sets minSup */
	private void infoConfirm(String[] args) throws Exception {
		// sett transaFileName
		if (args.length != 0)
			transaFileName = args[0];
		else
			transaFileName = "Medium.dat"; // default

		// set minsupport
		if (args.length >= 2)
			minSup = (Double.valueOf(args[1]).doubleValue());
		else
			minSup = 2;// by default
		if (minSup > 100 || minSup < 0)
			throw new Exception("minSup: Out of Boundary");

		// going thourgh the file to compute itemsNum and transaNum
		itemsNum = 0;
		transaNum = 0;
		@SuppressWarnings("resource")
		BufferedReader data_in = new BufferedReader(new FileReader(
				transaFileName));
		while (data_in.ready()) {
			String line = data_in.readLine();
			if (line.matches("\\s*"))
				continue; // be friendly with empty lines
			transaNum++;
			StringTokenizer t = new StringTokenizer(line, " ");
			while (t.hasMoreTokens()) {
				int x = Integer.parseInt(t.nextToken());
				if (x + 1 > itemsNum)
					itemsNum = x + 1;
			}
		}
		outputinfo();
	}

	/**
	 * outputs the current configuration
	 */
	private void outputinfo() {
		// output config info to the user
		System.out.println("***********************************************");
		System.out.println("***************Input Information***************");
		System.out.println("***********************************************");
		System.out.println("\n" + ">> [Number of items] >>\t\t" + itemsNum
				+ "\n>> [Number of transactions] >>\t" + transaNum
				+ "\n>> [Number of minSup] >>\t" + minSup
				+ "% (Specific Value: " + Math.round(transaNum * minSup / 100)
				+ " )");
	}

	/** starts the algorithm after configuration */
	private void algoApriori() throws Exception {
		// timer to start
		long start = System.currentTimeMillis();

		// first we generate the candidates of size 1
		itemsets = new ArrayList<int[]>();
		for (int i = 0; i < itemsNum; i++) {
			int[] cand = { i };
			itemsets.add(cand);
		}

		int itemsetSize = 1; // the current itemset being looked at
		int freSetNum = 0;
		String process = null;

		while (itemsets.size() > 0) {
			switch (itemsetSize) {
			case 1:
				process = "**Process One";
				break;
			case 2:
				process = "**Process Two";
				break;
			case 3:
				process = "Process Three";
				break;
			case 4:
				process = "*Process Four";
				break;
			case 5:
				process = "*Process Five";
				break;
			}
			System.out
					.println("\n\n***********************************************");
			System.out.println("************" + process
					+ " Starts***************");
			System.out
					.println("***********************************************");
			calculateFrequentItemsets();
			if (itemsets.size() != 0) {
				freSetNum += itemsets.size();
				System.out
						.println("\n\n***********************************************");
				System.out.println("************" + process
						+ " Done*****************");
				System.out
						.println("***********************************************");
				System.out.println("\n>> Frequent itemsets of size "
						+ itemsetSize + " are found.");
				System.out.println(">> Total number is " + itemsets.size());

				System.out.println(">> Current support " + (minSup)
						+ "% (Absolute: "
						+ Math.round(transaNum * minSup / 100) + " )\n\n");
				nextSizeSetCreation();
			} else {
				System.out.println(">> No itemset of size " + itemsetSize
						+ " is survived.");
				System.out
						.println("\n\n***********************************************");
				System.out.println("************" + process
						+ " Done*****************");
				System.out
						.println("***********************************************");

			}

			itemsetSize++;
		}

		// display the execution time
		long end = System.currentTimeMillis();
		System.out
				.println("\n\n***********************************************");
		System.out.println("***********************************************");
		System.out.println("***********************************************");
		System.out.println("\n\n>> Execution time is: "
				+ ((double) (end - start) / 1000) + " seconds.");
		System.out.println(">> Found " + freSetNum
				+ " frequents sets for support " + (minSup) + "% (Absolute "
				+ Math.round(transaNum * minSup / 100) + ")");
		System.out.println("\nAll Done");
	}

	private void calculateFrequentItemsets() throws Exception {

		System.out
				.println(">> Passing through the data to compute the frequency of "
						+ itemsets.size()
						+ " itemsets of size "
						+ itemsets.get(0).length);

		ArrayList<int[]> frequentCandidates = new ArrayList<int[]>(); // the
																		// frequent
																		// candidates
																		// for
																		// the
																		// current
																		// itemset

		boolean match; // whether the transaction has all the items in an
						// itemset
		boolean pairMatch;
		int count[] = new int[itemsets.size()]; // the number of successful
												// matches, initialized by zeros

		// load the transaction file
		BufferedReader data_in = new BufferedReader(new InputStreamReader(
				new FileInputStream(transaFileName)));

		boolean[] trans = new boolean[itemsNum];

		// for each transaction
		for (int i = 0; i < transaNum; i++) {

			String line = data_in.readLine();
			boolArraySize1(line, trans);

			// check each candidate
			for (int c = 0; c < itemsets.size(); c++) {

				match = true; // reset match to false
				// tokenize the candidate so that we know what items need to be
				// present for a match
				int[] cand = itemsets.get(c);
				// check each item in the itemset to see if it is present in the
				// transaction
				for (int xx : cand) {
					if (trans[xx] == false) {
						match = false;
						break;
					}
				}
				if (match) { // if at this point it is a match, increase the
								// count
					count[c]++;
				}

				// During Pass 1 of A-priori
				// Use the idle memory to keep counts of buckets into which the
				// pairs of items are hashed.
				// Just the count, not the pairs themselves.
				if (itemsets.get(0).length == 1 && Math.round(transaNum * minSup / 100)<minValueToUseHash) {
					if (match) {
						for (int k = c + 1; k < itemsets.size(); k++) {
							pairMatch = true;
							int nextItam = itemsets.get(k)[0];
							if (trans[nextItam] == false) {
								pairMatch = false;
							}

							if (pairMatch) {
								bitList[hashCal(itemsets.get(c)[0],
										itemsets.get(k)[0])]++;
							}
						}

					}

				}

			}

		}

		// During Pass 1 of A-priori
		// Initialize the bit vector
		if (itemsets.get(0).length == 1&& Math.round(transaNum * minSup / 100)<minValueToUseHash) {
			for (int i = 0; i < hashBucketsNum; i++) {
				if (bitList[i] >= Math.round(transaNum * minSup / 100)) {
					bitVector[i] = 1;
				} else {
					bitVector[i] = 0;
				}
			}
		}

		data_in.close();
		System.out.println("\n>> Results are shown below:");
		System.out.println("\n>> Current size of itemset: "
				+ itemsets.get(0).length);
		for (int i = 0; i < itemsets.size(); i++) {
			// if the count% is larger than the minSup%, add to the candidate to
			// the frequent candidates
			if ((count[i] / (double) (transaNum)) >= minSup / 100) {
				foundFrequentItemSet(itemsets.get(i), count[i]);
				frequentCandidates.add(itemsets.get(i));
			}
		}

		// new candidates are only the frequent candidates
		itemsets.clear();
		itemsets = frequentCandidates;
	}

	/** triggers actions if a frequent item set has been found */
	private void foundFrequentItemSet(int[] itemset, int support) {
		DecimalFormat df = new DecimalFormat("#0.0000 ");

		System.out.println(">> Frequent itemset: " + Arrays.toString(itemset)
				+ "\t[Frequency: "
				+ df.format((support / (double) transaNum) * 100)
				+ "%\tAbsolute: " + support + "\t]");
	}

	/**
	 * if m is the size of the current itemsets, generate all possible itemsets
	 * of size n+1 from pairs of current itemsets replaces the itemsets of
	 * itemsets by the new ones
	 */
	private void nextSizeSetCreation() {
		// by construction, all existing itemsets have the same size
		int currentSizeOfItemsets = itemsets.get(0).length;
		System.out.println(">> Creating itemsets of size "
				+ (currentSizeOfItemsets + 1) + " based on " + itemsets.size()
				+ " itemsets of size " + currentSizeOfItemsets);
		ArrayList<int[]> tempCandi = new ArrayList<int[]>();

		// HashMap<String, int[]> tempCandidates = new HashMap<String, int[]>();
		// // temporary
		// candidates
		// compare each pair of itemsets of size n-1
		for (int i = 0; i < itemsets.size(); i++) {
			for (int j = i + 1; j < itemsets.size(); j++) {
				int[] X= itemsets.get(i);
				int[] Y = itemsets.get(j);

				// Count all pairs {i, j } that meet the conditions:
				// Both i and j are frequent items.
				// The pair {i, j }, hashes to a bucket number whose bit in the
				// bit vector is 1.
				// In this program, there is no bit vector

				if (currentSizeOfItemsets == 1&& Math.round(transaNum * minSup / 100)<minValueToUseHash) {
					
					if(bitVector[hashCal(X[0],Y[0])]==0)
					{
						break;// do not meet the conditions
					}
				}

				assert (X.length == Y.length);

				int[] newCand = new int[currentSizeOfItemsets + 1];
				for (int s = 0; s < newCand.length - 1; s++) {
					newCand[s] = X[s];
				}

				int ndifferent = 0;
				// then we find the missing value
				for (int s1 = 0; s1 < Y.length; s1++) {
					boolean found = false;
					// is Y[s1] in X?
					for (int s2 = 0; s2 < X.length; s2++) {
						if (X[s2] == Y[s1]) {
							found = true;
							break;
						}
					}
					if (!found) { // Y[s1] is not in X
						ndifferent++;
						// we put the missing value at the end of newCand
						newCand[newCand.length - 1] = Y[s1];
					}

				}

				// we have to find at least 1 different, otherwise it means that
				// we have two times the same set in the existing candidates
				assert (ndifferent > 0);

				if (ndifferent == 1) {

					Arrays.sort(newCand);
					tempCandi.add(newCand);
				}
			}
		}

		// set the new itemsets

		itemsets.clear();
		for (int i = 0; i < tempCandi.size(); i++) {
			itemsets.add(tempCandi.get(0));
			tempCandi.remove(0);
		}

		System.out.println(">> Successfully created " + itemsets.size()
				+ " unique itemsets of size " + (currentSizeOfItemsets + 1));

	}

	/** Put "true" in trans[i] if the integer i is in line */
	private void boolArraySize1(String line, boolean[] trans) {
		Arrays.fill(trans, false);
		StringTokenizer stFile = new StringTokenizer(line, " "); // read a line
																	// from the
																	// file to
																	// the
																	// tokenizer
		// put the contents of that line into the transaction array
		while (stFile.hasMoreTokens()) {

			int parsedVal = Integer.parseInt(stFile.nextToken());
			trans[parsedVal] = true; // if it is not a 0, assign the value to
										// true
		}
	}

	private void buildHashTable() {

	}

	private int hashCal(int i, int j) {
		return (i * j) % hashBucketsNum;
	}

}