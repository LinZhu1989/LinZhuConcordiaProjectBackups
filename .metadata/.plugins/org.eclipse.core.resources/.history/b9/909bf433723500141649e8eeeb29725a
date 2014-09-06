package project1_myMethod2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Advanced Database Technology and Applications (COMP6521) Project 1 - External
 * Sorting Algorithm, 2PMMS Implementation
 * 
 * @author Lin Xu
 * @author Lin Zhu
 * @author Xin Shao
 * 
 *         This is a small program which implements 2PMMS. Given the dataset
 *         with different sizes, user can use this program to to read the
 *         records from the file and sort them using 2 Phase Multiway Merge Sort
 *         (2PMMS). The final output file will be in the same folder of the
 *         program.
 * 
 */
public class ExternalSorting {
	static String location = "C://Temp//";
	int fileCount = 1;
	int[][] buffer;
	BufferedReader[] reader;

	FileInputStream[] fis;
	BufferedInputStream[] bis;
	DataInputStream[] dis;

	int MAXSIZE = 900000;
	int MAXBUFFERS = 50;

	BufferedWriter writer;
	int buffersNum; // number of buffers
	int bufferSize; // size of buffers
	int heads[];
	int current, pos;
	int filesRead = 1;
	int filesReadInPhase = 0;
	ArrayList<Integer> filesNum = new ArrayList<Integer>();

	int total;
	boolean bufNumflag = false;

	/**
	 * Function sortPhase1 performs the phase 1 of the External Sorting
	 * Algorithm, 2PMMS
	 * 
	 * @param fileName
	 * @throws Exception
	 */

	public void sortPhase1(String fileName) throws Exception {
		filesNum.add(0);
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		DataOutputStream dos = null;

		int[] numbers = new int[MAXSIZE];
		String line = null;
		int i = 0;

		while ((line = reader.readLine()) != null) {
			if (i < numbers.length - 1) {
				numbers[i] = (Integer.parseInt(line));
				i++;
				// read in an integer from the original file if the size of
				// numbers[] is not larger than MAXSIZE
			} else {
				numbers[i] = (Integer.parseInt(line));
				quickSort(numbers, 0, numbers.length - 1);
				// read in the last integer and sort all the numbers in
				// numbers[] using quickSort
				fos = new FileOutputStream(new File(location + "_temp_file_"
						+ fileCount));
				bos = new BufferedOutputStream(fos, 4096);
				// Creates a new buffered output stream to write data to the
				// specified underlying output stream with the specified buffer
				// size.
				dos = new DataOutputStream(bos);
				// Creates a new data output stream to write data to the
				// specified underlying output stream. The counter written is
				// set to zero.
				for (int j = 0; j < numbers.length; j++) {
					dos.writeInt(numbers[j]);
					// Writes all the integers in number[] to the underlying
					// output stream as four bytes, high byte first.
				}
				dos.flush();
				dos.close();
				bos.close();
				fos.close();

				i = 0;
				fileCount++;
			}

		}
		// do the same thing with the last number[] with a size less than
		// MAXSIZE
		quickSort(numbers, 0, i - 1);
		fos = new FileOutputStream(new File(location + "_temp_file_"
				+ fileCount));
		bos = new BufferedOutputStream(fos, 4096);
		dos = new DataOutputStream(bos);
		for (int j = 0; j < i; j++) {
			dos.writeInt(numbers[j]);
		}
		dos.flush();
		dos.close();
		bos.close();
		fos.close();
		reader.close();

		filesNum.add(fileCount); // add a number of file counter into the
									// filesNum[]
	}

	/**
	 * Function quickSort: Quicksort, or partition-exchange sort, is a sorting
	 * algorithm developed by Tony Hoare that, on average, makes O(n log n)
	 * comparisons to sort n items.
	 * 
	 * @param [] arr, left , right
	 */
	public void quickSort(int arr[], int left, int right) {
		int i = left, j = right;
		int tmp;
		int pivot = arr[(left + right) / 2];
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
		}
		if (left < i - 1)
			quickSort(arr, left, i - 1);
		if (i < right)
			quickSort(arr, i, right);
	}

	/**
	 * Function sortPhase2 performs the phase 2 of the External Sorting
	 * Algorithm, 2PMMS
	 * 
	 */
	public void sortPhase2() throws Exception {
		int i = 1;
		int k = 0;

		while (filesNum.get(i) > 1) {
			if (filesNum.get(i) > MAXBUFFERS) {
				bufNumflag = true; // boolean bufNumflag is true if number of
									// temp files
									// is larger than max number of buffers
			} else {
				bufNumflag = false; // else it is false which means that the
									// current
				// number of temp files is less the MAXBUFFERS
			}

			k = 0;
			filesNum.add(0);
			filesReadInPhase = 0;

			while (k < filesNum.get(i)) {
				externalMerge(i);
				k = k + MAXBUFFERS;
				filesNum.set(i + 1, (filesNum.get(i + 1) + 1));
			}
			i++;
		}

		for (int z = 1; z <= fileCount; z++) {
			File f = new File(location + "_temp_file_" + z);
			f.delete();
		}
	}

	/**
	 * Function externalMerge performs the external Merge in 2PMMS
	 * 
	 * @param phase
	 */
	public void externalMerge(int myInt) throws Exception {

		// determine the number of buffers
		if (filesNum.get(myInt) < MAXBUFFERS) {
			buffersNum = filesNum.get(myInt);
		} else {
			if (filesNum.get(myInt) - filesReadInPhase < MAXBUFFERS) {
				buffersNum = filesNum.get(myInt) - filesReadInPhase;
			} else {
				buffersNum = MAXBUFFERS;
			}
		}

		bufferSize = MAXSIZE / buffersNum;
		buffer = new int[buffersNum][bufferSize];
		fis = new FileInputStream[buffersNum];
		bis = new BufferedInputStream[buffersNum];
		dis = new DataInputStream[buffersNum];
		heads = new int[buffersNum];

		int currentBuffer = 0;

		while (currentBuffer < buffersNum && filesRead <= fileCount) {
			fillNewBuffer(currentBuffer);
			currentBuffer++;
			filesRead++;
			filesReadInPhase++;
		}

		for (int z = 0; z < buffersNum; z++)
			heads[z] = buffer[z][0];

		if (bufNumflag) {
			// If the number of buffers is less than the number of temp files
			getMinBinary();
		} else {
			writer = new BufferedWriter(new FileWriter(location
					+ "ExternalSortedOutput.txt"));
			getMin();
		}
	}

	/**
	 * Function getMin()
	 * 
	 */
	public void getMin() throws Exception {
		int sample = 2147483647;
		int pos = 0;
		int empty = 0;
		int bi[] = new int[buffersNum];

		while (empty < buffersNum) {
			for (int x = 0; x < buffersNum; x++) {
				if (heads[x] < sample) {
					sample = heads[x];
					pos = x;
				}
			}
			if (sample != 2147483647 && sample != -1) {
				writer.write(sample + "");
				writer.newLine();
			}

			sample = 2147483647;

			bi[pos] = bi[pos] + 1;
			if (bi[pos] < bufferSize) {
				heads[pos] = buffer[pos][bi[pos]];

			} else if (bi[pos] >= bufferSize
					&& (buffer[pos][bufferSize - 1] != -1)) {
				fillBuffer(pos);
				bi[pos] = 0;
				heads[pos] = buffer[pos][bi[pos]];
			}
			if (bi[pos] >= bufferSize && (buffer[pos][bufferSize - 1] == -1)) {
				empty++;
				heads[pos] = 2147483647;
			}
		}

		writer.flush();
		writer.close();

		for (int z = 0; z < dis.length; z++) {
			dis[z].close();
			bis[z].close();
			fis[z].close();
		}
		buffer = null;
		writer = null;
		dis = null;
		bis = null;
		fis = null;
	}

	/**
	 * Function getMinBinary()
	 * 
	 */
	public void getMinBinary() throws Exception {
		int sample = 2147483647;
		int pos = 0;
		int empty = 0;
		int bi[] = new int[buffersNum];

		FileOutputStream fos = new FileOutputStream(new File(location
				+ "_temp_file_" + ++fileCount));
		BufferedOutputStream bos = new BufferedOutputStream(fos, 4096);
		DataOutputStream dos = new DataOutputStream(bos);
		;

		while (empty < buffersNum) {
			for (int x = 0; x < buffersNum; x++) {
				if (heads[x] < sample) {
					sample = heads[x];
					pos = x;
				}
			}
			if (sample != 2147483647 && sample != -1) {
				dos.writeInt(sample);
			}

			sample = 2147483647;

			bi[pos] = bi[pos] + 1;
			if (bi[pos] < bufferSize) {
				heads[pos] = buffer[pos][bi[pos]];

			} else if (bi[pos] >= bufferSize
					&& (buffer[pos][bufferSize - 1] != -1)) {
				fillBuffer(pos);
				bi[pos] = 0;
				heads[pos] = buffer[pos][bi[pos]];
			}
			if (bi[pos] >= bufferSize && (buffer[pos][bufferSize - 1] == -1)) {
				empty++;
				heads[pos] = 2147483647;
			}
		}

		dos.flush();
		dos.close();
		bos.close();
		fos.close();

		for (int z = 0; z < dis.length; z++) {
			dis[z].close();
			bis[z].close();
			fis[z].close();
		}

		dos = null;
		bos = null;
		fos = null;
		dis = null;
		bis = null;
		fis = null;
		buffer = null;
		System.gc();
		System.gc();
		System.gc();
	}

	/**
	 * Function fillNewBuffer()
	 * 
	 */

	public void fillNewBuffer(int cb) throws Exception {
		fis[cb] = new FileInputStream(new File(location + "_temp_file_"
				+ filesRead));
		bis[cb] = new BufferedInputStream(fis[cb], 4096);
		dis[cb] = new DataInputStream(bis[cb]);
		int i = 0;
		while (i < bufferSize && (buffer[cb][i++] = dis[cb].readInt()) != -1)
			;
	}

	/**
	 * Function fillBuffer()
	 * 
	 */

	public void fillBuffer(int cb) throws Exception {
		int i = 0;
		try {
			while (i < bufferSize && (buffer[cb][i] = dis[cb].readInt()) != -1)
				i++;
		} catch (Exception e) {
		}

		while (i < bufferSize)
			buffer[cb][i++] = -1;

	}

	public static void showMenu() {
		System.out
				.println("\n****Welcome to 2PMMS Implementation Testing Program****\n");
		System.out.println(">> Please select an test case (1-4)");
		System.out.println(">> 1. small.dat");
		System.out.println(">> 2. medium.dat");
		System.out.println(">> 3. large.dat");
		System.out.println(">> 4. others");
		System.out.println(">> 5. Exit");
	}

	public static void main(String[] args) {

		showMenu();
		Scanner keyboard = new Scanner(System.in);
		String fileName = null;
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

			ExternalSorting s = new ExternalSorting();
			try {
				// fileName = "large.dat";
				long ts = System.nanoTime(); // Starting time of 2PMMS
				s.sortPhase1(location+fileName);
				s.sortPhase2();
				System.out.println("Total execution time:"
						+ ((double) (System.nanoTime() - ts) / 1000000000)
						+ " secs.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
