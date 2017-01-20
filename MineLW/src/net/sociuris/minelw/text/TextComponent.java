package net.sociuris.minelw.text;

import java.util.ArrayList;
import java.util.List;

public abstract class TextComponent {

	private TextComponent parent = null;
	private TextColor color = TextColor.WHITE;
	private Boolean bold = false;
	private Boolean italic = false;
	private Boolean underlined = false;
	private Boolean strikethrough = false;
	private Boolean obfuscated = false;
	private List<TextComponent> extra = new ArrayList<TextComponent>();

	public TextColor getColor() {
		if (color != null)
			return color;
		else {
			if (parent != null)
				return parent.getColor();
			else
				return TextColor.WHITE;
		}
	}

	public TextComponent setColor(TextColor color) {
		this.color = color;
		return this;
	}

	public boolean isBold() {
		if (bold != null)
			return bold.booleanValue();
		else
			return (parent != null) && (parent.isBold());
	}

	public TextComponent setBold(Boolean bold) {
		this.bold = bold;
		return this;
	}

	public boolean isItalic() {
		if (italic != null)
			return italic.booleanValue();
		else
			return (parent != null) && (parent.isItalic());
	}

	public TextComponent setItalic(Boolean italic) {
		this.italic = italic;
		return this;
	}

	public boolean isUnderlined() {
		if (underlined != null)
			return underlined.booleanValue();
		else
			return (parent != null) && (parent.isUnderlined());
	}

	public TextComponent setUnderlined(Boolean underlined) {
		this.underlined = underlined;
		return this;
	}

	public boolean isStrikethrough() {
		if (strikethrough != null)
			return strikethrough.booleanValue();
		else
			return (parent != null) && (parent.isStrikethrough());
	}

	public TextComponent setStrikethrough(Boolean strikethrough) {
		this.strikethrough = strikethrough;
		return this;
	}

	public boolean isObfuscated() {
		if (obfuscated != null)
			return obfuscated.booleanValue();
		else
			return (parent != null) && (parent.isObfuscated());
	}

	public TextComponent setObfuscated(Boolean obfuscated) {
		this.obfuscated = obfuscated;
		return this;
	}

	public TextComponent addExtra(String text) {
		this.addExtra(new TextComponentString(text));
		return this;
	}

	public TextComponent addExtra(TextComponent component) {
		component.parent = this;
		this.extra.add(component);
		return this;
	}

	public TextComponent setExtra(List<TextComponent> components) {
		for (TextComponent component : components)
			component.parent = this;
		this.extra = components;
		return this;
	}

	public List<TextComponent> getExtra() {
		return extra;
	}

	public abstract String toPrintableMessage();

	public TextComponentArray toArray() {
		return new TextComponentArray(this);
	}

	@Override
	public String toString() {
		return "TextComponent[color=" + getColor().name() + ",bold=" + String.valueOf(isBold()) + ",italic="
				+ String.valueOf(isItalic()) + ",underlined=" + String.valueOf(isUnderlined()) + ",strikethrough="
				+ String.valueOf(isStrikethrough()) + ",obfuscated=" + String.valueOf(isObfuscated()) + "]";
	}

}