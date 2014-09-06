package common;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * this is the class represents the stop package for stop the 
 * server.
 * 
 * @author Jingang.Li
 * 
 */
public class StopPackage extends UdpPackage {
		
	public StopPackage (InetAddress ipAddress, int port) {
		super(ipAddress,port);
	}
	
	public String getDefaultCmdType() {
		return UdpPackage.CMD_STOP;
	}
	
	public boolean parse(DatagramPacket pkg) {
		
		if (getCmdType(pkg) != this.getDefaultCmdType())
			return false;		
		
		super.parseHead(pkg);

		this.remoteAddr = pkg.getAddress();
		this.remotePort = pkg.getPort();
		
		return true;
	}

	@Override
	public String composePackageString() {		
		String result = super.composeHead();		
		return result;
	}

	@Override
	public String getDefaultPackageType() {		
		return UdpPackage.PKG_REQ;
	}
}
