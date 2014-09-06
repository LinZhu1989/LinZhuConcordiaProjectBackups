package common;

import java.util.Random;

public class ServerCommunication {
	
	private final static Random generator = new Random();	
	
	public static Integer sleepAwhile() {
		Integer time = (generator.nextInt(1) + 1) * WAITING_TIME;
		System.out.printf("sleep for %d second to let CORBA catch up.\n", time / 1000);
		return time;
	}
	
	public static final int REPLICA_MANAGER_PORT = 2020;
	public static final String REPLICA_HOST = "localhost";
			
	public static final String SPVM = "spvm";
	public static final String SPL = "spl";
	public static final String SPB = "spb";
	
	public static final String REPLICA_INFO_FILE = "c:\\temp\\replicaInfo.txt";
	
	/**
	 * this three port is used to communicate with the FE
	 */
	public static int SPVM_PORT = 5100;
	public static int SPL_PORT = 5101;
	public static int SPB_PORT = 5102;
		
	/**
	 * this three port is used to communicate within the SERVERS
	 */
	public static int SPVM_PORT_LCOAL = 9200;
	public static int SPL_PORT_LCOAL = 9201;
	public static int SPB_PORT_LCOAL = 9202;	
	
	/**
	 * the waiting time for each thread.
	 */
	public static int WAITING_TIME = 2000;	
	
	public static int getListeningPortByName(String name) {
		if (name.compareToIgnoreCase(SPVM) == 0) {
			return SPVM_PORT;
		} else if (name.compareToIgnoreCase(SPL) == 0) {
			return SPL_PORT;
		} else {
			return SPB_PORT;	
		}
	}	
	
	public static int getLocalListeningPortByName(String name) {
		if (name.compareToIgnoreCase(SPVM) == 0) {
			return SPVM_PORT_LCOAL;
		} else if (name.compareToIgnoreCase(SPL) == 0) {
			return SPL_PORT_LCOAL;
		} else {
			return SPB_PORT_LCOAL;	
		}
	}	
	
}
