package Server_Replica;

import java.io.Serializable;

/**
 * File name : Server_Replica.java
 * 
 * COMP 6231 DISTRIBUTED SYSTEM DESIGN Project 1
 * 
 * @author Lin Zhu 6555659
 * 
 */

@SuppressWarnings("serial")
public class Server_Replica extends Server_UDP implements Serializable {

	public Server_Replica(Server_DPISImpl server) {
		super(server);
	}
	public static void main(String[] args) {
		logFile.write("ServerDPIS", "Start to bind server objects ......");
		final Server_DPISImpl SPVMserver = new Server_DPISImpl("SPVMserver", 8888,8891);
		final Server_DPISImpl SPLserver = new Server_DPISImpl("SPLserver", 8889,8892);
		final Server_DPISImpl SPBserver = new Server_DPISImpl("SPBserver", 8890,8893);

		Thread s1 = new Thread(new Server_Replica(SPVMserver));
		Thread s2 = new Thread(new Server_Replica(SPLserver));
		Thread s3 = new Thread(new Server_Replica(SPBserver));
		s1.start();
		s2.start();
		s3.start();
		
		Thread s4 = new Thread(new Receiver_UDP(SPVMserver));
		Thread s5 = new Thread(new Receiver_UDP(SPLserver));
		Thread s6 = new Thread(new Receiver_UDP(SPBserver));
		s4.start();
		s5.start();
		s6.start();
	}

}
