package Server_Replica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import common.UdpPackage;

public class Receiver_UDP implements Runnable {

	private Server_DPISImpl server;

	public Receiver_UDP() {
	}

	public Receiver_UDP(Server_DPISImpl server) {
		this.server = server;
	}

	@SuppressWarnings("resource")
	@Override
	public void run() {

		logFile.write(server.getServerName(), server.getServerName()
				+ " public thread is starting......");
		System.out.println(">> " + server.getServerName()
				+ " public thread is starting......");

		try {
			DatagramSocket socket = new DatagramSocket(
					server.getServerPublicPort());
			// getServerPublicPort() will return the public port number ,the one know by the FE side
			
			while (true) {
				byte buffer[] = new byte[UdpPackage.pkgSize];
				DatagramPacket packet = new DatagramPacket(buffer, UdpPackage.pkgSize);
				socket.receive(packet);
				// Receive the packet which is from the FE for sure, so create a
				// thread for response
				// Pass the server obj , packet itself
				// The server obj is used for the methods being invoked
				Thread s = new Thread(new Response_UDP(server, packet));
				s.start();
			}
		} catch (Exception e) {

			logFile.write(server.getServerName(), "[ERROR] >> Error in "
					+ server.getServerName() + " public Thread");

			System.out.println(e.getMessage());
		}
	}

}
