package net.sociuris.minelw.command;

import net.sociuris.minelw.text.TextComponentArray;
import net.sociuris.minelw.translation.MinecraftTranslation;

public interface CommandSender {

	public String getUsername();

	public boolean hasPermission(String permissionKey);

	public void sendMessage(TextComponentArray textComponentArray);

	public void sendMessage(String message);

	public void translateAndSendMessage(String key, String... args);

	public void sendMinecraftMessage(MinecraftTranslation minecraftTranslation, String... args);

	public void sendError(TextComponentArray error);

	public void sendSuccess(TextComponentArray success);

	public void sendWarning(TextComponentArray warning);

}