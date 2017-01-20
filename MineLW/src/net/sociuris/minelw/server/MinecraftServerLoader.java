package net.sociuris.minelw.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import net.sociuris.crash.CrashReport;
import net.sociuris.logger.Logger;
import net.sociuris.util.NumberUtils;

public class MinecraftServerLoader {

	private final static Logger LOGGER = Logger.getLogger();

	public static void main(String[] args) {
		Thread.currentThread().setName("Main");

		LOGGER.info("Starting Minecraft server...");

		String workingDirPath = System.getProperty("user.dir", ".");
		String serverPropertiesFile = "server.properties";
		boolean acceptEula = false;

		// server properties arguments
		String ipAddress = "0.0.0.0";
		int port = -1;
		Boolean onlineMode = null;

		for (int i = 0; i < args.length; i++) {
			String arg = args[i].toLowerCase();
			if (arg.startsWith("--") && arg.length() > 2) {
				arg = arg.substring(2);
				String value = null;
				boolean hasValue = (args.length > i);
				if (hasValue)
					value = args[i++];

				switch (arg) {
				case "debug":
					LOGGER.setDebug(true);
					break;
				case "working-dir":
					if (hasValue)
						workingDirPath = value;
					break;
				case "server-properties":
					if (hasValue)
						serverPropertiesFile = value;
					break;
				case "accept-eula":
					if (hasValue && NumberUtils.isBoolean(value))
						acceptEula = Boolean.parseBoolean(value);
					break;

				// server properties arguments
				case "ip-address":
					if (hasValue)
						ipAddress = value;
					break;
				case "port":
					if (hasValue && NumberUtils.isInteger(value)) {
						int unverifiedPort = Integer.parseInt(value);
						if (unverifiedPort > 0 && unverifiedPort < 65535)
							port = unverifiedPort;
					}
					break;
				case "online-mode":
					if (hasValue && NumberUtils.isBoolean(value))
						onlineMode = Boolean.parseBoolean(value);
					break;
				default:
					break;
				}
			}
		}

		File workingDirectory = new File(workingDirPath);
		workingDirectory.mkdirs();
		
		if (!acceptEula) {
			if (!hasAcceptedEula()) {
				LOGGER.error("You need to agree to the Mojang's EULA in order to run the server.");
				System.exit(2);
			}
		}

		try {

			// load server properties file
			ServerProperties properties = new ServerProperties(new File(workingDirectory, serverPropertiesFile));
			properties.setPort(port);
			LOGGER.info("online mode: %b", onlineMode);
			// properties.setOnlineMode(onlineMode);

			// create and start Minecraft server
			new MinecraftServer(workingDirectory, properties);
		} catch (Throwable throwable) {
			CrashReport.makeCrashReport("Unable to start Minecraft server", throwable, 4);
		}
	}

	public static boolean hasAcceptedEula() {
		boolean accepted = false;

		File eulaFile = new File("eula.properties");
		if (!eulaFile.exists()) {
			try {
				LOGGER.info(
						"Do you accept the Mojang's EULA (https://account.mojang.com/documents/minecraft_eula)? (y/n)");
				if (((char) System.in.read()) == 'y') {
					accepted = true;
					eulaFile.createNewFile();
					FileOutputStream fileOutputStream = new FileOutputStream(eulaFile);
					fileOutputStream
							.write("You have accepted to the Mojang's EULA: https://account.mojang.com/documents/minecraft_eula"
									.getBytes(StandardCharsets.UTF_8));
					fileOutputStream.close();
				}
			} catch (IOException e) {
				LOGGER.warn("You have accepted the Mojang's EULA but the file cannot be saved: %s",
						e.getLocalizedMessage());
			}
		} else
			accepted = true;

		return accepted;
	}

}