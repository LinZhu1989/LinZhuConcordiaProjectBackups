package common;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * this class is responsible to compose/parse the
 * GetServerAddressReplyPackage
 * 
 * Reply Pkg: each time the RM get getAddress request, it returns
 * all the replicas' address infor in the format below:
 * [pkgType,cmdType,seqNumber,
 * serverPort11,serverPort12,severPort13,serverName1,//1st replica's infor
 * serverPort21,serverPort22,severPort23,serverName2,//2nd replica's infor
 * serverPort31,serverPort32,severPort33,serverName3,//3rd replica's infor
 *  
 * @author Jingang.Li
 *
 */
public class GetServerAddressReplyPackage extends UdpPackage {

	/**
	 * The IP address of the replica:	  
	 * use public just for convenient.
	 */
	public String replicaIP1;
	/**
	 * The port of the replica:	  
	 * use public just for convenient.
	 */
	public Integer replicaPort11;
	public Integer replicaPort12;
	public Integer replicaPort13;
	
	public String replicaIP2;
	public Integer replicaPort21;
	public Integer replicaPort22;
	public Integer replicaPort23;
	
	public String replicaIP3;
	public Integer replicaPort31;
	public Integer replicaPort32;
	public Integer replicaPort33;	

	public GetServerAddressReplyPackage (InetAddress ipAddress, int port) {
		super(ipAddress,port);		
	}	
	
	@Override
	public boolean parse(DatagramPacket pkg) {
		
//		if (getCmdType(pkg).compareToIgnoreCase(this.getDefaultCmdType()) != 0)
//			return false;
		
		String[] datas = new String(pkg.getData()).trim().split(",");
		
		int i = 0;
		this.replicaPort11 = Integer.parseInt(datas[i++]);
		this.replicaPort12 = Integer.parseInt(datas[i++]);
		this.replicaPort13 = Integer.parseInt(datas[i++]);
		this.replicaIP1 =   datas[i++];
		
		this.replicaPort21 = Integer.parseInt(datas[i++]);
		this.replicaPort22 = Integer.parseInt(datas[i++]);
		this.replicaPort23 = Integer.parseInt(datas[i++]);
		this.replicaIP2 =   datas[i++];

		this.replicaPort31 = Integer.parseInt(datas[i++]);
		this.replicaPort32 = Integer.parseInt(datas[i++]);
		this.replicaPort33 = Integer.parseInt(datas[i++]);
		this.replicaIP3 =   datas[i++];		
		
		return true;
	}

	@Override
	public String composePackageString() {
		String result = super.composeHead();
		result = String.format("%d,%d,%d,%s,%d,%d,%d,%s,%d,%d,%d,%s",				
				this.replicaPort11,
				this.replicaPort12,
				this.replicaPort13,
				this.replicaIP1,
				this.replicaPort21,
				this.replicaPort22,
				this.replicaPort23,
				this.replicaIP2,
				this.replicaPort31,
				this.replicaPort32,
				this.replicaPort33,
				this.replicaIP3);
		return result;
	}

	@Override
	public String getDefaultPackageType() {		
		return UdpPackage.PKG_ACK;
	}

	@Override
	public String getDefaultCmdType() {
		return UdpPackage.CMD_GETADDR;
	}
}
