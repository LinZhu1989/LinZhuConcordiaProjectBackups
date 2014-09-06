package server;

import java.text.SimpleDateFormat;

import server.RecordInterface;

/**
 * Implement class for the MisingRecord
 * 
 * @author Jingang.Li
 * 
 */

public class MissingRecord implements RecordInterface,
		Comparable<MissingRecord> {

	private String recordID;
	private String firstName;
	private String lastname;
	private char status;
	private String address;
	private String lastSeenDate;
	private String lastSeenPlace;

	@Override
	public String getFirstName() {
		return this.firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastname() {
		return this.lastname;
	}

	@Override
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String getRecordID() {
		return this.recordID;
	}

	@Override
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	@Override
	public char getStatus() {
		return this.status;
	}

	@Override
	public void setStatus(char status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLastSeenDate() {
		return lastSeenDate;
	}

	public void setLastSeenDate(String lastSeenDate) {
		this.lastSeenDate = lastSeenDate;
	}

	public String getLastSeenPlace() {
		return lastSeenPlace;
	}

	public void setLastSeenPlace(String lastSeenPlace) {
		this.lastSeenPlace = lastSeenPlace;
	}

	public MissingRecord(String recordId, String firstName, String lastName,
			String address, String lastSeenDate, String lastSeenPlace,
			char status) {
		this.recordID = recordId;
		this.firstName = firstName;
		this.lastname = lastName;
		this.address = address;
		this.lastSeenPlace = lastSeenPlace;
		this.lastSeenDate = lastSeenDate;
		this.status = status;
	}

	public String toString() {
		return String.format("MissingRecord#%s#%s#%s#%s#%s#%s#%s",
				this.recordID, this.firstName, this.lastname, this.address,
				this.lastSeenPlace, this.lastSeenDate, this.status);
	}

	public int compareTo(MissingRecord cr) {
		return this.recordID.compareTo(cr.recordID);
	}

	public static boolean isValidRecordID(String str) {
		return str.trim().matches("MR[0-9][0-9][0-9][0-9][0-9]");
	}

	public static boolean isValidStatus(String str) {
		if (str.length() > 1)
			return false;

		return str.charAt(0) == RecordInterface.MR_FOUND
				|| str.charAt(0) == RecordInterface.MR_MISSING;
	}

	@Override
	public boolean parseString(String str) {
		String[] result = str.split("#");
		if (result.length != 8)
			return false;
		String value = result[0].trim();
		if (value.compareTo("MissingRecord") != 0)
			return false;
		value = result[1].trim();
		if (isValidRecordID(value) == false)
			return false;
		else
			this.setRecordID(value);

		value = result[2].trim();
		this.setFirstName(value);

		value = result[3].trim();
		this.setLastname(value);

		value = result[4].trim();
		this.setAddress(value);

		value = result[5].trim();
		this.setLastSeenPlace(value);

		value = result[6].trim();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		try {
			df.parse(value);
			this.setLastSeenDate(value);
		} catch (Exception e) {
			return false;
		}

		value = result[7].trim();
		if (isValidStatus(value) == false)
			return false;
		else
			this.setStatus(value.charAt(0));

		return true;
	}
}
