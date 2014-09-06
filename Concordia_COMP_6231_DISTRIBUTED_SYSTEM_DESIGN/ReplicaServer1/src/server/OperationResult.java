package server;
/**
 * use this class to pass the operation result back
 * declare all the member variable as public just to 
 * simplify the process, and save some time.
 * @author Jingang.Li
 *
 */
public class OperationResult {
	
	/**
	 * add this constructor to fulfill the WebService's 
	 * requirement
	 */
	
	public String toString() {
		return result ? "True" + msg : "False" + msg;
	}
	
	public OperationResult() {
		this.result = false;
		this.msg = new String();
	}
	
	public OperationResult (boolean result, String msg) {
		this.result = result;
		this.msg = msg;
	}
	//boolean to indicate if the operation of the web function call is success or not.
	public boolean result;
	//the message returned by the remote server.
	public String msg;
}
