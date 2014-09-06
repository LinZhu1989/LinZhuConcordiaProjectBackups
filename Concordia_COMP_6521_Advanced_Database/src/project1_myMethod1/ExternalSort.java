package project1_myMethod1;

/**
 * COMP6251 project one.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class ExternalSort {

	public static void externalSort(ArrayList<String> info) throws IOException{
		
		// Data Initialization
		String fileName = info.get(0);
		int N = Integer.parseInt(info.get(1));
		int slices = Integer.parseInt(info.get(2));
		int inBufferSize = Integer.parseInt(info.get(3)); 										
		int outBufferSize = Integer.parseInt(info.get(4)); 
	
		// Data Initialization Information printed out
/*		System.out.println("Number of the integers to be sorted�� " + N);
		System.out.println("Number of sublists�� " + slices);
		System.out.println("Number of input buffers�� " + slices);
		System.out.println("Number of ouput buffers�� " + 1);
		System.out.println("Number of integers that a input buffer can hold�� " + inBufferSize);
		System.out.println("Number of integers that a output buffer can hold�� " + outBufferSize);
		*/
		doPhase1(fileName,N,slices);
		doPhase2(N,slices,inBufferSize,outBufferSize);
		
}
	
	/**
	 * Phase One for the TPMMS algorithm.
	 * 
	 */

		public static void doPhase1(String fName, int n, int sli) throws IOException{
			String fileName = fName;
			int N = n;
			int slices = sli;
			long p1Time1 = System.currentTimeMillis();
			int readBuffer = (int) Math.ceil((double) N / slices);
			String tfile = "temp-file-";
			int[] buffer = new int[readBuffer < N ? readBuffer : N];
			
			try {
				FileReader fr = new FileReader(fileName);
				BufferedReader br = new BufferedReader(fr);

				int i, j;
				i = j = 0;
				long sortTime = 0;
				// Iterate through the elements in the file
				for (i = 0; i < slices; i++) {
				// Read M-element chunk at a time from the file
					for (j = 0; j < (readBuffer < N ? readBuffer : N); j++) {
						String t = br.readLine();
						if (t != null)
							buffer[j] = Integer.parseInt(t);
						else
							break;
					}
					// Sort M elements
					long sortTime1 = System.currentTimeMillis();
					Arrays.sort(buffer);		
					long sortTime2 = System.currentTimeMillis();
					long tempSortTime = (sortTime2 - sortTime1);
					sortTime += tempSortTime;

					// Write the sorted numbers to temp file
					FileWriter fw = new FileWriter(tfile + Integer.toString(i) + ".txt");
					PrintWriter pw = new PrintWriter(fw);
					for (int k = 0; k < j; k++)
						pw.println(buffer[k]);
					pw.close();
					fw.close();
				}

				System.out.println("Total Sorting time in Phase 1: " + sortTime + " Msec");

				br.close();
				fr.close();

				long p1Time2 = System.currentTimeMillis();

				System.out.println("Phase One time: " + (p1Time2 - p1Time1) + " Msec");
			
		}catch(Exception e){e.getMessage();}
			}

			
	/**
	 * Phase two for the TPMMS algorithm.
	 * 
	 * @author Lin Zhu
	 */

@SuppressWarnings("unused")
private static void doPhase2(int N, int slices, int inBufferSize,int outBufferSize){
	
	long p2Time1 = System.currentTimeMillis();
	
	try{
	int i, j;
	i = j = 0;
	String tfile = "temp-file-";
	ArrayList<ArrayList<Integer>> bufferMatrix = new ArrayList<ArrayList<Integer>>(slices);
	ArrayList<Integer> outBuffer = new ArrayList<Integer>(outBufferSize);
	BufferedReader[] brs = new BufferedReader[slices];

	// Read first inBufferSize of integers of all the sublists by turns
	for (i = 0; i < slices; i++) {
		brs[i] = new BufferedReader(new FileReader(tfile + Integer.toString(i) + ".txt"));
		ArrayList<Integer> tempBuffer = new ArrayList<Integer>(inBufferSize);

		for (j = 0; j < inBufferSize; j++) {
			String t = brs[i].readLine();
			tempBuffer.add(Integer.parseInt(t));
		}
		bufferMatrix.add(tempBuffer);
	}

	FileWriter fw = new FileWriter("D:\\external-sorted.txt");
	PrintWriter pw = new PrintWriter(fw);

	for (int k = 0; k < (int) Math.ceil((double) N / outBufferSize); k++) {

		// Find the minimun integer of all the top numbers among the sublists
		// Find until the output buffer is full
		for (i = 0; i < outBufferSize; i++) {
			int min = bufferMatrix.get(0).get(0);
			int minFile = 0;
			for (j = 0; j < slices; j++) {
				if (min > bufferMatrix.get(j).get(0)) {
					min = bufferMatrix.get(j).get(0);
					minFile = j;
				}
			}

			outBuffer.add(min);

			// After find and add the min number in bufferMatrix[minFile], remove the top number

			bufferMatrix.get(minFile).remove(0);

			if (bufferMatrix.get(minFile).isEmpty()) {

				for (j=0;j < inBufferSize;j++) {
					String t = brs[minFile].readLine();
					if (t != null){
						bufferMatrix.get(minFile).add(
								Integer.parseInt(t));
						}
					else
						bufferMatrix.get(minFile)
								.add(Integer.MAX_VALUE);
					break;
				}
			}

			else
				continue;
		}

		for (i = 0; i < outBufferSize; i++) {
			if (outBuffer.get(i) != Integer.MAX_VALUE) {
				pw.println(outBuffer.get(i));
			} else
				continue;
		}

		outBuffer.clear();

	}
	for (i = 0; i < slices; i++)
		brs[i].close();

	pw.close();
	fw.close();

	long p2Time2 = System.currentTimeMillis();
	System.out.println("Phase Two time: " + (p2Time2 - p2Time1) + " Msec");
} catch (FileNotFoundException e){
	   e.printStackTrace();
	  } catch (IOException e) {
	   e.printStackTrace();}  
}



	

	public static int count(String filename) throws IOException {

		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count + 1;

		} finally {
			is.close();
		}

	}

	public static ArrayList<String> readInfo() throws IOException {
		ArrayList<String> info = new ArrayList<String>(5);
		Scanner in = null;

		try {
			in = new Scanner(System.in);
			System.out.println("Please input the name of the file to be sorted:");
			String fileName = in.next();
//			System.out.println("Please input memory usage limit(MB) :");
//			String memUsage = in.nextLine();
//			System.out.println("Please input the rate of memory usage limit for in/out buffers (eg. 0.5) :");
//			String bufferUsage = in.nextLine();
			System.out.println("Please input the number of sublists :");
			String subNum = in.next();
			System.out.println("Please input the maximum number of integers that a input buffer can hold :"); 
			String inSize = in.next();
			System.out.println("Please input the maximum number of integers that a output buffer can hold :");
			String outSize = in.next();
			
			long readTime1 = System.currentTimeMillis();
			int lineNum = count(fileName);
			long readTime2 = System.currentTimeMillis();
			
//			int memUse = Integer.parseInt(memUsage) * 1024 * 1024;
//			int bufUse = Integer.parseInt(bufferUsage);
			
			System.out.println("Name of the file to be sorted�� " + fileName);
			System.out.println("Time of counting line numbers�� " + (readTime2 - readTime1) + " Msec");
			
			info.add(fileName);
			info.add(Integer.toString(lineNum));
			info.add(subNum);
			info.add(inSize);
			info.add(outSize);

		} catch (Exception e) {
			System.out.println("Error Input infomation , please re-run the program.");
		}
	
		in.close();
		
		return info;
	}

	public static void main(String[] args) throws IOException {
		
			ArrayList<String> infomation = readInfo();

			long startTime = System.currentTimeMillis();

			externalSort(infomation);

			long endTime = System.currentTimeMillis();

			System.out.println("Total Execution Time�� " + (endTime - startTime) + "Msec");
	}
}