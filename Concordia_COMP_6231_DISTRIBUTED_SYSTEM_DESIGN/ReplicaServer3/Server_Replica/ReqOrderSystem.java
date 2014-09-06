package Server_Replica;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ReqOrderSystem {

	private static int curReqID = 1;
	private static int MaxReqID = 0;
	private static ArrayList<DatagramPacket> reqList = new ArrayList<DatagramPacket>();
	private static Queue<DatagramPacket> reqQueue = new LinkedList<DatagramPacket>();

	public ReqOrderSystem() {

	}

	synchronized public void updateCurMsgID() {
		curReqID++;
	}
	
	public boolean checkOrder(DatagramPacket requestPacket) {

		PackageAnalyzer analyzer = new PackageAnalyzer(requestPacket);
		int theReqID = analyzer.getRequestID();
		if (theReqID == curReqID) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkOrder(int requestID) {

		if (requestID == curReqID) {
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * Functions below are never used till now
	 * 
	 */
	public int getCurMsgID() {
		return curReqID;
	}

	public int getMaxMsgID() {
		return MaxReqID;
	}

	public ArrayList<DatagramPacket> getReqList() {
		return reqList;
	}

	public Queue<DatagramPacket> getReqQueue() {
		return reqQueue;
	}

	public boolean clearReqList() {
		reqList.clear();
		return (reqList.size() == 0 ? true : false);
	}

	public boolean clearReqQueue() {
		reqQueue.clear();
		return (reqQueue.size() == 0 ? true : false);
	}


	

	public boolean insertReq(DatagramPacket requestPacket){
		return reqList.add(requestPacket);
	}
	
	public boolean sortReqList(){
		
		
		ArrayList<DatagramPacket> tempList = this.getReqList();
		if(tempList.size()==0){
			return false;
		}
		this.clearReqList();

		while(reqList.size()<tempList.size()){
			int reqNum = curReqID;
			for(int i=0;i<tempList.size();i++){
				PackageAnalyzer analyzer = new PackageAnalyzer(
						tempList.get(i));
				int theReqID = analyzer.getRequestID();
				if (theReqID == reqNum) {
					reqList.add(tempList.get(i));
					reqNum++;
					continue;
				}
			}	
		}
		tempList.clear();
		return true;
		
	}

	public boolean deliveryQueue() {

		if ((MaxReqID - curReqID + 1) != reqList.size()) {
			return false;
		} else {

			int reqNum = curReqID;
			while (reqQueue.size() != (MaxReqID - curReqID + 1)) {
				for (int i = 0; i < reqList.size(); i++) {
					PackageAnalyzer analyzer = new PackageAnalyzer(
							reqList.get(i));
					int theReqID = analyzer.getRequestID();
					if (theReqID == reqNum) {
						reqQueue.add(reqList.get(i));
					}
				}
				reqNum++;
			}
			reqList.clear();
			return true;
		}

	}


}
