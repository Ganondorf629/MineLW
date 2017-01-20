package net.sociuris.minelw.command.exception;

public class CommandNotFoundException extends CommandException {

	private static final long serialVersionUID = 1L;

	public CommandNotFoundException() {
		super("commands.generic.notFound");
	}

}