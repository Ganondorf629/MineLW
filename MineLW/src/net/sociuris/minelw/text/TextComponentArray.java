package net.sociuris.minelw.text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sociuris.util.StringUtils;

public class TextComponentArray implements Iterable<TextComponent> {

	private final List<TextComponent> textComponentList;

	public TextComponentArray() {
		this.textComponentList = new ArrayList<TextComponent>();
	}

	public TextComponentArray(List<TextComponent> textComponentList) {
		this.textComponentList = textComponentList;
	}

	public TextComponentArray(TextComponent... textComponents) {
		this();
		for (TextComponent textComponent : textComponents)
			this.textComponentList.add(textComponent);
	}

	public TextComponent getTextComponent(int index) {
		return textComponentList.get(index);
	}

	public TextComponent setTextComponent(int index, TextComponent textComponent) {
		return textComponentList.set(index, textComponent);
	}

	public void addAllTextComponent(TextComponent... textComponents) {
		for (TextComponent textComponent : textComponents)
			textComponentList.add(textComponent);
	}

	public boolean addTextComponent(TextComponent textComponent) {
		return textComponentList.add(textComponent);
	}

	public TextComponent[] toArray() {
		return textComponentList.toArray(new TextComponent[textComponentList.size()]);
	}

	@Override
	public Iterator<TextComponent> iterator() {
		return this.textComponentList.iterator();
	}

	@Override
	public String toString() {
		return StringUtils.toString(this);
	}

	public String toPrintableMessage() {
		StringBuilder builder = new StringBuilder();
		for (TextComponent textComponent : textComponentList)
			builder.append(textComponent.toPrintableMessage());
		return builder.toString();
	}

}