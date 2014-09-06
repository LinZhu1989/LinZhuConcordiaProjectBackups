package common;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * this class is responsible to compose/parse the
 * GetServerAddressRequsetPackage
 * 
 * Request Pkg: each time the FE request the information
 * of the replicas, the RM will return all the replicas'
 * address. so we do not need to identify the replica's name.
 * [pkgType,cmdType,seqNumber]
 * 
 * @author Jingang.Li
 *
 */
public class GetServerAddressRequestPackage extends UdpPackage {
	
	public GetServerAddressRequestPackage (InetAddress ipAddress, int port) {
		super(ipAddress,port);		
	}	
	
	@Override
	public boolean parse(DatagramPacket pkg) {
		if (getCmdType(pkg).compareToIgnoreCase(this.getDefaultCmdType()) != 0)
			return false;
				
		super.parseHead(pkg);
		
		String content = new String (pkg.getData());
		String[] values = content.split(",");

		this.remoteAddr = pkg.getAddress();
		this.remotePort = pkg.getPort();
		
		return true;
	}

	@Override
	public String composePackageString() {
		String result;
		result = String.format("%s",this.composeHead());		
		return result;
	}

	@Override
	public String getDefaultCmdType() {		
		return UdpPackage.CMD_GETADDR;
	}

	@Override
	public String getDefaultPackageType() {
		return UdpPackage.PKG_REQ;
	}

}
