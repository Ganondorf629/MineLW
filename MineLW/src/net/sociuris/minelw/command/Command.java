package net.sociuris.minelw.command;

import java.util.HashMap;
import java.util.Map;

import net.sociuris.minelw.command.exception.CommandException;
import net.sociuris.minelw.server.MinecraftServer;

public abstract class Command {

	private final Map<CommandProperties, Object> commandPropertiesMap = new HashMap<CommandProperties, Object>();

	public Command() {
		setDefaultProperties();
	}

	/**
	 * Gets the command name
	 * 
	 * @return the command name
	 */
	public abstract String getName();

	/**
	 * Gets command aliases
	 * 
	 * @return the aliases
	 */
	public abstract String[] getAliases();

	/**
	 * Execute the command with given arguments
	 * 
	 * @param minecraftServer
	 *            the minecraft server
	 * @param commandSender
	 *            the command sender
	 * @param args
	 *            the arguments
	 * @throws CommandException
	 */
	public abstract void execute(MinecraftServer minecraftServer, CommandSender commandSender, String[] args)
			throws CommandException;

	/**
	 * Gets the availables arguments for the given raw command
	 * 
	 * @param minecraftServer
	 *            the minecraft server
	 * @param commandSender
	 *            the command sender
	 * @param args
	 *            the arguments
	 * @return the availables arguments
	 */
	public abstract String[] getTabCompletionOptions(MinecraftServer minecraftServer, CommandSender commandSender,
			String[] args);

	/**
	 * Sets the default properties of the command
	 */
	private void setDefaultProperties() {
		this.setProperties(CommandProperties.ARGS_LENGTH, 0);
		this.setProperties(CommandProperties.SECRET, false);
		this.setProperties(CommandProperties.SENDER_NEED_TO_BE_PLAYER, false);
	}

	/**
	 * Sets the property of the command
	 * 
	 * @param properties
	 * @param value
	 * @return the last property
	 */
	public Object setProperties(CommandProperties property, Object value) {
		if (property.getValueClass() == value.getClass()) {
			return commandPropertiesMap.put(property, value);
		} else {
			throw new IllegalArgumentException(
					"The property don't accept " + value.getClass().getSimpleName() + " type");
		}
	}

	/**
	 * Gets value of the property
	 * 
	 * @param properties
	 * @return the value
	 */
	public Object getProperties(CommandProperties properties) {
		return commandPropertiesMap.get(properties);
	}

}
