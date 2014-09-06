package common;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * 
 * @author Yulong Song
 * @param DatagramPacket requestPacket
 * analyze any UDP package just put the package in!
 * PackageAnalyzer PA = new PackageAnalyzer(requestPacket)
 * PA.getLastName();
 * get parameters that you need
 */
public class PackageAnalyzer{
		private String lastName;
		private String firstName;
		private String badgeID;
		private String description;
		private String status;
		private String address;
		private String lastDate;
		private String lastLocation;
		private String recordID;
		private String newStatus;
		//private String errorHostIP;  //maybe not used
		private int errorHostPort;
		private int crashHostPort;
		private String remoteServerName;
		private int requestID;
		private int hostPort;
		private InetAddress hostAddress;
		private String command;
		public PackageAnalyzer(){
			
		}
		public PackageAnalyzer(DatagramPacket requestPacket){
			String data = new String(requestPacket.getData()).trim();
			
			System.out.println(data);
			
			String [] request = data.split(",");
			if(request[0].equalsIgnoreCase("createCRecord")){
				this.command = request[0];
				this.firstName = request[1];
				this.lastName = request[2];
				this.description =request[3];
				this.status = request[4];
				this.badgeID = request[5];
				this.requestID = Integer.parseInt(request[6]);
				this.hostAddress = requestPacket.getAddress();
				this.hostPort = requestPacket.getPort();
				
			}
			else if(request[0].equalsIgnoreCase("createMRecord")){
				this.command = request[0];
				this.firstName = request[1];
				this.lastName = request[2];
				this.address =request[3];
				this.lastDate = request[4];
				this.lastLocation = request[5];
				this.status = request[6];
				this.badgeID = request[7];
				this.requestID = Integer.parseInt(request[8]);
				this.hostAddress = requestPacket.getAddress();
				this.hostPort = requestPacket.getPort();
				
			}
			else if(request[0].equalsIgnoreCase("editCRecord")){
				this.command = request[0];
				this.lastName = request[1];
				this.recordID = request[2];
				this.newStatus =request[3];
				this.badgeID = request[4];
				this.requestID = Integer.parseInt(request[5]);
				this.hostAddress = requestPacket.getAddress();
				this.hostPort = requestPacket.getPort();
			}
			else if(request[0].equalsIgnoreCase("getRecordCounts")){
				this.command = request[0];
				this.badgeID = request[1];
				this.requestID = Integer.parseInt(request[2]);
				this.hostAddress = requestPacket.getAddress();
				this.hostPort = requestPacket.getPort();
			}
			else if(request[0].equalsIgnoreCase("transferRecord")){
				this.command = request[0];
				this.badgeID = request[1];
				 this.recordID = request[2];
				this.remoteServerName =request[3];
				this.requestID = Integer.parseInt(request[4]);
				this.hostAddress = requestPacket.getAddress();
				this.hostPort = requestPacket.getPort();
				
			}
			else if(request[0].equalsIgnoreCase("Error")){
				this.command = request[0];
				this.errorHostPort = Integer.parseInt(request[1]);
				this.requestID =Integer.parseInt(request[2]);
				this.hostAddress = requestPacket.getAddress();
				this.hostPort = requestPacket.getPort();
			}
			else if(request[0].equalsIgnoreCase("Crash")){
				this.command = request[0];
				this.crashHostPort = Integer.parseInt(request[1]);
				this.requestID =Integer.parseInt(request[2]);
				this.hostAddress = requestPacket.getAddress();
				this.hostPort = requestPacket.getPort();
			}
			
		
		}
		public String getFirstName(){
			return this.firstName;
		}
		public String getLastName(){
			return this.lastName;
		}
		public String getBadgeID(){
			return this.badgeID;
		}
		public String getRecordID(){
			return this.recordID;
		}
		public String getStatus(){
			return this.status;
		}
		public String getNewStatus(){
			return this.newStatus;
		}
		public String getDescription(){
			return this.description;
		}
		public int getRequestID(){
			return this.requestID;
		}
		public String getLastDate(){
			return this.lastDate;
		}
		public String getLastLocation(){
			return this.lastLocation;
		}
		public String getAddress(){
			return this.address;
		}
		public int getErrorHostPort(){
			return this.errorHostPort;
		}
		public int getCrashHostPort(){
			return this.crashHostPort;
		}
		public String getRemoteServerName(){
			return this.remoteServerName;
		}
		public InetAddress getHostInetAddress(){
			return this.hostAddress;
		}
		public int getHostPort(){
			return this.hostPort;
		}
		public String getCommand(){
			return this.command;
		}
}