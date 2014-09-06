package replica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class PackageAnalyzer{
	private String lastName;
	private String firstName;
	private String badgeID;
	private String description;
	private String status;
	private String address;
	private String lastDate;
	private String lastLocation;
	private String recordID;
	private String newStatus;
	//private String errorHostIP;  //maybe not used
	private int errorHostPort;
	private int crashHostPort;
	private String remoteServerName;
	private int requestID;
	private int hostPort;
	private InetAddress hostAddress;
	private String command;
	public PackageAnalyzer(){
		
	}
	public PackageAnalyzer(DatagramPacket requestPacket){
		//System.out.println(new String(requestPacket.getData()).trim());
		String [] request =( new String(requestPacket.getData()).trim()).split(",");
		/*for(int i = 0;i<request.length;i++){
			System.out.println(request[i]);
		}*/
		if(request[0].equalsIgnoreCase("createCRecord")){
			this.command = request[0];
			this.firstName = request[1];
			this.lastName = request[2];
			this.description =request[3];
			this.status = request[4];
			this.badgeID = request[5];
			this.requestID = Integer.parseInt(request[6]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
			
		}
		else if(request[0].equalsIgnoreCase("createMRecord")){
			this.command = request[0];
			this.firstName = request[1];
			this.lastName = request[2];
			this.address =request[3];
			this.lastDate = request[4];
			this.lastLocation = request[5];
			this.status = request[6];
			this.badgeID = request[7];
			this.requestID = Integer.parseInt(request[8]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
			
		}
		else if(request[0].equalsIgnoreCase("editCRecord")){
			this.command = request[0];
			this.lastName = request[1];
			this.recordID = request[2];
			this.newStatus =request[3];
			this.badgeID = request[4];
			this.requestID = Integer.parseInt(request[5]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		}
		else if(request[0].equalsIgnoreCase("getRecordCounts")){
			this.command = request[0];
			this.badgeID = request[1];
			this.requestID = Integer.parseInt(request[2]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		}
		else if(request[0].equalsIgnoreCase("transferRecord")){
			
			this.command = request[0];
			this.badgeID = request[1];
			 this.recordID = request[2];
			this.remoteServerName =request[3];
			this.requestID = Integer.parseInt(request[4]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
			
		}
		else if(request[0].equalsIgnoreCase("error")){
			this.command = request[0];
			this.errorHostPort = Integer.parseInt(request[1]);
			this.requestID =Integer.parseInt(request[2]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		}
		else if(request[0].equalsIgnoreCase("crash")){
			this.command = request[0];
			this.crashHostPort = Integer.parseInt(request[1]);
			this.requestID =Integer.parseInt(request[2]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		}
		
	
	}
	public String getFirstName(){
		return this.firstName;
	}
	public String getLastName(){
		return this.lastName;
	}
	public String getBadgeID(){
		return this.badgeID;
	}
	public String getRecordID(){
		return this.recordID;
	}
	public String getStatus(){
		return this.status;
	}
	public String getNewStatus(){
		return this.newStatus;
	}
	public String getDescription(){
		return this.description;
	}
	public int getRequestID(){
		return this.requestID;
	}
	public String getLastDate(){
		return this.lastDate;
	}
	public String getLastLocation(){
		return this.lastLocation;
	}
	public String getAddress(){
		return this.address;
	}
	public int getErrorHostPort(){
		return this.errorHostPort;
	}
	public int getCrashHostPort(){
		return this.crashHostPort;
	}
	public String getRemoteServerName(){
		return this.remoteServerName;
	}
	public InetAddress getHostInetAddress(){
		return this.hostAddress;
	}
	public int getHostPort(){
		return this.hostPort;
	}
	public String getCommand(){
		return this.command;
	}
}
class CRecordIDGenerator {
	public CRecordIDGenerator() {

	}

	public static int ID = 10000;

	public synchronized static String getID() {
		if (ID < 99999) {			
			return ("CR" + ID++);
		} else {
			return null;
		}
	}

}

class MRecordIDGenerator {
	public static int ID = 10000;

	public MRecordIDGenerator() {

	}

	public synchronized static String getID() {
		if (ID < 99999) {			
			return ("MR" + ID++);
		} else {
			return null;
		}
	}

}

/**
 * 
 * @author Yulong Song
 * 
 */
// This is the Record Structure ID + Info
class Records implements Serializable {
	public String ID;
	public String Info;

	public Records() {

	}

	public Records(String ID, String Info) {
		setParameter(ID, Info);
	}

	public synchronized void setParameter(String ID, String Info) {
		this.ID = ID;
		this.Info = Info;
	}

	public String getID() {
		return ID;
	}

	public String getInfo() {
		return Info;
	}

	public synchronized boolean setInfo(String newStatus) {
		boolean flag = false;
		Pattern P = Pattern.compile("on the run|captured");
		Matcher M = P.matcher(this.Info);
		flag = M.find();
		this.Info = new String(M.replaceAll(newStatus));

		return flag;
	}

	public String toString() {
		return ID + " " + Info;
	}

}

/**
 * 
 * @author Yulong Song
 * 
 */
// This is the log generator
class logFile {
	public logFile() {

	}

	public static synchronized void write(String fileNameHead, String logString) {
		try {
			String logFilePathName = null;
			Calendar cd = Calendar.getInstance();
			int year = cd.get(Calendar.YEAR);
			String month = addZero(cd.get(Calendar.MONTH) + 1);
			String day = addZero(cd.get(Calendar.DAY_OF_MONTH));
			String hour = addZero(cd.get(Calendar.HOUR_OF_DAY));
			String min = addZero(cd.get(Calendar.MINUTE));
			String sec = addZero(cd.get(Calendar.SECOND));

			File fileParentDir = new File("./log");
			if (!fileParentDir.exists()) {
				fileParentDir.mkdir();
			}
			if (fileNameHead == null || fileNameHead.equals("")) {
				logFilePathName = "./log/" + ".log";
			} else {
				logFilePathName = "./log/" + fileNameHead + ".log";
			}

			PrintWriter printWriter = new PrintWriter(new FileOutputStream(
					logFilePathName, true));
			String time = "[" + day + "/" + month + "/" + year + "-" + hour
					+ ":" + min + ":" + sec + "] ";
			printWriter.println(time + logString);
			printWriter.flush();

		} catch (FileNotFoundException e) {

			e.getMessage();
		}
	}

	public static String addZero(int i) {

		if (i < 10) {
			String tmpString = "0" + i;
			return tmpString;
		} else {
			return String.valueOf(i);
		}
	}
}



public class replica2 implements Runnable{
	private String ServerName;
	private int port;
	
	private Hashtable<Character, ArrayList<Records>> serverDataBase = null;
     public replica2(){
    	 
     }
	public replica2(String serverName, int port) {
		this.ServerName = serverName;
		this.port = port;
		serverDataBase = new Hashtable<Character, ArrayList<Records>>();
		for (int i = 0; i < 26; i++) {
			serverDataBase.put((char) (i + 65), new ArrayList<Records>());
			
		}
		
	}
/*public replica2 getSPVMserver(){
	return this.SPVMser;
}
public replica2 getSPLserver(){
	return this.SPLser;
}
public replica2 getSPBserver(){
	return this.SPBser;
}*/
public String getServerName(){
	return this.ServerName;
}
public int getServerPort(){
	return this.port;
}

public Hashtable<Character, ArrayList<Records>> getServerDataBase(){
	return this.serverDataBase;
}

	public String createCRecord(String firstName, String lastName,
			String description, String status, String badgeID) {
		// TODO Auto-generated method stub
		String uniqueCRID = null;
		boolean flag = false;
		boolean returnFlag = false;
		String result = null;

		char initial = lastName.toUpperCase().charAt(0);

		logFile.write(getServerName(), badgeID + " "
				+ "[createCRecord()]Add criminal record begin!");
		uniqueCRID = CRecordIDGenerator.getID();
		if (uniqueCRID == null) {
			result = "reach the maximum CRecordID!";
			logFile.write(getServerName(), result);
			return result;
		}

		synchronized (this.serverDataBase.get(initial)) {
			if (this.serverDataBase.get(initial) == null) {
				ArrayList<Records> tempV = new ArrayList<Records>();
				returnFlag = tempV.add(new Records(uniqueCRID, String
						.format("%s.%s,%s,%s", firstName, lastName,
								description, status)));
				this.serverDataBase.put((Character) initial, tempV);
				result = (returnFlag == true ? "Add CRecord succeed!"
						: "Add CRecord failed!");
				logFile.write(getServerName(), result);
				return result;
			} else {
				returnFlag = this.serverDataBase.get(initial).add(
						new Records(uniqueCRID, String.format("%s.%s,%s,%s",
								firstName, lastName, description, status)));
			}
			result = (returnFlag == true ? "Add CRecord succeed!"
					: "Add CRecord failed!");
			logFile.write(getServerName(), result);
			return result;
		}
	}

	
	public String createMRecord(String firstName, String lastName,
			String address, String lastDate, String lastLocation,
			String status, String badgeID) {
		// TODO Auto-generated method stub
		String uniqueMRID = null;
		boolean flag = false;
		boolean returnFlag = false;
		String result = null;
		char initial = lastName.toUpperCase().charAt(0);
		logFile.write(getServerName(), badgeID + " "
				+ "[createMRecord()]Add missing record begin!");

		uniqueMRID = MRecordIDGenerator.getID();
		if (uniqueMRID == null) {
			result = "reach the maximum MRecordID!";
			logFile.write(getServerName(), result);
			return result;
		}
		synchronized (this.serverDataBase.get(initial)) {
			if (this.serverDataBase.get(initial) == null) {

				ArrayList<Records> tempV = new ArrayList<Records>();
				returnFlag = tempV.add(new Records(uniqueMRID, String.format(
						"%s.%s,%s,%s,%s,%s", firstName, lastName, address,
						lastDate, lastLocation, status)));

				this.serverDataBase.put((Character) initial, tempV);
				result = (returnFlag == true ? "Add MRecord succeed!"
						: "Add MRecord failed!");
				logFile.write(getServerName(), result);
				return result;

			} else {

				returnFlag = this.serverDataBase.get(initial).add(
						new Records(uniqueMRID, String.format(
								"%s.%s,%s,%s,%s,%s", firstName, lastName,
								address, lastDate, lastLocation, status)));
			}
			result = (returnFlag == true ? "Add MRecord succeed!"
					: "Add MRecord failed!");
			logFile.write(getServerName(), result);
			return result;
		}
	}
/**
 * to-do finish the public void run()!!!!!!!!!!!!!!!!
 * @param badgeID
 * @return
 */
	
	public String getRecordCounts(String badgeID) {
		// TODO Auto-generated method stub
		logFile.write(getServerName(), badgeID + " "
				+ "[getRecordCounts()]getRecordCounts begin!");
		String result = "Current numbers: ";
		try {

			InetAddress aHost = InetAddress.getByName("localhost");
			String IP = aHost.getHostAddress();
			int port = 0;
			String[] names = { "SPVMserver", "SPBserver", "SPLserver" };

			for (int i = 0; i < names.length; i++) {
				logFile.write(getServerName(),
						": Check number of records in station" + " " + names[i]);
				// if the port number is not fixed this should change
				if (names[i].toString().equalsIgnoreCase("SPVMserver")) {
					port = 7000;
				} else if (names[i].toString().equalsIgnoreCase("SPLserver")) {
					port = 7001;
				} else if (names[i].toString().equalsIgnoreCase("SPBserver")) {
					port = 7002;
				}
				DatagramSocket dataSocket = new DatagramSocket();
				byte[] request = new byte[100];
				request = "Localcounts".getBytes();
				logFile.write(getServerName(), "Ask for number of records");
				InetAddress server = InetAddress.getByName("localhost");
				logFile.write(getServerName(), "Send the request...");
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, server, port);
				dataSocket.send(requestPacket);
				byte[] buffer = new byte[100];
				DatagramPacket reply = new DatagramPacket(buffer,
						buffer.length, server, port);
				dataSocket.receive(reply);
				logFile.write(getServerName(), "Received reply");
				result += new String((new String(reply.getData()) + " "))
						.trim() + " ";
				logFile.write(getServerName(), "The reply is: " + result);
				dataSocket.close();
			}
		} catch (Exception e) {
			System.out.println("error in getRecordCounts()" + e.getMessage());
			logFile.write(getServerName(),
					"error in getRecordCounts()" + e.getMessage());
		}
		return result;
	}

	
	public String editCRecord(String lastName, String recordID,
			String newStatus, String badgeID) {
		// TODO Auto-generated method stub
		String result = null;
		boolean found = false;
		boolean replace = false;
		boolean resetResult = false;
		char initial = lastName.toUpperCase().charAt(0);
		logFile.write(getServerName(), badgeID + " "
				+ "[editCRecord()]edit CRecord begin!");
		synchronized (this.serverDataBase.get(initial)) {
			if (newStatus.equalsIgnoreCase("captured") == false
					&& newStatus.equalsIgnoreCase("on the run") == false) {
				result = "Error, incorrect status type!";
				logFile.write(getServerName(), result);
				return result;
			}
			ArrayList<Records> tempV = this.serverDataBase.get(initial);

			if (tempV == null) {
				result = "Error,incorrect lastName!";
				logFile.write(getServerName(), result);
				return result;
			}
			for (int i = 0; i < tempV.size(); i++) {
				found = tempV.get(i).getID().equals(recordID);
				if (found == true) {
					replace = this.serverDataBase.get(initial).get(i)
							.setInfo(newStatus);
					result = (replace == true ? "The new status is updated!"
							: "Error occurred,failed to update the new status!");
					logFile.write(getServerName(), result);
					return result;

				}
			}
			result = "Failed to find the recordID!";
			logFile.write(getServerName(), result);
			return result;
		}

	}

	
	public String transferRecord(String badgeID, String recordID,
			String remoteStationServerName) {
		String StationCheck = badgeID.substring(0, 3);
		String result = null;
		boolean localFlag = false;
		boolean localFlag2 = false;
		boolean localFlag3 = false;
		boolean remoteFlag = false;
		boolean remoteFlag2 = false;
		boolean remoteFlag3 = false;
		Records record = null;
		if(badgeID.length()>8||badgeID.length()<7){ //check the badgeID 
			result = "badgeID wrong!";
			logFile.write(this.getServerName(), badgeID+" "+result);
			return result;
		}
		if(recordID.length()!=7){  //check the recordID
			result = "recordID wrong!";
			logFile.write(this.getServerName(), badgeID+" "+ result);
			return result;
		}
		if(!(remoteStationServerName.equalsIgnoreCase(getServerName())||  //check the remoteStationServerName
				remoteStationServerName.equalsIgnoreCase("SPBserver")||
				remoteStationServerName.equalsIgnoreCase("SPLserver"))){
			result = "remoteStationServerName wrong!";  
			logFile.write(this.getServerName(), badgeID+" "+ result);
			return result;
		}
		

		if (StationCheck.equalsIgnoreCase("SPV")) {  //locate the local station 
			                                      //according to the BadgeAcronym 
			logFile.write(this.getServerName(), badgeID+" "+"begin to find record on SPVMserver...");
			ArrayList<ArrayList<Records>> tempR = new ArrayList<>();
			for (ArrayList<Records> e : this.getServerDataBase().values()) {
				tempR.add(e);
			}

			for (ArrayList<Records> e : tempR) {
				synchronized (e) {  //check each list to find the record
					for (Records R : e) {

						localFlag = R.getID().equalsIgnoreCase(recordID);
						if (localFlag == true) {
							record = R;  //find the record
							e.remove(R);  //once found remove the record break out of the list
							logFile.write(this.getServerName(), badgeID+" "+ "find the record!");
							break;
						}

					}

					
				}
				if (localFlag == true) {
					
					break;
				}
			}
			if (localFlag == false) {  //can not find the record
				result = "failed to find the record";
				logFile.write(this.getServerName(), badgeID+" "+ result);
				return result;
			}
			do {  //use do-while here,the break condition is a true returned by the transfer method
				try {

					if (remoteStationServerName.equalsIgnoreCase("SPLserver")) {  //according to the remoteStationServerName,find out the target server
						logFile.write(this.getServerName(), badgeID+" "+ "remoteStationServerName is SPLserver");
						logFile.write(this.getServerName(), badgeID+" "+ "transfer the record to SPLserver");
						DatagramSocket dataSocket = new DatagramSocket();
						byte[] request = new byte[100];
						request = record.toString().getBytes();
						logFile.write("SPVMserver", "Transfer the record...");
						InetAddress server = InetAddress.getByName("localhost");
						logFile.write("SPVMserver", "Send the request...");
						DatagramPacket requestPacket = new DatagramPacket(request,
								request.length, server, 7001);
						dataSocket.send(requestPacket);
						byte[] buffer = new byte[100];
						DatagramPacket reply = new DatagramPacket(buffer,
								buffer.length, server, 7001);
						dataSocket.receive(reply);
						String TransferReply = new String(reply.getData()).trim();
						if(TransferReply.equalsIgnoreCase("true")){
							remoteFlag = true;
						
					}
					}else if (remoteStationServerName
							.equalsIgnoreCase("SPBserver")) {
						logFile.write(this.getServerName(), badgeID+" "+ "remoteStationServerName is SPBserver");
						
						logFile.write(this.getServerName(), badgeID+" "+ "transfer the record to SPBserver");
						DatagramSocket dataSocket = new DatagramSocket();
						byte[] request = new byte[100];
						request = record.toString().getBytes();
						logFile.write("SPVMserver", "Transfer the record...");
						InetAddress server = InetAddress.getByName("localhost");
						logFile.write("SPVMserver", "Send the request...");
						DatagramPacket requestPacket = new DatagramPacket(request,
								request.length, server, 7002);
						dataSocket.send(requestPacket);
						byte[] buffer = new byte[100];
						DatagramPacket reply = new DatagramPacket(buffer,
								buffer.length, server, 7002);
						dataSocket.receive(reply);
						String TransferReply = new String(reply.getData()).trim();
						if(TransferReply.equalsIgnoreCase("true")){
							remoteFlag = true;
						
					} 

				}
				}catch (Exception e) {
					System.out.println(e.getMessage());
					logFile.write(this.getServerName(), badgeID+" "+ "transfer the record from SPVMserver encounter an error");
				}
			} while (remoteFlag == false);  //keep trying until get a true reply from the transfer()

			result = "transferRecord succeed!";
			logFile.write(this.getServerName(), badgeID+" "+ "transfer record from SPVMserver success!");
			return result;
		}

		else if (StationCheck.equalsIgnoreCase("SPL")) {  //this is the beginning for the second station,same as the previous
			ArrayList<ArrayList<Records>> tempR = new ArrayList<>();
			for (ArrayList<Records> e : this.getServerDataBase().values()) {
				tempR.add(e);
			}
			logFile.write(this.getServerName(), badgeID+" "+ "begin to find record on SPLserver...");
			for (ArrayList<Records> e : tempR) {
				synchronized (e) {
					for (Records R : e) {
						localFlag2 = R.getID().equalsIgnoreCase(recordID);
						if (localFlag2 == true) {
							record = R;
							e.remove(R);
							logFile.write(this.getServerName(), badgeID+" "+ "find the record");
							break;
						}

					}

				}

				if (localFlag2 == true) {
					break;
				}
			}
			if (localFlag2 == false) {
				result = "failed to find the record";
				logFile.write(this.getServerName(), badgeID+" "+ result);
				return result;
			}
			do {
				try {

					if (remoteStationServerName.equalsIgnoreCase("SPVMserver")) {
						logFile.write(this.getServerName(), badgeID+" "+ "remoteStationServerName is SPVMserver");
						
						logFile.write(this.getServerName(), badgeID+" "+ "transfer the record to SPVMserver");
						DatagramSocket dataSocket = new DatagramSocket();
						byte[] request = new byte[100];
						request = record.toString().getBytes();
						logFile.write("SPBserver", "Transfer the record...");
						InetAddress server = InetAddress.getByName("localhost");
						logFile.write("SPBserver", "Send the request...");
						DatagramPacket requestPacket = new DatagramPacket(request,
								request.length, server, 7000);
						dataSocket.send(requestPacket);
						byte[] buffer = new byte[100];
						DatagramPacket reply = new DatagramPacket(buffer,
								buffer.length, server, 7000);
						dataSocket.receive(reply);
						String TransferReply = new String(reply.getData()).trim();
						if(TransferReply.equalsIgnoreCase("true")){
							remoteFlag2 = true;
						
					} 

				}
						 else if (remoteStationServerName
							.equalsIgnoreCase("SPBserver")) {
						logFile.write(this.getServerName(), badgeID+" "+ "remoteStationServerName is SPBserver");

	
						logFile.write(this.getServerName(), badgeID+" "+ "transfer the record to SPBserver");
						DatagramSocket dataSocket = new DatagramSocket();
						byte[] request = new byte[100];
						request = record.toString().getBytes();
						logFile.write("SPLserver", "Transfer the record...");
						InetAddress server = InetAddress.getByName("localhost");
						logFile.write("SPLserver", "Send the request...");
						DatagramPacket requestPacket = new DatagramPacket(request,
								request.length, server, 7002);
						dataSocket.send(requestPacket);
						byte[] buffer = new byte[100];
						DatagramPacket reply = new DatagramPacket(buffer,
								buffer.length, server, 7002);
						dataSocket.receive(reply);
						String TransferReply = new String(reply.getData()).trim();
						if(TransferReply.equalsIgnoreCase("true")){
							remoteFlag2 = true;
						
					} 

				}
						
					

				} catch (Exception e) {
					System.out.println(e.getMessage());
					logFile.write(this.getServerName(), badgeID+" "+ "transfer the record from SPLserver encounter an error");
				}
			} while (remoteFlag2 == false);

			result = "transferRecord succeed!";
			logFile.write(this.getServerName(),  badgeID+" "+"transfer record from SPL success!");
			return result;
		}

		else if (StationCheck.equalsIgnoreCase("SPB")) {
			ArrayList<ArrayList<Records>> tempR = new ArrayList<>();
			for (ArrayList<Records> e : this.getServerDataBase().values()) {
				tempR.add(e);
			}
			logFile.write(this.getServerName(), badgeID+" "+ "begin to find record on SPBserver...");
			for (ArrayList<Records> e : tempR) {
				synchronized (e) {
					for (Records R : e) {
						localFlag3 = R.getID().equalsIgnoreCase(recordID);
						if (localFlag3 == true) {
							record = R;
							e.remove(R);
							logFile.write(this.getServerName(), badgeID+" "+ "find the record");
							break;
						}

					}

				}

				if (localFlag3 == true) {
					break;
				}
			}
			if (localFlag3 == false) {
				result = "failed to find the record";
				logFile.write(this.getServerName(), badgeID+" "+result);
				return result;
			}
			do {
				try {

					if (remoteStationServerName.equalsIgnoreCase("SPVMserver")) {
						logFile.write(this.getServerName(), badgeID+" "+ "remoteStationServerName is SPVMserver");
						
						logFile.write(this.getServerName(), badgeID+" "+ "transfer the record to SPVMserver");
						DatagramSocket dataSocket = new DatagramSocket();
						byte[] request = new byte[100];
						request = record.toString().getBytes();
						logFile.write("SPBserver", "Transfer the record...");
						InetAddress server = InetAddress.getByName("localhost");
						logFile.write("SPBserver", "Send the request...");
						DatagramPacket requestPacket = new DatagramPacket(request,
								request.length, server, 7000);
						dataSocket.send(requestPacket);
						byte[] buffer = new byte[100];
						DatagramPacket reply = new DatagramPacket(buffer,
								buffer.length, server, 7000);
						dataSocket.receive(reply);
						String TransferReply = new String(reply.getData()).trim();
						if(TransferReply.equalsIgnoreCase("true")){
							remoteFlag3 = true;
						
					} 
						
					} else if (remoteStationServerName
							.equalsIgnoreCase("SPLserver")) {
						logFile.write(this.getServerName(), badgeID+" "+ "remoteStationServerName is SPLserver");
						
						logFile.write(this.getServerName(), badgeID+" "+ "transfer the record to SPLserver");
						DatagramSocket dataSocket = new DatagramSocket();
						byte[] request = new byte[100];
						request = record.toString().getBytes();
						logFile.write("SPBserver", "Transfer the record...");
						InetAddress server = InetAddress.getByName("localhost");
						logFile.write("SPBserver", "Send the request...");
						DatagramPacket requestPacket = new DatagramPacket(request,
								request.length, server, 7001);
						dataSocket.send(requestPacket);
						byte[] buffer = new byte[100];
						DatagramPacket reply = new DatagramPacket(buffer,
								buffer.length, server, 7001);
						dataSocket.receive(reply);
						String TransferReply = new String(reply.getData()).trim();
						if(TransferReply.equalsIgnoreCase("true")){
							remoteFlag3 = true;
						
					} 

				}

				} catch (Exception e) {
					System.out.println(e.getMessage());
					logFile.write(this.getServerName(), badgeID+" "+ "transfer the record from SPBserver encounter an error");
				}
			} while (remoteFlag3 == false);

			result = "transferRecord succeed!";
			logFile.write(this.getServerName(), badgeID+" "+ "transfer record from SPBserver success!");
			return result;
		}
		return result;
	}


	public boolean transfer(String record) {
		
		String lastName = null;
		boolean returnFlag = false;
		boolean result = false;
		String recordID = record.substring(0, 7);
		String recordInfo = record.substring(8, record.length());
		Matcher m = Pattern.compile("\\.\\w+").matcher(record);
		
		System.out.print(recordID);
		System.out.print(recordInfo);
		
		
		while (m.find()) { // use regular expression to find the last name
							// initial
			lastName = m.group();
		}
		ArrayList<Records> tempV = new ArrayList<Records>();
		char initial = lastName.charAt(1);// find the initial
		logFile.write(getServerName(), "begin to receive records...");
		logFile.write(getServerName(), "the last name initial is " + " " + initial);

		synchronized (tempV) { // synchronized the list
			if (this.serverDataBase.get(initial) == null) { // if the initial is
															// not in the
															// hashtable as a
															// key,create a new
															// one
				logFile.write(getServerName(),
						"the last name initial is not in the hashtable,begin to "
								+ " create a new initial as a key");
				returnFlag = tempV.add(new Records(recordID, recordInfo));
				this.serverDataBase.put((Character) initial, tempV);
				result = returnFlag;
				logFile.write(getServerName(), "the transfer result is " + " "
						+ result);
				return result;
			} else { // if the initial has already in the hashtable just add the
						// record
				logFile.write(
						getServerName(),
						"the last name initial exists in the hashtable,add the record with the exist key");
				returnFlag = this.serverDataBase.get(initial).add(
						new Records(recordID, recordInfo));
			}
			result = returnFlag;
			logFile.write(getServerName(), "transmission result:" + " " + result);
		}
		return result;
	}

	public String getTotalRecordsNumber() {
		int counter = 0;
		Hashtable<Character, ArrayList<Records>> temp = this.serverDataBase;

		synchronized (temp) {

			for (ArrayList<Records> e : temp.values()) {
				counter = counter + e.size();
			}
			return (getServerName() + " " + counter);
		}
	}
	
	public void run() {
		
		
		try {
			
			DatagramSocket socket = new DatagramSocket(getServerPort());
			while (true) {
				byte[] buffer = new byte[8192];
				
				DatagramPacket packet = new DatagramPacket(buffer,
						8192);
				socket.receive(packet);
				
				//System.out.println("fuck thread2");
				logFile.write(getServerName(),getServerName()+ "thread.start()");
				
				PackageAnalyzer PA = new PackageAnalyzer(packet);
				
				Thread s = new Thread(new Response_UDP(this, packet));
				s.start();
				/*String command = PA.getCommand();
				if(command.equalsIgnoreCase("createCRecord")){
					
					buffer = createCRecord(PA.getFirstName(),PA.getLastName(),
							PA.getDescription(),PA.getStatus(),PA.getBadgeID()).getBytes();
					DatagramPacket packettR = new DatagramPacket(
							buffer, buffer.length, packet
									.getAddress(), packet.getPort());
					System.out.println(new String(packettR.getData()).trim());
					socket.send(packettR);
				}
				else if(command.equalsIgnoreCase("createMRecord")){
					buffer = createMRecord(PA.getFirstName(),PA.getLastName(),
							PA.getAddress(),PA.getLastDate(),PA.getLastLocation(),PA.getStatus(),PA.getBadgeID()).getBytes();
					DatagramPacket packettR = new DatagramPacket(
							buffer, buffer.length, packet
									.getAddress(), packet.getPort());
					socket.send(packettR);
					
				}
				else if(command.equalsIgnoreCase("getRecordCounts")){
					
					buffer = getRecordCounts(PA.getBadgeID()).getBytes();
					DatagramPacket packettR = new DatagramPacket(
							buffer, buffer.length, packet
									.getAddress(), packet.getPort());
					socket.send(packettR);
				}
				else if(command.equalsIgnoreCase("editCRecord")){		
							buffer = editCRecord(PA.getLastName(),PA.getRecordID(),PA.getNewStatus(),PA.getBadgeID()).getBytes();
					DatagramPacket packettR = new DatagramPacket(
							buffer, buffer.length, packet
									.getAddress(), packet.getPort());
					socket.send(packettR);
				}else if(command.equalsIgnoreCase("transferRecord")){
					//System.out.println("hahahahhahah");
					buffer =  transferRecord(PA.getBadgeID(), PA.getRecordID(),
							PA.getRemoteServerName()).getBytes();
					DatagramPacket packettR = new DatagramPacket(
							buffer, buffer.length, packet
									.getAddress(), packet.getPort());
					socket.send(packettR);
				}
				
			}*/
			}} catch (Exception e) {
			logFile.write(getServerName(),
					"Error in Thread");
			System.out.println("fuck thread");
		}
	}
	
	

public static void main(String[] Args) throws Exception{
	replica2 SPVM = new replica2("SPVMserver",5677);
	replica2 SPL = new replica2("SPLserver",5678);
	replica2 SPB = new replica2("SPBserver",5679);
	localCall localSPVM = new localCall(SPVM,7000);
	localCall localSPL = new localCall(SPL,7001);
	localCall localSPB = new localCall(SPB,7002);
	Thread T1 = new Thread(SPVM);
	Thread T2 = new Thread(SPL);
	Thread T3 = new Thread(SPB);
	Thread T4 = new Thread(localSPVM);
	Thread T5 = new Thread(localSPL);
	Thread T6 = new Thread(localSPB);
	T1.start();
	T2.start();
	T3.start();
	T4.start();
	T5.start();
	T6.start();
	System.out.println("replica2 runs!");
	
	 
	/* DatagramSocket socket = new DatagramSocket();
	 byte[] reply = new byte[8192];
	 byte[] request = ("getRecordCounts"+","+"SPB1111"+","+"3").getBytes();
	 DatagramPacket requestPacket = new DatagramPacket(request, request.length, 
			 InetAddress.getByName("localhost") , 5679);
	 socket.send(requestPacket);
	 DatagramPacket replyPacket = new DatagramPacket(reply, reply.length, 
			 InetAddress.getByName("localhost") , 5679);
	 while(true){
		 socket.receive(replyPacket);
		 String result = new String(replyPacket.getData()).trim();
		System.out.println(result.trim());
	//System.out.println( SPVM.getServerDataBase().get('S'));	
	 socket.close();
	 break;
	 }*/
	/* 
	 DatagramSocket socket2 = new DatagramSocket();
	 byte[] reply2 = new byte[8192];
	 byte[] request2 = ("getRecordCounts"+","+"SPB1111"+","+"1").getBytes();
			 
	
	 DatagramPacket requestPacket2 = new DatagramPacket(request2, request2.length, 
			 InetAddress.getByName("localhost") , 5679);
	
	
	 socket2.send(requestPacket2);
	 DatagramPacket replyPacket2 = new DatagramPacket(reply2, reply2.length, 
			 InetAddress.getByName("localhost") , 5679);
	 while(true){
		 socket2.receive(replyPacket2);
		 String result2 = new String(replyPacket2.getData()).trim();
		System.out.println(result2);
	 socket2.close();
	 //System.out.println(SPB.getServerDataBase().get('S'));
	 
	 	break;
	
	
	 	
	

}*/
}
}


class localCall implements Runnable{
private int localPort;
private replica2 server;
public final int MAX_UDP_BUFFER_SIZE = 8192;
	public localCall(){
	
}
	public localCall(replica2 r1, int port){
		this.localPort = port;
		this.server = r1;
	}
	@Override
	public void run() {
			
			try {
				DatagramSocket socket = new DatagramSocket(localPort);
				
				while (true) {
					
					byte buffer[] = new byte[MAX_UDP_BUFFER_SIZE];
					DatagramPacket packet = new DatagramPacket(buffer,
							MAX_UDP_BUFFER_SIZE);
					boolean transferFlag = false;
					String result = "true";
					socket.receive(packet);
					logFile.write(server.getServerName(), server.getServerName()+"localThread.start()");
					String request = new String(packet.getData()).trim();
					if(request.equalsIgnoreCase("Localcounts")){
					
					{
						byte buffer2[] = new byte[MAX_UDP_BUFFER_SIZE];
						buffer2 = server.getTotalRecordsNumber().getBytes();
						DatagramPacket packettR = new DatagramPacket(
								buffer2, buffer2.length, packet
										.getAddress(), packet.getPort());
						socket.send(packettR);
						
					}
					}else{
						System.out.println(new String(packet.getData()).trim());
						transferFlag = server.transfer(request);
						if(transferFlag){
						byte buffer3[] = new byte[MAX_UDP_BUFFER_SIZE];
						buffer3 = result.getBytes();
						DatagramPacket packettR2 = new DatagramPacket(
								buffer3, buffer3.length, packet
										.getAddress(), packet.getPort());
						socket.send(packettR2);
					}else{
						result = "Failed to receive Record!";
						byte buffer4[] = new byte[MAX_UDP_BUFFER_SIZE];
						buffer4 = result.getBytes();
						DatagramPacket packettR3 = new DatagramPacket(
								buffer4, buffer4.length, packet
										.getAddress(), packet.getPort());
						socket.send(packettR3);
					}
					}
				}
			} catch (Exception e) {
				logFile.write(server.getServerName(),
						"Error in new Thread");
				System.out.println(e.getMessage());
			}
		}
		
	}


	
