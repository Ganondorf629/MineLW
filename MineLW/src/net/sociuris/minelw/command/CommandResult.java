package net.sociuris.minelw.command;

public class CommandResult {

	private final Command command;

	protected CommandResult(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

}