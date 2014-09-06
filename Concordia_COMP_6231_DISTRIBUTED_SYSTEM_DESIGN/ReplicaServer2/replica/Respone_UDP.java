package replica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * File name : Response_UDP.java
 * 
 * COMP 6231 DISTRIBUTED SYSTEM DESIGN Project 1
 * 
 * @author Lin Zhu 6555659
 * 
 */
class Response_UDP implements Runnable {

	private replica2 server;
	private PackageAnalyzer analyzer;
	private InetAddress address;
	private int portNum;

	public Response_UDP() {
	}

	public Response_UDP(replica2 server, DatagramPacket theRequest) {
		this.server = server;
		this.address = theRequest.getAddress();
		this.portNum = theRequest.getPort();
		this.analyzer = new PackageAnalyzer(theRequest);
	}

	@Override
	public void run() {

		try {

			DatagramSocket socket = new DatagramSocket();

			ReqOrderSystem reqOrder = new ReqOrderSystem();
			byte resultBuffer[] = new byte[8192];

			logFile.write(
					server.getServerName(),
					server.getServerName()
							+ " response thread for request No. "
							+ analyzer.getRequestID() + " is starting......");
			
			// Check if can execute 
			while (!reqOrder.checkOrder(analyzer.getRequestID())) {

				resultBuffer = "wait".getBytes();
				DatagramPacket packettR = new DatagramPacket(resultBuffer,
						resultBuffer.length, address, portNum);
				socket.send(packettR);
			}

			// Remote Method Call
			switch (analyzer.getCommand()) {
			case "createCRecord":
				resultBuffer = server.createCRecord(analyzer.getFirstName(),
						analyzer.getLastName(), analyzer.getDescription(),
						analyzer.getStatus(), analyzer.getBadgeID()).getBytes();
				DatagramPacket packettR = new DatagramPacket(resultBuffer,
						resultBuffer.length, address, portNum);
				socket.send(packettR);
				reqOrder.updateCurMsgID();
				break;
			case "createMRecord":
				resultBuffer = server.createMRecord(analyzer.getFirstName(),
						analyzer.getLastName(), analyzer.getAddress(),
						analyzer.getLastDate(), analyzer.getLastLocation(),
						analyzer.getStatus(), analyzer.getBadgeID()).getBytes();
				packettR = new DatagramPacket(resultBuffer,
						resultBuffer.length, address, portNum);
				socket.send(packettR);
				reqOrder.updateCurMsgID();
				break;
			case "editCRecord":
				resultBuffer = server.editCRecord(analyzer.getLastName(),
						analyzer.getRecordID(), analyzer.getNewStatus(),
						analyzer.getBadgeID()).getBytes();
				packettR = new DatagramPacket(resultBuffer,
						resultBuffer.length, address, portNum);
				socket.send(packettR);
				reqOrder.updateCurMsgID();
				break;
			case "getRecordCounts":
				System.out.println("getRecordCounts!");
				resultBuffer = server.getRecordCounts(analyzer.getBadgeID())
						.getBytes();
				packettR = new DatagramPacket(resultBuffer,
						resultBuffer.length, address, portNum);
				socket.send(packettR);
				reqOrder.updateCurMsgID();
				break;
			case "transferRecord":
				resultBuffer = server.transferRecord(analyzer.getBadgeID(),
						analyzer.getRecordID(), analyzer.getRemoteServerName())
						.getBytes();
				packettR = new DatagramPacket(resultBuffer,
						resultBuffer.length, address, portNum);
				socket.send(packettR);
				reqOrder.updateCurMsgID();
				break;
			}
			logFile.write(
					server.getServerName(),
					server.getServerName()
							+ " response thread for request No. "
							+ analyzer.getRequestID() + " is closing......");

			socket.close();

		} catch (Exception e) {

			logFile.write(server.getServerName(), "[ERROR] >> Error in "
					+ server.getServerName() + " Response Thread");

			System.out.println(e.getMessage());
		}

	}
}
