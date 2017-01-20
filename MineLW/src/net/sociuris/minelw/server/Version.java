package net.sociuris.minelw.server;

import java.util.ArrayList;
import java.util.List;

public class Version {

	private String versionName;
	private final List<Integer> protocolList = new ArrayList<Integer>();

	public Version(String versionName, int[] protocolList) {
		this.versionName = versionName;
		for (int protocol : protocolList)
			this.protocolList.add(protocol);
	}

	/**
	 * Gets the server version name
	 * 
	 * @return the version name
	 */
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String name) {
		this.versionName = name;
	}

	/**
	 * Checks if the player is compatible with the server<br>
	 * This is checked using the protocol version
	 * 
	 * @see <a href="http://wiki.vg/Protocol_version_numbers">Protocol version
	 *      numbers - wiki.vg</a>
	 * 
	 * @param playerProtocol
	 *            the player protocol version
	 * @return {@code true} if the player is compatible, {@code false} otherwise
	 */
	public boolean isCompatible(int playerProtocol) {
		return protocolList.contains(playerProtocol);
	}

	/**
	 * Adds a protocol version
	 * 
	 * @param protocolVersion
	 */
	public void addProtocolVersion(int protocolVersion) {
		this.protocolList.add(protocolVersion);
	}

	/**
	 * Sets the protocol versions
	 * 
	 * @param protocolVersions
	 */
	public void setProtocolVersions(int... protocolVersions) {
		this.protocolList.clear();
		for (int protocolVersion : protocolVersions)
			this.protocolList.add(protocolVersion);
	}

	/**
	 * Removes a protocol version
	 * 
	 * @param protocolVersion
	 *            The protocol version that needs to be removed
	 */
	public void removeProtocolVersion(int protocolVersion) {
		this.protocolList.remove(protocolVersion);
	}

	/**
	 * Clears the protocol versions
	 */
	public void clearProtocolVersion() {
		this.protocolList.clear();
	}

	/**
	 * Gets available protocol versions
	 * 
	 * @return All available protocol versions
	 */
	public List<Integer> getProtocolVersions() {
		return protocolList;
	}

}