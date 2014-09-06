package Server_Replica;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * File name : Records.java
 * 
 * COMP 6231 DISTRIBUTED SYSTEM DESIGN Project 1
 * 
 * @author Lin Zhu 6555659
 * 
 *         This is the Records class which shows the construct of the 2 types of
 *         records.
 */

/*
 * There are two types of Records: CriminalRecord and MissingRecord. A Record
 * can be identified by a unique RecordID starting with CR (for CriminalRecord)
 * or MR (for MissingRecord) and ending with a 5 digits number (e.g. CR10000 for
 * a CriminalRecord or MR10001 for a MissingRecord). A CriminalRecord also
 * contains the first name and last name of the criminal, a description of the
 * crime committed by this person and a status (captured/on the run). Similarly,
 * a MissingRecord contains information about a missing individual. It holds the
 * first name, last name, last known address, date and place last seen and a
 * status (found/missing).
 */

@SuppressWarnings("serial")
public class Records implements Serializable  {
	private String ID;
	private String RecordInfo;
	public Records() {

	}

	public Records(String ID, String RecordInfo) {
		setParameter(ID, RecordInfo);
	}

	public synchronized void setParameter(String ID, String RecordInfo) {
		this.ID = ID;
		this.RecordInfo = RecordInfo;
	}

	public String getID() {
		return ID;
	}

	public String getInfo() {
		return RecordInfo;
	}

	public synchronized boolean setInfo(String newStatus) {
		boolean result = false;
		Pattern P = Pattern.compile("on the run|captured");
		Matcher M = P.matcher(this.RecordInfo);
		result = M.find();
		this.RecordInfo = new String(M.replaceAll(newStatus));
		return result;
	}

	public static ArrayList<Records> getArrayList(char c,int startVal) {
		ArrayList<Records> temp = new ArrayList<Records>(20);
		int valueID = startVal;
		for (int i = 0; i < 10; i++) {
			temp.add(new Records("CR"+Integer.toString(valueID++), RecordGenerator
					.randomCRecord(c)));
			temp.add(new Records("MR"+Integer.toString(valueID++), RecordGenerator
					.randomMRecord(c)));
		}
		return temp;
	}

	public static ArrayList<String> getIDList(ArrayList<Records> Vtemp) {
		ArrayList<String> tempArraylist = new ArrayList<String>(Vtemp.size());
		for (Records e : Vtemp) {
			tempArraylist.add(e.getID());
		}
		return tempArraylist;
	}

}
