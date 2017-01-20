package net.sociuris.minelw.text;

public class TextComponentString extends TextComponent {

	public static final TextComponentString EMPTY = new TextComponentString("");
	
	private final String text;

	public TextComponentString(String text) {
		if(text != null)
			this.text = text;
		else
			throw new IllegalArgumentException("text is null");
	}

	public String getText() {
		return text;
	}

	@Override
	public String toPrintableMessage() {
		return text;
	}

}