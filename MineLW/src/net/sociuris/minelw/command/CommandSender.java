package net.sociuris.minelw.command;

import net.sociuris.minelw.text.TextComponentArray;
import net.sociuris.minelw.translation.DefaultTranslation;

public interface CommandSender {

	public String getUsername();
	
	public boolean hasPermission(String permissionKey);

	public boolean sendMessage(TextComponentArray textComponentArray);

	public boolean sendMessage(String message);
	
	public boolean sendMinecraftMessage(DefaultTranslation minecraftLanguage, String... args);
	
	public boolean sendError(TextComponentArray error);

	public boolean sendSuccess(TextComponentArray success);
	
	public boolean sendWarning(TextComponentArray warning);
	
	public boolean sendInformation(TextComponentArray information);
	
}