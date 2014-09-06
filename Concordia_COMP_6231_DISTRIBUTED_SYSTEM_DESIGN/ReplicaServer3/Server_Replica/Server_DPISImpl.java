package Server_Replica;

import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File name : ServerDPISImpl.java
 * 
 * COMP 6231 DISTRIBUTED SYSTEM DESIGN Project 1
 * 
 * @author Lin Zhu 6555659
 * 
 *         This is the Implementation of the CORBA interface ServerDPIS
 */

@SuppressWarnings("serial")
public class Server_DPISImpl implements Serializable {

	private String nameDPIS;
	private int port;
	private int pubPort;
	private int valID = 10000;
	private static int SPVMport = 8888;
	private static int SPLport = 8889;
	private static int SPBport = 8890;
	private ConcurrentHashMap<Character, ArrayList<Records>> stationSeverData = null;

	public Server_DPISImpl(String name, int port, int publicPort) {
		logFile.write(name, "Server object is running......");
		this.nameDPIS = name;
		this.port = port;
		this.pubPort = publicPort;
		stationSeverData = new ConcurrentHashMap<Character, ArrayList<Records>>();		
		System.out.println(">> Server "+nameDPIS
				+ " is running......");
		initialData();
		
	}
	
	public void initialData(){
		for (int i = 0; i < 26; i++) {
			stationSeverData.put((char) (i + 65), new ArrayList<Records>());
			
		}
//		String Initial = "BCDEFGHIJKLMNOPQRSTUVWXYZ";
//		/*
//		 * Initialize records in the hash table 20 records for each letter
//		 * except 'A' 500 records in total
//		 */
//		int startVal = 20000;
//		for (int i = 0; i < 25; i++) {
//			stationSeverData.put(Initial.charAt(i),
//					Records.getArrayList(Initial.charAt(i), startVal));
//			startVal += 20;
//		}
//
//		/*
//		 * Add some particular records in the hash table 3 CR and 3 MR records
//		 * Lastnames of all of them are begin with 'A'
//		 */
//		ArrayList<Records> information = new ArrayList<Records>();
//		information.add(new Records("CR30001", String.format("%s.%s,%s,%s",
//				"CRXYZ", "ACRone", "tall", "on the run")));
//		information.add(new Records("CR30002", String.format("%s.%s,%s,%s",
//				"CRXYZ", "ACRtwo", "strong", "on the run")));
//		information.add(new Records("CR30003", String.format("%s.%s,%s,%s",
//				"CRXYZ", "ACRthree", "short", "on the run")));
//		information.add(new Records("MR30001", String.format("%s.%s,%s,%s",
//				"MRXYZ", "AMRone", "smart", "missing")));
//		information.add(new Records("MR30002", String.format("%s.%s,%s,%s",
//				"MRXYZ", "AMRtwo", "young", "missing")));
//		information.add(new Records("MR30003", String.format("%s.%s,%s,%s",
//				"MRXYZ", "AMRthree", "long hair", "missing")));
//
//		stationSeverData.put('A', information);
//
//		logFile.write("ServerDPIS", "Created Server" + " " + nameDPIS + " "
//				+ "and initialized DataBase");
//		logFile.write("ServerDPIS", "There are 526 records in " + nameDPIS
//				+ " server station");
	}

	public ConcurrentHashMap<Character, ArrayList<Records>> getServerDataBase() {
		return stationSeverData;
	}

	public int showCurrentID()
	{
		return valID;
	}
	
	public int getUnusedID()
	{
		return valID++;
	}

	public String getServerName() {
		return this.nameDPIS;
	}

	public int getServerPort() {
		return this.port;
	}
	
	public int getServerPublicPort() {
		return this.pubPort;
	}


	public boolean checkUniqueID(String ID) {

		boolean ifUnique = true;
		ConcurrentHashMap<Character, ArrayList<Records>> tempHash;
		tempHash = this.getServerDataBase();
		for (ArrayList<Records> e : tempHash.values()) {
			for (int i = 0; i < e.size(); i++) {
				if (ID == e.get(i).getID()) {
					ifUnique = false;
					return ifUnique;
				}
			}
		}

		return true;

	}
	
	public boolean addTransferredRecord(String request){
		
		String uniqueID;
		char typeRecord = request.toUpperCase().charAt(0);
		char initial = request.toUpperCase().charAt(1);
		boolean condition = false;
		boolean result = false;
		String recordInfo = request.substring(2,
				request.length());
		

		do {
			if (typeRecord == 'C') {
				uniqueID = "CR" + Integer.toString(valID++);
			} else {
				uniqueID = "MR" + Integer.toString(valID++);
			}
			condition = checkUniqueID(uniqueID);
		} while (condition == false);
		// Try to add the record into local database
		if (stationSeverData.get(initial) == null) {
			ArrayList<Records> tempV = new ArrayList<Records>();
			result = tempV.add(new Records(
					uniqueID, recordInfo));
			stationSeverData
					.put((Character) initial, tempV);
		} else {
			result = stationSeverData.get(initial)
					.add(new Records(uniqueID, recordInfo));
		}
		return result;
	}
	
	
	public String getTotalRecordsNumber() {
		int counter = 0;
		ConcurrentHashMap<Character, ArrayList<Records>> temp = this
				.getServerDataBase();
		synchronized (temp) {

			for (ArrayList<Records> e : temp.values()) {
				counter = counter + e.size();
			}
			return (this.getServerName() + " " + counter);
		}
	}
	
	public String getTotalRecordsNumber(String type) {
		int counter = 0;
		ConcurrentHashMap<Character, ArrayList<Records>> temp = this
				.getServerDataBase();
		synchronized (temp) {

			for (ArrayList<Records> e : temp.values()) {
				for (int i=0;i<e.size();i++){
					if(e.get(0).getID().charAt(0)==type.charAt(0)){
						counter++;
					}
				}
			}
			return (this.getServerName() + " " + counter);
		}
	}

	/*
	 * Function : createCRecord() When a police officer invokes this method from
	 * his/her station through a client program called OfficerClient, the server
	 * associated with this officer (determined by the BadgeID prefix) attempts
	 * to create a CriminalRecord with the information passed, assigns a unique
	 * RecordID and inserts the Record at the appropriate location in the hash
	 * table. The server returns information to the officer whether the
	 * operation was successful or not and both the server and the officer store
	 * this information in their logs.
	 */
	public String createCRecord(String firstName, String lastName,
			String description, String status, String badgeID) {
		String uniqueCRID = null;
		boolean condition = false;
		boolean returnCondition = false;
		String result = null;
		char initial = lastName.toUpperCase().charAt(0);

		synchronized (this.getServerDataBase().get(initial)) {
			logFile.write(
					this.getServerName(),
					badgeID
							+ " invokes >> "
							+ " createCRecord() >> Start to create criminal record ......");
			do {
				uniqueCRID = "CR" + Integer.toString(valID++);
				condition = this.checkUniqueID(uniqueCRID);
			} while (condition == false);
			if (this.getServerDataBase().get(initial) == null) {
				ArrayList<Records> tempV = new ArrayList<Records>();
				returnCondition = tempV.add(new Records(uniqueCRID, String
						.format("%s.%s,%s,%s", firstName, lastName,
								description, status)));
				this.getServerDataBase().put((Character) initial, tempV);
				result = (returnCondition == true ? "Add CRecord succeed!"
						: "[ERROR] >> Add CRecord failed!");
				logFile.write(this.getServerName(), badgeID + " >> " + result);
				return result;
			} else {
				returnCondition = this
						.getServerDataBase()
						.get(initial)
						.add(new Records(uniqueCRID, String.format(
								"%s.%s,%s,%s", firstName, lastName,
								description, status)));
			}
			result = (returnCondition == true ? "Add CRecord succeed!"
					: "[ERROR] >> Add CRecord failed!");
			logFile.write(this.getServerName(), badgeID + " >> " + result);
			return result;
		}
	}


	/*
	 * Function : createMRecord() When a police officer invokes this method from
	 * a OfficerClient, the server associated with this officer (determined by
	 * the BadgeID prefix) attempts to create a MissingRecord with the
	 * information passed, assigns a unique RecordID and inserts the Record at
	 * the appropriate location in the hash table. The server returns
	 * information to the officer whether the operation was successful or not
	 * and both the server and the officer store this information in their logs.
	 */
	public String createMRecord(String firstName, String lastName,
			String address, String lastDate, String lastLocation,
			String status, String badgeID) {

		String uniqueMRID = null;
		boolean condition = false;
		boolean returnCondition = false;
		String result = null;
		char initial = lastName.toUpperCase().charAt(0);

		synchronized (this.getServerDataBase().get(initial)) {
			logFile.write(
					this.getServerName(),
					badgeID
							+ " invokes >> "
							+ " createMRecord() >> Start to create missing record ......");
			do {
				uniqueMRID = "MR" + Integer.toString(valID++);
				condition = this.checkUniqueID(uniqueMRID);
			} while (condition == false);
			if (this.getServerDataBase().get(initial) == null) {
				ArrayList<Records> tempV = new ArrayList<Records>();
				returnCondition = tempV.add(new Records(uniqueMRID, String
						.format("%s.%s,%s,%s,%s,%s", firstName, lastName,
								address, lastDate, lastLocation, status)));
				this.getServerDataBase().put((Character) initial, tempV);
				result = (returnCondition == true ? "Add MRecord succeed!"
						: "[ERROR] >> Add MRecord failed!");
				logFile.write(this.getServerName(), badgeID + " >> " + result);
				return result;
			} else {
				returnCondition = this
						.getServerDataBase()
						.get(initial)
						.add(new Records(uniqueMRID, String.format(
								"%s.%s,%s,%s,%s,%s", firstName, lastName,
								address, lastDate, lastLocation, status)));
			}
			result = (returnCondition == true ? "Add MRecord succeed!"
					: "[ERROR] >> Add MRecord failed!");
			logFile.write(this.getServerName(), badgeID + " >> " + result);
			return result;
		}
	}


	/*
	 * Function : editCRecord() When invoked by an officer, the server
	 * associated with this officer (determined by the BadgeID) searches in the
	 * hash table to find the recordID and changes the status of the record
	 * according to the newStatus if it is found. Upon success or failure it
	 * returns a message to the officer and the logs are updated with this
	 * information. If the status does not match the RecordType, it is invalid.
	 * For example, if the found Record is a CriminalRecord and the newStatus is
	 * for a MissingRecord, the server shall return an error.
	 */
	public String editCRecord(String lastName, String recordID,
			String newStatus, String badgeID) {
		String result = null;
		boolean found = false;
		boolean replace = false;
		char initial = lastName.toUpperCase().charAt(0);
		synchronized (this.getServerDataBase().get(initial)) {
			logFile.write(
					this.getServerName(),
					badgeID
							+ " invokes >> "
							+ " editCRecord() >> Start to edit a criminal record ......");
			if (newStatus.equalsIgnoreCase("captured") == false
					&& newStatus.equalsIgnoreCase("on the run") == false) {
				result = "[ERROR] >> Fail to edit a criminal record with an incorrect status type!";
				logFile.write(this.getServerName(), badgeID + " >> " + result);
				return result;
			}
			ArrayList<Records> tempV = this.getServerDataBase().get(initial);

			if (tempV == null) {
				result = "[ERROR] >> Fail to edit a criminal record with an incorrect lastName!";
				logFile.write(this.getServerName(), badgeID + " >> " + result);
				return result;
			}
			for (int i = 0; i < tempV.size(); i++) {
				found = tempV.get(i).getID().equals(recordID);
				if (found == true) {
					replace = this.getServerDataBase().get(initial).get(i)
							.setInfo(newStatus);
					result = (replace == true ? "The new status is updated!"
							: "[ERROR] >> Fail to edit a criminal record!");
					logFile.write(this.getServerName(), badgeID + " >> "
							+ result);
					return result;
				}
			}
			result = "[ERROR] >> Fail to find the recordID!";
			logFile.write(this.getServerName(), badgeID + " >> " + result);
			return result;
		}
	}


	/*
	 * Function : getRecordCounts() A police officer invokes this method from
	 * his/her OfficerClient and the server associated with that officer
	 * concurrently finds out the number of records (both CR and MR) in the
	 * other stations using UDP/IP sockets and returns the result to the
	 * officer. Please note that it only returns the record counts (a number)
	 * and not the records themselves. For example if SPVM has 6 records, SPL
	 * has 7 and SPB had 8, it should return the following: SPVM 6, SPL 7, SPB
	 * 8.
	 */
	public String getRecordCounts(String badgeID) {

		logFile.write(this.getServerName(), badgeID + " invokes >> "
				+ " getRecordCounts() >> Start to get record counts ......");
		String result = "Current numbers: ";
		try {
			int port = 0;
			String[] names = { "SPVMserver", "SPBserver", "SPLserver" };
			for (int i = 0; i < names.length; i++) {
				logFile.write(this.getServerName(), badgeID + " >> "
						+ "Check number of records in station" + " " + names[i]);

				if (names[i].toString().equalsIgnoreCase("SPVMserver")) {
					port = SPVMport;
				} else if (names[i].toString().equalsIgnoreCase("SPLserver")) {
					port = SPLport;
				} else if (names[i].toString().equalsIgnoreCase("SPBserver")) {
					port = SPBport;
				}
				DatagramSocket dataSocket = new DatagramSocket();
				byte[] request = new byte[100];
				request = "Number of records request".getBytes();
				logFile.write(this.getServerName(), badgeID + " >> "
						+ "Asking for number of records ......");
				InetAddress server = InetAddress.getByName("localhost");
				logFile.write(this.getServerName(), badgeID + " >> "
						+ "Sending the request ......");
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, server, port);
				dataSocket.send(requestPacket);
				byte[] buffer = new byte[100];
				DatagramPacket reply = new DatagramPacket(buffer,
						buffer.length, server, port);
				dataSocket.receive(reply);
				logFile.write(this.getServerName(), badgeID + " >> "
						+ "Successfully recieved the reply ......");
				result += new String((new String(reply.getData()) + " "))
						.trim() + " ";
				logFile.write(this.getServerName(), badgeID + " >> "
						+ "The reply is: " + result);
				dataSocket.close();
			}
		} catch (Exception e) {
			System.out.println("error in getRecordCounts()" + e.getMessage());
			logFile.write(
					this.getServerName(),
					badgeID + " >> "
							+ "[ERROR] >> Fail to use getRecordCounts()!"
							+ e.getMessage());
		}
		return result;

	}

	/*
	 * Function : transferRecord() When a police officer invokes this method
	 * from his/her station, the server associated with this officer (determined
	 * by the badgeID prefix) searches its hash table to find if the record with
	 * recordID exists. If it exists, the entire record is transferred to the
	 * remoteStationServer. Note that the record should be removed from the hash
	 * table of the initial server and should be added to the hash table of the
	 * remoteStationServer atomically. The server informs the officer whether
	 * the operation was successful or not and both the server and the officer
	 * store this information in their logs.
	 */

	public String transferRecord(String badgeID, String recordID,
			String remoteStationServerName) {
		String badgeCheck = badgeID.substring(0, 3);
		String remoteCheck = remoteStationServerName.substring(0, 3);
		String result = null;
		boolean ifTransfer = false;
		boolean ifExist = false;
		int keyVal = 0;
		String theRecordID = null;
		String theRecordInfo = null;
		String dictionary = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int remotePort = 0;

		int curReocrd = 0;

		logFile.write(
				this.getServerName(),
				badgeID
						+ "invokes >> transferRecord() >> Start to transfer record ......");
		if (badgeCheck.equalsIgnoreCase("SPV")
				| badgeCheck.equalsIgnoreCase("SPL")
				| badgeCheck.equalsIgnoreCase("SPB")) {
			try {
				if ((!remoteCheck.equalsIgnoreCase("SPV")
						& !remoteCheck.equalsIgnoreCase("SPL") & !remoteCheck
							.equalsIgnoreCase("SPB"))
						| remoteCheck.equalsIgnoreCase(badgeCheck)) {
					result = "[ERROR] >> remoteName is incorrect!";
					logFile.write(this.getServerName(), badgeID + " >> "
							+ badgeCheck + result);
					return result;
				}

				for (int i = 0; i < 26; i++) {
					synchronized (this.getServerDataBase().get(
							dictionary.charAt(i))) {
						int size = this.getServerDataBase()
								.get(dictionary.charAt(i)).size();
						for (int j = 0; j < size; j++) {
							if (this.getServerDataBase()
									.get(dictionary.charAt(i)).get(j).getID()
									.equalsIgnoreCase(recordID)) {
								keyVal = i;
								curReocrd = j;
								ifExist = true;
								theRecordID = recordID;
								theRecordInfo = this.getServerDataBase()
										.get(dictionary.charAt(keyVal)).get(j)
										.getInfo();
								this.getServerDataBase()
										.get(dictionary.charAt(i))
										.remove(curReocrd);
								break;
							} else
								continue;
						}
					}
					if (ifExist) {
						break;
					}
				}
				if (ifExist) {
					switch (remoteCheck.toUpperCase()) {
					case "SPV":
						remotePort = SPVMport;
						break;
					case "SPL":
						remotePort = SPLport;
						break;
					case "SPB":
						remotePort = SPBport;
						break;
					}
					int time = 0;
					while (!ifTransfer) {
						ifTransfer = transfer(
								String.valueOf(recordID.charAt(0)) + String.valueOf(dictionary.charAt(keyVal))
										+ theRecordInfo, remotePort);
						time++;
						if (time >= 10) {
							break;
						}
					}
					if (!ifTransfer && ifExist) {
						this.getServerDataBase().get(dictionary.charAt(keyVal))
								.add(new Records(theRecordID, theRecordInfo));
					}

					result = (ifTransfer == true ? "transferRecord succeed!"
							: "[ERROR1] >> Fail to transfer the record!");					
					logFile.write(this.getServerName(), badgeID + " >> "
							+ result);
					return result;
				} else {
					result = "[ERROR] >> RecordID is not existed!";
				}
			} catch (Exception e) {
				System.out
						.println("error in transferRecord()" + e.getMessage());
				logFile.write(this.getServerName(),
						badgeID + " >> "
								+ "[ERROR2] >> Fail to use transferRecord()!"
								+ e.getMessage());
			}

		} else {
			result = "[ERROR] >> badgeID is illegal!";
			logFile.write(this.getServerName(), badgeID + " >> " + badgeCheck
					+ result);
			return result;
		}

		return result;
	}

	/*
	 * Function : transfer() helps to transfer the local record to remote server
	 * station, return ture if successful added or false if the record has not
	 * been added
	 */
	public boolean transfer(String record, int remotePort) {

		try {
			DatagramSocket dataSocket = new DatagramSocket();
			byte[] request = new byte[100];
			request = record.getBytes();
			InetAddress server = InetAddress.getByName("localhost");
			DatagramPacket requestPacket = new DatagramPacket(request,
					request.length, server, remotePort);
			dataSocket.send(requestPacket);
			byte[] buffer = new byte[100];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length,
					server, remotePort);
			dataSocket.receive(reply);
			String transferResult = new String(reply.getData()).trim();
			dataSocket.close();
			if (transferResult.equalsIgnoreCase("TRUE")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("error in transfer()" + e.getMessage());
		}
		return false;
	}
	
	
	
}

