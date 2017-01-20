package net.sociuris.minelw.command.exception;

public class SyntaxErrorException extends CommandException {

	private static final long serialVersionUID = 1L;

	public SyntaxErrorException() {
		super("commands.generic.syntax");
	}

}
