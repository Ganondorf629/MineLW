package net.sociuris.minelw.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import net.sociuris.crash.CrashReport;
import net.sociuris.logger.Logger;
import net.sociuris.util.NumberUtils;

public class MinecraftServerLoader {

	private final static Logger LOGGER = Logger.getLogger();
	private static File workingDirectory = new File(System.getProperty("user.dir", "."));

	public static void main(String[] args) {
		Thread.currentThread().setName("Main");

		LOGGER.info("Starting Minecraft server...");

		String serverPropertiesFile = "server.properties";
		boolean acceptEula = false;

		// server properties arguments
		String ipAddress = null;
		int port = -1;
		//Boolean onlineMode = null;

		for (int i = 0; i < args.length; i++) {
			String arg = args[i].toLowerCase();
			if (arg.startsWith("--") && arg.length() > 2) {
				arg = arg.substring(2);
				String value = null;
				boolean hasValue = (args.length > i++);
				if (hasValue) {
					value = args[i];
				}

				switch (arg) {
				case "debug":
					LOGGER.setDebug(true);
					break;
				case "working-dir":
					if (hasValue)
						workingDirectory = new File(value);
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
				/*case "online-mode":
					if (hasValue && NumberUtils.isBoolean(value))
						onlineMode = Boolean.parseBoolean(value);
					break;*/
				default:
					break;
				}
			}
		}

		workingDirectory.mkdirs();

		if (!acceptEula && !hasAcceptedEula()) {
			LOGGER.error("You need to agree to the Mojang's EULA in order to run the server.");
			System.exit(2);
		}

		try {
			// load server properties file
			ServerProperties properties = new ServerProperties(new File(workingDirectory, serverPropertiesFile));

			if (ipAddress != null)
				properties.setIpAddress(ipAddress);
			if (port != -1)
				properties.setPort(port);

			// create and start Minecraft server
			new MinecraftServer(workingDirectory, properties);
		} catch (Throwable throwable) {
			CrashReport.makeCrashReport("Unable to start Minecraft server", throwable, 4);
		}
	}

	public static boolean hasAcceptedEula() {
		boolean accepted = false;

		File eulaFile = new File(workingDirectory, "eula.properties");
		Properties eulaProperties = new Properties();

		if (!eulaFile.exists()) {
			try {
				LOGGER.info(
						"Do you accept the Mojang's EULA (https://account.mojang.com/documents/minecraft_eula)? (y/n)");
				accepted = (((char) System.in.read()) == 'y');
			} catch (IOException e) {
				LOGGER.error("An error occurred while reading console input: %s", e.getLocalizedMessage());
			}

			try {
				eulaFile.createNewFile();
				FileOutputStream outStream = new FileOutputStream(eulaFile);
				eulaProperties.setProperty("eula", String.valueOf(accepted));
				eulaProperties.store(outStream,
						"If you set eula to true, you agree to the Mojang's EULA (https://account.mojang.com/documents/minecraft_eula)");
				outStream.close();
			} catch (IOException e) {
				LOGGER.error("Unable to save EULA file: %s", e.getLocalizedMessage());
				LOGGER.info(
						"You can create a file called \"eula.properties\" and put \"eula=true\" inside it to fix the error.");
			}
		} else {
			try {
				FileInputStream inStream = new FileInputStream(eulaFile);
				eulaProperties.load(inStream);
				accepted = Boolean.parseBoolean(eulaProperties.getProperty("eula"));
			} catch (IOException e) {
				LOGGER.error("Unable to load EULA file: %s", e.getLocalizedMessage());
			}
		}

		return accepted;
	}

}