package net.sociuris.minelw.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import net.sociuris.minelw.command.exception.CommandException;
import net.sociuris.minelw.command.exception.SyntaxErrorException;
import net.sociuris.minelw.registry.RegistryNamespaced;
import net.sociuris.minelw.server.MinecraftServer;
import net.sociuris.minelw.text.TextComponentTranslation;
import net.sociuris.minelw.translation.DefaultTranslation;

public class CommandManager extends RegistryNamespaced<Command> {

	private final MinecraftServer minecraftServer;

	public CommandManager(MinecraftServer server) {
		logger.debug("Load command manager...");
		this.minecraftServer = server;
	}

	public void registerCommand(Command command) {
		String commandName = command.getName();
		super.register(commandName, command);
		for (String commandAlias : command.getAliases())
			super.register(commandAlias, command);
	}
	
	public Command getCommand(String commandName) {
		return super.getValue(commandName);
	}

	/**
	 * Execute une commande donnée
	 * 
	 * @param player
	 * @param rawCommand
	 * @return {@code true} si la commande existe, {@code false} dans le cas
	 *         contraire
	 * @throws IOException
	 */
	public boolean executeCommand(CommandSender commandSender, String rawCommand) {
		rawCommand = rawCommand.trim();
		if (rawCommand.startsWith("/"))
			rawCommand = rawCommand.substring(1);

		String commandName = CommandUtils.getCommandName(rawCommand);
		Command command = this.getCommand(commandName);
		if (command != null) {
			String[] args = CommandUtils.getCommandArguments(rawCommand);
			if (commandSender.hasPermission("command." + command.getName() + ".use")) {
				try {
					if (args.length >= (int) command.getProperties(CommandProperties.ARGS_LENGTH))
						command.execute(minecraftServer, commandSender, args);
					else
						throw new SyntaxErrorException();
				} catch (SyntaxErrorException syntaxErrorException) {
					commandSender.sendError(new TextComponentTranslation("commands.generic.usage").toArray());
				} catch (CommandException commandException) {
					String translationKey = (commandException.getTranslationKey() == null ? "commands.generic.exception"
							: commandException.getTranslationKey());
					commandSender.sendError(new TextComponentTranslation(translationKey).toArray());
				} catch (Throwable throwable) {
					commandSender.sendError(DefaultTranslation.COMMANDS_GENERIC_EXCEPTION.getTranslationComponent(args).toArray());
					throwable.initCause(new CommandException("Couldn't process command: " + rawCommand));
					logger.printStackTrace(throwable);
				}
			} else
				commandSender.sendError(new TextComponentTranslation((boolean) command.getProperties(CommandProperties.SECRET)
						? "commands.generic.notFound" : "commands.generic.permission").toArray());
		} else
			commandSender.sendError(new TextComponentTranslation("commands.generic.notFound").toArray());
		return false;
	}

	public List<String> getTabCompletion(CommandSender commandSender, String rawCommand) {
		rawCommand = rawCommand.toLowerCase();
		if (rawCommand.startsWith("/"))
			rawCommand = rawCommand.substring(1);

		String name = CommandUtils.getCommandName(rawCommand);
		String[] args = CommandUtils.getCommandArguments(rawCommand);

		List<String> commandList = new ArrayList<String>();
		if (args.length == 0) {
			for (Entry<String, Command> entry : super.entrySet()) {
				String commandName = entry.getValue().getName();
				if (entry.getKey().startsWith(rawCommand) && commandSender.hasPermission("command." + entry.getKey() + ".use")
						&& !commandList.contains("/" + commandName))
					commandList.add("/" + commandName);
			}
		} else if (args.length > 0) {
			Command command = getValue(name);
			if (command != null) {
				for (String commandName : command.getTabCompletionOptions(minecraftServer, commandSender, args))
					commandList.add(commandName);
			}
		}
		return commandList;
	}

	public MinecraftServer getMinecraftServer() {
		return minecraftServer;
	}

}