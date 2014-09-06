package PoliceStation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.ORB;

import common.ServerCommunication;
import FECORBAInterface.commonMethods;
import FECORBAInterface.commonMethodsHelper;

class testGetRecordCounts implements Runnable {
	public String serverName;
	public int Id;
	public commonMethods FE;
	public clientImpl P1;
	
	public testGetRecordCounts(String serverName, int Id, commonMethods FE) {
		this.serverName = serverName;
		this.Id = Id;
		this.FE = FE;
		P1 = new clientImpl(serverName, Id, FE);
	}
	
	@Override
	public void run() {
		logFile.write(P1.toString(), "[testGetRecordCounts()]");
		try {
			Thread.sleep(ServerCommunication.sleepAwhile());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = P1.testGetRecordCounts();
		System.out.println(result);
		logFile.write(P1.toString(), result);
	}
}

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

public class clientImpl {
	private String BadgeAcronym;
	private int BadgeID;

	private commonMethods RemoteServer;

	public clientImpl() {

	}

	public clientImpl(String BadgeAcronym, int BadgeID,
			commonMethods RemoteServer) {
		this.BadgeAcronym = BadgeAcronym;
		this.BadgeID = BadgeID;
		this.RemoteServer = RemoteServer;

		logFile.write(this.toString(), this.toString() + " "
				+ "connects to RemoteServer" + ":" + " " + BadgeAcronym
				+ "server");

	}

	public String getBadgeAcronym() {
		return BadgeAcronym;
	}

	public int getBadgeID() {
		return BadgeID;
	}

	public String testCreateCRecord(String firstName, String lastName,
			String description, String status) {
		boolean flag1 = false;

		String reply = null;
		String result = null;
		try {
			reply = RemoteServer.createCRecord(firstName, lastName,
					description, status, this.toString());
			flag1 = reply.equalsIgnoreCase("Add CRecord succeed!");

			if (flag1 == true) {
				result = ("Test add CRecord success!");
			} else {
				result = ("Test add CRecord failed");
			}
		} catch (Exception e) {
			result = ("Test add CRecord had an error:" + e.getMessage());
		}
		return result;
	}

	public String testTransferRecord(String badgeID, String recordID,
			String remoteServer) {
		String result = null;
		String flag = null;
		try {
			flag = RemoteServer.transferRecord(badgeID, recordID, remoteServer);
			if (flag.equalsIgnoreCase("transferRecord succeed!")) {
				result = "test TransferRecord success!";
			} else {
				result = "test TransferRecord failed!";
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public String testCreateMRecord(String firstName, String lastName,
			String address, String lastDate, String lastLocation, String status) {
		boolean flag1 = false;

		String reply = null;
		String result = null;
		try {
			reply = RemoteServer.createMRecord(firstName, lastName, address,
					lastDate, lastLocation, status, this.toString());
			flag1 = reply.equalsIgnoreCase("Add MRecord succeed!");

			if (flag1 == true) {
				result = ("Test add MRecord success!");
			} else {
				result = ("Test add MRecord failed!");
			}
		} catch (Exception e) {
			result = ("Test addMRecord had an error:" + e.getMessage());
		}
		return result;
	}

	public String testEditCRecord(String lastName, String recordID,
			String newStatus) {
		boolean flag = false;
		String result = null;
		try {
			flag = RemoteServer.editCRecord(lastName, recordID, newStatus,
					this.toString()).equalsIgnoreCase(
					"The new status is updated!");
			if (flag == true) {
				result = ("Test Edit CRecord success!");
			} else {
				result = ("Test Edit CRecord failed");
			}
		} catch (Exception e) {
			result = ("Test edit CRecord had an error:" + e.getMessage());
		}
		return result;
	}

	/*
	 * public void testGetDataBase(char c) { try { RemoteServer.getDataBase(c);
	 * } catch (Exception e) { System.out.println(e.getMessage()); } }
	 */

	public String testGetRecordCounts() {
		String result = null;
		try {
			String s = new String(RemoteServer.getRecordCounts(this.toString())
					.trim());
			result = s;
		} catch (Exception e) {
			result = ("Test GetRecordCounts had an error" + e.getMessage());
		}
		return result;
	}

	public String toString() {
		return getBadgeAcronym() + getBadgeID();
	}

	// main()

	public static void main(String[] Args) throws Exception {
		ORB orb = ORB.init(Args, null);
		BufferedReader br = new BufferedReader(new FileReader("ior.txt"));
		String FEIor = br.readLine();
		System.out.println("READ SERVER!");

		br.close();
		org.omg.CORBA.Object FEobject = orb.string_to_object(FEIor);

		final commonMethods FE = commonMethodsHelper.narrow(FEobject);

		System.out.println("Start client...");

		Scanner keyboard = null;
		try {

			keyboard = new Scanner(System.in);
			boolean exit = false;
			while (!exit) {
				System.out.println("What's your choice:");
				System.out.println("0. single thread test.");
				System.out.println("1. multiple thread test.");
				System.out.println("2. error test.");				
				System.out.println("7. quit.");

				int userChoice = keyboard.nextInt();
				switch (userChoice) {
				case 0:
					singleThreadTest(FE);
					break;
				case 1:
					multipleThreadTest(FE);
					break;
				case 2:
					errorTest(FE);
					break;
				case 7:
					exit = true;
				}
				keyboard.nextLine();// clear the residue end-line char.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (keyboard != null)
				keyboard.close();
		}
	}

	/**
	 * Replica1 get the counts by the record type, while the
	 * other two does not distinguish the record type.
	 *  
	 * Because the Replica1 has different return result compare to 
	 * Replica2, and Replica3.
	 * 
	 * Call the getRecordCounts 3 times to mimic the 3 continues
	 * error in the replica side
	 * @param FE
	 */
	private static void errorTest(commonMethods FE) throws InterruptedException {

		testGetRecordCounts test1 = new testGetRecordCounts("SPB", 1111, FE);
		Thread t11 = new Thread(test1);
		t11.start();

		testGetRecordCounts test2 = new testGetRecordCounts("SPB", 1111, FE);
		Thread t12 = new Thread(test2);
		t12.start();

		testGetRecordCounts test3 = new testGetRecordCounts("SPB", 1111, FE);
		Thread t13 = new Thread(test3);
		t13.start();
		
		t11.join();
		t12.join();
		t13.join();
	}

	/**
	 * this is the test case for testing the basic scenario:
	 * under the multi-threading environment
	 * 
	 * GetRecord counts (return 0 0 0)
	 * CReate CRecord
	 * CReate CRecord
	 * Create MRecord
	 * Create MRecord
	 * GetRecordCounts  (return 4 0 0)
	 * 
	 * The sequence number at each replica should be
	 * in order. 
	 * @param FE
	 * @throws InterruptedException
	 */
	private static void multipleThreadTest(final commonMethods FE) throws InterruptedException {

			Thread t11 = new Thread(new Runnable() {

				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPB", 1111, FE);
					logFile.write(P1.toString(), "[testGetRecordCounts()]");

					String result = P1.testGetRecordCounts();
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t11.start();
			t11.join();


			Thread t12 = new Thread(new Runnable() {
				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testCreateCRecord()]");

					String result = P1.testCreateCRecord("Yulong2", "Song2",
							"tall", "on the run");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t12.start();
			
			Thread t13 = new Thread(new Runnable() {
				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testCreateCRecord()]");

					String result = P1.testCreateCRecord("Yulong3", "Song3",
							"tall", "on the run");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t13.start();		

			Thread t14= new Thread(new Runnable() {
				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testCreateMRecord()]");

					String result = P1.testCreateMRecord("Afirstname4",
							"AlastName4", "Aaddress", "2000/11/31",
							"alastLocation", "missing");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t14.start();

			Thread t15= new Thread(new Runnable() {

				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testCreateMRecord()]");

					String result = P1.testCreateMRecord("Afirstname5",
							"AlastName5", "Aaddress", "2000/11/31",
							"alastLocation", "missing");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t15.start();			
			
			t12.join();
			t13.join();
			t14.join();
			t15.join();

		
			Thread t16 = new Thread(new Runnable() {
				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPB", 1111, FE);
					logFile.write(P1.toString(), "[testGetRecordCounts()]");

					String result = P1.testGetRecordCounts();
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t16.start();
			t16.join();

	}
		
	
	/**
	 * this is the test case for testing the basic scenario:
	 * under the single thread environment
	 * 
	 * GetRecord counts (return 0 0 0)
	 * CReate CRecord
	 * Create MRecord
	 * GetRecordCounts  
	 * 	replica2,3 (return 2 0 0) and replica1 return (1 0 0)
	 * EditRecord
	 * transferRecord
	 * GetRecordCounts (return 1 0 1) 
	 * 
	 * @param FE
	 * @throws InterruptedException
	 */
	private static void singleThreadTest(final commonMethods FE) throws InterruptedException {
		{
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPB", 1111, FE);
					logFile.write(P1.toString(), "[testGetRecordCounts()]");

					String result = P1.testGetRecordCounts();
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t1.start();
			t1.join();
		}

		{
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testCreateCRecord()]");

					String result = P1.testCreateCRecord("Yulong", "Song",
							"tall", "on the run");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t1.start();
			t1.join();
		}

		{
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testCreateMRecord()]");

					String result = P1.testCreateMRecord("Afirstname",
							"AlastName", "Aaddress", "2000/11/31",
							"alastLocation", "missing");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t1.start();
			t1.join();
		}

		{
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPB", 1111, FE);
					logFile.write(P1.toString(), "[testGetRecordCounts()]");

					String result = P1.testGetRecordCounts();
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t1.start();
			t1.join();
		}

		{
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testEditCRecord()]");

					String result = P1.testEditCRecord("Song", "CR10000",
							"captured");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t1.start();
			t1.join();
		}

		{
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPVM", 1111, FE);
					logFile.write(P1.toString(), "[testTransferRecord()]");

					String result = P1.testTransferRecord("SPVM1111",
							"CR10000", "SPLServer");
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t1.start();
			t1.join();
		}

		{
			Thread t1 = new Thread(new Runnable() {

				@Override
				public void run() {
					clientImpl P1 = new clientImpl("SPB", 1111, FE);
					logFile.write(P1.toString(), "[testGetRecordCounts()]");

					String result = P1.testGetRecordCounts();
					System.out.println(result);
					logFile.write(P1.toString(), result);
				}
			});
			
			ServerCommunication.sleepAwhile();
			t1.start();
			t1.join();
		}

	}
}
