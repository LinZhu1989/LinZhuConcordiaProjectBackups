package server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * This is the class used to log the message to the file
 * @author Jingang.Li
 *
 */
public class FileLogger {
	private String fileName;
	
	public FileLogger(String name) {
		fileName = name;
		File f = new File(fileName);
		//delete the log file the first time when we run the program.
		if (f.exists())
			f.delete();		
	}
	
	public void logMessage(String msg) {
		FileWriter file;
		PrintWriter wtr; 
		try {
			file = new FileWriter(fileName, true);
			wtr = new PrintWriter(file);	
			wtr.println(msg);
			wtr.close();
			file.close();		
			} catch (IOException e) {
				e.printStackTrace();
			}
		System.out.println(msg);
	}
}
