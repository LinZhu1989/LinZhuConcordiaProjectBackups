package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import common.PackageAnalyzer;
import common.ServerCommunication;
import common.TransferRecordRequest;
import common.UdpFunctionCall;
import common.UdpPackage;
import common.WaitPackage;

/**
 * the hash map class used by the station server.
 * 
 * @author Jingang.Li
 * 
 */
class HashMap {
	ArrayList<HashMapNode> recordMap;

	public HashMap() {
		recordMap = new ArrayList<HashMapNode>();

		for (char c = 'A'; c <= 'Z'; ++c) {
			recordMap.add(new HashMapNode(c));
		}
	}

	public ArrayList<RecordInterface> get(char key) {
		if (key >= 'A' && key <= 'Z') {
			return recordMap.get((int) (key - 'A')).RecordList;
		}
		return null;
	}
}

/**
 * the pair <char, ArrayList> of the hash map
 * 
 * @author Jingang.Li
 * 
 */
class HashMapNode {
	public char key;
	ArrayList<RecordInterface> RecordList;

	public HashMapNode(char key) {
		this.key = key;
		this.RecordList = new ArrayList<RecordInterface>();
	}
}

public class StationServer implements StationServerInterface {

	public static void main(String[] args) {
		StationServer spvm = new StationServer(ServerCommunication.SPVM);
		spvm.startListening(spvm);
		System.out.println(spvm.getStationName() + "Station Server started");
		StationServer spl = new StationServer(ServerCommunication.SPL);
		spl.startListening(spl);
		System.out.println(spl.getStationName() + "Station Server started");
		StationServer spb = new StationServer(ServerCommunication.SPB);
		spb.startListening(spb);
		System.out.println(spb.getStationName() + "Station Server started");
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * The station name.
	 */
	String stationName;
	/**
	 * The records map.
	 */
	public HashMap records;
	/**
	 * The logger.
	 */
	private FileLogger logger = null;
	/**
	 * the internal counter for the records.
	 */
	public Integer nextRecordCount;

	/**
	 * the expected request Id for the totally order
	 */
	private static Integer nextRequestID;

	public final static Random generator = new Random();
	public final static int MAX_SOCKET_RETRY = 3;

	StationServer(String strName) {
		stationName = strName;

		this.records = new HashMap();

		logger = new FileLogger(getStationName() + "txt.Log");
		logger.logMessage("Server " + getStationName() + " created.");

		nextRecordCount = 10000;
		nextRequestID = 1;
	}

	/**
	 * start to listening to the "remote" and the "local" port for the server.
	 * 
	 * @param server
	 */
	public void startListening(StationServer server) {
		ServerListener handler = new ServerListener(server);
		Thread t = new Thread(handler);
		t.start();

		LocalServerListener lHandler = new LocalServerListener(server);
		Thread lt = new Thread(lHandler);
		lt.start();
	}

	String getStationName() {
		return stationName;
	}

	/**
	 * this function is used to get the recored count on the current server.
	 * 
	 * @param recordType
	 * @return
	 */
	private int getLocalRecordCount(char recordType) {
		int count = 0;
		for (char c = 'A'; c <= 'Z'; ++c) {
			List<RecordInterface> targetList = records.get(c);

			synchronized (targetList) {
				for (RecordInterface rec : targetList) {
					if (rec.getClass() == CriminalRecord.class
							&& recordType == RecordInterface.RECORD_CRIMINAL) {
						++count;
					} else if (rec.getClass() == MissingRecord.class
							&& recordType == RecordInterface.RECORD_MISSING) {
						++count;
					}
				}
			}
		}

		return count;
	}

	/**
	 * The runnable to use the socket to get the record count from remote
	 * server.
	 * 
	 * @author Jingang.Li
	 * 
	 */
	class RemoteRecordCountGetter implements Runnable {

		private String stationName;
		private String result;
		private char recordType;

		public RemoteRecordCountGetter(String stationName, char recordType) {
			this.stationName = stationName;
			this.recordType = recordType;
			result = "RemoteRecordCount timeout";
		}

		public String getResult() {
			return result;
		}

		@Override
		public void run() {
			DatagramPacket pkg = new DatagramPacket(
					new byte[UdpPackage.pkgSize], UdpPackage.pkgSize);
			DatagramSocket socket = null;
			RecordCountPackageHandler cmdPkg = new RecordCountPackageHandler();
			RecordCountPackageHandler rPkg = new RecordCountPackageHandler();

			try {
				cmdPkg.remoteAddr = InetAddress.getByName("localhost");
				cmdPkg.remotePort = ServerCommunication
						.getLocalListeningPortByName(stationName);
				cmdPkg.pkgType = RecordCountPackageHandler.PKG_REQ;
				cmdPkg.cmdType = RecordCountPackageHandler.CMD_GET_COUNT;
				cmdPkg.seqNumber = (byte) StationServer.generator.nextInt(128);
				cmdPkg.recordType = recordType;
				cmdPkg.recordCount = 0;
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			int timeoutCount = MAX_SOCKET_RETRY;
			try {
				socket = new DatagramSocket();
				socket.setSoTimeout(4 * ServerCommunication.WAITING_TIME);
				while (true && timeoutCount >= 0) {
					try {
						socket.send(cmdPkg.compose());
						socket.receive(pkg);
						rPkg.parse(pkg);
						if (rPkg.pkgType == RecordCountPackageHandler.PKG_ACK
								&& rPkg.cmdType == RecordCountPackageHandler.CMD_GET_COUNT
								&& rPkg.seqNumber == cmdPkg.seqNumber + 1) {
							result = stationName + " "
									+ Integer.toString((int) rPkg.recordCount);
							return;
						}
					} catch (SocketTimeoutException e) {
						timeoutCount--;
					}
				}
			} catch (SocketException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				socket.close();
			}
		}
	}

	/**
	 * add record to the list targetList. in order to maximize the concurrency,
	 * only lock the targetList belongs to one character.
	 * 
	 * @param targetList
	 * @param record
	 * @return
	 */
	private boolean addRecord(ArrayList<RecordInterface> targetList,
			RecordInterface record) {
		synchronized (targetList) {
			for (int i = 0; i < targetList.size(); ++i) {
				RecordInterface listRecord = targetList.get(i);
				if (record.getClass() != listRecord.getClass())
					continue;

				if (listRecord.getLastname().compareToIgnoreCase(
						record.getLastname()) == 0
						&& listRecord.getFirstName().compareToIgnoreCase(
								record.getFirstName()) == 0) {
					return false;
				}
			}
			targetList.add(record);
		}
		return true;
	}

	private char getFirstChar(String str) {
		return Character.toUpperCase(str.charAt(0));
	}

	/***
	 * check if the budgedID belongs to the server.
	 * 
	 * @param budgedId
	 * @return
	 */
	private boolean isValidClient(String badgedId) {
		String bServer = this.getServerName(badgedId);
		return bServer.compareToIgnoreCase(this.getStationName()) == 0;
	}

	@Override
	public OperationResult createCRecord(String badgeID, String firstName,
			String lastName, String description, char status) {

		String msg;
		if (isValidClient(badgeID) == false) {
			msg = "createCRecord: Invalid client connect request " + badgeID;
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		if (CriminalRecord.isValidStatus(Character.toString(status)) == false) {
			msg = "createCRecord: Invalid status" + status;
			logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		int count = increaseCount();
		if (count > 99999 || count < 0) {
			msg = "createCRecord: record ID out of range.";
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}
		String strId = String.format("CR%05d", count);
		CriminalRecord record = new CriminalRecord(strId, firstName, lastName,
				description, status);

		ArrayList<RecordInterface> lst = records.get(getFirstChar(lastName));
		String result = "";
		if (lst == null) {
			msg = "create CRecord Fail: record list not found";
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		} else {
			if (addRecord(lst, record) == true) {
				result = "succeed ";
				result = String.format("createCRecord: %s %s create %s",
						badgeID, result, record.toString());

				msg = result;
				logger.logMessage(msg);
				return new OperationResult(true, msg);
			} else {
				result = "fail ";
				result = String.format("createCRecord: %s %s create %s",
						badgeID, result, record.toString());

				msg = result;
				logger.logMessage(msg);
				return new OperationResult(false, msg);
			}
		}
	}

	@Override
	public OperationResult createMRecord(String badgeID, String firstName,
			String lastName, String address, String lastDate,
			String lastLocation, char status) {

		String msg = "";
		if (isValidClient(badgeID) == false) {
			msg = "createMRecord: Invalid client connect request " + badgeID;
			logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		if (MissingRecord.isValidStatus(Character.toString(status)) == false) {
			msg = "createMRecord: Invalid status" + status;
			logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		int count = increaseCount();
		if (count > 99999 || count < 0) {
			msg = "createMRecord: record ID out of range.";
			logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		String strId = String.format("MR%05d", count);
		MissingRecord record = new MissingRecord(strId, firstName, lastName,
				address, lastDate.toString(), lastLocation, status);

		ArrayList<RecordInterface> lst = records.get(getFirstChar(lastName));
		String result = "";
		if (lst == null) {
			result = "create MRecord Fail: record list not found";
			logger.logMessage(msg);
			return new OperationResult(false, msg);
		} else {
			if (addRecord(lst, record) == true) {
				result = "succeed ";
				result = String.format("createMRecord: %s %s create %s",
						badgeID, result, record.toString());

				msg = result;
				logger.logMessage(msg);
				return new OperationResult(true, msg);
			} else {
				result = "fail ";
				result = String.format("createMRecord: %s %s create %s",
						badgeID, result, record.toString());

				msg = result;
				logger.logMessage(msg);
				return new OperationResult(false, msg);
			}
		}
	}

	@Override
	public OperationResult getRecordCounts(String badgeID, char recordType) {

		String msg = "";
		if (isValidClient(badgeID) == false) {
			msg = "getRecordCounts: Invalid client connect request " + badgeID;
			logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		if (recordType != RecordInterface.RECORD_CRIMINAL
				&& recordType != RecordInterface.RECORD_MISSING) {
			msg = "getRecordCounts: Invalid recordType " + recordType;
			logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		String result = "getRecordCount "
				+ (recordType == RecordInterface.RECORD_CRIMINAL ? "Criminal "
						: "Missing ");
		result = result + getStationName() + " "
				+ Integer.toString(getLocalRecordCount(recordType));

		ArrayList<RemoteRecordCountGetter> getters = new ArrayList<RemoteRecordCountGetter>();
		getters.add(new RemoteRecordCountGetter(ServerCommunication.SPVM,
				recordType));
		getters.add(new RemoteRecordCountGetter(ServerCommunication.SPL,
				recordType));
		getters.add(new RemoteRecordCountGetter(ServerCommunication.SPB,
				recordType));

		if (this.getStationName().compareToIgnoreCase(ServerCommunication.SPVM) == 0)
			getters.remove(0);
		else if (this.getStationName().compareToIgnoreCase(
				ServerCommunication.SPL) == 0)
			getters.remove(1);
		else if (this.getStationName().compareToIgnoreCase(
				ServerCommunication.SPB) == 0)
			getters.remove(2);

		Thread t1 = new Thread(getters.get(0));
		Thread t2 = new Thread(getters.get(1));

		try {
			t1.start();
			t2.start();

			t1.join();
			t2.join();

			for (RemoteRecordCountGetter g : getters)
				result = result + " " + g.getResult();
		} catch (InterruptedException e) {
			msg = e.toString();
			logger.logMessage(msg);
		}

		result = "getRecordCounts: " + badgeID + " " + result;
		msg = result;
		logger.logMessage(msg);
		return new OperationResult(true, msg);
	}

	@Override
	public OperationResult editCRecord(String badgeID, String lastName,
			String recordID, char newStatus) {

		String msg = "";
		if (isValidClient(badgeID) == false) {
			msg = "Edit Record fail, Invalid client connect request " + badgeID;
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		String result = "";
		if (CriminalRecord.isValidRecordID(recordID) == false
				&& MissingRecord.isValidRecordID(recordID) == false) {
			msg = "Edit Record fail, Invalid RecordID " + recordID;
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		if (recordID.startsWith("CR")
				&& (newStatus != RecordInterface.CR_CAPTURED && newStatus != RecordInterface.CR_ON_RUN)) {
			msg = "Edit Record fail, incompatiable record type " + recordID
					+ " and status " + (newStatus);
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		} else if (recordID.startsWith("MR")
				&& (newStatus != RecordInterface.MR_FOUND && newStatus != RecordInterface.MR_MISSING)) {
			msg = "Edit Record fail, incompatiable record type " + recordID
					+ " and status " + (newStatus);
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		} else {
			ArrayList<RecordInterface> targetList = records
					.get(getFirstChar(lastName));

			if (targetList == null) {
				msg = "Edit Record fail, record list not found";
				this.logger.logMessage(msg);
				return new OperationResult(false, msg);
			} else {
				result = "Edit Record fail, record " + recordID + " not found.";
				for (int i = 0; i < targetList.size(); ++i) {
					RecordInterface record = targetList.get(i);
					/**
					 * for editing, only lock the record to maximize the
					 * concurrency.
					 */
					synchronized (record) {
						if (record.getRecordID().compareToIgnoreCase(recordID) == 0) {
							record.setLastname(lastName);
							record.setStatus(newStatus);
							result = "EditRecord succeed: " + recordID;
							result = badgeID + " " + result;
							logger.logMessage(result);
							return new OperationResult(true, msg);							
						}
					}
				}
			}
		}

		result = badgeID + " " + result;
		logger.logMessage(result);
		msg = result;
		return new OperationResult(false, msg);
	}

	private boolean isValidServerName(String serverName) {
		return serverName.toLowerCase().charAt(2) == 'v'
				||serverName.toLowerCase().charAt(2) == 'l'
				|| serverName.toLowerCase().charAt(2) == 'b';
	}

	private String getServerName(String badgeId) {
		String result = badgeId.substring(0, badgeId.length() - 4);
		return result;
	}

	@Override
	public OperationResult transferRecord(String badgeID, String recordID,
			String remoteStationServerName) {

		String msg = "";
		String serverName = getServerName(badgeID);
		if (isValidServerName(serverName) == false) {
			msg = "transferRecord: Invalid budge ID " + badgeID;
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		if (isValidServerName(remoteStationServerName) == false) {
			msg = "transferRecord: Invalid remoteStationServerName "
					+ remoteStationServerName;
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		if (remoteStationServerName.toLowerCase().charAt(2) == 'v')
		{
			remoteStationServerName = ServerCommunication.SPVM;
		}else if (remoteStationServerName.toLowerCase().charAt(2) == 'l') {
			remoteStationServerName = ServerCommunication.SPL;
		}else {
			remoteStationServerName = ServerCommunication.SPB;
		}
		
		RecordInterface record = this.isRecordExisted(recordID);
		if (record == null) {
			msg = "transferRecord: recordID " + recordID + " does not existed.";
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}

		ArrayList<RecordInterface> lst = records.get(this.getFirstChar(record
				.getLastname()));
		if (lst == null) {
			msg = "transferRecord: Error occurs during transferRecord.";
			this.logger.logMessage(msg);
			return new OperationResult(false, msg);
		}
		
		DatagramSocket socket = null;
		DatagramPacket pkg = null;
		synchronized (lst) {
			int timeoutCount = MAX_SOCKET_RETRY;
			
			String theRecord = record.toString();
			
			System.out.println("-----------------------Transfer this data:" + theRecord);
			
			try {
				socket = new DatagramSocket();
				
				socket.setSoTimeout(4 * ServerCommunication.WAITING_TIME);
				while (true && timeoutCount > 0) {
					TransferRecordRequest transRequest = new TransferRecordRequest(
							theRecord);
					transRequest.setRecordString(theRecord);
					transRequest.remoteAddr = InetAddress.getByName("localhost");
					transRequest.remotePort = ServerCommunication
							.getLocalListeningPortByName(remoteStationServerName);
					
					socket.send(transRequest.compose());
					pkg = new DatagramPacket(
							new byte[UdpPackage.pkgSize], UdpPackage.pkgSize);
					try {
						socket.receive(pkg);
						if (UdpPackage.getCmdType(pkg).compareToIgnoreCase(UdpPackage.CMD_LOCALTRNAS) == 0) {
							transRequest.parse(pkg);
							if (transRequest.getRecordString().compareToIgnoreCase("false") == 0) {
								return new OperationResult(false, "transferRecord: Error occurs during transferRecord.");
							} else {
								break;
							}
						}						
					} catch (SocketTimeoutException e) {
						timeoutCount--;
					}
				}
			} catch (UnknownHostException e) {
				msg = "transferRecord: Error: cannot find host";
				return new OperationResult(false, msg);
			} catch (SocketException e) {
				msg = "transferRecord: Error: cannot bind socket";
				return new OperationResult(false, msg);
			} catch (IOException e) {
				msg = "transferRecord: Error: send data error";
				return new OperationResult(false, msg);
			} finally {
				if (socket != null)
					socket.close();
			}

			lst.remove(record);
			msg = "transferRecord: Succeed " + theRecord.toString() + " to "
					+ remoteStationServerName;
			this.logger.logMessage(msg);
			return new OperationResult(true, msg);

		}
	}

	/**
	 * return the current expected request id. need lock the count to make sure
	 * the result correct.
	 * 
	 * @return
	 */
	public int getExpectedRequestID() {
		synchronized (nextRequestID) {
			return nextRequestID;
		}
	}

	/**
	 * increase the current expected id count and return the old id count. need
	 * lock the count to make sure the result correct.
	 * 
	 * @param nextRecordCount
	 */
	public int increaseExpectedRequestID() {
		synchronized (nextRequestID) {
			return nextRequestID++;

		}
	}

	/**
	 * return the current record id. need lock the count to make sure the result
	 * correct.
	 * 
	 * @return
	 */
	public int getCount() {
		synchronized (nextRecordCount) {
			return nextRecordCount;
		}
	}

	/**
	 * increase the current record id count and return the old id count. need
	 * lock the count to make sure the result correct.
	 * 
	 * @param nextRecordCount
	 */
	public int increaseCount() {
		synchronized (nextRecordCount) {
			return this.nextRecordCount++;

		}
	}

	/**
	 * Function to check if the record is already existed in the map.
	 * 
	 * @param rcd
	 * @return true if record is in the map, otherwise false.
	 */
	private boolean isRecordExisted(RecordInterface record) {
		return isRecordExisted(record.getRecordID()) != null;
	}

	/***
	 * check if the recordID existed in the server.
	 * 
	 * @param recordID
	 * @return
	 */
	private RecordInterface isRecordExisted(String recordID) {
		ArrayList<RecordInterface> lst = null;
		synchronized (records) {
			for (char key = 'A'; key <= 'Z'; ++key) {
				lst = records.get(key);
				synchronized (lst) {
					for (RecordInterface r : lst) {
						if (r.getRecordID().compareToIgnoreCase(recordID) == 0)
							return r;
					}
				}
			}
		}
		return null;
	}

	@Override
	public OperationResult transfer(String theRecord) {

		CriminalRecord crecord = new CriminalRecord("", "", "", "", 'a');
		MissingRecord mrecord = new MissingRecord("", "", "", "", "", "", 'a');

		if (crecord.parseString(theRecord) == false)
			crecord = null;
		if (mrecord.parseString(theRecord) == false)
			mrecord = null;

		RecordInterface record = crecord;
		if (record == null) {
			record = mrecord;
		}

		// both crecord and mrecord are null, the input string is not valid.
		if (record == null)
			return new OperationResult(false, "");

		this.logger.logMessage("received transfer data request:"
				+ record.toString());
		// this record already exist in the server, this is impossible.
		// only when theRecord is not in the map we can add the record
		// to the map.
		if (isRecordExisted(record))
			return new OperationResult(false, "");

		ArrayList<RecordInterface> lst = records.get(getFirstChar(record
				.getLastname()));
		if (lst == null)
			return new OperationResult(false, "");

		boolean result = false;
		result = this.addRecord(lst, record);

		if (result == false) {
			this.logger.logMessage("received transfer data request but add :"
					+ record.toString() + " failed.");
		} else {
			this.logger.logMessage("received transfer data request and add :"
					+ record.toString() + " succeed.");
		}

		return new OperationResult(true, "");
	}

	/**
	 * This class is used to handling the message request from other servers
	 * "locally".
	 * 
	 * @author Jingang.Li
	 * 
	 */
	class LocalServerListener implements Runnable {

		StationServer server;

		public LocalServerListener(StationServer server) {
			this.server = server;
		}

		@Override
		public void run() {

			logger.logMessage(String.format(
					"%s Start listening on local port:%d", server
							.getStationName(), ServerCommunication
							.getLocalListeningPortByName(server
									.getStationName())));

			DatagramPacket pkg = new DatagramPacket(
					new byte[UdpPackage.pkgSize], UdpPackage.pkgSize);
			DatagramSocket socket = null;
			RecordCountPackageHandler handler = new RecordCountPackageHandler();

			try {
				socket = new DatagramSocket(
						ServerCommunication
								.getLocalListeningPortByName(server.getStationName()));
				while (true) {
					try {
						socket.receive(pkg);
						handler.parse(pkg);

						if (handler.pkgType == RecordCountPackageHandler.PKG_REQ
								&& handler.cmdType == RecordCountPackageHandler.CMD_GET_COUNT) {
							handler.pkgType = RecordCountPackageHandler.PKG_ACK;
							handler.seqNumber += 1;

							handler.recordCount = (byte) getLocalRecordCount(handler.recordType);
							DatagramPacket ackPkg = handler.compose();

							DatagramSocket ackSocket = new DatagramSocket();
							ackSocket.send(ackPkg);
							ackSocket.close();
							continue;
						} else if (UdpPackage.getCmdType(pkg)
								.compareToIgnoreCase(UdpPackage.CMD_LOCALTRNAS) == 0) {
							TransferRecordRequest transHandler = new TransferRecordRequest(
									"");					
							transHandler.parse(pkg);
							
							OperationResult result = this.server
									.transfer(transHandler.getRecordString());
							transHandler.pkgType = UdpPackage.PKG_ACK;
							if (result.result)
								transHandler.setRecordString("true");
							else
								transHandler.setRecordString("false");
							DatagramPacket ackPkg = transHandler.compose();

							DatagramSocket ackSocket = new DatagramSocket();
							ackSocket.send(ackPkg);
							ackSocket.close();
							continue;
						}
						
					} catch (SocketTimeoutException e) {
					}
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				socket.close();
			}
		}
	}

	/**
	 * This class is used to handling the message request from FE.
	 * 
	 * @author Jingang.Li
	 * 
	 */
	class ServerListener implements Runnable {

		StationServer server;

		public ServerListener(StationServer server) {
			this.server = server;
		}

		@Override
		public void run() {
			logger.logMessage(String.format("%s Start listening on port:%d",
					server.getStationName(), ServerCommunication
							.getListeningPortByName(server.getStationName())));

			DatagramSocket socket = null;
			try {
				socket = new DatagramSocket(
						ServerCommunication
								.getListeningPortByName(getStationName()));
				while (true) {
					try {
						DatagramPacket pkg = new DatagramPacket(
								new byte[UdpPackage.pkgSize],
								UdpPackage.pkgSize);

						socket.receive(pkg);
						if (UdpPackage.getPkgType(pkg).compareToIgnoreCase(
								UdpPackage.PKG_REQ) == 0
								&& UdpPackage.getCmdType(pkg)
										.compareToIgnoreCase(
												UdpPackage.CMD_STOP) == 0) {
							logger.logMessage(String.format(
									"Received kill package, %s exit.\n",
									this.server.getStationName()));
							break;
						} else {
							UdpReponseThread udpThread = new UdpReponseThread(
									this.server, pkg);
							new Thread(udpThread).start();
						}
						continue;
					} catch (SocketTimeoutException e) {
					}
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				socket.close();
			}
		}
	}

	/**
	 * This class is used to handling each function call from the FE by
	 * separated thread
	 * 
	 * @author Jingang.Li
	 * 
	 */
	class UdpReponseThread implements Runnable {

		private StationServer server;
		private PackageAnalyzer analyzer;
		private InetAddress address;
		private int portNum;

		public UdpReponseThread(StationServer server, DatagramPacket theRequest) {
			this.server = server;
			this.address = theRequest.getAddress();
			this.portNum = theRequest.getPort();
			this.analyzer = new PackageAnalyzer(theRequest);
		}

		@Override
		public void run() {

			DatagramSocket socket = null;

			try {
				socket = new DatagramSocket();
				logger.logMessage(server.getStationName()
						+ " response thread for request No. "
						+ analyzer.getRequestID() + " is starting......");

				// Check if can execute
				while (server.getExpectedRequestID() != analyzer.getRequestID()) {
					
					logger.logMessage(server.getStationName()
							+ " response thread for request No. "
							+ analyzer.getRequestID() + " is out of order, expecting: " + server.getExpectedRequestID()
							+ "send wait package.");
					
					WaitPackage wait = new WaitPackage(address, portNum);
					socket.send(wait.compose());
				}

				DatagramPacket packettR = null;
				byte resultBuffer[] = null;
				OperationResult result;
				// Remote Method Call
				switch (analyzer.getCommand()) {
				case "createCRecord":
					result = server.createCRecord(analyzer.getBadgeID(),
							analyzer.getFirstName(), analyzer.getLastName(),
							analyzer.getDescription(), analyzer.getStatus()
									.charAt(0));
					if (result.result == true) {
						resultBuffer = new String("Add CRecord succeed!")
								.getBytes();
					} else {
						resultBuffer = result.msg.getBytes();
					}
					packettR = new DatagramPacket(resultBuffer,
							resultBuffer.length, address, portNum);
					socket.send(packettR);
					server.increaseExpectedRequestID();
					break;
				case "createMRecord":
					result = server.createMRecord(analyzer.getBadgeID(),
							analyzer.getFirstName(), analyzer.getLastName(),
							analyzer.getAddress(), analyzer.getLastDate(),
							analyzer.getLastLocation(), analyzer.getStatus()
									.charAt(0));
					if (result.result == true) {
						resultBuffer = new String("Add MRecord succeed!")
								.getBytes();
					} else {
						resultBuffer = result.msg.getBytes();
					}
					packettR = new DatagramPacket(resultBuffer,
							resultBuffer.length, address, portNum);
					socket.send(packettR);
					server.increaseExpectedRequestID();
					break;
				case "editCRecord":
					result = server.editCRecord(analyzer.getBadgeID(),
							analyzer.getLastName(), analyzer.getRecordID(),
							analyzer.getNewStatus().charAt(0));
					if (result.result == true) {
						resultBuffer = new String("The new status is updated!")
								.getBytes();
					} else {
						resultBuffer = result.msg.getBytes();
					}
					packettR = new DatagramPacket(resultBuffer,
							resultBuffer.length, address, portNum);
					socket.send(packettR);
					server.increaseExpectedRequestID();
					break;
				case "getRecordCounts":
					result = server.getRecordCounts(analyzer.getBadgeID(),
							RecordInterface.RECORD_CRIMINAL);
					resultBuffer = result.msg.getBytes();
					packettR = new DatagramPacket(resultBuffer,
							resultBuffer.length, address, portNum);
					socket.send(packettR);
					server.increaseExpectedRequestID();
					break;
				case "transferRecord":
					result = server.transferRecord(analyzer.getBadgeID(),
							analyzer.getRecordID(),
							analyzer.getRemoteServerName());
					if (result.result == true) {
						resultBuffer = new String("transferRecord succeed!")
								.getBytes();
					} else {
						resultBuffer = result.msg.getBytes();
					}
					packettR = new DatagramPacket(resultBuffer,
							resultBuffer.length, address, portNum);
					socket.send(packettR);
					server.increaseExpectedRequestID();
					break;
				}
			} catch (Exception e) {

				logger.logMessage("[ERROR] >> Error in "
						+ server.getStationName() + " Response Thread");

				System.out.println(e.getMessage());
			} finally {
				logger.logMessage(server.getStationName()
						+ " response thread for request No. "
						+ analyzer.getRequestID() + " is closing......");
				if (socket != null)
					socket.close();
			}
		}
	}

	/**
	 * this is the class to build/parse the command Package. REQ/ACK
	 * 
	 * @author Jingang.Li
	 * 
	 */
	class RecordCountPackageHandler {
		public static final int UNKNOWN = 0;

		public static final byte PKG_REQ = 10;
		public static final byte PKG_ACK = 11;

		public static final byte CMD_GET_COUNT = 1;

		public byte pkgType;
		public byte seqNumber;
		public byte cmdType;
		public char recordType;
		public byte recordCount;
		public InetAddress remoteAddr;
		public int remotePort;

		public RecordCountPackageHandler() {
			this.pkgType = UNKNOWN;
			this.seqNumber = UNKNOWN;
			this.cmdType = UNKNOWN;
			this.recordType = UNKNOWN;
			this.recordCount = 0;
			remoteAddr = null;
			remotePort = 0;
		}

		public void parse(DatagramPacket pkg) {
			int i = 0;
			this.pkgType = pkg.getData()[i++];
			this.seqNumber = pkg.getData()[i++];
			this.cmdType = pkg.getData()[i++];
			this.recordType = (char) pkg.getData()[i++];
			this.recordCount = pkg.getData()[i++];
			this.remoteAddr = pkg.getAddress();
			this.remotePort = pkg.getPort();
		}

		public DatagramPacket compose() {
			byte[] buffer = new byte[UdpPackage.pkgSize];
			int i = 0;
			buffer[i++] = (byte) this.pkgType;
			buffer[i++] = (byte) this.seqNumber;
			buffer[i++] = (byte) this.cmdType;
			buffer[i++] = (byte) this.recordType;
			buffer[i++] = (byte) this.recordCount;
			DatagramPacket result = new DatagramPacket(buffer,
					UdpPackage.pkgSize);
			result.setAddress(remoteAddr);
			result.setPort(remotePort);
			return result;
		}
	}

}
