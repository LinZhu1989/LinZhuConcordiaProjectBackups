package server;

/**
 * Interface for the CriminalRecord and MissingRecord
 * 
 * @author Jingang.Li
 */
public interface RecordInterface {

	char RECORD_CRIMINAL = 'c';
	char RECORD_MISSING = 'm';

	char CR_CAPTURED = 'c';
	char CR_ON_RUN = 'o';

	char MR_FOUND = 'f';
	char MR_MISSING = 'm';

	String getFirstName();

	void setFirstName(String firstName);

	String getLastname();

	void setLastname(String lastname);

	String getRecordID();

	void setRecordID(String recordID);

	char getStatus();

	void setStatus(char status);

	boolean parseString(String str);
}