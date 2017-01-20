package net.sociuris.minelw.command;

import net.sociuris.minelw.command.exception.CommandNotFoundException;

public enum CommandProperties {

	/**
	 * Indicated the minimum number to process the command
	 */
	ARGS_LENGTH(Integer.class),

	/**
	 * Indicated if the command return an {@link CommandNotFoundException} when
	 * the {@link CommandSender} don't have the permission to execute it<br>
	 * Accepted values: {@code true} and {@code false}
	 */
	SECRET(Boolean.class),

	/**
	 * Indicated if the {@link CommandSender} must be a {@link Player}
	 */
	SENDER_NEED_TO_BE_PLAYER(Boolean.class);

	private final Class<?> valueClass;

	private CommandProperties(Class<?> valueClass) {
		this.valueClass = valueClass;
	}

	public Class<?> getValueClass() {
		return valueClass;
	}

}
