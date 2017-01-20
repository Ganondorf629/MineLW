package net.sociuris.minelw.command.exception;

public class CommandException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String translationKey;

	public CommandException(String translationKey) {
		this.translationKey = translationKey;
	}
	
	public String getTranslationKey() {
		return translationKey;
	}

}