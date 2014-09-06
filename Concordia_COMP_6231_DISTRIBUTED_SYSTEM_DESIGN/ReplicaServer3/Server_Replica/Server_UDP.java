package Server_Replica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import common.UdpPackage;

/**
 * File name : Server_UDP.java
 * 
 * COMP 6231 DISTRIBUTED SYSTEM DESIGN Project 1
 * 
 * @author Lin Zhu 6555659
 * 
 *         This is the UDP_Server class which helps the 3 stations to
 *         communicate
 */
class Server_UDP implements Runnable {

	private Server_DPISImpl server;

	public Server_UDP() {
	}

	public Server_UDP(Server_DPISImpl server) {
		this.server = server;
	}

	@SuppressWarnings("resource")
	public void run() {

		logFile.write(server.getServerName(), server.getServerName()
				+ " internal thread is starting......");
		System.out.println(">> "+server.getServerName()
				+ " internal thread is starting......");

		try {
			DatagramSocket socket = new DatagramSocket(server.getServerPort());
			byte buffer[] = new byte[UdpPackage.pkgSize];
			DatagramPacket packet = new DatagramPacket(buffer, UdpPackage.pkgSize);
			while (true) {
				socket.receive(packet);
				String request = new String(packet.getData()).trim();
				// Local Case 1 : Asking for record number
		
					if (request.charAt(0) == 'N') {
						byte buffer1[] = new byte[UdpPackage.pkgSize];
						buffer1 = server.getTotalRecordsNumber().getBytes();
						DatagramPacket packettR = new DatagramPacket(buffer1,
								buffer1.length, packet.getAddress(),
								packet.getPort());
						socket.send(packettR);
					}
					// Local Case 2 : Asking for transfer a record
					else {
						byte buffer2[] = new byte[UdpPackage.pkgSize];
						boolean returnCondition = true;
						returnCondition = server
								.addTransferredRecord(request);
						buffer2 = (returnCondition == true ? "TRUE".getBytes()
								: "FALSE".getBytes());
						DatagramPacket packettR = new DatagramPacket(buffer2,
								buffer2.length, packet.getAddress(),
								packet.getPort());
						socket.send(packettR);
					}
				}

		} catch (Exception e) {

			logFile.write(server.getServerName(), "[ERROR] >> Error in "
					+ server.getServerName() + " internal Thread");

			System.out.println(e.getMessage());
		}
	}

}