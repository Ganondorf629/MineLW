package net.sociuris.minelw.server;

import java.io.File;

import net.sociuris.configuration.ConfigurationFile;
import net.sociuris.configuration.ConfigurationSection;
import net.sociuris.configuration.ConfigurationValue;
import net.sociuris.configuration.exception.ConfigurationParseException;

public class ServerProperties {

	// private final File serverPropertiesFile;
	private final ConfigurationFile configurationFile;
	private final ConfigurationSection serverSection;
	private final ConfigurationSection networkSection;

	protected ServerProperties(File serverPropertiesFile) throws ConfigurationParseException {
		// this.serverPropertiesFile = serverPropertiesFile;
		this.configurationFile = new ConfigurationFile(serverPropertiesFile);
		this.serverSection = configurationFile.getSection("server");
		this.networkSection = configurationFile.getSection("network");
	}

	public void setName(String name) {
		this.serverSection.addValue("name", name);
	}

	public String getName() {
		return serverSection.getValueAsString("name", "MineLW server");
	}

	public void setIpAddress(String ipAddress) {
		this.networkSection.addValue("ipAddress", ipAddress);
	}

	public String getIpAddress() {
		return networkSection.getValueAsString("ipAddress");
	}

	public void setPort(int port) {
		this.networkSection.addValue("port", port);
	}

	public int getPort() {
		return networkSection.getValue("port", new ConfigurationValue(25565)).getAsInteger();
	}

}