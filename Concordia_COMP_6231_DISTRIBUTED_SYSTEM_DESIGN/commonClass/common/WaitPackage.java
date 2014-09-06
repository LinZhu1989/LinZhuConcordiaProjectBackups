package common;

import java.net.DatagramPacket;
import java.net.InetAddress;


/**
 * this package is used to inform the FE that the current ID is not the
 * expected id, so the FE need to wait.
 * @author Jingang.Li
 *
 */
public class WaitPackage extends UdpPackage {

	public WaitPackage (InetAddress ipAddress, int port) {		
		this.remoteAddr = ipAddress;
		this.remotePort = port;
	}
	
	@Override
	public boolean parse(DatagramPacket pkg) {
		return false;
	}

	@Override
	public String composePackageString() {		
		return UdpPackage.CMD_WAIT;
	}

	@Override
	public String getDefaultPackageType() {
		// TODO Auto-generated method stub
		return UdpPackage.PKG_ACK;
	}

	@Override
	public String getDefaultCmdType() {
		return UdpPackage.CMD_WAIT;
	}

}
