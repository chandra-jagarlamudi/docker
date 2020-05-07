package com.ragas.rest.webservices.restfulwebservices.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;

@Service
public class InstanceInformationService {

	private static final String HOST_NAME = "HOSTNAME";

	private static final String DEFAULT_ENV_INSTANCE_GUID = "Local";

	// @Value(${ENVIRONMENT_VARIABLE_NAME:DEFAULT_VALUE})
	@Value("${" + HOST_NAME + ":" + DEFAULT_ENV_INSTANCE_GUID + "}")
	private String hostName;

	@Autowired
	BuildProperties buildProperties;

	public String retrieveInstanceInfo() {
		return "Spring Boot application " + buildProperties.getName()+":"+buildProperties.getVersion() + " running on " + hostName;
	}

	public String getSystemInfo(){
		StringBuilder builder = new StringBuilder();

		long maxMemory = Runtime.getRuntime().maxMemory();

		builder.append("<br/><br/>Available processors (cores): " + Runtime.getRuntime().availableProcessors())

				.append("<br/><br/>Free memory (bytes): " + Runtime.getRuntime().freeMemory())

				.append("<br/>Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory))

				.append("<br/>Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());

		File[] roots = File.listRoots();

		for (File root : roots) {
			builder.append("<br/><br/>File system root: " + root.getAbsolutePath())
					.append("<br/>Total space (bytes): " + root.getTotalSpace())
					.append("<br/>Free space (bytes): " + root.getFreeSpace())
					.append("<br/>Usable space (bytes): " + root.getUsableSpace());
		}

		builder.append("<br/><br/>Network Details:");
		Enumeration<NetworkInterface> nets;
		try {
			nets = NetworkInterface.getNetworkInterfaces();
			for (NetworkInterface netint : Collections.list(nets)) {
				Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
				for (InetAddress inetAddress : Collections.list(inetAddresses)) {
					builder.append("<br/>Display name: "+ netint.getDisplayName()).append("\t\tInetAddress: "+ inetAddress);
				}
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}

        return "<div><h2>"+retrieveInstanceInfo()+"</h2>" + builder.toString() + "</div>";
	}
}