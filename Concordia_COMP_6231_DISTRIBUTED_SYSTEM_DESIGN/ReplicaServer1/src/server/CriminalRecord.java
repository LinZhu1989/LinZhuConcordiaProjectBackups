package server;

import server.RecordInterface;

/**
 * The implement class for the Criminal class
 * 
 * @author Jingang.Li
 * 
 */
public class CriminalRecord implements RecordInterface,
		Comparable<CriminalRecord> {
	private String recordID;
	private String firstName;
	private String lastname;
	private char status;
	private String description;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#getLastname()
	 */
	@Override
	public String getLastname() {
		return lastname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#setLastname(java.lang.String)
	 */
	@Override
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#getRecordID()
	 */
	@Override
	public String getRecordID() {
		return recordID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#setRecordID(java.lang.String)
	 */
	@Override
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#getStatus()
	 */
	@Override
	public char getStatus() {
		return status;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dpsi.IRecord#setStatus(int)
	 */
	@Override
	public void setStatus(char status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CriminalRecord(String recordId, String firstName, String lastName,
			String description, char status) {
		this.recordID = recordId;
		this.firstName = firstName;
		this.lastname = lastName;
		this.description = description;
		this.status = status;
	}

	public String toString() {
		return String.format("CriminalRecord#%s#%s#%s#%s#%s", this.recordID,
				this.firstName, this.lastname, this.description, this.status);
	}

	public int compareTo(CriminalRecord cr) {
		return this.recordID.compareTo(cr.recordID);
	}

	public static boolean isValidRecordID(String str) {
		return str.trim().matches("CR[0-9][0-9][0-9][0-9][0-9]");
	}

	public static boolean isValidStatus(String str) {
		if (str.length() > 1)
			return false;

		return str.charAt(0) == RecordInterface.CR_CAPTURED
				|| str.charAt(0) == RecordInterface.CR_ON_RUN;
	}

	@Override
	public boolean parseString(String str) {
		String[] result = str.split("#");
		if (result.length != 6)
			return false;
		String value = result[0].trim();
		if (value.compareTo("CriminalRecord") != 0)
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
		this.setDescription(value);

		value = result[5].trim();
		if (isValidStatus(value) == false)
			return false;
		else
			this.setStatus(value.charAt(0));

		return true;
	}
}
