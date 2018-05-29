package com.basic.core.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Author: wangzw
 * @Description: 得到服务器IP
 * @Version: 1.0
 * @Date: 2017/10/9 20:27
 */
public class GetIpTools {

	public static String getServerIp() {
		String SERVER_IP = null;
		try {
			Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			//是否找到外网IP
			boolean finded = false;
			while (netInterfaces.hasMoreElements() && !finded) {
				NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
				Enumeration address = ni.getInetAddresses();
				while (address.hasMoreElements()) {
					ip = (InetAddress) address.nextElement();
					if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
						SERVER_IP = ip.getHostAddress();
						finded = true;
						break;
					} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
						SERVER_IP = ip.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return SERVER_IP;
	}

	public static void main(String[] args) {
		System.out.println(GetIpTools.getServerIp());
	}
}
