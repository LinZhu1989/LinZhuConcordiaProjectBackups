package FECORBAInterface;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import common.GetServerAddressReplyPackage;
import common.GetServerAddressRequestPackage;
import common.ServerCommunication;
import common.UdpPackage;

enum Sequencer {
	REQUESTID;

	private static int ID = 0;

	public synchronized int getID() {
		ID++;
		return ID;
	}
}

enum RMSequencer {
	RMREQUESTID;
	private static int ID = 0;

	public synchronized int getID() {
		ID++;
		return ID;
	}
}

class createCRecordUDP implements Callable<String> {
	private String lastName;
	private String firstName;
	private String badgeID;
	private String description;
	private String status;
	private int requestID;
	private int hostPort;
	private InetAddress hostAddress;
	private int failCounter = 3;
	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];
	private String result;

	public createCRecordUDP(String firstName, String lastName,
			String description, String status, String badgeID, int requestID,
			InetAddress hostAddress, int hostPort) {
		this.badgeID = badgeID;
		this.description = description;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.requestID = requestID;
		this.hostAddress = hostAddress;
		this.hostPort = hostPort;

	}

	public void minusCounter() {
		failCounter -= 1;
	}

	@Override
	public String call() throws Exception {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				request = ("createCRecord" + "," + firstName + "," + lastName
						+ "," + description + "," + status + "," + badgeID
						+ "," + requestID).getBytes();
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, hostAddress, hostPort);
				socket.send(requestPacket);
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, hostAddress, hostPort);
				while (true) {
					socket.receive(replyPacket);
					result = new String(replyPacket.getData()).trim();
					if (!result.equalsIgnoreCase("wait")) {
						break;
					}
				}

				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {
				minusCounter();
				if (failCounter == 0) {

					result = "crash";
					break;
				}
				continue;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return (result + "," + hostPort);
	}

}

class createMRecordUDP implements Callable<String> {
	private String lastName;
	private String firstName;
	private String badgeID;
	private String address;
	private String lastDate;
	private String lastLocation;
	private String status;
	private int requestID;
	private int hostPort;
	private InetAddress hostAddress;
	private int failCounter = 3;
	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];
	private String result;

	public createMRecordUDP(String firstName, String lastName, String address,
			String lastDate, String lastLocation, String status,
			String badgeID, int requestID, InetAddress hostAddress, int hostPort) {
		this.badgeID = badgeID;
		this.address = address;
		this.lastDate = lastDate;
		this.lastLocation = lastLocation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.requestID = requestID;
		this.hostAddress = hostAddress;
		this.hostPort = hostPort;

	}

	public void minusCounter() {
		failCounter -= 1;
	}

	@Override
	public String call() throws Exception {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				request = ("createMRecord" + "," + firstName + "," + lastName
						+ "," + address + "," + lastDate + "," + lastLocation
						+ "," + status + "," + badgeID + "," + requestID)
						.getBytes();
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, hostAddress, hostPort);
				socket.send(requestPacket);
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, hostAddress, hostPort);
				while (true) {
					socket.receive(replyPacket);
					result = new String(replyPacket.getData()).trim();
					if (!result.equalsIgnoreCase("wait")) {
						break;
					}
				}

				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {
				minusCounter();
				if (failCounter == 0) {

					result = "crash";
					break;
				}
				continue;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return (result + "," + hostPort);
	}

}

class getRecordCountsUDP implements Callable<String> {
	private String badgeID;
	private int requestID;
	private int hostPort;
	private InetAddress hostAddress;
	private int failCounter = 3;
	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];
	private String result;

	public getRecordCountsUDP(String badgeID, int requestID,
			InetAddress hostAddress, int hostPort) {
		this.badgeID = badgeID;
		this.requestID = requestID;
		this.hostAddress = hostAddress;
		this.hostPort = hostPort;

	}

	public void minusCounter() {
		failCounter -= 1;
	}

	@Override
	public String call() throws Exception {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				request = ("getRecordCounts" + "," + badgeID + "," + requestID)
						.getBytes();
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, hostAddress, hostPort);
				socket.send(requestPacket);
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, hostAddress, hostPort);
				while (true) {
					socket.receive(replyPacket);
					result = new String(replyPacket.getData()).trim();
					// System.out.println("this is the test"+" "+ result);
					if (!result.equalsIgnoreCase("wait")) {
						break;
					}
				}

				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {
				minusCounter();
				if (failCounter == 0) {
					result = "crash";
					break;
				}
				continue;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return (result + "," + hostPort);
	}

}

class editCRecordUDP implements Callable<String> {

	private String lastName;
	private String recordID;
	private String badgeID;
	private String newStatus;
	private int requestID;
	private int hostPort;
	private InetAddress hostAddress;
	private int failCounter = 3;
	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];
	private String result;

	public editCRecordUDP(String lastName, String recordID, String newStatus,
			String badgeID, int requestID, InetAddress hostAddress, int hostPort) {
		this.badgeID = badgeID;
		this.lastName = lastName;
		this.recordID = recordID;
		this.newStatus = newStatus;
		this.requestID = requestID;
		this.hostAddress = hostAddress;
		this.hostPort = hostPort;

	}

	public void minusCounter() {
		failCounter -= 1;
	}

	@Override
	public String call() throws Exception {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				request = ("editCRecord" + "," + lastName + "," + recordID
						+ "," + newStatus + "," + badgeID + "," + requestID)
						.getBytes();
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, hostAddress, hostPort);
				socket.send(requestPacket);
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, hostAddress, hostPort);
				while (true) {
					socket.receive(replyPacket);
					result = new String(replyPacket.getData()).trim();
					if (!result.equalsIgnoreCase("wait")) {
						break;
					}
				}

				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {
				minusCounter();
				if (failCounter == 0) {
					result = "crash";
					break;
				}
				continue;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return (result + "," + hostPort);
	}

}

class transferRecordUDP implements Callable<String> {
	private String badgeID;
	private String recordID;
	private String remoteServerName;
	private int requestID;
	private int hostPort;
	private InetAddress hostAddress;
	private int failCounter = 3;
	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];
	private String result;

	public transferRecordUDP(String badgeID, String recordID,
			String remoteServerName, int requestID, InetAddress hostAddress,
			int hostPort) {
		this.badgeID = badgeID;
		this.recordID = recordID;
		this.remoteServerName = remoteServerName;
		this.requestID = requestID;
		this.hostAddress = hostAddress;
		this.hostPort = hostPort;

	}

	public void minusCounter() {
		failCounter -= 1;
	}

	@Override
	public String call() throws Exception {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				request = ("transferRecord" + "," + badgeID + "," + recordID
						+ "," + remoteServerName + "," + requestID).getBytes();
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, hostAddress, hostPort);
				socket.send(requestPacket);
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, hostAddress, hostPort);
				while (true) {
					socket.receive(replyPacket);
					result = new String(replyPacket.getData()).trim();
					if (!result.equalsIgnoreCase("wait")) {
						break;
					}
				}

				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {
				minusCounter();
				if (failCounter == 0) {
					result = "crash";
					break;
				}
				continue;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return (result + "," + hostPort);
	}

}

/**
 * ask for serverAddress
 * 
 * @author Yulong Song
 * 
 */
class getServerAddressUDP implements Callable<String> {
	private String RMhostName;
	private int RMport;
	private String result;
	private int RMRequestID = RMSequencer.RMREQUESTID.getID();
	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];

	public getServerAddressUDP() {

	}

	public getServerAddressUDP(String RMhostName, int RMPort) {
		this.RMhostName = RMhostName;
		this.RMport = RMPort;

	}

	@Override
	public String call() throws Exception {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				InetAddress host = InetAddress.getByName(RMhostName);

				request = ("getServerAddress" + "," + RMRequestID).getBytes();

				GetServerAddressRequestPackage requestPkg = new GetServerAddressRequestPackage(
						host, 2020);
				// DatagramPacket requestPacket = new DatagramPacket(request,
				// request.length,
				// host , RMport);
				socket.send(requestPkg.compose());
				byte[] reply = new byte[UdpPackage.pkgSize];
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, host, RMport);
				socket.receive(replyPacket);

				GetServerAddressReplyPackage replyPkg = new GetServerAddressReplyPackage(
						replyPacket.getAddress(), replyPacket.getPort());
				replyPkg.parse(replyPacket);
				System.out.println(replyPkg.replicaIP1);
				System.out.println(replyPkg.replicaIP2);
				System.out.println(replyPkg.replicaIP3);
				System.out.println(replyPkg.replicaPort11);
				System.out.println(replyPkg.replicaPort12);
				System.out.println(replyPkg.replicaPort13);

				result = new String(replyPacket.getData()).trim();
				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {
				continue;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
}

/*
 * errorMessage = "error"+","+hostName+,+hostPort request =
 * (errorMessage+).getBytes();
 */
/**
 * 
 * @author Yulong Song to-do finish the result part,error can be crash if hits 3
 */
class notifyRMError implements Callable<String> {

	private int errorHostPort;
	private int RMRequestID = RMSequencer.RMREQUESTID.getID();
	private String RMName;
	private int RMPort;

	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];
	private String result = null;

	public notifyRMError() {

	}

	public notifyRMError(int errorHostPort, String RMName, int RMPort) {

		this.errorHostPort = errorHostPort;
		this.RMName = RMName;
		this.RMPort = RMPort;

	}

	@Override
	public String call() {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				InetAddress RMserver = InetAddress.getByName(RMName);
				request = ("Error" + "," + errorHostPort + "," + RMRequestID)
						.getBytes();
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, RMserver, RMPort);
				socket.send(requestPacket);
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, requestPacket.getAddress(),
						requestPacket.getPort());
				socket.receive(replyPacket);
				// here !!!!!!!!!!!!!!!!
				result = new String(replyPacket.getData()).trim();
				// if result!="alive" receive new address
				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {

				continue;

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

}

// String crashRequest = "Crash"+","+hostPort;
// here I only try to use the port number as the identity
/**
 * 
 * @author Yulong Song to-do finish the result part
 */
class notifyRMCrash implements Callable<String> {
	private int crashHostPort;
	private String RMhostName;
	private int RMport;
	private int RMRequestID = RMSequencer.RMREQUESTID.getID();
	private String result;
	private boolean flag = true;
	byte[] request = new byte[UdpPackage.pkgSize];
	byte[] reply = new byte[UdpPackage.pkgSize];

	public notifyRMCrash() {

	}

	public notifyRMCrash(int errorPortNumber, String RMhostName, int RMport) {
		this.crashHostPort = errorPortNumber;
		this.RMhostName = RMhostName;
		this.RMport = RMport;

	}

	@Override
	public String call() {

		while (flag) {
			try {
				DatagramSocket socket = new DatagramSocket();
				socket.setSoTimeout(ServerCommunication.WAITING_TIME);
				InetAddress host = InetAddress.getByName(RMhostName);
				request = ("Crash" + "," + crashHostPort + "," + RMRequestID)
						.getBytes();
				DatagramPacket requestPacket = new DatagramPacket(request,
						request.length, host, RMport);
				socket.send(requestPacket);
				DatagramPacket replyPacket = new DatagramPacket(reply,
						reply.length, host, RMport);
				socket.receive(replyPacket);
				result = new String(replyPacket.getData()).trim();
				flag = false;
				socket.close();

			} catch (SocketTimeoutException e) {
				continue;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

}

/**
 * 
 * @author Yulong Song
 * 
 */
// This is the Record Structure ID + Info

/**
 * 
 * @author Yulong Song
 * 
 */
// This is the log generator
class logFile {

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

/**
 * 
 * @author Yulong Song
 * @param DatagramPacket
 *            requestPacket analyze any UDP package just put the package in!
 *            PackageAnalyzer PA = new PackageAnalyzer(requestPacket)
 *            PA.getLastName(); get parameters that you need
 */
class PackageAnalyzer {
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
	private int errorHostPort;
	private int crashHostPort;
	private String remoteServerName;
	private int requestID;
	private int hostPort;
	private InetAddress hostAddress;
	private String command;

	public PackageAnalyzer() {

	}

	public PackageAnalyzer(DatagramPacket requestPacket) {
		String[] request = (new String(requestPacket.getData()).trim())
				.split(",");
		if (request[0].equalsIgnoreCase("createCRecord")) {
			this.command = request[0];
			this.firstName = request[1];
			this.lastName = request[2];
			this.description = request[3];
			this.status = request[4];
			this.badgeID = request[5];
			this.requestID = Integer.parseInt(request[6]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();

		} else if (request[0].equalsIgnoreCase("createMRecord")) {
			this.command = request[0];
			this.firstName = request[1];
			this.lastName = request[2];
			this.address = request[3];
			this.lastDate = request[4];
			this.lastLocation = request[5];
			this.status = request[6];
			this.badgeID = request[7];
			this.requestID = Integer.parseInt(request[8]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();

		} else if (request[0].equalsIgnoreCase("editCRecord")) {
			this.command = request[0];
			this.lastName = request[1];
			this.recordID = request[2];
			this.newStatus = request[3];
			this.badgeID = request[4];
			this.requestID = Integer.parseInt(request[5]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		} else if (request[0].equalsIgnoreCase("getRecordCounts")) {
			this.command = request[0];
			this.badgeID = request[1];
			this.requestID = Integer.parseInt(request[2]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		} else if (request[0].equalsIgnoreCase("transferRecord")) {
			this.command = request[0];
			this.badgeID = request[1];
			this.recordID = request[2];
			this.remoteServerName = request[3];
			this.requestID = Integer.parseInt(request[4]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();

		} else if (request[0].equalsIgnoreCase("getServerAddress")) {
			this.command = request[0];
			this.requestID = Integer.parseInt(request[1]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		} else if (request[0].equalsIgnoreCase("Error")) {
			this.command = request[0];
			this.errorHostPort = Integer.parseInt(request[1]);
			this.requestID = Integer.parseInt(request[2]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		} else if (request[0].equalsIgnoreCase("Crash")) {
			this.command = request[0];
			this.crashHostPort = Integer.parseInt(request[1]);
			this.requestID = Integer.parseInt(request[2]);
			this.hostAddress = requestPacket.getAddress();
			this.hostPort = requestPacket.getPort();
		}

	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getBadgeID() {
		return this.badgeID;
	}

	public String getRecordID() {
		return this.recordID;
	}

	public String getStatus() {
		return this.status;
	}

	public String getNewStatus() {
		return this.newStatus;
	}

	public String getDescription() {
		return this.description;
	}

	public int getRequestID() {
		return this.requestID;
	}

	public String getLastDate() {
		return this.lastDate;
	}

	public String getLastLocation() {
		return this.lastLocation;
	}

	public String getAddress() {
		return this.address;
	}

	public int getErrorHostPort() {
		return this.errorHostPort;
	}

	public int getCrashHostPort() {
		return this.crashHostPort;
	}

	public String getRemoteServerName() {
		return this.remoteServerName;
	}

	public InetAddress getHostInetAddress() {
		return this.hostAddress;
	}

	public int getHostPort() {
		return this.hostPort;
	}

	public String getCommand() {
		return this.command;
	}
}

class resultsAnalyzer {
	private String[] results = new String[3];
	private int[] resultPorts = new int[3];
	private String result = null;
	private String RMHostName;
	private int RMPort;

	public resultsAnalyzer() {
	}

	public resultsAnalyzer(String RMHostName, int RMPort) {
		this.RMHostName = RMHostName;
		this.RMPort = RMPort;
	}

	public String analyze(Future<String> result1, Future<String> result2,
			Future<String> result3) {
		synchronized (results) {
			try {
				ExecutorService executor = Executors.newCachedThreadPool();
				String[] result1Split = result1.get().split(",");
				String[] result2Split = result2.get().split(",");
				String[] result3Split = result3.get().split(",");
				results[0] = result1Split[0];
				results[1] = result2Split[0];
				results[2] = result3Split[0];
				resultPorts[0] = Integer.parseInt(result1Split[1]);
				resultPorts[1] = Integer.parseInt(result2Split[1]);
				resultPorts[2] = Integer.parseInt(result3Split[1]);
				// thanks god result
				if (results[0].equalsIgnoreCase(results[1])
						&& results[0].equalsIgnoreCase(results[2])) {
					result = results[0];
				}// one server on replica1 crashes
				else if (results[0].equalsIgnoreCase("crash") == true) {
					Future<String> result0 = executor.submit(new notifyRMCrash(
							resultPorts[0], RMHostName, RMPort));
					FEImpl.initializeReplicaInfo(result0.get().trim());
					result = results[1];
				}// one server on replica2 crashes
				else if (results[1].equalsIgnoreCase("crash") == true) {
					Future<String> result0 = executor.submit(new notifyRMCrash(
							resultPorts[1], RMHostName, RMPort));
					FEImpl.initializeReplicaInfo(result0.get());
					result = results[0];
				}// one server on replica3 crashes
				else if (results[2].equalsIgnoreCase("crash") == true) {
					Future<String> result0 = executor.submit(new notifyRMCrash(
							resultPorts[2], RMHostName, RMPort));
					FEImpl.initializeReplicaInfo(result0.get());
					result = results[1];
				}
				// from here, no crash but error is possible
				// one server on replica3 returns error result
				else if ((results[0].equalsIgnoreCase(results[1]) == true)
						&& (results[0].equalsIgnoreCase(results[2]) == false)) {
					result = results[0];
					Future<String> result4 = executor.submit(new notifyRMError(
							resultPorts[2], RMHostName, RMPort));
					if (!result4.get().equalsIgnoreCase("alive")) {
						// HERE if RM hits three,the reply is the new address
						FEImpl.initializeReplicaInfo(result4.get());
					}
				}// one server on replica2 returns error result
				else if ((results[0].equalsIgnoreCase(results[1]) == false)
						&& (results[0].equalsIgnoreCase(results[2]) == true)) {
					result = results[0];
					Future<String> result4 = executor.submit(new notifyRMError(
							resultPorts[1], RMHostName, RMPort));
					if (!result4.get().equalsIgnoreCase("alive")) {

						FEImpl.initializeReplicaInfo(result4.get());
					}
				}// one server on relica1 returns error result
				else {
					result = results[1];
					Future<String> result4 = executor.submit(new notifyRMError(
							resultPorts[0], RMHostName, RMPort));
					if (!result4.get().equalsIgnoreCase("alive")) {
						// HERE if RM hits three,the reply is the new address
						FEImpl.initializeReplicaInfo(result4.get());
					}
				}
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			return result;
		}
	}

}

/**
 * 
 * @author Yulong Song
 * 
 */

// This is the beginning of my Server
public class FEImpl extends commonMethodsPOA {
	private String ServerName;
	private int port;
	private ORB orb = null;
	private static String[][] replicaInfo = new String[4][3];

	// [0][0] replica1's name
	// [0][1] replica2's name
	// [0][2] replica3's name
	// [1][0] SPVM1's port
	// [2][0] SPL1's port
	// [3][0] SPB1's port
	// [1][1] SPVM2's port
	// [2][1] SPL2's port
	// [3][1] SPB2's port
	// [1][2] SPVM3's port
	// [2][2] SPL3's port
	// [3][2] SPB3's port

	// Empty constructor to initial three server Objects
	public FEImpl() {
		this.ServerName = "FE";
		this.port = 5676;
		// to-do set up replicaInfo!!!!!!!!
		try {
			ExecutorService executor = Executors.newCachedThreadPool();
			Future<String> result = executor.submit(new getServerAddressUDP(
					ServerCommunication.REPLICA_HOST,
					ServerCommunication.REPLICA_MANAGER_PORT));
			FEImpl.initializeReplicaInfo(result.get());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// This is the CORBA set up methods
	public void ORBsetup(String[] Args) throws Exception {
		orb = ORB.init(Args, null);

		POA rootPOA = POAHelper.narrow(orb
				.resolve_initial_references("RootPOA"));

		byte[] FEId = rootPOA.activate_object(this);
		org.omg.CORBA.Object FEref = rootPOA.id_to_reference(FEId);

		String FEIor = orb.object_to_string(FEref);

		PrintWriter file = new PrintWriter("ior.txt");
		file.println(FEIor);

		file.close();
		rootPOA.the_POAManager().activate();
		System.out.println("ORB runs!");
		orb.run();

	}

	public static void initializeReplicaInfo(String newAddress) {
		// according to the format
		String[] address = newAddress.split(",");
		synchronized (replicaInfo) {
			// [0][0] replica1's name
			// [0][1] replica2's name
			// [0][2] replica3's name
			// [1][0] SPVM1's port
			// [2][0] SPL1's port
			// [3][0] SPB1's port
			// [1][1] SPVM2's port
			// [2][1] SPL2's port
			// [3][1] SPB2's port
			// [1][2] SPVM3's port
			// [2][2] SPL3's port
			// [3][2] SPB3's port
			replicaInfo[1][0] = address[0];
			replicaInfo[2][0] = address[1];
			replicaInfo[3][0] = address[2];
			replicaInfo[0][0] = address[3];
			replicaInfo[1][1] = address[4];
			replicaInfo[2][1] = address[5];
			replicaInfo[3][1] = address[6];
			replicaInfo[0][1] = address[7];
			replicaInfo[1][2] = address[8];
			replicaInfo[2][2] = address[9];
			replicaInfo[3][2] = address[10];
			replicaInfo[0][2] = address[11];
			
			for (int i = 0; i <replicaInfo.length; i++ ) {
				for (String s: replicaInfo[i]) {
					System.out.println(s);
				}
			}

		}
	}

	public String getServerName() {
		return this.ServerName;
	}

	public int getServerPort() {
		return this.port;
	}

	public String toString() {
		return ServerName;
	}

	/*
	 * @Override public void getDataBase(char c) {
	 * 
	 * }
	 */

	// randomly given unique ID, creating CRecord depends on whether the initial
	// capital
	// is existed or not.

	@Override
	public String createCRecord(String firstName, String lastName,
			String description, String status, String badgeID) {
		int requestID = Sequencer.REQUESTID.getID();
		String StationCheck = badgeID.substring(0, 3);
		String result = "HGJGGJG";
		System.out.println(StationCheck);

		if (StationCheck.equalsIgnoreCase("SPV")) {
			try {
				
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);

				int SPVM1Port = Integer.parseInt(replicaInfo[1][0]);
				int SPVM2Port = Integer.parseInt(replicaInfo[1][1]);
				int SPVM3Port = Integer.parseInt(replicaInfo[1][2]);

				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host1, SPVM1Port));
				System.out.println("this is request ID"+":"+requestID);
				Future<String> result2 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host2, SPVM2Port));
				Future<String> result3 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host3, SPVM3Port));
				
				printResults(result1, result2, result3, "createCRecord");
				
				result = result1.get().split(",")[0];
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
				

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		else if (StationCheck.equalsIgnoreCase("SPL")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPL1Port = Integer.parseInt(replicaInfo[2][0]);
				int SPL2Port = Integer.parseInt(replicaInfo[2][1]);
				int SPL3Port = Integer.parseInt(replicaInfo[2][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host1, SPL1Port));
				Future<String> result2 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host2, SPL2Port));
				Future<String> result3 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host3, SPL3Port));

				printResults(result1, result2, result3, "createCRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPB")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPB1Port = Integer.parseInt(replicaInfo[3][0]);
				int SPB2Port = Integer.parseInt(replicaInfo[3][1]);
				int SPB3Port = Integer.parseInt(replicaInfo[3][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host1, SPB1Port));
				Future<String> result2 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host2, SPB2Port));
				Future<String> result3 = executor.submit(new createCRecordUDP(
						firstName, lastName, description, status, badgeID,
						requestID, host3, SPB3Port));
				
				printResults(result1, result2, result3, "createCRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;

	}

	private void printResults(Future<String> result1, Future<String> result2,
			Future<String> result3, String caller) throws InterruptedException,
			ExecutionException {
		System.out.println("Retrun from Server1 " + result1.get());
		System.out.println("Retrun from Server2 " + result2.get());
		System.out.println("Retrun from Server3 " + result3.get());
	}

	// same way as the creating CRecord
	@Override
	public String createMRecord(String firstName, String lastName,
			String address, String lastDate, String lastLocation,
			String status, String badgeID) {
		int requestID = Sequencer.REQUESTID.getID();
		String StationCheck = badgeID.substring(0, 3);
		String result = null;

		if (StationCheck.equalsIgnoreCase("SPV")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPVM1Port = Integer.parseInt(replicaInfo[1][0]);
				int SPVM2Port = Integer.parseInt(replicaInfo[1][1]);
				int SPVM3Port = Integer.parseInt(replicaInfo[1][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host1, SPVM1Port));
				Future<String> result2 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host2, SPVM2Port));
				Future<String> result3 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host3, SPVM3Port));

				printResults(result1, result2, result3, "createMRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPL")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPL1Port = Integer.parseInt(replicaInfo[2][0]);
				int SPL2Port = Integer.parseInt(replicaInfo[2][1]);
				int SPL3Port = Integer.parseInt(replicaInfo[2][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host1, SPL1Port));
				Future<String> result2 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host2, SPL2Port));
				Future<String> result3 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host3, SPL3Port));

				printResults(result1, result2, result3, "createMRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPB")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPB1Port = Integer.parseInt(replicaInfo[3][0]);
				int SPB2Port = Integer.parseInt(replicaInfo[3][1]);
				int SPB3Port = Integer.parseInt(replicaInfo[3][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host1, SPB1Port));
				Future<String> result2 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host2, SPB2Port));
				Future<String> result3 = executor.submit(new createMRecordUDP(
						firstName, lastName, address, lastDate, lastLocation,
						status, badgeID, requestID, host3, SPB3Port));

				printResults(result1, result2, result3, "createMRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	// use UDP to send request for record number and return the total record
	// number of
	// three server objects

	@Override
	public String getRecordCounts(String badgeID) {
		int requestID = Sequencer.REQUESTID.getID();
		String StationCheck = badgeID.substring(0, 3);
		String result = null;

		if (StationCheck.equalsIgnoreCase("SPV")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);

				int SPVM1Port = Integer.parseInt(replicaInfo[1][0]);
				int SPVM2Port = Integer.parseInt(replicaInfo[1][1]);
				int SPVM3Port = Integer.parseInt(replicaInfo[1][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host1, SPVM1Port));
				Future<String> result2 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host2, SPVM2Port));
				Future<String> result3 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host3, SPVM3Port));

				printResults(result1, result2, result3, "getRecordCounts");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
				result = result1.get().split(",")[0];
				System.out.println(result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPL")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPL1Port = Integer.parseInt(replicaInfo[2][0]);
				int SPL2Port = Integer.parseInt(replicaInfo[2][1]);
				int SPL3Port = Integer.parseInt(replicaInfo[2][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host1, SPL1Port));
				Future<String> result2 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host2, SPL2Port));
				Future<String> result3 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host3, SPL3Port));

				printResults(result1, result2, result3, "getRecordCounts");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPB")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPB1Port = Integer.parseInt(replicaInfo[3][0]);
				int SPB2Port = Integer.parseInt(replicaInfo[3][1]);
				int SPB3Port = Integer.parseInt(replicaInfo[3][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host1, SPB1Port));
				Future<String> result2 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host2, SPB2Port));
				Future<String> result3 = executor
						.submit(new getRecordCountsUDP(badgeID, requestID,
								host3, SPB3Port));
				
				printResults(result1, result2, result3, "getRecordCounts");

				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	@Override
	public String editCRecord(String lastName, String recordID,
			String newStatus, String badgeID) {
		int requestID = Sequencer.REQUESTID.getID();
		String StationCheck = badgeID.substring(0, 3);
		String result = null;

		if (StationCheck.equalsIgnoreCase("SPV")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPVM1Port = Integer.parseInt(replicaInfo[1][0]);
				int SPVM2Port = Integer.parseInt(replicaInfo[1][1]);
				int SPVM3Port = Integer.parseInt(replicaInfo[1][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host1, SPVM1Port));
				Future<String> result2 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host2, SPVM2Port));
				Future<String> result3 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host3, SPVM3Port));

				printResults(result1, result2, result3, "editCRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPL")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPL1Port = Integer.parseInt(replicaInfo[2][0]);
				int SPL2Port = Integer.parseInt(replicaInfo[2][1]);
				int SPL3Port = Integer.parseInt(replicaInfo[2][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host1, SPL1Port));
				Future<String> result2 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host2, SPL2Port));
				Future<String> result3 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host3, SPL3Port));

				printResults(result1, result2, result3, "editCRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPB")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPB1Port = Integer.parseInt(replicaInfo[3][0]);
				int SPB2Port = Integer.parseInt(replicaInfo[3][1]);
				int SPB3Port = Integer.parseInt(replicaInfo[3][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host1, SPB1Port));
				Future<String> result2 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host2, SPB2Port));
				Future<String> result3 = executor.submit(new editCRecordUDP(
						lastName, recordID, newStatus, badgeID, requestID,
						host3, SPB3Port));

				printResults(result1, result2, result3, "editCRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;

	}

	/**
	 * @author Yulong Song New methods of assignment 2
	 */
	@Override
	public String transferRecord(String badgeID, String recordID,
			String remoteStationServerName) {
		int requestID = Sequencer.REQUESTID.getID();
		String StationCheck = badgeID.substring(0, 3);
		String result = null;

		if (StationCheck.equalsIgnoreCase("SPV")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);

				int SPVM1Port = Integer.parseInt(replicaInfo[1][0]);
				int SPVM2Port = Integer.parseInt(replicaInfo[1][1]);
				int SPVM3Port = Integer.parseInt(replicaInfo[1][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host1, SPVM1Port));
				Future<String> result2 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host2, SPVM2Port));
				Future<String> result3 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host3, SPVM3Port));

				printResults(result1, result2, result3, "transferRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);

				// System.out.println(result);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPL")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPL1Port = Integer.parseInt(replicaInfo[2][0]);
				int SPL2Port = Integer.parseInt(replicaInfo[2][1]);
				int SPL3Port = Integer.parseInt(replicaInfo[2][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host1, SPL1Port));
				Future<String> result2 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host2, SPL2Port));
				Future<String> result3 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host3, SPL3Port));

				printResults(result1, result2, result3, "transferRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else if (StationCheck.equalsIgnoreCase("SPB")) {
			try {
				InetAddress host1 = InetAddress.getByName(replicaInfo[0][0]);
				InetAddress host2 = InetAddress.getByName(replicaInfo[0][1]);
				InetAddress host3 = InetAddress.getByName(replicaInfo[0][2]);
				int SPB1Port = Integer.parseInt(replicaInfo[3][0]);
				int SPB2Port = Integer.parseInt(replicaInfo[3][1]);
				int SPB3Port = Integer.parseInt(replicaInfo[3][2]);

				System.out.println(requestID);
				ExecutorService executor = Executors.newCachedThreadPool();
				Future<String> result1 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host1, SPB1Port));
				Future<String> result2 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host2, SPB2Port));
				Future<String> result3 = executor.submit(new transferRecordUDP(
						badgeID, recordID, remoteStationServerName, requestID,
						host3, SPB3Port));

				printResults(result1, result2, result3, "transferRecord");
				
				resultsAnalyzer RA = new resultsAnalyzer(
						ServerCommunication.REPLICA_HOST,
						ServerCommunication.REPLICA_MANAGER_PORT);
				result = RA.analyze(result1, result2, result3);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return result;
	}

	@Override
	public boolean transfer(String record) { // transfer function
		int requestID = Sequencer.REQUESTID.getID();
		System.out.println(requestID);
		return true;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FEImpl server = new FEImpl();

		server.ORBsetup(args); // ORB setup function		

	}

}
