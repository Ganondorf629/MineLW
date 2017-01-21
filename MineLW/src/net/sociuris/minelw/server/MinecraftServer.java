package net.sociuris.minelw.server;

import java.io.File;

import net.sociuris.logger.Logger;
import net.sociuris.minelw.block.BlockManager;
import net.sociuris.minelw.command.CommandManager;
import net.sociuris.minelw.command.CommandSender;
import net.sociuris.minelw.text.TextComponentArray;
import net.sociuris.minelw.translation.MinecraftTranslation;
import net.sociuris.util.StringUtils;

public class MinecraftServer implements CommandSender {

	private static MinecraftServer instance;

	/**
	 * Gets the server instance
	 * 
	 * @return the server instance
	 */
	public static MinecraftServer getInstance() {
		return MinecraftServer.instance;
	}

	private final Logger logger = Logger.getLogger();
	private final long startedTime = System.currentTimeMillis();

	private final Version version = new Version("1.11.2", new int[] { 0 });
	private final File workingDirectory;
	private final ServerProperties properties;

	private final BlockManager blockRegistry = new BlockManager();
	private final CommandManager commandManager = new CommandManager(this);

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

	/**
	 * Gets the server's "username"
	 * 
	 * @return the version name
	 */
	@Override
	public String getUsername() {
		return version.getVersionName();
	}

	/**
	 * Checks if the server has given permission
	 * 
	 * @param permissionKey
	 * @return {@code true} everytime
	 */
	@Override
	public boolean hasPermission(String permissionKey) {
		return true;
	}

	@Override
	public void sendMessage(TextComponentArray textComponentArray) {
		logger.info(textComponentArray.toPrintableMessage());
	}

	@Override
	public void sendMessage(String message) {
		logger.info(message);
	}

	@Override
	public void translateAndSendMessage(String key, String... args) {
		throw new UnsupportedOperationException("try to translate this: " + key);
	}

	@Override
	public void sendMinecraftMessage(MinecraftTranslation minecraftTranslation, String... args) {
		throw new UnsupportedOperationException("try to translate this: " + minecraftTranslation.getTranslationKey());
	}

	@Override
	public void sendError(TextComponentArray error) {
		logger.error(error.toPrintableMessage());
	}

	@Override
	public void sendSuccess(TextComponentArray success) {
		logger.success(success.toPrintableMessage());
	}

	@Override
	public void sendWarning(TextComponentArray warning) {
		logger.warn(warning.toPrintableMessage());
	}

}