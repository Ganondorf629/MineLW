package net.sociuris.minelw.command;

public abstract class CommandUtils {

	private CommandUtils() {
	}

	public static String getCommandName(String rawCommand) {
		rawCommand = formatCommand(rawCommand);
		if (rawCommand.contains(" "))
			return rawCommand.split(" ", 1)[0];
		else
			return rawCommand;
	}

	public static String[] getCommandArguments(String rawCommand) {
		rawCommand = formatCommand(rawCommand);
		final int substringIndex = getCommandName(rawCommand).length() + 1;
		if (rawCommand.length() >= substringIndex)
			return rawCommand.substring(substringIndex).split(" ");
		else
			return new String[0];
	}
	
	/**
	 * Format the raw command<br>
	 * Trim the command name and if it start with a slash, remove it<br>
	 * 
	 * @param rawCommand
	 * @return the formatted raw command
	 */
	public static String formatCommand(String rawCommand) {
		rawCommand = rawCommand.trim();
		if (rawCommand.startsWith("/"))
			rawCommand = rawCommand.substring(1).trim();
		return rawCommand;
	}

}