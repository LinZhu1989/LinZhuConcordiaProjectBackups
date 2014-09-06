package common;

import java.net.DatagramPacket;

public interface FunctionCallHandler  {
	boolean handlingFunction(DatagramPacket pkg, UdpFunctionCall functionExecutor);
}

