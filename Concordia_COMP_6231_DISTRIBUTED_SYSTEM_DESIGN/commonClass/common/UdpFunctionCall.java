package common;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class UdpFunctionCall extends Thread {

	final static int MAX_SOCKET_RETRY = 3;	
	ExecResult result;
	FunctionCallHandler handler;
	UdpPackage cmdPkg;
	
	//true means the function call can exit
	private boolean canExit;

	public enum ExecResult {
		succeed,
		fail,
		time_out,
		unknown
	}	

	public UdpFunctionCall(UdpPackage cmdPkg, FunctionCallHandler handler) {
		this.cmdPkg = cmdPkg;
		this.result = ExecResult.unknown;
		this.handler = handler;
		this.setCanExit(false);
	}

	public ExecResult getResult() {
		return result;
	}

	@Override
	public void run() {
		int timeoutCount = MAX_SOCKET_RETRY;

		DatagramSocket socket = null;
		try {
			socket = new DatagramSocket();						

			socket.setSoTimeout(4 * ServerCommunication.WAITING_TIME);
			while (true && timeoutCount >= 0) {
				try {
					socket.send(cmdPkg.compose());
					DatagramPacket pkg = new DatagramPacket(
							new byte[UdpPackage.pkgSize],
							UdpPackage.pkgSize);
						socket.receive(pkg);
						
						if (this.handler.handlingFunction(pkg, this) == true) {
							if (this.getCanExit() == true) {
								this.result = ExecResult.succeed;
								System.out.println(new String(pkg.getData()));
								System.out.println("\nIn UdpFunctionCall: close socket:" + socket.getLocalPort());
								socket.close();
								return;
							}
						} 						
						
				} catch (SocketTimeoutException e) {
					timeoutCount--;
				}
				this.result = ExecResult.time_out;
				return;
			}
		} catch (SocketException e) {
			this.result = ExecResult.fail;
		} catch (IOException e) {
			this.result = ExecResult.fail;
		} finally {
			socket.close();
		}
	}

	public boolean getCanExit() {
		return canExit;
	}

	public void setCanExit(boolean canExit) {
		this.canExit = canExit;
	}
}

