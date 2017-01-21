package net.sociuris.minelw.crash;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sociuris.logger.Logger;
import net.sociuris.minelw.server.MinecraftServer;

public class CrashReport {

	private final Logger logger = Logger.getLogger();
	private final MinecraftServer minecraftServer = MinecraftServer.getInstance();
	private final File crashReportDir = new File(minecraftServer.getWorkingDirectory(), "crash-reports");

	private final String description;
	private final Throwable throwable;
	private final File file;

	private CrashReport(String description, Throwable throwable) {
		this.description = description;
		this.throwable = throwable;

		crashReportDir.mkdirs();
		int crashReportIndex = 0;
		File crashReportFile;
		do {
			crashReportFile = new File(crashReportDir, "crash-report-" + String.valueOf(crashReportIndex) + ".log");
			crashReportIndex++;
		} while (crashReportFile.exists());
		this.file = crashReportFile;
	}

	public File save() {
		final StringBuilder builder = new StringBuilder();
		final String lineSeparator = System.lineSeparator();
		try {
			final FileWriter fileWriter = new FileWriter(file);

			builder.append("---- Crash Report ----").append(lineSeparator).append(lineSeparator);

			builder.append("Time: ").append((new SimpleDateFormat()).format(new Date())).append(lineSeparator);
			builder.append("Description: ").append(description).append(lineSeparator);

			builder.append(lineSeparator);
			builder.append(throwable.getClass().getName()).append(": ").append(throwable.getLocalizedMessage())
					.append(lineSeparator);
			for (StackTraceElement stackTraceElement : throwable.getStackTrace())
				builder.append('\t').append("at ").append(stackTraceElement.toString()).append(lineSeparator);

			builder.append(lineSeparator);
			builder.append("---- Server informations ----").append(lineSeparator);
			if (minecraftServer != null) {

			} else
				builder.append("The server didn't start :/");
			builder.append(lineSeparator);

			builder.append(lineSeparator);
			builder.append("---- System details ----").append(lineSeparator);
			builder.append("Operating system: ").append(System.getProperty("os.name")).append(" (")
					.append(System.getProperty("os.arch")).append(") version ")
					.append(System.getProperty("os.version"));
			builder.append(lineSeparator);
			builder.append("Java version: ").append(System.getProperty("java.version")).append(", ")
					.append(System.getProperty("java.vendor"));
			builder.append(lineSeparator);
			builder.append("JVM version: ").append(System.getProperty("java.vm.name")).append(" (")
					.append(System.getProperty("java.vm.info")).append("), ")
					.append(System.getProperty("java.vm.vendor"));

			fileWriter.write(builder.toString());
			fileWriter.close();

			logger.info("An critical error occurred, the crash report has been saved to: %s", file.getAbsolutePath());
			return file;
		} catch (IOException e) {
			logger.error("Crash report cannot be saved: %1$s%2$s%2$s%3$s", e.getLocalizedMessage(), lineSeparator,
					builder.toString());
			return null;
		}
	}

	public String getDescription() {
		return description;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public File getFile() {
		return file;
	}

	public static void makeCrashReport(String description, Throwable throwable, int exitCode) {
		CrashReport crashReport;
		if (throwable instanceof ReportedException)
			crashReport = ((ReportedException) throwable).getCrashReport();
		else
			crashReport = new CrashReport(description, throwable);

		crashReport.save();

		// try to open crash report in default text editor
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Action.EDIT)) {
				try {
					desktop.edit(crashReport.file);
				} catch (IOException e) {
				}
			}
		}

		System.exit(exitCode);
	}

}