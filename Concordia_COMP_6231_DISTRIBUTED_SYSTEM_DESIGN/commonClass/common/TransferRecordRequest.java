package common;

import java.net.DatagramPacket;

public class TransferRecordRequest extends UdpPackage {

	private String recordString;
	
	public TransferRecordRequest(String recordString) {
				this.setRecordString(recordString);
	}
	
	@Override
	public boolean parse(DatagramPacket pkg) {
		if (getCmdType(pkg).compareToIgnoreCase(this.getDefaultCmdType()) != 0)
			return false;
				
		super.parseHead(pkg);
		
		String content = new String (pkg.getData());
		String[] values = content.split(",");
		this.setRecordString(values[3].trim());

		this.remoteAddr = pkg.getAddress();
		this.remotePort = pkg.getPort();
		
		return true;
	}

	@Override
	public String composePackageString() {
		String result;
		result = String.format("%s,%s",this.composeHead(),this.getRecordString());		
		return result;
	}

	@Override
	public String getDefaultPackageType() {
		return UdpPackage.PKG_REQ;
	}

	@Override
	public String getDefaultCmdType() {
		return UdpPackage.CMD_LOCALTRNAS;
	}

	public String getRecordString() {
		return recordString;
	}

	public void setRecordString(String recordString) {
		this.recordString = recordString;
	}
}
