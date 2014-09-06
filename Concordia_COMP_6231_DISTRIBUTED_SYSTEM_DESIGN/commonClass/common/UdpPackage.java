package common;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * this is the class to build/parse the command Package. REQ/ACK
 * 
 *  This class compose the shared header of the UDP package
 * 
 * 	[tpkgType,cmdType,seqNumber]
 * 
 * @author Jingang.Li
 * 
 */
public abstract class UdpPackage {
	public static final int pkgSize = 8192;
	
	public static final String UNKNOWN = "Undef";

	public static final String PKG_REQ = "Req";
	public static final String PKG_ACK = "Ack";
	
	public static final String CMD_STOP = "stop";	
	public static final String CMD_GETADDR = "getAddr";
	
	
	public static final String CMD_CREATECRECORD = "createCRecord";
	public static final String CMD_CREATEMRECORD = "createMRecord";
	public static final String CMD_EDITRECORD = "editCRecord";
	public static final String CMD_GETRECORDCOUNTS = "getRecordCounts";
	public static final String CMD_TRANSFERRECORD = "transferRecord";
	
	public static final String CMD_ERROR = "Error";
	public static final String CMD_CRASH = "Crash";	
	
	public static final String CMD_WAIT = "wait";	
	
	//this command is used to transfer the data between the server locally.
	public static final String CMD_LOCALTRNAS = "trasnferLocal";
	
	public String pkgType;
	public String seqNumber;
	public String cmdType;

	public InetAddress remoteAddr;
	public int remotePort;

	public UdpPackage() {
		this.pkgType = getDefaultPackageType();
		this.seqNumber = UNKNOWN;
		this.cmdType = 	getDefaultCmdType();
		remoteAddr = null;
		remotePort = 0;
	}
	
	public UdpPackage (InetAddress ipAddress, int port) {
		this();
		this.remoteAddr = ipAddress;
		this.remotePort = port;
	}

	public abstract boolean parse(DatagramPacket pkg);
	public abstract String composePackageString();	
	
	public abstract String getDefaultPackageType();
	public abstract String getDefaultCmdType();
	
	public DatagramPacket compose() {
		
		byte[] buffer = new byte[UdpPackage.pkgSize];
		byte [] data = composePackageString().getBytes();
		
		for (int i = 0; i < data.length; i++) {
			buffer[i] = data[i];
		}
		
		DatagramPacket result = new DatagramPacket(buffer, UdpPackage.pkgSize);
		result.setAddress(remoteAddr);
		result.setPort(remotePort);
		return result;
	}	

	public static String getPkgType(DatagramPacket pkg) {
		String content = new String (pkg.getData());
		String[] values = content.split(",");
		return values[0];
	}
 
	public static String getSeqNumber(DatagramPacket pkg) {
		String content = new String (pkg.getData());
		String[] values = content.split(",");
		return values[values.length - 1];
	}

	public static String getCmdType(DatagramPacket pkg) {
		String content = new String (pkg.getData());
		String[] values = content.split(",");
		return values[1];
	}
	
	public boolean parseHead(DatagramPacket pkg) {
		String content = new String (pkg.getData());
		String[] values = content.split(",");
		this.pkgType = values[0].trim();
		this.cmdType = values[1].trim();
		this.seqNumber = values[values.length - 1].trim();		
		return true;
	}
	
	public String composeHead() {
		return String.format("%s,%s,%s", this.pkgType,this.cmdType,this.seqNumber);
	}
}
