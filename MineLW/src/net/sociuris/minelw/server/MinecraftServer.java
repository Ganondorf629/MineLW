package net.sociuris.minelw.server;

import java.io.File;

import net.sociuris.logger.Logger;
import net.sociuris.util.StringUtils;

public class MinecraftServer {

	private static MinecraftServer instance;

	/**
	 * Obtient l'instance de {@link MinecraftServer}
	 * 
	 * @return the {@link MinecraftServer} instance
	 */
	public static MinecraftServer getInstance() {
		return MinecraftServer.instance;
	}

	private final Logger logger = Logger.getLogger();
	private final long startedTime = System.currentTimeMillis();

	private final Version version = new Version("1.11.2", new int[] { 0 });
	private final File workingDirectory;
	private final ServerProperties properties;

	private boolean isStarted = true;

	protected MinecraftServer(File workingDir, ServerProperties properties) {
		if (MinecraftServer.instance == null) {
			logger.debug("Server version: %s (Available protocols: %s)", version.getVersionName(),
					StringUtils.listToString(version.getProtocolVersions()));

			this.workingDirectory = workingDir;
			this.properties = properties;

			logger.info("MineLW started in %.3fs on %s", ((float) (System.currentTimeMillis() - startedTime) / 1000),
					"0.0.0.0:25565");
			MinecraftServer.instance = this;
		} else
			throw new RuntimeException("The Minecraft server is already started");
	}

	public Version getVersion() {
		return version;
	}
	
	public File getWorkingDirectory() {
		return workingDirectory;
	}

	public ServerProperties getProperties() {
		return properties;
	}

	public boolean isStarted() {
		return isStarted;
	}

}