package replicaManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import common.FunctionCallHandler;
import common.GetServerAddressReplyPackage;
import common.GetServerAddressRequestPackage;
import common.ServerCommunication;
import common.StopPackage;
import common.UdpFunctionCall;
import common.UdpPackage;
import common.PackageAnalyzer;

/**
 * This class is used to handling the message request from other servers.
 * 
 * @author Jingang.Li
 * 
 */
class ServerListener implements Runnable {

	private ReplicaManager manager;

	public ServerListener(ReplicaManager manager) {
		this.manager = manager;
	}

	@Override
	public void run() {
		DatagramSocket socket = null;

		try {
			socket = new DatagramSocket(
					ServerCommunication.REPLICA_MANAGER_PORT);
			while (true) {
				try {
					DatagramPacket pkg = new DatagramPacket(
							new byte[UdpPackage.pkgSize], UdpPackage.pkgSize);
					socket.receive(pkg);

					// now this package need to be handled by the RM
					String cmdType = UdpPackage.getCmdType(pkg);
					System.out
							.println("get pkg from " + pkg.getSocketAddress());
					if (cmdType.compareToIgnoreCase(UdpPackage.CMD_GETADDR) == 0) {
						GetServerAddressReplyPackage reply = handleGetAddressRequest(pkg);
						socket.send(reply.compose());
					} else { // first check if the package need to be handled by
								// PackageAnalyzer
						PackageAnalyzer pa = new PackageAnalyzer(pkg);
						if (pa != null
								&& pa.getCommand().compareToIgnoreCase(
										UdpPackage.CMD_CRASH) == 0) {
							System.out.println("Server "
									+ pa.getHostInetAddress() + "crashed");
							System.out.println("Send new address information:");
							GetServerAddressReplyPackage reply = handleGetAddressRequest(pkg);
							socket.send(reply.compose());
							continue;

						} else if (pa != null
								&& pa.getCommand().compareToIgnoreCase(
										UdpPackage.CMD_ERROR) == 0) {
							manager.recordErrorAndRebootIfNeeded("", pa.getErrorHostPort(), pkg.getAddress(), pkg.getPort());
							continue;
						}
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

	private GetServerAddressReplyPackage handleGetAddressRequest(
			DatagramPacket pkg) {
		GetServerAddressRequestPackage request = new GetServerAddressRequestPackage(
				pkg.getAddress(), pkg.getPort());
		request.parse(pkg);

		GetServerAddressReplyPackage reply = new GetServerAddressReplyPackage(
				pkg.getAddress(), pkg.getPort());

		reply.seqNumber = request.seqNumber.trim();

		reply.replicaIP1 = manager.getReplica1SMVP().ServerName;
		reply.replicaPort11 = manager.getReplica1SMVP().port;
		reply.replicaPort12 = manager.getReplica1SPL().port;
		reply.replicaPort13 = manager.getReplica1SPB().port;

		reply.replicaIP2 = manager.getReplica2SMVP().ServerName;
		reply.replicaPort21 = manager.getReplica2SMVP().port;
		reply.replicaPort22 = manager.getReplica2SPL().port;
		reply.replicaPort23 = manager.getReplica2SPB().port;

		reply.replicaIP3 = manager.getReplica3SMVP().ServerName;
		reply.replicaPort31 = manager.getReplica3SMVP().port;
		reply.replicaPort32 = manager.getReplica3SPL().port;
		reply.replicaPort33 = manager.getReplica3SPB().port;

		return reply;
	}
}

/**
 * use this class to remember the replica information for simplicity, make all
 * the member as public.
 * 
 * @author Jingang.Li
 * 
 */
class ReplicaInfo {

	public ReplicaInfo(String name, int port) {
		this.ServerName = name;
		this.port = port;
		this.errorCount = 0;
	}

	public String ServerName;
	public Integer port;
	public Integer errorCount;

	public String toString() {
		return new String("ServerName = " + ServerName + " Port = "
				+ port.toString() + " Error count = " + errorCount.toString());
	}
}

public class ReplicaManager implements FunctionCallHandler {

	private static ReplicaManager manger = new ReplicaManager();

	// use this ArrayList to make the search process simpler.
	ArrayList<ReplicaInfo> replicas;

	private GetServerAddressReplyPackage handleGetAddressRequest(
			DatagramPacket pkg) {
		GetServerAddressRequestPackage request = new GetServerAddressRequestPackage(
				pkg.getAddress(), pkg.getPort());
		request.parse(pkg);

		GetServerAddressReplyPackage reply = new GetServerAddressReplyPackage(
				pkg.getAddress(), pkg.getPort());

		reply.seqNumber = request.seqNumber.trim();

		reply.replicaIP1 = this.getReplica1SMVP().ServerName;
		reply.replicaPort11 = this.getReplica1SMVP().port;
		reply.replicaPort12 = this.getReplica1SPL().port;
		reply.replicaPort13 = this.getReplica1SPB().port;

		reply.replicaIP2 = this.getReplica2SMVP().ServerName;
		reply.replicaPort21 = this.getReplica2SMVP().port;
		reply.replicaPort22 = this.getReplica2SPL().port;
		reply.replicaPort23 = this.getReplica2SPB().port;

		reply.replicaIP3 = this.getReplica3SMVP().ServerName;
		reply.replicaPort31 = this.getReplica3SMVP().port;
		reply.replicaPort32 = this.getReplica3SPL().port;
		reply.replicaPort33 = this.getReplica3SPB().port;

		return reply;
	}

	public boolean loadReplicaInfors() {
		replicas = new ArrayList<ReplicaInfo>();

		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(
					ServerCommunication.REPLICA_INFO_FILE));
			String strCount = br.readLine();
			int argCount = Integer.parseInt(strCount);

			for (int i = 0; i < argCount; ++i) {
				String line = br.readLine().trim();
				String[] infos = line.split(",");
				for (int j = 1; j < infos.length; ++j) {
					replicas.add(new ReplicaInfo(infos[0], Integer
							.parseInt(infos[j])));
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public ReplicaManager() {
		System.out.println("load Replica information from:"
				+ ServerCommunication.REPLICA_INFO_FILE);
		loadReplicaInfors();
		System.out.println(this.getReplica1SMVP().toString());
		System.out.println(this.getReplica1SPL().toString());
		System.out.println(this.getReplica1SPB().toString());

		System.out.println(this.getReplica2SMVP().toString());
		System.out.println(this.getReplica2SPL().toString());
		System.out.println(this.getReplica2SPB().toString());

		System.out.println(this.getReplica3SMVP().toString());
		System.out.println(this.getReplica3SPL().toString());
		System.out.println(this.getReplica3SPB().toString());
	}

	public boolean recordErrorAndRebootIfNeeded(String name, int errorPort, InetAddress remoteAddress, int remotePort) {
		for (ReplicaInfo info : replicas) {
			if (info.port == errorPort) {
				++info.errorCount;
				if (info.errorCount > 2) {
					System.out
							.printf("Server %d error 3 time consecutively, reboot it.", errorPort);

					DatagramSocket socket = null;
					try {
						socket = new DatagramSocket();

						System.out.println("Send new address information:");
						GetServerAddressReplyPackage reply = new GetServerAddressReplyPackage(remoteAddress,remotePort);
						socket.send(reply.compose());
						socket.close();
						info.errorCount = 0;

					} catch (SocketException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (socket != null)
							socket.close();
					}

					return true;
				}
				else {
					DatagramSocket socket = null;
					try {
						String strAlive = "alive";
						socket = new DatagramSocket();
						DatagramPacket pkg = new DatagramPacket(
								strAlive.getBytes(), strAlive.getBytes().length);
						
						pkg.setAddress(remoteAddress);
						pkg.setPort(remotePort);

						System.out.println("Send alive!");
						socket.send(pkg);
						socket.close();
					} catch (SocketException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (socket != null)
							socket.close();
					}				

					return true;
				}
			} else {
				info.errorCount = 0;
			}
		}
		return false;
	}

	private static void startListening() {
		ServerListener listener = new ServerListener(manger);
		Thread t = new Thread(listener);
		t.start();
		System.out.println(String.format("%s Start listening on port:%d",
				"ReplicaManager", ServerCommunication.REPLICA_MANAGER_PORT));
	}

	public static void main(String[] args) {
		System.out.println("Start ReplicaManager");

		startListening();

		Scanner keyboard = null;
		try {

			keyboard = new Scanner(System.in);
			boolean exit = false;
			while (!exit) {
				System.out.println("What's your choice:");
				System.out.println("0. start process.");
				System.out.println("1. kill process.");
				System.out.println("2. getAddress.");
				System.out.println("7. Quit.");

				int userChoice = keyboard.nextInt();
				switch (userChoice) {
				case 0:
					startProcess();
					break;
				case 1:
					killProcess();
					break;
				case 2:
					getAddress();
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

	static void startProcess() {

	}

	static void getAddress() {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("localhost");

			getServerAddress(address, ServerCommunication.SPVM);
			getServerAddress(address, ServerCommunication.SPB);
			getServerAddress(address, ServerCommunication.SPL);

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}

	private static void getServerAddress(InetAddress address, String serverName) {
		Integer port = ServerCommunication.REPLICA_MANAGER_PORT;
		GetServerAddressRequestPackage pkg = new GetServerAddressRequestPackage(
				address, port);

		UdpFunctionCall call = new UdpFunctionCall(pkg, new ReplicaManager());
		call.run();

		try {
			call.join();
			System.out.printf("\nFunction call result:%s\n", call.getResult()
					.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static void killProcess() {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("localhost");
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}

		Integer port = ServerCommunication
				.getListeningPortByName(ServerCommunication.SPVM);

		StopPackage pkg = new StopPackage(address, port);

		UdpFunctionCall call = new UdpFunctionCall(pkg, new ReplicaManager());
		call.run();

		try {
			call.join();
			System.out.printf("Function call result:%s\n", call.getResult()
					.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean handlingFunction(DatagramPacket pkg,
			UdpFunctionCall functionExecutor) {

		functionExecutor.setCanExit(true);
		return true;
	}

	ReplicaInfo getReplica1SMVP() {
		return replicas.get(0);
	}

	ReplicaInfo getReplica1SPL() {
		return replicas.get(1);
	}

	ReplicaInfo getReplica1SPB() {
		return replicas.get(2);
	}

	ReplicaInfo getReplica2SMVP() {
		return replicas.get(3);
	}

	ReplicaInfo getReplica2SPL() {
		return replicas.get(4);
	}

	ReplicaInfo getReplica2SPB() {
		return replicas.get(5);
	}

	ReplicaInfo getReplica3SMVP() {
		return replicas.get(6);
	}

	ReplicaInfo getReplica3SPL() {
		return replicas.get(7);
	}

	ReplicaInfo getReplica3SPB() {
		return replicas.get(8);
	}
}
