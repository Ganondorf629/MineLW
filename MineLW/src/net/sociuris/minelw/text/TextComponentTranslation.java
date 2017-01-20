package net.sociuris.minelw.text;

import java.util.Properties;

import net.sociuris.json.JsonElement;

public class TextComponentTranslation extends TextComponent {

	private final String translationKey;
	private final String[] args;

	public TextComponentTranslation(String translationKey, String... args) {
		this.translationKey = translationKey;
		this.args = args;
	}

	public TextComponentTranslation(String translationKey, JsonElement[] args) {
		this.translationKey = translationKey;
		String[] argsArray = new String[args.length];
		for (int i = 0; i < args.length; i++)
			argsArray[i] = args[i].getAsString();
		this.args = argsArray;
	}

	public String getTranslationKey() {
		return translationKey;
	}

	public String[] getArguments() {
		return args;
	}

	public String translate(Properties properties) {
		return String.format(properties.getProperty(translationKey), (Object[]) args);
	}

	@Override
	public String toPrintableMessage() {
		return String.format(translationKey, (Object[]) args);
	}

}
